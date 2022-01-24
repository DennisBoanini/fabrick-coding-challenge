package com.example.demo.service.impl;

import com.example.demo.client.BankAccountClient;
import com.example.demo.client.model.response.bank_transfer.BankTransferResponse;
import com.example.demo.dto.BalanceDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.bank_transfer.MoneyTransferDTO;
import com.example.demo.exception.ValidationException;
import com.example.demo.mapper.BankAccountMapper;
import com.example.demo.model.transaction.Transaction;
import com.example.demo.model.transaction.TransactionValue;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.model.transaction.TransactionHistory;
import com.example.demo.repository.AccountRepository;
import com.example.demo.service.AccountService;
import com.example.demo.validation.ValidationStatus;
import com.example.demo.validation.money_transfer.MoneyTransferValidationEngine;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

	private final BankAccountClient bankAccountClient;
	private final MoneyTransferValidationEngine moneyTransferValidationEngine;
	private final BankAccountMapper bankAccountMapper;
	private final AccountRepository accountRepository;

	public AccountServiceImpl(
			final BankAccountClient bankAccountClient,
			final MoneyTransferValidationEngine moneyTransferValidationEngine,
			final BankAccountMapper bankAccountMapper, final AccountRepository accountRepository) {
		this.bankAccountClient = bankAccountClient;
		this.moneyTransferValidationEngine = moneyTransferValidationEngine;
		this.bankAccountMapper = bankAccountMapper;
		this.accountRepository = accountRepository;
	}

	@Override
	public BalanceDTO getAccountBalance(final long accountId) {
		return this.bankAccountMapper.toDTO(this.bankAccountClient.getAccountBalance(accountId));
	}

	@Override
	@Transactional
	public TransactionDTO getAccountTransactions(final long accountId, final LocalDate from, final LocalDate to) {
		final Transaction accountTransactions = this.bankAccountClient.getAccountTransactions(accountId, from, to);

		final List<String> transactionIds = accountTransactions.transactions().stream().map(TransactionValue::getTransactionId).toList();
		final List<Long> savedHistoryIds = this.accountRepository.findAllByAccountIdAndTransactionIds(accountId, transactionIds);
		this.accountRepository.deleteAllById(savedHistoryIds);

		final List<TransactionHistory> entities = this.bankAccountMapper.toHistoryModels(accountTransactions.transactions());
		for (final TransactionHistory transactionHistory : entities) {
			transactionHistory.setAccountId(accountId);
		}
		this.accountRepository.saveAll(entities);

		return this.bankAccountMapper.toDTO(accountTransactions);
	}

	@Override
	public BankTransferResponse makeMoneyTransfer(final long accountId, final String timeZone, final MoneyTransferDTO moneyTransfer) {
		final MoneyTransfer transfer = this.bankAccountMapper.toModel(moneyTransfer);

		final var validationStatus = new ValidationStatus();
		this.moneyTransferValidationEngine.validate(transfer, validationStatus);
		if (validationStatus.hasErrors()) {
			throw new ValidationException(validationStatus);
		}

		return this.bankAccountClient.makeMoneyTransfer(accountId, timeZone, transfer);
	}
}

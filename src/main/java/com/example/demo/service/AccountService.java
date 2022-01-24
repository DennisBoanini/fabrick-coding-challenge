package com.example.demo.service;

import com.example.demo.client.model.response.bank_transfer.BankTransferResponse;
import com.example.demo.dto.BalanceDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.bank_transfer.MoneyTransferDTO;

import java.time.LocalDate;

public interface AccountService {

	BalanceDTO getAccountBalance(long accountId);

	TransactionDTO getAccountTransactions(long accountId, LocalDate from, LocalDate to);

	BankTransferResponse makeMoneyTransfer(long accountId, String timeZone, MoneyTransferDTO moneyTransfer);
}

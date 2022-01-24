package com.example.demo.mapper;


import com.example.demo.dto.BalanceDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.bank_transfer.MoneyTransferDTO;
import com.example.demo.model.Balance;
import com.example.demo.model.transaction.Transaction;
import com.example.demo.model.transaction.TransactionValue;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.model.transaction.TransactionHistory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface BankAccountMapper {

	BalanceDTO toDTO(Balance balance);

	TransactionDTO toDTO(Transaction transaction);

	List<TransactionHistory> toHistoryModels(List<TransactionValue> transactionValues);

	TransactionHistory toHistoryModel(TransactionValue transactionValue);

	MoneyTransfer toModel(MoneyTransferDTO moneyTransfer);
}

package com.example.demo.client;

import com.example.demo.client.model.response.bank_transfer.BankTransferResponse;
import com.example.demo.model.Balance;
import com.example.demo.model.transaction.Transaction;
import com.example.demo.model.bank_transfer.MoneyTransfer;

import java.time.LocalDate;

public interface BankAccountClient {

	Balance getAccountBalance(long accountId);

	Transaction getAccountTransactions(long accountId, LocalDate from, LocalDate to);

	BankTransferResponse makeMoneyTransfer(long accountId, String timeZone, MoneyTransfer transfer);
}

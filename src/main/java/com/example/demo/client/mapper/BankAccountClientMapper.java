package com.example.demo.client.mapper;

import com.example.demo.client.model.response.balance.BalanceResponse;
import com.example.demo.client.model.response.transaction.TransactionResponse;
import com.example.demo.client.model.response.transaction.TransactionValueResponse;
import com.example.demo.model.Balance;
import com.example.demo.model.transaction.Transaction;
import com.example.demo.model.transaction.TransactionValue;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankAccountClientMapper {

	Balance toModel(BalanceResponse balanceResponse);

	@Mapping(target = "transactions", source = "list")
	Transaction toModel(TransactionResponse transactionResponse);

	@Mapping(target = "type", source = "type.value")
	TransactionValue toModel(TransactionValueResponse transactionValueResponse);
}

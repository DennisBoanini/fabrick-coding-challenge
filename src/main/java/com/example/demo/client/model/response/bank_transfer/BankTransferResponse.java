package com.example.demo.client.model.response.bank_transfer;

import com.example.demo.client.model.response.FeeResponse;
import com.example.demo.client.model.response.PersonCreditorResponse;
import com.example.demo.client.model.response.PersonDebtorResponse;
import com.example.demo.client.model.response.enumeration.ETransferDirection;
import com.example.demo.client.model.response.enumeration.ETransferStatus;
import com.example.demo.enumeration.EFeeType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record BankTransferResponse(
		String moneyTransferId,
		ETransferStatus status,
		ETransferDirection direction,
		PersonCreditorResponse creditor,
		PersonDebtorResponse debtor,
		String cro,
		String uri,
		String trn,
		String description,
		LocalDateTime createdDatetime,
		LocalDateTime accountedDatetime,
		LocalDate debtorValueDate,
		LocalDate creditorValueDate,
		TransferAmountResponse amount,
		boolean isUrgent,
		boolean isInstant,
		EFeeType feeType,
		String feeAccountId,
		List<FeeResponse> fees,
		boolean hasTaxRelief
) {
}

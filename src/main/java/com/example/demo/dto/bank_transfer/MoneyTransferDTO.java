package com.example.demo.dto.bank_transfer;

import com.example.demo.enumeration.EFeeType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MoneyTransferDTO(
		CreditorDTO creditor,
		LocalDate executionDate,
		String uri,
		String description,
		BigDecimal amount,
		String currency,
		boolean isUrgent,
		boolean isInstant,
		EFeeType feeType,
		String feeAccountId,
		TaxReliefDTO taxRelief
) {
}

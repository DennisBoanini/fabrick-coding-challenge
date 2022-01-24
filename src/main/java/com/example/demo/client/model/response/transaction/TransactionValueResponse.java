package com.example.demo.client.model.response.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionValueResponse(
		String transactionId,
		String operationId,
		LocalDate accountingDate,
		LocalDate valueDate,
		TransactionTypeResponse type,
		BigDecimal amount,
		String currency,
		String description
) {
}

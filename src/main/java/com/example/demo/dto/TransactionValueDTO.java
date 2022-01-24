package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionValueDTO(
		String transactionId,
		String operationId,
		LocalDate accountingDate,
		LocalDate valueDate,
		String type,
		BigDecimal amount,
		String currency,
		String description
) { }

package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BalanceDTO(
		LocalDate date,
		BigDecimal balance,
		BigDecimal availableBalance,
		String currency
) { }

package com.example.demo.client.model.response.bank_transfer;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransferAmountResponse(
		BigDecimal debtorAmount,
		String debtorCurrency,
		BigDecimal creditorAmount,
		String creditorCurrency,
		LocalDate creditorCurrencyDate,
		int exchangeRate
) {
}

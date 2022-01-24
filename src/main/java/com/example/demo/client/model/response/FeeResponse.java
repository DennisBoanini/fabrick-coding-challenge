package com.example.demo.client.model.response;

import java.math.BigDecimal;

public record FeeResponse(
		String feeCode,
		String description,
		BigDecimal amount,
		String currency
) { }

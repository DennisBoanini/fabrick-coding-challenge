package com.example.demo.client.model.response.balance;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BalanceResponse(LocalDate date, BigDecimal balance, BigDecimal availableBalance, String currency) { }

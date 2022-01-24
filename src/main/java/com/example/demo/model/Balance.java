package com.example.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Balance(LocalDate date, BigDecimal balance, BigDecimal availableBalance, String currency) { }

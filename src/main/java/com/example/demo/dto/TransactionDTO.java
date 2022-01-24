package com.example.demo.dto;

import java.util.List;

public record TransactionDTO(
		List<TransactionValueDTO> transactions
) { }

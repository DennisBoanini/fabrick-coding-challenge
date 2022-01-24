package com.example.demo.dto.bank_transfer;

public record CreditorDTO(
		String name,
		AccountDTO account,
		AddressDTO address
) {
}

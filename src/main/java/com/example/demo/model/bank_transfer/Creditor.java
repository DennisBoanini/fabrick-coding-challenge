package com.example.demo.model.bank_transfer;

public class Creditor {

	private String name;
	private Account account;
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(final Account account) {
		this.account = account;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(final Address address) {
		this.address = address;
	}
}

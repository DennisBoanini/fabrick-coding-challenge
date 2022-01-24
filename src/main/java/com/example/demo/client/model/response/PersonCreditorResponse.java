package com.example.demo.client.model.response;

public class PersonCreditorResponse extends PersonResponse {

	public AccountResponse account;
	public AddressResponse address;

	public AccountResponse getAccount() {
		return account;
	}

	public void setAccount(final AccountResponse account) {
		this.account = account;
	}

	public AddressResponse getAddress() {
		return address;
	}

	public void setAddress(final AddressResponse address) {
		this.address = address;
	}
}

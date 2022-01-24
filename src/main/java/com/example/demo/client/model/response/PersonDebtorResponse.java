package com.example.demo.client.model.response;

public class PersonDebtorResponse extends PersonResponse {

	public AccountResponse account;

	public AccountResponse getAccount() {
		return account;
	}

	public void setAccount(final AccountResponse account) {
		this.account = account;
	}
}

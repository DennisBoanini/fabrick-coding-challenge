package com.example.demo.model.transaction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "USER_TRANSACTION")
public class TransactionHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	private long accountId;
	private String transactionId;
	private LocalDate valueDate;
	private BigDecimal amount;
	private String currency;
	private String description;

	public long getId() {
		return id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(final long accountId) {
		this.accountId = accountId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(final String transactionId) {
		this.transactionId = transactionId;
	}

	public LocalDate getValueDate() {
		return valueDate;
	}

	public void setValueDate(final LocalDate valueDate) {
		this.valueDate = valueDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(final BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(final String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
}

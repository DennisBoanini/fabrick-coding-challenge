package com.example.demo.model.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionValue {

	private String transactionId;
	private String operationId;
	private LocalDate accountingDate;
	private LocalDate valueDate;
	private String type;
	private BigDecimal amount;
	private String currency;
	private String description;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(final String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(final String operationId) {
		this.operationId = operationId;
	}

	public LocalDate getAccountingDate() {
		return accountingDate;
	}

	public void setAccountingDate(final LocalDate accountingDate) {
		this.accountingDate = accountingDate;
	}

	public LocalDate getValueDate() {
		return valueDate;
	}

	public void setValueDate(final LocalDate valueDate) {
		this.valueDate = valueDate;
	}

	public String getType() {
		return type;
	}

	public void setType(final String type) {
		this.type = type;
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

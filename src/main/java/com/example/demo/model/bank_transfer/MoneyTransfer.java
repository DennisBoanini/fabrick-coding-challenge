package com.example.demo.model.bank_transfer;

import com.example.demo.enumeration.EFeeType;

import java.math.BigDecimal;

public class MoneyTransfer {

	private Creditor creditor;
	private String executionDate;
	private String uri;
	private String description;
	private BigDecimal amount;
	private String currency;
	private boolean isUrgent;
	private boolean isInstant;
	private EFeeType feeType;
	private String feeAccountId;
	private TaxRelief taxRelief;

	public Creditor getCreditor() {
		return creditor;
	}

	public void setCreditor(final Creditor creditor) {
		this.creditor = creditor;
	}

	public String getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(final String executionDate) {
		this.executionDate = executionDate;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(final String uri) {
		this.uri = uri;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
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

	public boolean isUrgent() {
		return isUrgent;
	}

	public void setUrgent(final boolean urgent) {
		isUrgent = urgent;
	}

	public boolean isInstant() {
		return isInstant;
	}

	public void setInstant(final boolean instant) {
		isInstant = instant;
	}

	public EFeeType getFeeType() {
		return feeType;
	}

	public void setFeeType(final EFeeType feeType) {
		this.feeType = feeType;
	}

	public String getFeeAccountId() {
		return feeAccountId;
	}

	public void setFeeAccountId(final String feeAccountId) {
		this.feeAccountId = feeAccountId;
	}

	public TaxRelief getTaxRelief() {
		return taxRelief;
	}

	public void setTaxRelief(final TaxRelief taxRelief) {
		this.taxRelief = taxRelief;
	}
}

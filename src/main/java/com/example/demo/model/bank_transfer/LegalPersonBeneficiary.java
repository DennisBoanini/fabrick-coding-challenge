package com.example.demo.model.bank_transfer;

public class LegalPersonBeneficiary {

	private String fiscalCode;
	private String legalRepresentativeFiscalCode;

	public String getFiscalCode() {
		return fiscalCode;
	}

	public void setFiscalCode(final String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public String getLegalRepresentativeFiscalCode() {
		return legalRepresentativeFiscalCode;
	}

	public void setLegalRepresentativeFiscalCode(final String legalRepresentativeFiscalCode) {
		this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
	}
}

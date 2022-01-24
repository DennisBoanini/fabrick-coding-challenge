package com.example.demo.model.bank_transfer;

import com.example.demo.enumeration.EBeneficiaryType;

public class TaxRelief {

	private String taxReliefId;
	private boolean isCondoUpgrade;
	private String creditorFiscalCode;
	private EBeneficiaryType beneficiaryType;
	private NaturalPersonBeneficiary naturalPersonBeneficiary;
	private LegalPersonBeneficiary legalPersonBeneficiary;

	public String getTaxReliefId() {
		return taxReliefId;
	}

	public void setTaxReliefId(final String taxReliefId) {
		this.taxReliefId = taxReliefId;
	}

	public boolean isCondoUpgrade() {
		return isCondoUpgrade;
	}

	public void setCondoUpgrade(final boolean condoUpgrade) {
		isCondoUpgrade = condoUpgrade;
	}

	public String getCreditorFiscalCode() {
		return creditorFiscalCode;
	}

	public void setCreditorFiscalCode(final String creditorFiscalCode) {
		this.creditorFiscalCode = creditorFiscalCode;
	}

	public EBeneficiaryType getBeneficiaryType() {
		return beneficiaryType;
	}

	public void setBeneficiaryType(final EBeneficiaryType beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}

	public NaturalPersonBeneficiary getNaturalPersonBeneficiary() {
		return naturalPersonBeneficiary;
	}

	public void setNaturalPersonBeneficiary(final NaturalPersonBeneficiary naturalPersonBeneficiary) {
		this.naturalPersonBeneficiary = naturalPersonBeneficiary;
	}

	public LegalPersonBeneficiary getLegalPersonBeneficiary() {
		return legalPersonBeneficiary;
	}

	public void setLegalPersonBeneficiary(final LegalPersonBeneficiary legalPersonBeneficiary) {
		this.legalPersonBeneficiary = legalPersonBeneficiary;
	}
}

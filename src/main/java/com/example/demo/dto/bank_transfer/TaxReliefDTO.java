package com.example.demo.dto.bank_transfer;

import com.example.demo.enumeration.EBeneficiaryType;

public record TaxReliefDTO(
		String taxReliefId,
		boolean isCondoUpgrade,
		String creditorFiscalCode,
		EBeneficiaryType beneficiaryType,
		NaturalPersonBeneficiaryDTO naturalPersonBeneficiary,
		LegalPersonBeneficiaryDTO legalPersonBeneficiary
) {
}

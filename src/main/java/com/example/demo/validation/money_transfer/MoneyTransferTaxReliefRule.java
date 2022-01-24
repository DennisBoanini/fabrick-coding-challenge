package com.example.demo.validation.money_transfer;

import com.example.demo.enumeration.EBeneficiaryType;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationRule;
import com.example.demo.validation.ValidationStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MoneyTransferTaxReliefRule implements ValidationRule<MoneyTransfer> {

	@Override
	public void validate(final MoneyTransfer transfer, final ValidationStatus validationStatus) {
		if (Objects.isNull(transfer.getTaxRelief())) {
			return;
		}

		if (StringUtils.isBlank(transfer.getTaxRelief().getCreditorFiscalCode())) {
			validationStatus.addError("Creditor fiscal code must be present");
		}

		if (Objects.isNull(transfer.getTaxRelief().getBeneficiaryType())) {
			validationStatus.addError("Beneficiary type must be present");
			return;
		}

		final var isNaturalPerson = EBeneficiaryType.NATURAL_PERSON.equals(transfer.getTaxRelief().getBeneficiaryType());
		if (isNaturalPerson && Objects.nonNull(transfer.getTaxRelief().getNaturalPersonBeneficiary())) {
			if (StringUtils.isBlank(transfer.getTaxRelief().getNaturalPersonBeneficiary().getFiscalCode1())) {
				validationStatus.addError("Fiscal code for natural person beneficiary must be present");
			}
		}

		if (!isNaturalPerson && Objects.nonNull(transfer.getTaxRelief().getLegalPersonBeneficiary())) {
			if (StringUtils.isBlank(transfer.getTaxRelief().getLegalPersonBeneficiary().getFiscalCode())) {
				validationStatus.addError("Fiscal code for legal person beneficiary must be present");
			}
		}
	}
}

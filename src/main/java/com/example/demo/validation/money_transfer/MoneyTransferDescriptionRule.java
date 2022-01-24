package com.example.demo.validation.money_transfer;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationRule;
import com.example.demo.validation.ValidationStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MoneyTransferDescriptionRule implements ValidationRule<MoneyTransfer> {

	public static final int DESCRIPTION_MAX_LENGTH = 140;

	@Override
	public void validate(final MoneyTransfer transfer, final ValidationStatus validationStatus) {
		if (StringUtils.isBlank(transfer.getDescription())) {
			validationStatus.addError("The description must be present");
			return;
		}

		if (transfer.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
			validationStatus.addError("Description length must less or equal to " + DESCRIPTION_MAX_LENGTH + " character");
		}
	}
}

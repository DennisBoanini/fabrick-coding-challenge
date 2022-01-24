package com.example.demo.validation.money_transfer;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationRule;
import com.example.demo.validation.ValidationStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MoneyTransferExecutionDateRule implements ValidationRule<MoneyTransfer> {

	@Override
	public void validate(final MoneyTransfer transfer, final ValidationStatus validationStatus) {
		if (transfer.isInstant() && StringUtils.isBlank(transfer.getExecutionDate())) {
			validationStatus.addError("The execution date is required when operation is instant");
		}
	}
}

package com.example.demo.validation.money_transfer;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationRule;
import com.example.demo.validation.ValidationStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class MoneyTransferCurrencyRule implements ValidationRule<MoneyTransfer> {

	@Override
	public void validate(final MoneyTransfer transfer, final ValidationStatus validationStatus) {
		if (StringUtils.isBlank(transfer.getCurrency())) {
			validationStatus.addError("Currency must be present");
		}
	}
}

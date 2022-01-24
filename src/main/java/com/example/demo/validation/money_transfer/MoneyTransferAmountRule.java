package com.example.demo.validation.money_transfer;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationRule;
import com.example.demo.validation.ValidationStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MoneyTransferAmountRule implements ValidationRule<MoneyTransfer> {

	@Override
	public void validate(final MoneyTransfer transfer, final ValidationStatus validationStatus) {
		if (Objects.isNull(transfer.getAmount())) {
			validationStatus.addError("The amount is required for make the operation");
		}
	}
}

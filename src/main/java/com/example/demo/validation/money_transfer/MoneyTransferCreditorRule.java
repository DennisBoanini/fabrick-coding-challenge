package com.example.demo.validation.money_transfer;

import com.example.demo.model.bank_transfer.Account;
import com.example.demo.model.bank_transfer.Creditor;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationRule;
import com.example.demo.validation.ValidationStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

@Component
public class MoneyTransferCreditorRule implements ValidationRule<MoneyTransfer> {

	public static final Pattern COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Z]{2}$");
	public static final int CREDITOR_NAME_MAX_LENGTH = 70;

	@Override
	public void validate(final MoneyTransfer transfer, final ValidationStatus validationStatus) {
		if (Objects.isNull(transfer.getCreditor())) {
			validationStatus.addError("Creditor field should not be null");
		} else {
			this.validateCreditor(transfer.getCreditor(), validationStatus);
		}
	}

	private void validateCreditor(final Creditor creditor, final ValidationStatus validationStatus) {
		if (StringUtils.isBlank(creditor.getName())) {
			validationStatus.addError("Creditor name must be not null or empty");
		} else {
			if (creditor.getName().length() > CREDITOR_NAME_MAX_LENGTH) {
				validationStatus.addError("Creditor name length must be less or equal to " + CREDITOR_NAME_MAX_LENGTH + " character");
			}
		}

		if (Objects.isNull(creditor.getAccount())) {
			validationStatus.addError("The account object is required");
		} else {
			this.validateCreditorAccount(creditor.getAccount(), validationStatus);
		}

		if (Objects.nonNull(creditor.getAddress()) && StringUtils.isNotBlank(creditor.getAddress().getCountryCode()) && !COUNTRY_CODE_PATTERN.matcher(creditor.getAddress().getCountryCode()).matches()) {
			validationStatus.addError("Country code is not valid");
		}
	}

	private void validateCreditorAccount(final Account account, final ValidationStatus validationStatus) {
		if (StringUtils.isBlank(account.getAccountCode())) {
			validationStatus.addError("The account code must be present");
		}
	}
}

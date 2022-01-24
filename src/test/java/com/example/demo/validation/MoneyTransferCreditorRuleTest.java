package com.example.demo.validation;

import com.example.demo.model.bank_transfer.Account;
import com.example.demo.model.bank_transfer.Creditor;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.money_transfer.MoneyTransferCreditorRule;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.demo.validation.money_transfer.MoneyTransferCreditorRule.CREDITOR_NAME_MAX_LENGTH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;


@ExtendWith(SpringExtension.class)
class MoneyTransferCreditorRuleTest {

	@InjectMocks
	private MoneyTransferCreditorRule rule;

	private ValidationStatus validationStatus;
	private MoneyTransfer transfer;

	@BeforeEach
	void setup() {
		this.validationStatus = new ValidationStatus();
		this.transfer = new MoneyTransfer();
	}

	@Test
	void Should_AddCreditorFieldError_When_CreditorObjectIsNull() {
		this.transfer.setCreditor(null);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Creditor field should not be null"));
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddCreditorNameError_When_CreditorNameIsNullOrEmptyOrOnlySpaces(String creditorName) {
		final Creditor mockCreditor = new Creditor();
		mockCreditor.setName(creditorName);

		final Account mockAccount = new Account();
		mockAccount.setAccountCode("QWERTY");
		mockAccount.setBicCode("QWERTY");

		mockCreditor.setAccount(mockAccount);
		this.transfer.setCreditor(mockCreditor);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Creditor name must be not null or empty"));
	}

	@Test
	void Should_AddCreditorNameError_When_CreditorNameLengthIsGreaterThan70() {
		final Creditor mockCreditor = new Creditor();
		mockCreditor.setName(RandomStringUtils.random(CREDITOR_NAME_MAX_LENGTH + 1));

		final Account mockAccount = new Account();
		mockAccount.setAccountCode("QWERTY");
		mockAccount.setBicCode("QWERTY");

		mockCreditor.setAccount(mockAccount);
		this.transfer.setCreditor(mockCreditor);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Creditor name length must be less or equal to " + CREDITOR_NAME_MAX_LENGTH + " character"));
	}

	@Test
	void Should_AddCreditorAccountError_When_CreditorAccountIsNull() {
		final Creditor mockCreditor = new Creditor();
		mockCreditor.setName(RandomStringUtils.random(CREDITOR_NAME_MAX_LENGTH));

		mockCreditor.setAccount(null);
		this.transfer.setCreditor(mockCreditor);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("The account object is required"));
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddCreditorAccountCodeError_When_CreditorAccountCodeIsNullOrEmptyOrOnlySpaces(String accountCode) {
		final Creditor mockCreditor = new Creditor();
		mockCreditor.setName(RandomStringUtils.random(CREDITOR_NAME_MAX_LENGTH));

		final Account mockAccount = new Account();
		mockAccount.setAccountCode(accountCode);
		mockAccount.setBicCode("QWERTY");

		mockCreditor.setAccount(mockAccount);
		this.transfer.setCreditor(mockCreditor);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("The account code must be present"));
	}

	private static String[] nullEmptyBlankSource() {
		return new String[]{null, "", " "};
	}
}

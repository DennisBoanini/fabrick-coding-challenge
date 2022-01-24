package com.example.demo.validation;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.money_transfer.MoneyTransferDescriptionRule;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.demo.validation.money_transfer.MoneyTransferDescriptionRule.DESCRIPTION_MAX_LENGTH;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
public class MoneyTransferDescriptionRuleTest {

	@InjectMocks
	private MoneyTransferDescriptionRule rule;

	private ValidationStatus validationStatus;
	private MoneyTransfer mockTransfer;

	@BeforeEach
	void setup() {
		this.validationStatus = new ValidationStatus();
		this.mockTransfer = new MoneyTransfer();
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddDescriptionFieldError_When_DescriptionIsNullOrEmptyOrOnlySpaces(String description) {
		this.mockTransfer.setDescription(description);

		this.rule.validate(this.mockTransfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("The description must be present"));
	}

	@Test
	void Should_AddDescriptionFieldError_When_DescriptionLengthIsGreaterThan140() {
		this.mockTransfer.setDescription(RandomStringUtils.random(DESCRIPTION_MAX_LENGTH + 1));

		this.rule.validate(this.mockTransfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Description length must less or equal to " + DESCRIPTION_MAX_LENGTH + " character"));
	}

	private static String[] nullEmptyBlankSource() {
		return new String[]{null, "", " "};
	}
}

package com.example.demo.validation;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.money_transfer.MoneyTransferCurrencyRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
public class MoneyTransferCurrencyRuleTest {

	@InjectMocks
	private MoneyTransferCurrencyRule rule;

	private ValidationStatus validationStatus;
	private MoneyTransfer transfer;

	@BeforeEach
	void setup() {
		this.validationStatus = new ValidationStatus();
		this.transfer = new MoneyTransfer();
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddCurrencyFieldError_When_CurrencyIsNullOrEmptyOrOnlySpaces(String currency) {
		this.transfer.setCurrency(currency);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Currency must be present"));
	}

	private static String[] nullEmptyBlankSource() {
		return new String[]{null, "", " "};
	}
}

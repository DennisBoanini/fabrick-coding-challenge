package com.example.demo.validation;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.money_transfer.MoneyTransferExecutionDateRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
public class MoneyTransferExecutionDateRuleTest {

	@InjectMocks
	private MoneyTransferExecutionDateRule rule;

	private ValidationStatus validationStatus;
	private MoneyTransfer mockTransfer;

	@BeforeEach
	void setup() {
		this.validationStatus = new ValidationStatus();
		this.mockTransfer = new MoneyTransfer();
	}

	@Test
	void Should_AddExecutionDateFieldError_When_MoneyTransferIsInstantAndExecutionDateIsNull() {
		this.mockTransfer.setInstant(true);
		this.mockTransfer.setExecutionDate(null);

		this.rule.validate(this.mockTransfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("The execution date is required when operation is instant"));
	}
}

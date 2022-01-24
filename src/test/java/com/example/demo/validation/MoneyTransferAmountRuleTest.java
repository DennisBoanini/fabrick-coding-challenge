package com.example.demo.validation;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.money_transfer.MoneyTransferAmountRule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
class MoneyTransferAmountRuleTest {

	@InjectMocks
	private MoneyTransferAmountRule rule;

	private ValidationStatus validationStatus;
	private MoneyTransfer transfer;

	@BeforeEach
	void setup() {
		this.validationStatus = new ValidationStatus();
		this.transfer = new MoneyTransfer();
	}

	@Test
	void Should_AddAmountFieldError_When_AmountIsNull() {
		this.transfer.setAmount(null);

		this.rule.validate(transfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("The amount is required for make the operation"));
	}
}

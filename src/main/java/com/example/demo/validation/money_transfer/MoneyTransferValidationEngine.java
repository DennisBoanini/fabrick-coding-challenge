package com.example.demo.validation.money_transfer;

import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.validation.ValidationEngine;
import com.example.demo.validation.ValidationRule;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MoneyTransferValidationEngine extends ValidationEngine<MoneyTransfer> {

	private final MoneyTransferCreditorRule creditorRule;
	private final MoneyTransferExecutionDateRule transferExecutionDateRule;
	private final MoneyTransferDescriptionRule descriptionRule;
	private final MoneyTransferAmountRule amountRule;
	private final MoneyTransferCurrencyRule currencyRule;
	private final MoneyTransferTaxReliefRule taxReliefRule;

	public MoneyTransferValidationEngine(
			final MoneyTransferCreditorRule creditorRule,
			final MoneyTransferExecutionDateRule transferExecutionDateRule,
			final MoneyTransferDescriptionRule descriptionRule,
			final MoneyTransferAmountRule amountRule,
			final MoneyTransferCurrencyRule currencyRule,
			final MoneyTransferTaxReliefRule taxReliefRule) {
		this.creditorRule = creditorRule;
		this.transferExecutionDateRule = transferExecutionDateRule;
		this.descriptionRule = descriptionRule;
		this.amountRule = amountRule;
		this.currencyRule = currencyRule;
		this.taxReliefRule = taxReliefRule;
	}

	@Override
	protected List<ValidationRule<MoneyTransfer>> getRules() {
		return List.of(
				this.creditorRule,
				this.transferExecutionDateRule,
				this.descriptionRule,
				this.amountRule,
				this.currencyRule,
				this.taxReliefRule
		);
	}
}

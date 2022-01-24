package com.example.demo.validation;

import com.example.demo.enumeration.EBeneficiaryType;
import com.example.demo.model.bank_transfer.LegalPersonBeneficiary;
import com.example.demo.model.bank_transfer.MoneyTransfer;
import com.example.demo.model.bank_transfer.NaturalPersonBeneficiary;
import com.example.demo.model.bank_transfer.TaxRelief;
import com.example.demo.validation.money_transfer.MoneyTransferTaxReliefRule;
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
public class MoneyTransferTaxReliefRuleTest {

	@InjectMocks
	private MoneyTransferTaxReliefRule rule;

	private ValidationStatus validationStatus;
	private MoneyTransfer mockTransfer;

	@BeforeEach
	void setup() {
		this.validationStatus = new ValidationStatus();
		this.mockTransfer = new MoneyTransfer();
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddTaxReliefCreditorFiscalCodeFieldError_When_TaxReliefCreditorFiscalCodeIsNull(String creditorFiscalCode) {
		final TaxRelief mockTaxRelief = new TaxRelief();
		mockTaxRelief.setCreditorFiscalCode(creditorFiscalCode);
		mockTaxRelief.setBeneficiaryType(EBeneficiaryType.NATURAL_PERSON);

		this.mockTransfer.setTaxRelief(mockTaxRelief);

		this.rule.validate(this.mockTransfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Creditor fiscal code must be present"));
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddNaturalBeneficiaryFiscalCodeFieldError_When_NaturalBeneficiaryFiscalCodeIsNull(String naturalBeneficiaryFiscalCode) {
		final TaxRelief mockTaxRelief = new TaxRelief();
		mockTaxRelief.setCreditorFiscalCode("creditorFiscalCode");
		mockTaxRelief.setBeneficiaryType(EBeneficiaryType.NATURAL_PERSON);

		final NaturalPersonBeneficiary mockNaturalPersonBeneficiary = new NaturalPersonBeneficiary();
		mockNaturalPersonBeneficiary.setFiscalCode1(naturalBeneficiaryFiscalCode);
		mockTaxRelief.setNaturalPersonBeneficiary(mockNaturalPersonBeneficiary);

		this.mockTransfer.setTaxRelief(mockTaxRelief);

		this.rule.validate(this.mockTransfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Fiscal code for natural person beneficiary must be present"));
	}

	@ParameterizedTest
	@MethodSource("nullEmptyBlankSource")
	void Should_AddLegalBeneficiaryFiscalCodeFieldError_When_LegalBeneficiaryFiscalCodeIsNull(String legalBeneficiaryFiscalCode) {
		final TaxRelief mockTaxRelief = new TaxRelief();
		mockTaxRelief.setCreditorFiscalCode("creditorFiscalCode");
		mockTaxRelief.setBeneficiaryType(EBeneficiaryType.LEGAL_PERSON);

		final LegalPersonBeneficiary mockLegalPersonBeneficiary = new LegalPersonBeneficiary();
		mockLegalPersonBeneficiary.setFiscalCode(legalBeneficiaryFiscalCode);
		mockTaxRelief.setLegalPersonBeneficiary(mockLegalPersonBeneficiary);

		this.mockTransfer.setTaxRelief(mockTaxRelief);

		this.rule.validate(this.mockTransfer, this.validationStatus);
		assertThat(this.validationStatus.hasErrors(), is(true));
		assertThat(this.validationStatus.getErrors(), hasSize(1));

		final String error = this.validationStatus.getErrors().get(0);
		assertThat(error, is("Fiscal code for legal person beneficiary must be present"));
	}

	private static String[] nullEmptyBlankSource() {
		return new String[]{null, "", " "};
	}
}

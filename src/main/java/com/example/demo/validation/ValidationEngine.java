package com.example.demo.validation;

import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class ValidationEngine<T> {

	protected abstract List<ValidationRule<T>> getRules();

	public void validate(T object, ValidationStatus validationStatus) {
		List<ValidationRule<T>> rules = getRules();
		if (CollectionUtils.isEmpty(rules)) {
			return;
		}

		for (ValidationRule<T> rule : rules) {
			rule.validate(object, validationStatus);
		}
	}
}

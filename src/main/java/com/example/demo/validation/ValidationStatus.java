package com.example.demo.validation;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ValidationStatus {

	private List<String> errors;

	public ValidationStatus() {
		this.errors = new ArrayList<>();
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(final List<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		this.errors.add(error);
	}

	public boolean hasErrors() {
		return !CollectionUtils.isEmpty(this.errors);
	}
}

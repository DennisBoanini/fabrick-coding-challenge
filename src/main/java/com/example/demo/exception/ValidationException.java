package com.example.demo.exception;

import com.example.demo.validation.ValidationStatus;

public class ValidationException extends RuntimeException {

	private final ValidationStatus validationStatus;

	public ValidationException(ValidationStatus validationStatus) {
		super();
		this.validationStatus = validationStatus;
	}

	public ValidationStatus getValidationStatus() {
		return validationStatus;
	}
}

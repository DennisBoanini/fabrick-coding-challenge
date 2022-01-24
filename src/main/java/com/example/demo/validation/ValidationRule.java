package com.example.demo.validation;

public interface ValidationRule<T> {

	void validate(T object, ValidationStatus validationStatus);
}

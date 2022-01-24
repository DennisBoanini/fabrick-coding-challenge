package com.example.demo.exception;

import com.example.demo.client.exception.ServerException;
import com.example.demo.client.model.response.server.ServerErrorResponse;
import com.example.demo.exception.ValidationException;
import com.example.demo.validation.ValidationStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<List<ServerErrorResponse>> handleError(ServerException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getErrors());
	}

	@ExceptionHandler
	public ResponseEntity<ValidationStatus> handleError(ValidationException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getValidationStatus());
	}
}

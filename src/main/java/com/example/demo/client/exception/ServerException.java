package com.example.demo.client.exception;

import com.example.demo.client.model.response.server.ServerErrorResponse;

import java.util.List;

public class ServerException extends RuntimeException {

	private final List<ServerErrorResponse> errors;

	public ServerException(List<ServerErrorResponse> errors) {
		super();
		this.errors = errors;
	}

	public List<ServerErrorResponse> getErrors() {
		return errors;
	}
}

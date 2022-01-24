package com.example.demo.client.model.response.server;

import com.example.demo.client.model.response.enumeration.EServerResponseStatus;

import java.util.List;

public class ServerResponse<T> {

	private EServerResponseStatus status;
	private List<ServerErrorResponse> errors;
	private T payload;

	public EServerResponseStatus getStatus() {
		return status;
	}

	public void setStatus(final EServerResponseStatus status) {
		this.status = status;
	}

	public List<ServerErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(final List<ServerErrorResponse> errors) {
		this.errors = errors;
	}

	public T getPayload() {
		return payload;
	}

	public void setPayload(final T payload) {
		this.payload = payload;
	}
}

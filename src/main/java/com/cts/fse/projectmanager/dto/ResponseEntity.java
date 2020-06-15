package com.cts.fse.projectmanager.dto;

public class ResponseEntity<T> {

	private T response;
	private String responseCode;

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

}

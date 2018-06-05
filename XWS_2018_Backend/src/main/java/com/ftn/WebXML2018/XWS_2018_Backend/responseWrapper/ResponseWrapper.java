package com.ftn.WebXML2018.XWS_2018_Backend.responseWrapper;

import org.springframework.http.HttpStatus;

public class ResponseWrapper<T> {
	
	private T responseBody;
	private String message;
	private boolean success = true;
	
	public ResponseWrapper() {}
	
	public ResponseWrapper(T responseBody, String message, boolean success) {
		this.responseBody = responseBody;
		this.message = message;
		this.success = success;
	}
	
	public ResponseWrapper(T responseBody, boolean success) {
		this.responseBody = responseBody;
		this.success = success;
	}
	
	public T getResponseBody() {
		return responseBody;
	}
	
	public void setResponseBody(T responseBody) {
		this.responseBody = responseBody;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}

package com.metro.Metro.exception;

import org.springframework.http.HttpStatusCode;

public class UserException extends RuntimeException {

	public String errorMessage;
	public static final HttpStatusCode errorResponseCode = HttpStatusCode.valueOf(400);

	public UserException(String message) {
		super();
		this.errorMessage = message;
	}

}

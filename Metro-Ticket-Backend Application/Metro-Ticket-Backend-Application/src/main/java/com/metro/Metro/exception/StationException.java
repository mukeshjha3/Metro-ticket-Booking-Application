package com.metro.Metro.exception;

import org.springframework.http.HttpStatus;

public class StationException extends RuntimeException {

	public String errorMessage;
	public static final HttpStatus errorResponseCode=HttpStatus.BAD_REQUEST;
	
	public StationException(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}
	
	
}

package com.metro.Metro.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalUserExceptionHandler {

	@ExceptionHandler(UserException.class)
	public ResponseEntity<String> userExceptionHandler(UserException e){
		return new ResponseEntity<String>(e.errorMessage,e.errorResponseCode);
	}
	
	@ExceptionHandler(StationException.class)
	public ResponseEntity<String> sationExceptionHandler(StationException e){
		return new ResponseEntity<String>(e.errorMessage,e.errorResponseCode);
	}
}

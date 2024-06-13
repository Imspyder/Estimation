package com.vehicle.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * 
 * Custom Exception Class for User Registration
 *
 */
public class UserRegistrationException extends HttpStatusCodeException{

	private static final long serialVersionUID = 1L;

	public UserRegistrationException(HttpStatus statusCode) {
		super(statusCode);
		
	}
	
	public UserRegistrationException(HttpStatus statusCode, String message) {
		super(statusCode, message);
		
	}

}

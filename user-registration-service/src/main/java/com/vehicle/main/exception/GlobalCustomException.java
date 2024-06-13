package com.vehicle.main.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vehicle.main.response.UserResponse;

/*
 * This class is for handling Custom Global Exception  
 */

@ControllerAdvice
public class GlobalCustomException {

	@Autowired
	private Environment env;

	@Autowired
	private UserResponse userResponse;

	/**
	 * This methods is to handle UserRegistrationException
	 */
	@ExceptionHandler(UserRegistrationException.class)
	public ResponseEntity<UserResponse> handleMethodNotAllowed(UserRegistrationException e) {
		userResponse.setSuccess(false);
		if (e.getStatusCode().equals(HttpStatus.ALREADY_REPORTED)) {
			userResponse.setResponse(env.getProperty("8001"));
			return ResponseEntity.badRequest().body(userResponse);
		}

		userResponse.setResponse(e.getMessage());
		return ResponseEntity.internalServerError().body(userResponse);

	}

}

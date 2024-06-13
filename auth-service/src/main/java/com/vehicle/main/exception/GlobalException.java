package com.vehicle.main.exception;

import com.vehicle.main.entity.LoginResponse;

import com.vehicle.main.pojo.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.LoginException;

@ControllerAdvice
public class GlobalException {

	@Autowired
	private Environment env;
	

	@ExceptionHandler(LoginExceptionService.class)
	public ResponseEntity<LoginResponse> handleMethodNotAllowed(LoginExceptionService a) {

		LoginResponse response = new LoginResponse();
		response.setSuccess(false);
		if (a.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
			response.setResponse(env.getProperty("9000"));
			return ResponseEntity.badRequest().body(response);
		}
		if (a.getStatusCode().equals(HttpStatus.FORBIDDEN)) {
			response.setResponse(env.getProperty("9001"));
			return ResponseEntity.badRequest().body(response);
		}
		if (a.getStatusCode().equals(HttpStatus.UNPROCESSABLE_ENTITY)) {
			response.setResponse(env.getProperty("9002"));
			return ResponseEntity.badRequest().body(response);
		}
		if (a.getStatusCode().equals(HttpStatus.EXPECTATION_FAILED)) {
			response.setResponse(env.getProperty("9003"));
			return ResponseEntity.badRequest().body(response);
		}
		if (a.getStatusCode().equals(HttpStatus.NOT_FOUND)) {
			response.setResponse(env.getProperty("9004"));
			return ResponseEntity.badRequest().body(response);
		}
		if (a.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
			response.setResponse(env.getProperty("9005"));
			return ResponseEntity.badRequest().body(response);
		}
		if (a.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE)) {
			response.setResponse(env.getProperty("9006"));
			return ResponseEntity.badRequest().body(response);
		}
		response.setResponse(a.getMessage());
		return ResponseEntity.internalServerError().body(response);
	}




}
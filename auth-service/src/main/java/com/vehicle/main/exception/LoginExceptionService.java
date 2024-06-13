package com.vehicle.main.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;




public class LoginExceptionService extends HttpStatusCodeException {

    public LoginExceptionService(HttpStatus statusCode) {
        super(statusCode);
        // TODO Auto-generated constructor stub
    }

    public LoginExceptionService(HttpStatus statusCode, String message) {
        super(statusCode, message);
        // TODO Auto-generated constructor stub
    }

    public LoginExceptionService(String message) {
        super(HttpStatus.valueOf(message));
        // TODO Auto-generated constructor stub
    }


    /**
     *
     */
    private static final long serialVersionUID = 1L;





}


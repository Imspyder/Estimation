package com.vehicle.schedular.exception;

import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;

public class DateFormatException extends RuntimeException {

    public DateFormatException(String filename){
        super();
    }
}

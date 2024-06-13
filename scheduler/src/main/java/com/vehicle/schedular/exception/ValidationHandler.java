package com.vehicle.schedular.exception;

import com.vehicle.schedular.model.VehicleErrorLogDTO;
import org.hibernate.sql.ordering.antlr.Node;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@ComponentScan(basePackages = "package com.vehicle.schedular.controller.*")
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<VehicleErrorLogDTO> handleValidations(MethodArgumentNotValidException ex) {
        Map<String ,String> errorList = new HashMap<>();
        VehicleErrorLogDTO vehicleErrorLogDTO = new VehicleErrorLogDTO();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errorList.put(((FieldError) error).getField() , error.getDefaultMessage());
            System.out.println(error);

            System.out.println(fieldName + "\t" + message);
            vehicleErrorLogDTO.setErrorMsg(errorList.toString());
            vehicleErrorLogDTO.setExceptionMsg(error.getObjectName());
        });
        return new ResponseEntity(vehicleErrorLogDTO, HttpStatus.BAD_REQUEST);
    }

   /* @ExceptionHandler(ConstraintViolationException.class)
    public VehicleErrorLogDTO handleValidations(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();

        VehicleErrorLogDTO vehicleErrorLogDTO;
        {
            Map<String,String> errorList = new HashMap<>();
            vehicleErrorLogDTO = new VehicleErrorLogDTO();
            ex.getConstraintViolations().forEach((error) -> {
                String name = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
                vehicleErrorLogDTO.setExceptionMsg(error.getMessage());
                errorList.put(name,error.getMessage());
                System.out.println(error);
            });
            vehicleErrorLogDTO.setErrorMsg(errorList.toString());

        }
        return vehicleErrorLogDTO;
    }*/

//    @ExceptionHandler(ConstraintViolationException.class)
//    public StringBuffer handleValidations(ConstraintViolationException ex) {
//        StringBuffer errorMessage=null;
//
//            ex.getConstraintViolations().forEach((error) -> {
////                 errorMessage=error.getMessage();
//                System.out.println(error);
//            });
//        return errorMessage;
//    }
}

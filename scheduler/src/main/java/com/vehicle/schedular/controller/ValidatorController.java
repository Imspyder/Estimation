package com.vehicle.schedular.controller;

import com.vehicle.schedular.model.VehicleDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@Controller
public class ValidatorController {

    public ResponseEntity<Object> valid(@RequestBody @Valid VehicleDto vehicleDto){
        try {
            return new ResponseEntity<>(vehicleDto,HttpStatus.OK);
        }catch (Exception e){
            //return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

package com.vehicle.main.controller;

import com.vehicle.main.controller.UserRegistrationController;
import com.vehicle.main.dto.UserDto;
import com.vehicle.main.response.UserResponse;
import com.vehicle.main.service.UserServiceHandlers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class UserRegistrationControllerTest {
    @Mock
    UserServiceHandlers userServiceHandlers;
    @InjectMocks
    UserRegistrationController userRegistrationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {

        when(userServiceHandlers.saveUser(any())).thenReturn(new UserResponse());
        UserDto user=new UserDto();
        user.setName("mayur");
        user.setEmail("@");
        user.setPassword("123");
        user.setMobileNumber("99");
        user.setUsername("mc");
        ResponseEntity<UserResponse> result = userRegistrationController.registerUser(user);
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
      //  Assertions.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
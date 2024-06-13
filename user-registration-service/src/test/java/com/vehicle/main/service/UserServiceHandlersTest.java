package com.vehicle.main.service;

import com.vehicle.main.config.PropertyConfig;
import com.vehicle.main.dto.UserDto;
import com.vehicle.main.entity.User;
import com.vehicle.main.repository.RoleRepository;
import com.vehicle.main.repository.UserRepository;
import com.vehicle.main.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class UserServiceHandlersTest {
    @Mock
    UserRepository userRepository;
    @Mock
    PropertyConfig props;
    @Mock
    RoleRepository roleRepo;
    @InjectMocks
    UserServiceHandlers userServiceHandlers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User u=new User();
        String conditionTrue;
        //when(userRepository.findByUsername(anyString())).thenReturn(u);
        when(props.getConditionTrue()).thenReturn("getConditionTrueResponse");
        when(props.getConditionFalse()).thenReturn("getConditionFalseResponse");
        UserDto user=new UserDto();
        user.setName("mayur");
        user.setEmail("@");
        user.setPassword("123");
        user.setMobileNumber("99");
        user.setUsername("mc");
        UserResponse result = userServiceHandlers.saveUser(user );

        Assertions.assertEquals("getConditionFalseResponse",result.getResponse());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme
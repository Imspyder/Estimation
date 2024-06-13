package com.vehicle.main.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vehicle.main.config.PropertyConfig;
import com.vehicle.main.dto.UserDto;
import com.vehicle.main.entity.Role;
import com.vehicle.main.entity.User;
import com.vehicle.main.entity.UserRole;
import com.vehicle.main.exception.UserRegistrationException;
import com.vehicle.main.repository.RoleRepository;
import com.vehicle.main.repository.UserRepository;
import com.vehicle.main.response.UserResponse;

@Service
public class UserServiceHandlers {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PropertyConfig props;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserResponse saveUser(UserDto userDto) {
		UserResponse response = new UserResponse();
		try {
			String encpass = passwordEncoder.encode(userDto.getPassword());
			userDto.setPassword(encpass);

			ObjectMapper objectMapper = new ObjectMapper();
			User user = objectMapper.convertValue(userDto, User.class);
			Role role = new Role();
			role.setRoleId(222L);
			role.setRoleType("USER");

			roleRepo.save(role);

			Set<UserRole> roles = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);
			roles.add(userRole);
			user.setRoles(roles);

			for (UserRole userR : user.getRoles()) {
				roleRepo.save(userR.getRole());
			}

			user = userRepository.save(user);

			response.setSuccess(true);
			response.setResponse(props.getConditionTrue());

		} catch (Exception e) {
			response.setSuccess(false);
			response.setResponse(props.getConditionFalse());
//			System.out.println(e.getMessage() + e.getStackTrace().toString());
			throw new UserRegistrationException(HttpStatus.ALREADY_REPORTED);
		} finally {
//			response = null;
		}

		return response;

	}

}

package com.vehicle.main.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vehicle.main.entity.User;
import com.vehicle.main.entity.UserRole;
import com.vehicle.main.exception.LoginExceptionService;
import com.vehicle.main.exception.ServiceException;
import com.vehicle.main.jwt.JwtUtil;
import com.vehicle.main.repository.RoleRepository;
import com.vehicle.main.repository.UserRepository;
import com.vehicle.main.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public void createUser(List<User> listUser) throws ServiceException {
		log.trace("The createUser method  start");
		listUser.stream().forEach(user -> {
			try {
				Optional<User> findByUsername = userRepo.findByUsername(user.getUsername());
				log.info("The userName we found {}" + findByUsername);
				if (findByUsername.isPresent()) {
					log.warn("User already exist");
					throw new LoginExceptionService(HttpStatus.EXPECTATION_FAILED);
				} else {
					for (UserRole userRole : user.getRoles()) {
						roleRepo.save(userRole.getRole());
					}
					user.getRoles().addAll(user.getRoles());
					user.setPassword(passwordEncoder.encode(user.getPassword()));
					userRepo.save(user);
					log.warn("User Saved :::");
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				throw new LoginExceptionService(HttpStatus.EXPECTATION_FAILED);
			}
		});
	}

	@Override
	public User getUser(String username) throws ServiceException {
		try {
			User user = null;
			log.trace("getUser Method start");
			Optional<User> newUser = userRepo.findByUsername(username);
			if (!newUser.isPresent()) {
				log.warn("Username is not found !");
				throw new LoginExceptionService(HttpStatus.NOT_FOUND);
			} else {
				user = newUser.get();
			}
			return user;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new LoginExceptionService(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public String generateToken(String username) throws ServiceException {
		try {
			log.info("generateToken method start {}");
			String token = "";
			token = jwtUtil.generateToken(username);
			log.info("Token {}" + token);
			return token;
		} catch (Exception e) {
			log.error("Invalid Token Access!");
			throw new LoginExceptionService(HttpStatus.UNAUTHORIZED);
		}
	}

	@Override
	public Map<String, Object> getBearerToken(String token) throws ServiceException {
		String beareToken = "";
		User userNew = new User();
		Map<String, Object> tokenMap = new HashMap<>();
		try {
			String extractUsername = jwtUtil.extractUsername(token);
			Optional<User> userDetails = userRepo.findByUsername(extractUsername);
			if(userDetails.isPresent()) {
				 User user = userDetails.get();
				 userNew.setUserId(user.getUserId());
				 userNew.setEmail(user.getEmail());
				 userNew.setMobileNumber(user.getMobileNumber());
				 userNew.setUsername(user.getUsername());
			}
			beareToken = jwtUtil.generateToken(extractUsername);
			tokenMap.put("currentLoggedUser", userNew);
			tokenMap.put("bearer-token", beareToken);
			log.info("Invalid Token Access!");
	
		} catch (Exception e) {
			log.warn("Token Validate Fails");
			throw new LoginExceptionService(HttpStatus.FORBIDDEN);
		}
		return tokenMap;
	}


//	@Override
//	public List<String> getUserByRole(String username) throws ServiceException {
//		try {
//			List<String> roleList = new ArrayList<>();
//			List<User> userRolesList = userRepo.getUserRoles(username);
//			if (!userRolesList.isEmpty()) {
//
//				Optional<User> userData = userRolesList.stream()
//						.filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst();
//
//				if (userData.isPresent()) {
//					Set<UserRole> userRoles = userData.get().getRoles();
//					for (UserRole userRole : userRoles) {
//						String roleName = userRole.getRole().getRoleType();
//						roleList.add(roleName);
//					}
//				} else {
//					log.warn("User Not Present");
//					throw new LoginExceptionService(HttpStatus.NOT_FOUND);
//				}
//			}
//			return roleList;
//		} catch (Exception e) {
//			log.error(" " + e);
//			throw new LoginExceptionService(HttpStatus.NOT_FOUND);
//		}
//	}

}

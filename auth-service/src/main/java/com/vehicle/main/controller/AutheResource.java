package com.vehicle.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vehicle.main.exception.LoginExceptionService;
import com.vehicle.main.pojo.AuthRequest;
import com.vehicle.main.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/user/")
public class AutheResource {

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(path = "login", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getToken(@RequestBody AuthRequest authRequest) {
		try {
			String generateToken = "";
			Map<String, Object> respMap = new HashMap<>();
			List<Map<String,Object>> listTokenMap = new ArrayList<>();
			Map<String,Object> tokenMap = new HashMap<>();
			log.trace("The getToken() Endpoint start");
			String username = authRequest.getUsername();
			String password = authRequest.getPassword();
			log.info("the authenticate username  {}" + authRequest.getUsername());

			Authentication authenticate = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));

			if (authenticate.isAuthenticated()) {
				generateToken = userServiceImpl.generateToken(username);
				
				Optional<String> roleName = authenticate.getAuthorities().stream().filter(p -> p.getAuthority() != null)
						.map(auth -> auth.getAuthority()).findFirst();

				if (roleName.isPresent()) {
					tokenMap.put("auth-token", generateToken);
					tokenMap.put("userRole", roleName.get().substring(5).toLowerCase());
					listTokenMap.add(tokenMap);
					respMap.put("success", true);
					respMap.put("response", listTokenMap);
				} else {
					respMap.put("success", false);
					respMap.put("response", listTokenMap);
				}
			}
			return new ResponseEntity<>(respMap, HttpStatus.OK);
		} catch (Exception e) {
			log.error("the userRole   {}" + "INTERNAL_SERVER_ERROR");
			throw new LoginExceptionService(HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(path = "generateBearerToken", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getBearerToken(@RequestParam("token") String token) {

		String generateToken = "";
		Map<String, Object> respMap = new HashMap<>();
		List<Map<String, Object>> listTokenMap = new ArrayList<>();
		Map<String, Object> tokenMap = new HashMap<>();

		try {
			log.info("The getToken() Enpoint start");
			 Map<String, Object> resultMap = userServiceImpl.getBearerToken(token);
			log.trace("The Genarte token " + generateToken);
			if (!resultMap.isEmpty()) {
				listTokenMap.add(resultMap);
				respMap.put("success", true);
				respMap.put("response", listTokenMap);
			}
			return new ResponseEntity<>(respMap, HttpStatus.OK);
		} catch (Exception e) {
			log.error("INTERNAL_SERVER_ERROR");
			throw new LoginExceptionService(HttpStatus.BAD_REQUEST);
		}

	}

}

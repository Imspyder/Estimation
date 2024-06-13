package com.vehicle.main;

import com.vehicle.main.entity.Role;
import com.vehicle.main.entity.User;
import com.vehicle.main.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.vehicle.main.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
//public class AuthServiceApplication implements CommandLineRunner{
public class AuthServiceApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	public void run(String... args) throws Exception {


		User user = new  User();
		user.setName("Sagar Satunkar");
		//user.setLastName("Satunkar");
		user.setUsername("sagarvijay");
		user.setPassword("Sagar123");
		user.setMobileNumber("+918862021787");
		user.setEmail("abc@gmail.com");

		Role role = new Role();
		role.setRoleId(111L);
		role.setRoleType("ADMIN");

		Set<UserRole> userRoleSet = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		userRoleSet.add(userRole);
		user.setRoles(userRoleSet);


		User user2 = new  User();
		user2.setName("Ankit Belge");
		//user2.setLastName("Belge");
		user2.setUsername("ankit");
		user2.setPassword("ankit123");
		user2.setMobileNumber("+918862021787");
		user2.setEmail("abc@gmail.com");

		Role role2 = new Role();
		role2.setRoleId(222L);
		role2.setRoleType("USER");

		Set<UserRole> userRoleSet2 = new HashSet<>();
		UserRole userRole2 = new UserRole();
		userRole2.setUser(user2);
		userRole2.setRole(role2);
		userRoleSet2.add(userRole2);
		user2.setRoles(userRoleSet2);

		List<User> allUser = new ArrayList<>();
		allUser.add(user);
		allUser.add(user2);
	    userService.createUser(allUser);

	}

}

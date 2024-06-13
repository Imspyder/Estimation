package com.gateway.main.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RoutValidator {

	public static final List<String> openApiEndpoints = List.of("/api/auth/token", "/api/user/login",
			"/api/auth/validate-token","/api/user/generateBearerToken", "/eureka", "/h2-console", "/api/upload");

	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndpoints.stream()
			.noneMatch(uri -> request.getURI().getPath().contains(uri));

}

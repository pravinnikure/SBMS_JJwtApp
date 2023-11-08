package com.app.restapi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.restapi.entity.User;
import com.app.restapi.payload.UserRequest;
import com.app.restapi.payload.UserResponse;
import com.app.restapi.service.IUserService;
import com.app.restapi.service.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserRestController 
{
	@Autowired
	private IUserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/save")
	public ResponseEntity<String> createUser(
			@RequestBody User user)
	{
	  Integer id = service.saveUser(user);
		
		return ResponseEntity.ok("User created" + id);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody UserRequest request)
	{
		//This coe reads usename and password from client and tries to validate by checking useing userdetails service for token generations
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(), 
		
						request.getPassword()));
		
		String token = jwtUtil.generateToken(request.getUsername());
		return ResponseEntity.ok(new UserResponse(token,"Generated by Pravin"));
	}
	
	@PostMapping("/welcome")
	public ResponseEntity<String> showUser(Principal principal)
	{
		System.out.println(principal);
		System.out.println(principal.getClass().getName());
			
		return ResponseEntity.ok("Hello " + principal.getName());
	}
	
}

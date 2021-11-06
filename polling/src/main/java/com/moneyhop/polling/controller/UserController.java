package com.moneyhop.polling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.security.User;
import com.moneyhop.polling.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public Response register(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PostMapping("/login")
	public Response login(@RequestBody User user) throws Exception {
		return userService.loginUser(user);
	}

}

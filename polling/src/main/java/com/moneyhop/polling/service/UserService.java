package com.moneyhop.polling.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.security.User;

public interface UserService  extends UserDetailsService {

	Response createUser(User user);
	
	Response loginUser(User user);
}

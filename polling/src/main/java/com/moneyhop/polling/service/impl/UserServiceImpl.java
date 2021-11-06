package com.moneyhop.polling.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.moneyhop.polling.Response;
import com.moneyhop.polling.Response.StatusCode;
import com.moneyhop.polling.repository.UserRepository;
import com.moneyhop.polling.security.TokenUtil;
import com.moneyhop.polling.security.User;
import com.moneyhop.polling.service.UserService;

@Component
public class UserServiceImpl implements UserService{

	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private TokenUtil tokenUtil;

	@Override
	public UserDetails loadUserByUsername(String userName) {
		User tempUser = userRepository.getUserByUserName(userName);
		
		if (tempUser == null) {
			return null;
		}

		return new org.springframework.security.core.userdetails.User(tempUser.getUserName(), tempUser.getPassword(), new ArrayList<>());
	}


	@Override
	public Response createUser(User user) {
		
		Response validate = validate(user);
		
		if(validate!=null) {
			return validate;
		}
		
		try {
			userRepository.save(user);
		} catch (Exception e) {
			Response.failed();
		}
		
		return Response.success();
	}
	
	private Response validate(User user) {
		
		if(user.getPassword()==null||user.getPassword().isEmpty()) {
			return Response.mandatory("Password");
		}
		
		if(user.getUserName()==null||user.getUserName().isEmpty()) {
			return Response.mandatory("UserName");
		}
		
		return null;
	}

	@Override
	public Response loginUser(User user) {
		
		try {
			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		} catch (BadCredentialsException e) {
			return new Response(StatusCode.ERROR,"invalid Credentials");
		}
		
		String token = null;
		try {
			UserDetails userDetails = loadUserByUsername(user.getUserName());
			token = tokenUtil.generateToken(userDetails);
			user.setToken(token);
		} catch (Exception e) {
			return new Response(StatusCode.ERROR,"unable to generate Token");
		}

		return user;
	}
}

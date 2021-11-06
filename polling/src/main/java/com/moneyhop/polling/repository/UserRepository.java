package com.moneyhop.polling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneyhop.polling.security.User;

public interface UserRepository  extends JpaRepository<User, Long> {
	User getUserByUserName(String userName);
	
}

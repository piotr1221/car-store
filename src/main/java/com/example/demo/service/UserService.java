package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
		if (userRepository.findByUsername(user.getUsername()) != null) {
			return null;
		}
		return userRepository.save(user);
	}
	
	public User login(User user) {
		User user_login = userRepository.findByUsername(user.getUsername());;
		if (user_login == null) {
			return null;
		}
		
		if (!user_login.getPassword().equals(user.getPassword())) {
			return null;
		}
		
		return user_login;
	}
}

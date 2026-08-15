package com.user.login.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.login.dto.RegisterRequest;
import com.user.login.entity.User;
import com.user.login.repository.UserRepository;

@Service
public class UserService {
	
	 private final UserRepository repository;
	    private final PasswordEncoder passwordEncoder;

	    public UserService(UserRepository repository,
	                       PasswordEncoder passwordEncoder) {
	        this.repository = repository;
	        this.passwordEncoder = passwordEncoder;
	    }

	    public User register(RegisterRequest request) {
	    	
	    User existingUser = repository.findByEmail(request.getEmail()).orElse(null);
          if (existingUser != null) {
			  throw new IllegalArgumentException("Email is already in use");
		  }else {
			  User user = new User();
		        user.setName(request.getName());
		        user.setEmail(request.getEmail());
		        user.setMobile(request.getMobile());
		        user.setPassword(
		                passwordEncoder.encode(request.getPassword())
		        );
		        user.setRole("ROLE_USER");
		        return repository.save(user);
		  }
	        
	    }

}

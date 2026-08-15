package com.user.login.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.login.dto.ProfileRequest;
import com.user.login.dto.ProfileResponse;
import com.user.login.entity.User;
import com.user.login.repository.UserRepository;

@RestController
@RequestMapping("/api/user")
//@CrossOrigin(origins = {"http://localhost:5173","http://localhost:3000"})
public class UserController {
	
	 private final UserRepository repository;

	    public UserController(UserRepository repository) {
	        this.repository = repository;
	    }

	    @GetMapping("/profile")
	    public ProfileResponse getProfile(Principal principal) {

	        User user = repository.findByEmail(principal.getName())
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));
	        System.out.println("Fetching profile for user: " + user.getEmail());
            ProfileResponse profileResponseLog=new ProfileResponse(
	                user.getId(),
	                user.getName(),
	                user.getEmail(),
	                user.getMobile(),
	                user.getRole()
	        );
            
            System.out.println("ProfileResponse: " + profileResponseLog.toString());
	        return profileResponseLog;
	    }
	    
	    @PutMapping("/profile")
	    public ProfileResponse updateProfile(
	            @RequestBody ProfileRequest request,
	            Principal principal) {

	        User user = repository.findByEmail(principal.getName())
	                .orElseThrow(() ->
	                        new RuntimeException("User not found"));

	        user.setName(request.getName());
	        user.setMobile(request.getMobile());

	        repository.save(user);

	        return new ProfileResponse(
	                user.getId(),
	                user.getName(),
	                user.getEmail(),
	                user.getMobile(),
	                user.getRole()
	        );
	    }

}

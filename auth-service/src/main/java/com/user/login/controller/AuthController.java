package com.user.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.login.dto.LoginRequest;
import com.user.login.dto.LoginResponse;
import com.user.login.entity.User;
import com.user.login.repository.UserRepository;
import com.user.login.security.JwtUtil;



@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    
    private static final Logger log =
            LoggerFactory.getLogger(AuthController.class);

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody LoginRequest request) {
    	
    	log.info(
    		    "LOGIN REQUEST username={}",
    		    request.getEmail() );

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(

                        request.getEmail(),

                        request.getPassword()

                )

        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = JwtUtil.generateToken(user.getEmail(),user.getId());

        return new LoginResponse(

                token,

                user.getId(),

                user.getName(),

                user.getEmail(),

                user.getRole()

        );

    }

}

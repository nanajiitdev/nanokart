package com.user.login.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.login.dto.ConsumerProfileRequest;
import com.user.login.dto.ConsumerProfileResponse;
import com.user.login.service.ConsumerProfileService;

@RestController
@RequestMapping("/api/customer")
public class ConsumerProfileController {

    private final ConsumerProfileService service;

    public ConsumerProfileController(
            ConsumerProfileService service) {

        this.service = service;
    }

    @PostMapping("/profile")
    public ResponseEntity<ConsumerProfileResponse> saveProfile(
            @RequestBody ConsumerProfileRequest request,
            Principal principal) {

        ConsumerProfileResponse response =
                service.saveProfile(
                        request,
                        principal.getName());

        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED);
    }

    @GetMapping("/profile")
    public ResponseEntity<ConsumerProfileResponse> getProfile(
            Principal principal) {

        ConsumerProfileResponse response =
                service.getProfile(
                        principal.getName());

        return ResponseEntity.ok(response);
    }

}
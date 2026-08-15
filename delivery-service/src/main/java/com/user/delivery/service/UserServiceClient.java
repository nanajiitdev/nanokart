package com.user.delivery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.delivery.model.ConsumerResponse;
import com.user.delivery.model.UserResponse;

@Service
public class UserServiceClient {

    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ConsumerResponse getUser(Long customerId) {

        String url = userServiceUrl + "/api/orders/customer/" + customerId;
        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+url);

        return restTemplate.getForObject(url, ConsumerResponse.class);

    }

}
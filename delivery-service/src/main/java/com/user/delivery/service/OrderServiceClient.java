package com.user.delivery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.delivery.model.OrderResponse;

@Service
public class OrderServiceClient {

    private final RestTemplate restTemplate;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public OrderResponse getOrder(Long orderId) {

        String url = orderServiceUrl + "/api/orders/" + orderId;

        return restTemplate.getForObject(url, OrderResponse.class);

    }

}
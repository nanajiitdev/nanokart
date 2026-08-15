package com.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ecommerce.orderservice.dto.PaymentRequest;

@FeignClient(name = "PAYMENT-SERVICE")
public interface PaymentServiceClient {

    @PostMapping("/api/payments")
    String makePayment(@RequestBody PaymentRequest request);

}
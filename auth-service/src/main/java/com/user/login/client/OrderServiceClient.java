package com.user.login.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.login.dto.CustomerRequest;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderServiceClient {

    @PostMapping("/api/customers")
    CustomerRequest saveCustomer(
            @RequestBody CustomerRequest request);

}
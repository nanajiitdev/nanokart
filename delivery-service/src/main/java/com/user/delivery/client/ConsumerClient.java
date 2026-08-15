package com.user.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.delivery.model.ConsumerResponse;

//@FeignClient(name = "ORDER-SERVICE")

@FeignClient(
	    name = "ORDER-SERVICE",
	    contextId = "consumerClient"
	)
public interface ConsumerClient {

    @GetMapping("/api/orders/customer/{customerId}")
    ConsumerResponse getUser(
            @PathVariable("customerId") Long customerId);

}
package com.user.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.delivery.model.OrderResponse;

@FeignClient(
	    name = "ORDER-SERVICE",
	    contextId = "orderClient"
	)

public interface OrderClient {

    @GetMapping("/api/orders/{orderId}")
    OrderResponse getOrder(
            @PathVariable("orderId") Long orderId);

}
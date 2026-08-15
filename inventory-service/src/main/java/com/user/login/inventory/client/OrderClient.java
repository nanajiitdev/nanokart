package com.user.login.inventory.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.login.inventory.dto.OrderDetailsResponse;

@FeignClient(name = "ORDER-SERVICE")
public interface OrderClient {

    /*
     * Get Complete Order Details
     */
    @GetMapping("/api/orders/{orderId}/details")
    OrderDetailsResponse getOrderDetails(
            @PathVariable("orderId") Long orderId);

}
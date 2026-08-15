package com.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.orderservice.dto.InventoryResponse;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryServiceClient {

    @GetMapping("/inventory/{productId}")
    InventoryResponse getInventory(
            @PathVariable("productId") Long productId);

}
package com.user.login.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.login.dto.InventoryResponse;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryServiceClient {

    @GetMapping("/api/inventory/product/{productId}")
    InventoryResponse getInventoryByProductId(
            @PathVariable("productId") Long productId);
    
    /*
     * Get All Inventory
     */
    @GetMapping("/api/inventory")
    List<InventoryResponse> getAllInventory();
}
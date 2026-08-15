package com.ecommerce.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.orderservice.config.FeignConfig;
import com.ecommerce.orderservice.dto.ProductResponse;

@FeignClient(
        name = "AUTH-SERVICE",
        configuration = FeignConfig.class
)
public interface ProductServiceClient {

    @GetMapping("/api/products/{id}")
    ProductResponse getProductById(
            @PathVariable("id") Long id);

}
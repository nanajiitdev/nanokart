package com.ecommerce.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.orderservice.entity.Customer;
import com.ecommerce.orderservice.service.ConsumerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private ConsumerService customerService;

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(
            @RequestBody Customer customer) {

        return ResponseEntity.ok(
                customerService.saveCustomer(customer));
    }
}
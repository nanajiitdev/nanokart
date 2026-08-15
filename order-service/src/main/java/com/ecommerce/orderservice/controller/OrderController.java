package com.ecommerce.orderservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.orderservice.dto.ConsumerResponse;
import com.ecommerce.orderservice.dto.OrderDetailsResponse;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.service.ConsumerService;
import com.ecommerce.orderservice.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
	
	private static final Logger log =
	        LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private ConsumerService consumerService;

    // Create Order
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @Valid @RequestBody OrderRequest orderRequest) {
    	log.info("========== CREATE ORDER REQUEST ==========");
    	log.info(
    		    "ORDER CREATED orderId={} customerId={} totalAmount={}",
    		    orderRequest.getCustomerId()
    		);
        OrderResponse response = orderService.createOrder(orderRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Orders
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {

        List<OrderResponse> response = orderService.getAllOrders();

        return ResponseEntity.ok(response);
    }

    // Get Order By Id
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(
            @PathVariable("id") Long id) {
    	System.out.println("Fetching order with ID: " + id);

        OrderResponse response = orderService.getOrderById(id);
        System.out.println("response : " + response);
        
        log.info(
        	    "CALLING PAYMENT SERVICE orderId={} amount={}",
        	    response.getOrderId(),
        	    response.getTotalAmount()
        	);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // Delete Order
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(
            @PathVariable("id") Long id) {

        orderService.deleteOrder(id);

        return ResponseEntity.ok("Order Deleted Successfully.");
    }
    
    // get conumer by customer id

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<ConsumerResponse> getOrdersByCustomerId(
			@PathVariable("customerId") Long customerId) {
    	System.out.println( "Fetching consumer with customer ID: >>>>>>>>>>" + customerId);
		ConsumerResponse response = consumerService.getConumnerByCustomerId(customerId);
        System.out.println("response =============: " + response);
		return ResponseEntity.ok(response);
	}
    
    
    @GetMapping("/customer/{customerId}/orders")
    public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId1(
            @PathVariable Long customerId) {

        return ResponseEntity.ok(
                orderService.getOrdersByCustomerId(customerId));
    }
    
    @GetMapping("/{orderId}/details")
    public ResponseEntity<OrderDetailsResponse> getOrderDetails(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(
                orderService.getOrderDetails(orderId));

    }
    
}
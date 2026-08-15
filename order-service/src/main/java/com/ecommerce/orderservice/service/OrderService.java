package com.ecommerce.orderservice.service;

import java.util.List;

import com.ecommerce.orderservice.dto.OrderDetailsResponse;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;

public interface OrderService {

    OrderResponse createOrder(OrderRequest orderRequest);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long orderId);

    void deleteOrder(Long orderId);
    
    List<OrderResponse> getOrdersByCustomerId(Long customerId);

    OrderDetailsResponse getOrderDetails(Long orderId);

}
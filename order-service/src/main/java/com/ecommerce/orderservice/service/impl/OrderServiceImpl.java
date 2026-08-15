package com.ecommerce.orderservice.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.client.InventoryServiceClient;
import com.ecommerce.orderservice.client.PaymentServiceClient;
import com.ecommerce.orderservice.client.ProductServiceClient;
import com.ecommerce.orderservice.dto.InventoryResponse;
import com.ecommerce.orderservice.dto.OrderDetailsResponse;
import com.ecommerce.orderservice.dto.OrderItemDto;
import com.ecommerce.orderservice.dto.OrderItemResponse;
import com.ecommerce.orderservice.dto.OrderRequest;
import com.ecommerce.orderservice.dto.OrderResponse;
import com.ecommerce.orderservice.dto.PaymentRequest;
import com.ecommerce.orderservice.dto.ProductResponse;
import com.ecommerce.orderservice.entity.Customer;
import com.ecommerce.orderservice.entity.Order;
import com.ecommerce.orderservice.entity.OrderItem;
import com.ecommerce.orderservice.repository.CustomerRepository;
import com.ecommerce.orderservice.repository.OrderRepository;
import com.ecommerce.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private ProductServiceClient productClient;

    @Autowired
    private InventoryServiceClient inventoryClient;

    @Autowired
    private PaymentServiceClient paymentClient;
    
    @Autowired
    private CustomerRepository customerRepository;
    

    @Override
    public OrderResponse createOrder(OrderRequest orderRequest) {

        Order order = new Order();
        System.out.println("======================"+orderRequest.getCustomerId());
//        order.setCustomerId(orderRequest.getCustomerId());
//        Customer customer = new Customer();
//        order.getCustomer().setCustomerId(orderRequest.getCustomerId());
//        customer.setCustomerId(orderRequest.getCustomerId());
        
        Customer customer = customerRepository
                .findById(orderRequest.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found with id: " + orderRequest.getCustomerId()));

        order.setCustomer(customer);
        
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.ZERO;

        if (orderRequest.getItems() != null) {

            for (OrderItemDto dto : orderRequest.getItems()) {

                OrderItem item = new OrderItem();

                item.setProductId(dto.getProductId());
                item.setQuantity(dto.getQuantity());

                // Temporary price
                ProductResponse product =
                        productClient.getProductById(dto.getProductId());

                item.setPrice(product.getPrice());

                totalAmount = totalAmount.add(
                        product.getPrice()
                                .multiply(BigDecimal.valueOf(dto.getQuantity()))
                );

                item.setOrder(order);

                orderItems.add(item);
                
				/*
				 * InventoryResponse inventory =
				 * inventoryClient.getInventory(dto.getProductId());
				 * 
				 * if(inventory.getAvailableQuantity() < dto.getQuantity()){
				 * 
				 * throw new RuntimeException("Insufficient Stock");
				 * 
				 * }
				 */
                
                
            }
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        
        
        
        
        Order savedOrder = orderRepository.save(order);
        
        
		
		  PaymentRequest payment = new PaymentRequest();
		  
		  payment.setOrderId(savedOrder.getOrderId());
		  payment.setAmount(savedOrder.getTotalAmount());
		  
		  payment.setPaymentMode("COD");
		  
		  paymentClient.makePayment(payment);
		 

        return convertToResponse(savedOrder);
    }

    @Override
    public List<OrderResponse> getAllOrders() {

        List<Order> orders = orderRepository.findAll();

        List<OrderResponse> responses = new ArrayList<>();

        for (Order order : orders) {
            responses.add(convertToResponse(order));
        }

        return responses;
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {

        Optional<Order> optional = orderRepository.findById(orderId);

        if (optional.isPresent()) {
            return convertToResponse(optional.get());
        }

        return null;
    }

    @Override
    public void deleteOrder(Long orderId) {

        orderRepository.deleteById(orderId);

    }

    private OrderResponse convertToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getOrderId());
//        response.setCustomerId(order.getCustomerId());
        response.setCustomerId(order.getCustomer().getCustomerId());
        response.setTotalAmount(order.getTotalAmount());
        response.setStatus(order.getStatus());
        response.setOrderDate(order.getOrderDate());

        return response;
    }
    
    @Override
    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {

        return orderRepository
                .findByCustomerCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }
    
    private OrderResponse mapToResponse(Order order) {

        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getOrderId());

        response.setCustomerId(order.getCustomer().getCustomerId());

        response.setTotalAmount(order.getTotalAmount());

        response.setStatus(order.getStatus());

        response.setOrderDate(order.getOrderDate());

        return response;

    }

	@Override
	public OrderDetailsResponse getOrderDetails(Long orderId) {
		// TODO Auto-generated method stub
		return orderRepository.findById(orderId)
				.map(order -> {
					OrderDetailsResponse response = new OrderDetailsResponse();
					response.setOrderId(order.getOrderId());
					response.setCustomerId(order.getCustomer().getCustomerId());
					response.setTotalAmount(order.getTotalAmount());
					response.setStatus(order.getStatus());
					response.setOrderDate(order.getOrderDate());
					Customer customer = order.getCustomer();

					response.setCustomerName(customer.getCustomerName());
					response.setEmail(customer.getEmail());
					response.setMobile(customer.getMobile());
					response.setAddress(customer.getAddress());
					response.setPaymentStatus("PAID");
					response.setDeliveryStatus("PROCESSING");
					response.setTrackingNumber("NK" + order.getOrderId());

					List<OrderItemResponse> itemDtos = new ArrayList<>();
					for (OrderItem item : order.getOrderItems()) {
						OrderItemResponse  itemDto = new OrderItemResponse();
						itemDto.setProductId(item.getProductId());
						itemDto.setQuantity(item.getQuantity());
						itemDto.setPrice(item.getPrice());

						// Fetch product details from Product Service
						ProductResponse product = productClient.getProductById(item.getProductId());
						itemDto.setProductName(product.getProductName());
						itemDto.setCategory(product.getCategory());
						itemDto.setDescription(product.getDescription());

						itemDtos.add(itemDto);
					}
					response.setItems(itemDtos);

					return response;
				})
				.orElse(null);
	}

}
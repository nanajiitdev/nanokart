package com.ecommerce.orderservice.service;

import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.dto.ConsumerResponse;
import com.ecommerce.orderservice.entity.Customer;
@Service
public interface ConsumerService {

	ConsumerResponse getConumnerByCustomerId(Long customerId);
	
	 Customer saveCustomer(Customer customer);

}

package com.ecommerce.orderservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orderservice.dto.ConsumerResponse;
import com.ecommerce.orderservice.entity.Customer;
import com.ecommerce.orderservice.repository.CustomerRepository;
import com.ecommerce.orderservice.service.ConsumerService;

@Service
public class ConsumerServiceImpl implements ConsumerService {
	@Autowired
	public CustomerRepository customerRepository;

	@Override
	public ConsumerResponse getConumnerByCustomerId(Long customerId) {
		Customer customer = customerRepository.findById(customerId)
		        .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
		if(customer.getCustomerId() == null) {
			throw new RuntimeException("Customer not found with id: " + customerId);
		}else {
			ConsumerResponse consumerResponse = new ConsumerResponse();
			consumerResponse.setConsumerId(customer.getCustomerId());
			consumerResponse.setConsumerName(customer.getCustomerName());
			consumerResponse.setEmail(customer.getEmail());
			consumerResponse.setAddress(customer.getAddress());
			consumerResponse.setMobile(customer.getMobile());
			return consumerResponse;
		}
		
			
        
      }

	@Override
	public Customer saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		 return customerRepository.save(customer);
	}
}

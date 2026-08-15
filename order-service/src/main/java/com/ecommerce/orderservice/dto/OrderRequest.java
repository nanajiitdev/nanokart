package com.ecommerce.orderservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public class OrderRequest {

    @NotNull
    private Long customerId;

    private List<OrderItemDto> items;
    
    
    

	public OrderRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderRequest(@NotNull Long customerId, List<OrderItemDto> items) {
		super();
		this.customerId = customerId;
		this.items = items;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<OrderItemDto> getItems() {
		return items;
	}

	public void setItems(List<OrderItemDto> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderRequest [customerId=" + customerId + ", items=" + items + "]";
	}
    
    
    
    
}
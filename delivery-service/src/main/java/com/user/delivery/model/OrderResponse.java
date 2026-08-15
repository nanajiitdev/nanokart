package com.user.delivery.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderResponse {

    private Long orderId;

    private Long customerId;

    private BigDecimal totalAmount;

    private String status;

    private LocalDateTime orderDate;

    public OrderResponse() {
    }

    public OrderResponse(Long orderId, Long customerId, BigDecimal totalAmount, String status, LocalDateTime orderDate) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.orderDate = orderDate;
	}

    public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
	
	@Override
	public String toString() {
		return "OrderResponse{" +
				"orderId=" + orderId +
				", customerId=" + customerId +
				", totalAmount=" + totalAmount +
				", status='" + status + '\'' +
				", orderDate=" + orderDate +
				'}';
		 
	 }
}
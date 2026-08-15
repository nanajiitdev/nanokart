package com.ecommerce.smsservice.model;

import java.io.Serializable;

public class DeliveryNotification implements Serializable {

    private Long orderId;
    private Long customerId;
    private String customerName;
    private String email;
    private String mobile;
    private String productName;
    private Integer quantity;
    private String deliveryPartner;
    private String deliveryStatus;
    private String trackingNumber;
    private String address;

    public DeliveryNotification() {
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryPartner() {
        return deliveryPartner;
    }

    public void setDeliveryPartner(String deliveryPartner) {
        this.deliveryPartner = deliveryPartner;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    

    public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	
	
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "DeliveryNotification [orderId=" + orderId + ", customerId=" + customerId + ", customerName="
				+ customerName + ", email=" + email + ", mobile=" + mobile + ", productName=" + productName
				+ ", quantity=" + quantity + ", deliveryPartner=" + deliveryPartner + ", deliveryStatus="
				+ deliveryStatus + ", trackingNumber=" + trackingNumber + ", address=" + address + "]";
	}

	
}
package com.user.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class DeliveryRequest {

    @NotNull(message = "Order Id is required")
    private Long orderId;

    @NotNull(message = "Product Id is required")
    private Long productId;

    @NotBlank(message = "Customer Name is required")
    private String customerName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Mobile is required")
    private String mobile;

    @NotBlank(message = "Address is required")
    private String address;

    public DeliveryRequest() {
    }

    public DeliveryRequest(
            Long orderId,
            Long productId,
            String customerName,
            String email,
            String mobile,
            String address) {

        this.orderId = orderId;
        this.productId = productId;
        this.customerName = customerName;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
package com.user.delivery.model;

public class CustomerDetails {

    private Long orderId;

    private String customerName;

    private String email;

    private String mobile;

    private String address;

    public CustomerDetails() {
    }

    public CustomerDetails(Long orderId,
                           String customerName,
                           String email,
                           String mobile,
                           String address) {
        this.orderId = orderId;
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

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
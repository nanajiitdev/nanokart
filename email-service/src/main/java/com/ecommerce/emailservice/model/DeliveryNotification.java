package com.ecommerce.emailservice.model;

public class DeliveryNotification {

    private Long orderId;

    private String customerName;

    private String email;

    private String mobile;

    private String address;

    private String deliveryPartner;

    private String deliveryStatus;

    public DeliveryNotification() {
    }

    public DeliveryNotification(Long orderId,
                                String customerName,
                                String email,
                                String mobile,
                                String address,
                                String deliveryPartner,
                                String deliveryStatus) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.deliveryPartner = deliveryPartner;
        this.deliveryStatus = deliveryStatus;
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

    @Override
    public String toString() {
        return "DeliveryNotification{" +
                "orderId=" + orderId +
                ", customerName='" + customerName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                ", deliveryPartner='" + deliveryPartner + '\'' +
                ", deliveryStatus='" + deliveryStatus + '\'' +
                '}';
    }
}
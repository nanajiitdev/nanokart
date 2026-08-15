package com.ecommerce.paymentservice.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentRequest {

    @NotNull(message = "Order Id is required")
    private Long orderId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "1.0", message = "Amount should be greater than zero")
    private BigDecimal amount;

    @NotBlank(message = "Payment Mode is required")
    private String paymentMode;

    public PaymentRequest() {
    }

    public PaymentRequest(
            Long orderId,
            BigDecimal amount,
            String paymentMode) {

        this.orderId = orderId;
        this.amount = amount;
        this.paymentMode = paymentMode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    @Override
    public String toString() {
        return "PaymentRequest [orderId=" + orderId +
                ", amount=" + amount +
                ", paymentMode=" + paymentMode + "]";
    }

}
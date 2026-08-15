package com.ecommerce.paymentservice.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {

    private Long paymentId;

    private Long orderId;

    private BigDecimal amount;

    private String paymentStatus;

    private String paymentMode;

    private String transactionId;

    private String correlationId;

    private LocalDateTime paymentDate;

    public PaymentResponse() {
    }

    public PaymentResponse(
            Long paymentId,
            Long orderId,
            BigDecimal amount,
            String paymentStatus,
            String paymentMode,
            String transactionId,
            String correlationId,
            LocalDateTime paymentDate) {

        this.paymentId = paymentId;
        this.orderId = orderId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paymentMode = paymentMode;
        this.transactionId = transactionId;
        this.correlationId = correlationId;
        this.paymentDate = paymentDate;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "PaymentResponse [paymentId=" + paymentId
                + ", orderId=" + orderId
                + ", amount=" + amount
                + ", paymentStatus=" + paymentStatus
                + ", paymentMode=" + paymentMode
                + ", transactionId=" + transactionId
                + ", correlationId=" + correlationId
                + ", paymentDate=" + paymentDate + "]";
    }
}
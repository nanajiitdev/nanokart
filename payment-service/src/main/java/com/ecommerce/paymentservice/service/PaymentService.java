package com.ecommerce.paymentservice.service;

import java.util.List;

import com.ecommerce.paymentservice.dto.PaymentRequest;
import com.ecommerce.paymentservice.dto.PaymentResponse;

public interface PaymentService {

    PaymentResponse processPayment(
            PaymentRequest request);

    PaymentResponse getPaymentById(
            Long paymentId);

    PaymentResponse getPaymentByOrderId(
            Long orderId);

    List<PaymentResponse> getAllPayments();

    void deletePayment(
            Long paymentId);

}
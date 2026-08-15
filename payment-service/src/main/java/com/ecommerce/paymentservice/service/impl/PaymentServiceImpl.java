package com.ecommerce.paymentservice.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.paymentservice.dto.PaymentRequest;
import com.ecommerce.paymentservice.dto.PaymentResponse;
import com.ecommerce.paymentservice.entity.Payment;
import com.ecommerce.paymentservice.event.PaymentCompletedEvent;
import com.ecommerce.paymentservice.kafka.KafkaProducer;
import com.ecommerce.paymentservice.repository.PaymentRepository;
import com.ecommerce.paymentservice.service.PaymentService;


@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final KafkaProducer kafkaProducer;

    public PaymentServiceImpl(
            PaymentRepository paymentRepository,
            KafkaProducer kafkaProducer) {

        this.paymentRepository = paymentRepository;
        this.kafkaProducer = kafkaProducer;
    }

    /*
     * Process Payment
     */
    @Override
    public PaymentResponse processPayment(
            PaymentRequest request) {

        Payment payment = new Payment();

        payment.setOrderId(request.getOrderId());

        payment.setAmount(request.getAmount());

        payment.setPaymentMode(request.getPaymentMode());

        payment.setPaymentStatus("SUCCESS");

        payment.setTransactionId(generateTransactionId());

        payment.setCorrelationId(generateCorrelationId());

        payment.setPaymentDate(LocalDateTime.now());

        Payment savedPayment =
                paymentRepository.save(payment);

        /*
         * Publish Kafka Event
         */
        PaymentCompletedEvent event =
                new PaymentCompletedEvent();

        event.setPaymentId(savedPayment.getPaymentId());

        event.setOrderId(savedPayment.getOrderId());

        event.setAmount(savedPayment.getAmount());

        event.setPaymentMode(savedPayment.getPaymentMode());

        event.setPaymentStatus(savedPayment.getPaymentStatus());

        event.setTransactionId(savedPayment.getTransactionId());

        event.setCorrelationId(savedPayment.getCorrelationId());

        event.setPaymentDate(savedPayment.getPaymentDate());

        kafkaProducer.publishPaymentCompleted(event);

        return mapToResponse(savedPayment);
    }

    /*
     * Get Payment By Payment Id
     */
    @Override
    public PaymentResponse getPaymentById(
            Long paymentId) {

        Payment payment =
                paymentRepository.findById(paymentId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment Not Found"));

        return mapToResponse(payment);
    }

    /*
     * Get Payment By Order Id
     */
    @Override
    public PaymentResponse getPaymentByOrderId(
            Long orderId) {

        Payment payment =
                paymentRepository.findByOrderId(orderId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Payment Not Found"));

        return mapToResponse(payment);
    }

    /*
     * Get All Payments
     */
    @Override
    public List<PaymentResponse> getAllPayments() {

        return paymentRepository.findAll()

                .stream()

                .map(this::mapToResponse)

                .collect(Collectors.toList());

    }

    /*
     * Delete Payment
     */
    @Override
    public void deletePayment(
            Long paymentId) {

        paymentRepository.deleteById(paymentId);

    }

    /*
     * Entity -> DTO
     */
    private PaymentResponse mapToResponse(
            Payment payment) {

        PaymentResponse response =
                new PaymentResponse();

        response.setPaymentId(payment.getPaymentId());

        response.setOrderId(payment.getOrderId());

        response.setAmount(payment.getAmount());

        response.setPaymentStatus(payment.getPaymentStatus());

        response.setPaymentMode(payment.getPaymentMode());

        response.setTransactionId(payment.getTransactionId());

        response.setCorrelationId(payment.getCorrelationId());

        response.setPaymentDate(payment.getPaymentDate());

        return response;

    }

    /*
     * Generate Transaction Id
     */
    private String generateTransactionId() {

        return "TXN-" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 12)
                        .toUpperCase();

    }

    /*
     * Generate Correlation Id
     */
    private String generateCorrelationId() {

        return UUID.randomUUID().toString();

    }

}
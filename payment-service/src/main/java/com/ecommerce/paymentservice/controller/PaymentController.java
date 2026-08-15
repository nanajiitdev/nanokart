package com.ecommerce.paymentservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.paymentservice.dto.PaymentRequest;
import com.ecommerce.paymentservice.dto.PaymentResponse;
import com.ecommerce.paymentservice.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/payments")
@Validated
public class PaymentController {

    private final PaymentService paymentService;
    
    private static final Logger log =
            LoggerFactory.getLogger(PaymentController.class);

    public PaymentController(
            PaymentService paymentService) {

        this.paymentService = paymentService;
    }

    /*
     * Process Payment
     */
    @PostMapping
    public ResponseEntity<PaymentResponse> processPayment(
            @Valid @RequestBody PaymentRequest request) {

        PaymentResponse response =
                paymentService.processPayment(request);
        
        log.info(
    		    "PAYMENT REQUEST orderId={} amount={}",
    		    request.getOrderId(),
    		    request.getAmount()
    		);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
        
    }

    /*
     * Get Payment By Payment Id
     */
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse> getPaymentById(
            @PathVariable Long paymentId) {

        return ResponseEntity.ok(

                paymentService.getPaymentById(paymentId)

        );

    }

    /*
     * Get Payment By Order Id
     */
    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse> getPaymentByOrderId(
            @PathVariable Long orderId) {

        return ResponseEntity.ok(

                paymentService.getPaymentByOrderId(orderId)

        );

    }

    /*
     * Get All Payments
     */
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments() {

        return ResponseEntity.ok(

                paymentService.getAllPayments()

        );

    }

    /*
     * Delete Payment
     */
    @DeleteMapping("/{paymentId}")
    public ResponseEntity<String> deletePayment(
            @PathVariable Long paymentId) {

        paymentService.deletePayment(paymentId);

        return ResponseEntity.ok(

                "Payment Deleted Successfully."

        );

    }

}
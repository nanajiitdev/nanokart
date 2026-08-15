package com.user.login.inventory.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


import com.user.login.inventory.event.PaymentCompletedEvent;
import com.user.login.inventory.service.InventoryService;

@Component
public class PaymentCompletedConsumer {

    private final InventoryService inventoryService;
    
    private static final Logger log =
            LoggerFactory.getLogger(PaymentCompletedConsumer.class);

    public PaymentCompletedConsumer(
            InventoryService inventoryService) {

        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = "payment-completed",
            groupId = "inventory-group")
    public void consume(PaymentCompletedEvent event) {

        System.out.println("==================================");

        System.out.println("Payment Event Received");

        log.info("Payment Event Received: orderId={}, paymentId={}, transactionId={}",
                event.getOrderId(),
                event.getPaymentId(),
                event.getTransactionId()
            );

        inventoryService.updateStock(event);

        System.out.println("==================================");
    }

}
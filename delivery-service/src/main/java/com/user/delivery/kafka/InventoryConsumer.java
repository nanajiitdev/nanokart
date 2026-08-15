package com.user.delivery.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.user.delivery.model.InventoryUpdatedEvent;
import com.user.delivery.service.DeliveryService;

@Service
public class InventoryConsumer {

    private final DeliveryService deliveryService;
    
    private static final Logger log =
            LoggerFactory.getLogger(InventoryConsumer.class);


    public InventoryConsumer(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @KafkaListener(
            topics = "inventory-updated",
            groupId = "delivery-group-v2")
    public void consume(InventoryUpdatedEvent event) {

        System.out.println("Inventory Event Received : " + event);
        log.info("Inventory Event Received: {}", event);

        deliveryService.processDelivery(event);

    }

}
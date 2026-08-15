package com.user.delivery.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.user.delivery.client.ConsumerClient;
import com.user.delivery.client.OrderClient;
import com.user.delivery.entity.Delivery;
import com.user.delivery.model.ConsumerResponse;
import com.user.delivery.model.DeliveryNotification;
import com.user.delivery.model.InventoryUpdatedEvent;
import com.user.delivery.model.OrderResponse;
import com.user.delivery.rabbitmq.RabbitProducer;
import com.user.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

//    private final OrderServiceClient orderClient;
//
//    private final UserServiceClient userClient;
	
	private final OrderClient orderClient;

	private final ConsumerClient consumerClient;
	
	

    private final RabbitProducer rabbitProducer;

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(
    		OrderClient orderClient,
    		ConsumerClient consumerClient,
            RabbitProducer rabbitProducer,
            DeliveryRepository deliveryRepository) {

        this.orderClient = orderClient;
        this.consumerClient = consumerClient;
        this.rabbitProducer = rabbitProducer;
        this.deliveryRepository = deliveryRepository;
    }

    public void processDelivery(
            InventoryUpdatedEvent event) {

        System.out.println("Inventory Status : "
                + event.getInventoryStatus());

        if (!"STOCK_UPDATED".equalsIgnoreCase(
                event.getInventoryStatus())) {

            System.out.println("Inventory Not Updated.");

            return;
        }

        /*
         * Get Order Details
         */
        OrderResponse order =
                orderClient.getOrder(
                        event.getOrderId());

        /*
         * Get Customer Details
         */
        ConsumerResponse customer =
        		consumerClient.getUser(
                        order.getCustomerId());

        /*
         * Save Delivery
         */
        Delivery delivery = new Delivery();

        delivery.setOrderId(order.getOrderId());

        delivery.setProductId(event.getProductId());

        delivery.setTrackingNumber(
                generateTrackingNumber());

        delivery.setDeliveryPartner(
                "Nanaji Ruttala");

        delivery.setDeliveryStatus(
                "PENDING");

        delivery.setCustomerName(
                customer.getConsumerName());

        delivery.setEmail(
                customer.getEmail());

        delivery.setMobile(
                customer.getMobile());

        delivery.setAddress(
                customer.getAddress());

        delivery.setEstimatedDelivery(
                LocalDateTime.now().plusDays(5));

        deliveryRepository.save(delivery);

        /*
         * RabbitMQ Notification
         */
        DeliveryNotification notification =
                new DeliveryNotification();

        notification.setOrderId(
                order.getOrderId());

        notification.setCustomerName(
                customer.getConsumerName());

        notification.setEmail(
                customer.getEmail());

        notification.setMobile(
                customer.getMobile());

        notification.setAddress(
                customer.getAddress());

        notification.setDeliveryPartner(
                delivery.getDeliveryPartner());

        notification.setDeliveryStatus(
                delivery.getDeliveryStatus());

        notification.setTrackingNumber(
                delivery.getTrackingNumber());

        rabbitProducer.sendNotification(
                notification);

        System.out.println(
                "Delivery Created Successfully");

    }

    /*
     * Generate Tracking Number
     */
    private String generateTrackingNumber() {

        return "TRK-"
                + UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 10)
                        .toUpperCase();

    }

}
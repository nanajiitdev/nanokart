package com.ecommerce.smsservice.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.smsservice.config.RabbitMQConfig;
import com.ecommerce.smsservice.model.DeliveryNotification;

@RestController
public class TestController {

    private final RabbitTemplate rabbitTemplate;

    public TestController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/test")
    public String sendMessage() {

        DeliveryNotification notification = new DeliveryNotification();

        notification.setOrderId(102L);
        notification.setCustomerId(1L);
        notification.setCustomerName("Nanaji");
        notification.setEmail("nanaji90@gmail.com");
        notification.setMobile("8520004734");
        notification.setProductName("Laptop");
        notification.setQuantity(1);
        notification.setDeliveryPartner("Ravi Kumar");
        notification.setDeliveryStatus("ASSIGNED");

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                notification);

        return "Message Published Successfully";
    }
}
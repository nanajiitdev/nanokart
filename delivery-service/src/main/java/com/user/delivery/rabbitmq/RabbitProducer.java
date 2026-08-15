package com.user.delivery.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.user.delivery.config.RabbitMQConfig;
import com.user.delivery.model.DeliveryNotification;

@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(DeliveryNotification notification) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                notification);

        System.out.println("RabbitMQ Message Sent : " + notification);

    }

}
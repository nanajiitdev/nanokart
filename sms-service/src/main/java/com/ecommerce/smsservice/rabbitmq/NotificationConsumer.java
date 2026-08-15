package com.ecommerce.smsservice.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.ecommerce.smsservice.config.RabbitMQConfig;
import com.ecommerce.smsservice.model.DeliveryNotification;
import com.ecommerce.smsservice.service.SmsService;

import lombok.extern.java.Log;



@Component
public class NotificationConsumer {

    private final SmsService smsService;
    
    private static final Logger log =
            LoggerFactory.getLogger(NotificationConsumer.class);

    public NotificationConsumer(
            SmsService smsService) {

        this.smsService = smsService;

    }

    @RabbitListener(
            queues = RabbitMQConfig.QUEUE,containerFactory = "rabbitListenerContainerFactory")
    public void consume(
            DeliveryNotification notification) {

        try {

            System.out.println("==================================");
            System.out.println("SMS Notification Received");
            log.info("SMS Notification Received");
            System.out.println(notification);

            smsService.sendSms(notification);

            System.out.println("SMS Sent Successfully");
            System.out.println("==================================");

        } catch (Exception ex) {

            System.out.println("SMS Sending Failed");

            ex.printStackTrace();

        }

    }

}
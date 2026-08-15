package com.ecommerce.emailservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ecommerce.emailservice.model.DeliveryNotification;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(DeliveryNotification notification) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(notification.getEmail());

        message.setSubject("Order Delivery Update");

        message.setText(
                "Hello " + notification.getCustomerName() + ",\n\n" +
                "Your order has been assigned successfully.\n\n" +
                "Order Id        : " + notification.getOrderId() + "\n" +
                "Delivery Partner: " + notification.getDeliveryPartner() + "\n" +
                "Status          : " + notification.getDeliveryStatus() + "\n\n" +
                "Thank you for shopping with us.\n\n" +
                "E-Commerce Team"
        );

        mailSender.send(message);

        System.out.println("====================================");
        System.out.println("EMAIL SENT SUCCESSFULLY");
        System.out.println("To      : " + notification.getEmail());
        System.out.println("OrderId : " + notification.getOrderId());
        System.out.println("====================================");
    }
}
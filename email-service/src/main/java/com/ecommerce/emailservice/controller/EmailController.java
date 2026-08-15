package com.ecommerce.emailservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.emailservice.model.DeliveryNotification;
import com.ecommerce.emailservice.service.EmailService;

@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/test-email")
    public String sendEmail() {

        DeliveryNotification notification = new DeliveryNotification();

        notification.setCustomerName("Nanaji");
        notification.setEmail("nanaji.itdev@gmail.com");   // your email
        notification.setOrderId(101L);
        notification.setDeliveryPartner("Ravi Kumar");
        notification.setDeliveryStatus("ASSIGNED");

        emailService.sendEmail(notification);

        return "Email Sent Successfully";
    }

}
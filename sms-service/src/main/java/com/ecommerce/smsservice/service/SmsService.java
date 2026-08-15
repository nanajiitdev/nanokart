package com.ecommerce.smsservice.service;

import org.springframework.stereotype.Service;

import com.ecommerce.smsservice.model.DeliveryNotification;

@Service
public class SmsService {

    public void sendSms(DeliveryNotification notification) {

        try {

            System.out.println("\n==========================================");
            System.out.println("SMS SERVICE");
            System.out.println("==========================================");

            System.out.println("Order ID         : " + notification.getOrderId());

            System.out.println("Customer         : " + notification.getCustomerName());

            System.out.println("Mobile Number    : " + notification.getMobile());

            System.out.println("Tracking Number  : " + notification.getTrackingNumber());

            System.out.println("Delivery Partner : " + notification.getDeliveryPartner());

            System.out.println("Delivery Status  : " + notification.getDeliveryStatus());

            System.out.println("Delivery Address : " + notification.getAddress());

            System.out.println();

            System.out.println("SMS SENT SUCCESSFULLY");

            System.out.println("==========================================\n");

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
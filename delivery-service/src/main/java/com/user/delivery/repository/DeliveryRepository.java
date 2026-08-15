package com.user.delivery.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.delivery.entity.Delivery;

@Repository
public interface DeliveryRepository
        extends JpaRepository<Delivery, Long> {

    /*
     * Find All Deliveries By Order Id
     */
    List<Delivery> findByOrderId(Long orderId);

    /*
     * Find Delivery By Tracking Number
     */
    Optional<Delivery> findByTrackingNumber(
            String trackingNumber);

    /*
     * Find Deliveries By Status
     */
    List<Delivery> findByDeliveryStatus(
            String deliveryStatus);

}
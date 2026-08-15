package com.user.login.inventory.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.user.login.inventory.client.OrderClient;
import com.user.login.inventory.dto.InventoryRequest;
import com.user.login.inventory.dto.InventoryResponse;
import com.user.login.inventory.dto.OrderDetailsResponse;
import com.user.login.inventory.dto.OrderItemResponse;
import com.user.login.inventory.entity.Inventory;
import com.user.login.inventory.event.InventoryUpdatedEvent;
import com.user.login.inventory.event.PaymentCompletedEvent;
import com.user.login.inventory.exception.ResourceNotFoundException;
import com.user.login.inventory.kafka.InventoryKafkaProducer;
import com.user.login.inventory.repository.InventoryRepository;
import com.user.login.inventory.service.InventoryService;

import jakarta.transaction.Transactional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final OrderClient orderClient;

    private final InventoryKafkaProducer inventoryKafkaProducer;

    public InventoryServiceImpl(
            InventoryRepository inventoryRepository,
            OrderClient orderClient,
            InventoryKafkaProducer inventoryKafkaProducer) {

        this.inventoryRepository = inventoryRepository;
        this.orderClient = orderClient;
        this.inventoryKafkaProducer = inventoryKafkaProducer;
    }

    @Override
    public InventoryResponse saveProduct(InventoryRequest request) {

        Inventory inventory = new Inventory();

        inventory.setProductId(request.getProductId());
//        inventory.setProductName(request.getProductName());
        inventory.setAvailableQuantity(request.getAvailableQuantity());
//        inventory.setPrice(request.getPrice());

        Inventory saved = inventoryRepository.save(inventory);

        return mapToResponse(saved);
    }

    @Override
    public List<InventoryResponse> getAllProducts() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public InventoryResponse getProductById(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found : " + id));

        return mapToResponse(inventory);
    }

    @Override
    public InventoryResponse getProductByProductId(Long productId) {

        Inventory inventory = inventoryRepository.findByProductId(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found : " + productId));

        return mapToResponse(inventory);
    }

    @Override
    public InventoryResponse updateProduct(
            Long id,
            InventoryRequest request) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found : " + id));

        inventory.setProductId(request.getProductId());
//        inventory.setProductName(request.getProductName());
        inventory.setAvailableQuantity(request.getAvailableQuantity());
//        inventory.setPrice(request.getPrice());

        Inventory updated = inventoryRepository.save(inventory);

        return mapToResponse(updated);
    }

    @Override
    public void deleteProduct(Long id) {

        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Inventory not found : " + id));

        inventoryRepository.delete(inventory);
    }

    private InventoryResponse mapToResponse(
            Inventory inventory) {

        return new InventoryResponse(

                inventory.getId(),

                inventory.getProductId(),

//                inventory.getProductName(),

                inventory.getAvailableQuantity(),

//                inventory.getPrice(),

                inventory.getCreatedDate()

        );

    }

    /*
     * Kafka Consumer calls this method
     */
    @Override
    @Transactional
    public void updateStock(
            PaymentCompletedEvent paymentEvent) {

        System.out.println("======================================");
        System.out.println("Updating Inventory");
        System.out.println("Order Id : "
                + paymentEvent.getOrderId());

        /*
         * Fetch Order Details
         */
        OrderDetailsResponse order =
                orderClient.getOrderDetails(
                        paymentEvent.getOrderId());

        /*
         * Update Inventory For Every Product
         */
        for (OrderItemResponse item : order.getItems()) {

            Inventory inventory =
                    inventoryRepository.findByProductId(
                                    item.getProductId())
                            .orElseThrow(() ->
                                    new ResourceNotFoundException(
                                            "Product Not Found : "
                                                    + item.getProductId()));

            if (inventory.getAvailableQuantity()
                    < item.getQuantity()) {

                throw new RuntimeException(

                        "Insufficient Stock For Product : "

                                + item.getProductId());

            }

            inventory.setAvailableQuantity(

                    inventory.getAvailableQuantity()

                            - item.getQuantity());

            inventoryRepository.save(inventory);

            /*
             * Publish Inventory Updated Event
             */
            InventoryUpdatedEvent event =
                    new InventoryUpdatedEvent();

            event.setOrderId(order.getOrderId());

            event.setProductId(item.getProductId());

            event.setOrderedQuantity(item.getQuantity());

            event.setAvailableQuantity(
                    inventory.getAvailableQuantity());

            event.setInventoryStatus("STOCK_UPDATED");

            event.setUpdatedTime(LocalDateTime.now());

            inventoryKafkaProducer.publishInventoryUpdated(
                    event);

            System.out.println(
                    "Inventory Updated : "
                            + item.getProductId());

        }

        System.out.println("Inventory Update Completed");

        System.out.println("======================================");

    }

}
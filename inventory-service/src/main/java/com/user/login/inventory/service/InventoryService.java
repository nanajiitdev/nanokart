package com.user.login.inventory.service;

import java.util.List;

import com.user.login.inventory.dto.InventoryRequest;
import com.user.login.inventory.dto.InventoryResponse;
import com.user.login.inventory.event.PaymentCompletedEvent;

public interface InventoryService {

    // Create Product
    InventoryResponse saveProduct(InventoryRequest request);

    // Get All Products
    List<InventoryResponse> getAllProducts();

    // Get Product By Id
    InventoryResponse getProductById(Long id);

    // Get Product By Product Id
    InventoryResponse getProductByProductId(Long productId);

    // Update Product
    InventoryResponse updateProduct(Long id, InventoryRequest request);

    void  updateStock(PaymentCompletedEvent paymentEvent);
    
    // Delete Product
    void deleteProduct(Long id);

}
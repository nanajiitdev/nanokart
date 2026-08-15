package com.user.login.inventory.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.user.login.inventory.dto.InventoryRequest;
import com.user.login.inventory.dto.InventoryResponse;
import com.user.login.inventory.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
@Validated
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Create Product
     */
    @PostMapping
    public ResponseEntity<InventoryResponse> saveProduct(
            @Valid @RequestBody InventoryRequest request) {

        InventoryResponse response = inventoryService.saveProduct(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Get All Products
     */
    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllProducts() {

        return ResponseEntity.ok(inventoryService.getAllProducts());
    }

    /**
     * Get Product By Database Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(inventoryService.getProductById(id));
    }

    /**
     * Get Product By Product Id
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryResponse> getProductByProductId(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryService.getProductByProductId(productId));
    }

    /**
     * Update Product
     */
    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequest request) {

        return ResponseEntity.ok(
                inventoryService.updateProduct(id, request));
    }

    /**
     * Delete Product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        inventoryService.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully.");
    }

}
package com.user.login.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.login.client.InventoryServiceClient;
import com.user.login.dto.InventoryResponse;
import com.user.login.dto.ProductRequest;
import com.user.login.dto.ProductResponse;
import com.user.login.service.ProductService;

@RestController
@RequestMapping("/api/products")
//@CrossOrigin(origins = {"http://localhost:5173","http://localhost:3000"})
@EnableMethodSecurity
public class ProductController {

    private final ProductService service;
    private final InventoryServiceClient inventoryServiceClient;

    public ProductController(ProductService service,InventoryServiceClient inventoryServiceClient) {
        this.service = service;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    /*
     * Add Product
     */
    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(
            @RequestBody ProductRequest request) {
    	System.out.println("Received request to save product: " + request.getProductName());

        ProductResponse response = service.saveProduct(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    /*
     * Get All Products
     */
    @GetMapping
//    @PreAuthorize("hasRole('USER')")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {

        return ResponseEntity.ok(service.getAllProducts());

    }

    /*
     * Get Product By Id
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getProductById(id));

    }

    /*
     * Update Product
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request) {

        return ResponseEntity.ok(
                service.updateProduct(id, request));

    }

    /*
     * Delete Product
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Long id) {

        service.deleteProduct(id);

        return ResponseEntity.ok("Product deleted successfully");

    }
    
    /*
     * Search Products
     */
    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> searchProducts(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                service.searchProducts(keyword));

    }

    /*
     * Products By Category
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<ProductResponse>> getProductsByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                service.getProductsByCategory(category));

    }
    
    @GetMapping("/suggest")
    public ResponseEntity<List<ProductResponse>> suggestProducts(
            @RequestParam String keyword) {

        return ResponseEntity.ok(
                service.searchProducts(keyword)
        );

    }
    
    @GetMapping("/{productId}/quantity")
    public ResponseEntity<InventoryResponse> getProductQuantity(
            @PathVariable Long productId) {

        return ResponseEntity.ok(
                inventoryServiceClient
                        .getInventoryByProductId(productId));
    }
    

}

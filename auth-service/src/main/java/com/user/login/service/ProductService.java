package com.user.login.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.user.login.client.InventoryServiceClient;
import com.user.login.dto.InventoryResponse;
import com.user.login.dto.ProductRequest;
import com.user.login.dto.ProductResponse;
import com.user.login.entity.Product;
import com.user.login.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;
    
    private final InventoryServiceClient inventoryServiceClient;

    public ProductService(ProductRepository repository,InventoryServiceClient inventoryServiceClient) {
        this.repository = repository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    /*
     * Add Product
     */
    public ProductResponse saveProduct(ProductRequest request) {

        Product product = new Product();

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        Product savedProduct = repository.save(product);

        return mapToResponse(savedProduct);
    }

    /*
     * Get All Products
     */
    public List<ProductResponse> getAllProducts() {

        // 1. Get all products from Product table
        List<Product> products = repository.findAll();

        // 2. Get all inventory in ONE call
        List<InventoryResponse> inventories =
                inventoryServiceClient.getAllInventory();

        // 3. Convert inventory list to Map
        java.util.Map<Long, Integer> quantityMap =
                inventories.stream()
                        .collect(Collectors.toMap(
                                InventoryResponse::getProductId,
                                InventoryResponse::getAvailableQuantity
                        ));

        // 4. Combine Product + Inventory
        return products.stream()
                .map(product -> {

                    Integer quantity =
                            quantityMap.getOrDefault(
                                    product.getId(),
                                    0
                            );
                    String productImage =
                            "/products/" + product.getId() + ".jpg";

                    return new ProductResponse(

                            product.getId(),

                            product.getProductName(),

                            product.getCategory(),

                            product.getPrice(),

                            quantity,

                            product.getDescription(),
                            productImage
                    );

                })
                .collect(Collectors.toList());
    }

    /*
     * Get Product By Id
     */
    public ProductResponse getProductById(Long id) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));

        return mapToResponse(product);

    }

    /*
     * Update Product
     */
    public ProductResponse updateProduct(Long id,
                                         ProductRequest request) {

        Product product = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product Not Found"));

        product.setProductName(request.getProductName());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());

        Product updated = repository.save(product);

        return mapToResponse(updated);

    }

    /*
     * Delete Product
     */
    public void deleteProduct(Long id) {

        repository.deleteById(id);

    }

    /*
     * Common Mapping Method
     */
    /*
     * Common Mapping Method
     * Product + Inventory
     */
    private ProductResponse mapToResponse(Product product) {
    	
    	 String productImage =
    	            "/products/" + product.getId() + ".jpg";

        InventoryResponse inventory =
                inventoryServiceClient.getInventoryByProductId(
                        product.getId()
                );

        Integer quantity = 0;

        if (inventory != null) {
            quantity = inventory.getAvailableQuantity();
        }

        return new ProductResponse(

                product.getId(),

                product.getProductName(),

                product.getCategory(),

                product.getPrice(),

                quantity,

                product.getDescription(),
                productImage
        );
    }
    
    /*
     * Search Products
     */
    public List<ProductResponse> searchProducts(String keyword) {

        return repository
                .findByProductNameContainingIgnoreCaseOrCategoryContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword,
                        keyword,
                        keyword)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }

    /*
     * Get Products By Category
     */
    public List<ProductResponse> getProductsByCategory(String category) {

        return repository
                .findByCategoryIgnoreCase(category)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());

    }
    
    
    }

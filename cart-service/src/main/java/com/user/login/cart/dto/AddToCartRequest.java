package com.user.login.cart.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddToCartRequest {

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Product Id is required")
    private Long productId;

    @NotBlank(message = "Product Name is required")
    private String productName;

    private String productImage;

    @NotNull(message = "Unit Price is required")
    @Min(value = 1, message = "Unit Price should be greater than zero")
    private Double unitPrice;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity should be at least 1")
    private Integer quantity;

    public AddToCartRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
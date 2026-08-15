package com.user.login.inventory.dto;

import java.time.LocalDateTime;

public class InventoryResponse {

    private Long id;

    private Long productId;

//    private String productName;

    private Integer availableQuantity;

//    private BigDecimal price;

    private LocalDateTime createdDate;

    public InventoryResponse() {
    }

    public InventoryResponse(Long id, Long productId,
//    						 String productName,
                             Integer availableQuantity,
//                             BigDecimal price,
                             LocalDateTime createdDate) {
        this.id = id;
        this.productId = productId;
//        this.productName = productName;
        this.availableQuantity = availableQuantity;
//        this.price = price;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

	/*
	 * public String getProductName() { return productName; }
	 * 
	 * public void setProductName(String productName) { this.productName =
	 * productName; }
	 */

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

	/*
	 * public BigDecimal getPrice() { return price; }
	 * 
	 * public void setPrice(BigDecimal price) { this.price = price; }
	 */

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
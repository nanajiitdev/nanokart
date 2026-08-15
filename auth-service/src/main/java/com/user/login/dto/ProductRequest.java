package com.user.login.dto;

public class ProductRequest {
	
	private String productName;
    private String category;
    private Double price;
    private Integer quantity;
    private String description;

    public ProductRequest() {
    }

    public ProductRequest(String productName, String category,
                          Double price, Integer quantity,
                          String description) {
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

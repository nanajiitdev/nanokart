package com.user.login.inventory.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false, unique = true)
    private Long productId;

//    @Column(name = "product_name", nullable = false)
//    private String productName;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

	/*
	 * @Column(nullable = false) private BigDecimal price;
	 */

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    public Inventory() {
    }

    @PrePersist
    public void prePersist() {
        this.createdDate = LocalDateTime.now();
    }

    public Inventory(Long id, Long productId,
//    		         String productName,
                     Integer availableQuantity, 
//                     BigDecimal price,
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
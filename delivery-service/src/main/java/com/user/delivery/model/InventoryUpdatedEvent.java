package com.user.delivery.model;

import java.time.LocalDateTime;

public class InventoryUpdatedEvent {

    private Long orderId;

    private Long productId;

    private Integer orderedQuantity;

    private Integer availableQuantity;

    private String inventoryStatus;

    private LocalDateTime updatedTime;

    public InventoryUpdatedEvent() {
    }

    public InventoryUpdatedEvent(Long orderId,
                                 Long productId,
                                 Integer orderedQuantity,
                                 Integer availableQuantity,
                                 String inventoryStatus,
                                 LocalDateTime updatedTime) {
        this.orderId = orderId;
        this.productId = productId;
        this.orderedQuantity = orderedQuantity;
        this.availableQuantity = availableQuantity;
        this.inventoryStatus = inventoryStatus;
        this.updatedTime = updatedTime;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "InventoryUpdatedEvent{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", orderedQuantity=" + orderedQuantity +
                ", availableQuantity=" + availableQuantity +
                ", inventoryStatus='" + inventoryStatus + '\'' +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
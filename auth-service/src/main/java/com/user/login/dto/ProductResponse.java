package com.user.login.dto;

public class ProductResponse {
	
	 private Long id;
	    private String productName;
	    private String category;
	    private Double price;
	    private Integer quantity;
	    private String description;
	    private String productImage;

	    public ProductResponse() {
	    }

	    public ProductResponse(Long id, String productName,
	                           String category, Double price,
	                           Integer quantity, String description,String productImage) {
	        this.id = id;
	        this.productName = productName;
	        this.category = category;
	        this.price = price;
	        this.quantity = quantity;
	        this.description = description;
	        this.productImage = productImage;
	    }

	    public Long getId() {
	        return id;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public Double getPrice() {
	        return price;
	    }

	    public Integer getQuantity() {
	        return quantity;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public void setProductName(String productName) {
	        this.productName = productName;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }

	    public void setPrice(Double price) {
	        this.price = price;
	    }

	    public void setQuantity(Integer quantity) {
	        this.quantity = quantity;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

		public String getProductImage() {
			return productImage;
		}

		public void setProductImage(String productImage) {
			this.productImage = productImage;
		}
	    

}

package com.ecommerce.orderservice.dto;

public class ConsumerResponse {

	private Long consumerId;

    private String consumerName;

    private String email;

    private String mobile;

    private String address;
    
    public ConsumerResponse() {
	}
    
    public ConsumerResponse(Long consumerId,
							String consumerName,
							String email,
							String mobile,
							String address) {
		this.consumerId = consumerId;
		this.consumerName = consumerName;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
	}

	public Long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Long consumerId) {
		this.consumerId = consumerId;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "ConsumerResponse [consumerId=" + consumerId + ", consumerName=" + consumerName + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + "]";
	}

}

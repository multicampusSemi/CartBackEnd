package com.project.model;

import lombok.Data;

@Data
public class OrderItem {
	private String productName;
    private int quantity;
    private int productPrice;
    private int bookingId;
    private int totalAmount;
    private int shippingFee;
    private int productId;
    private int userId;
    private String status;
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public int getshippingFee() {
    	return shippingFee;
    }
    
    public void setshippingFee() {
    	this.shippingFee = shippingFee;
    }
    
    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;  // 값 설정
    }
}

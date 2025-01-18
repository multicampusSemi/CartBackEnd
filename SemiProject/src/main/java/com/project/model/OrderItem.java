package com.project.model;

import lombok.Data;

@Data
public class OrderItem {
	private String productName;
    private int quantity;
    private int productPrice;
    private int totalAmount;
    private int shippingFee;
    private int productId;
    
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
}

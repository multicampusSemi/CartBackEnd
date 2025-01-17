package com.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.mapper.LjmSemiMapper;
import com.project.model.Orders;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final LjmSemiMapper orderMapper;
	
	public void saveOrder(int userId, List<Orders> selectedProducts) {
		 selectedProducts.forEach(product -> {
		        int quantity = product.getQuantity();
		        int productPrice = product.getProductPrice();
		        int totalAmount = productPrice * quantity;  // 직접 계산
		        int shippingFee = product.getshippingFee();  // ShippingFee 가져오기

		        product.setTotalAmount(totalAmount);  // 모델에 설정
		        orderMapper.insertOrder(userId, quantity, totalAmount, shippingFee);  // 매퍼 호출
	    });
	}
	 public List<Orders> getOrders() {
	        return orderMapper.getAllOrders();  // 예시로 getAllOrders 메서드 추가 필요
	    }

	    public int calculateTotalPrice(List<Orders> orders) {
	        int totalPrice = 0;
	        for (Orders order : orders) {
	            totalPrice += order.getProductPrice() * order.getQuantity();
	        }
	        return totalPrice;
	    }
}




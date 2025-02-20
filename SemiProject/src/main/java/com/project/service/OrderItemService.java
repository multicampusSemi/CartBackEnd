package com.project.service;

import java.util.HashMap;
import java.util.List; 
import java.util.Map;

import org.springframework.stereotype.Service;

import com.project.mapper.LjmSemiMapper;
import com.project.model.OrderItem;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {
	private final LjmSemiMapper orderMapper;
	
	public void saveOrder(int userId, List<OrderItem> selectedProducts) {
		int totalPrice = 0;
        int bookingId = orderMapper.getBookingIdByUserId(userId);
        // 선택된 각 제품에 대해 총 금액 계산
        for (OrderItem product : selectedProducts) {
            int quantity = product.getQuantity();
            int productPrice = product.getProductPrice();
            int totalAmount = productPrice * quantity;  // 제품의 총 금액 계산
            product.setTotalAmount(totalAmount);  // 제품 모델에 총 금액 설정
            totalPrice += totalAmount;  // 전체 총 금액에 추가
        }
        
        String status = "PENDING"; // 기본 상태를 'PENDING'으로 설정
        
        Map<String, Object> params = new HashMap<>();
        params.put("bookingId", bookingId);
        params.put("userId", userId);
        params.put("totalPrice", totalPrice);
        params.put("status", status);

        orderMapper.insertOrderTotal(params);

        selectedProducts.forEach(product -> {
            product.setUserId(userId);
            product.setBookingId(bookingId); // bookingId를 사용
            orderMapper.insertOrder(bookingId, product.getProductId(), product.getQuantity());  // 매퍼 호출
        });
    }
	
	
	
	 public List<OrderItem> getOrders(int userId) {
		 List<OrderItem> orderItems = orderMapper.getAllOrders(userId);
		 return orderItems;
	    }

	    
	 
	 public int calculateTotalPrice(List<OrderItem> orders) {
	        int totalPrice = 0;
	        for (OrderItem order : orders) {
	            totalPrice += order.getProductPrice() * order.getQuantity();
	        }
	        return totalPrice;
	    }
}




package com.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.model.BookingList;
import com.project.model.OrderItem;


@Mapper
public interface LjmSemiMapper {
	int deleteProductsFromCart(@Param("userId") int userId, @Param("productIds") List<Integer> productIds);

    List<BookingList> showbookingitem(int userId);

    int getDefaultUserId();
	// 개별 주문 항목을 저장
    void insertOrder(@Param("bookingId") int bookingId, 
            @Param("productId") int productId, 
            @Param("quantity") int quantity);

// 전체 주문 정보 삽입
void insertOrderTotal(@Param("bookingId") int bookingId, 
                 @Param("totalPrice") int totalPrice, 
                 @Param("status") String status,
                 @Param("userId") int userId);
	   List<OrderItem> getAllOrders(int userId);
	   
	   void insertOrderTotal(Map<String, Object> params);
	   int getBookingIdByUserId(int userId);
	   List<OrderItem> getOrdersByUserId(int userId);  // 사용자 ID로 주문 조회
}

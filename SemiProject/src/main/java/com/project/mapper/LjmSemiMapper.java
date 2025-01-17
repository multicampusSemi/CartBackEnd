package com.project.mapper;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.project.model.BookingList;
import com.project.model.Orders;
import com.project.model.Products;

@Mapper
public interface LjmSemiMapper {
	    List<Products> showproduct();
	    int deleteProductsFromCart(@Param("userId") int userId, @Param("productIds") List<Integer> productIds);
	    List<BookingList> showbookingitem(int userId);
	    int getDefaultUserId();
	    void insertOrder(@Param("userId") int userId, 
                @Param("quantity") int quantity,
	    		@Param("totalAmount") int totalAmount, // totalAmount 추가
	    		@Param("shippingFee") int shippingFee);
	    List<Orders> getAllOrders();
}

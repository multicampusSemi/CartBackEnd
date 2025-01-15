package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.model.Cart;
import com.project.model.Products;

@Mapper
public interface LjmSemiMapper {
	  void addcart(Cart cart);  // XML에서 SQL 정의
	    List<Cart> findcart(int userId);  // XML에서 SQL 정의
	    void updatecart(Cart cart);  // XML에서 SQL 정의
	    List<Products> getAllProducts();
}

package com.project.controller;

import java.util.List; 

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.model.Cart;
import com.project.model.Products;
import com.project.service.CartService;
import com.project.service.ProductsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {
	private final CartService cartService;
	private final ProductsService productsService;
	
	
	@GetMapping("/cart/add")
	public String addProductCart(@RequestParam("userId") int userId, 
							@RequestParam("productId") int productId, 
							@RequestParam("productDescription") String productDescription,
							@RequestParam("shippingFee") double shippingFee, 
							@RequestParam("productPrice") double productPrice, 
							Model model) {
        // 장바구니에 상품 추가
        cartService.addProductCart(userId, productId, 
        						productDescription, shippingFee, productPrice);
		return "redirect:/booking";
	}
	
	@GetMapping("/booking")
	public String booking(@RequestParam("userId") int userId,  Model model) {
		List<Cart> cartItems = cartService.findAll(userId);
		model.addAttribute("cartItems",cartItems);
		return "booking";
	}
	
	@GetMapping("/product")
	public String getProducts(Model model) {
		List<Products> products = productsService.getAllProducts();
        model.addAttribute("products", products);
        return "product";  // product.jsp 페이지로 이동
	}
}

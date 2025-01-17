package com.project.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.BookingList;
import com.project.model.Orders;
import com.project.model.Products;
import com.project.service.CartService;
import com.project.service.OrderService;
import com.project.service.ProductsService;
import com.project.service.UsersService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {
	private final ProductsService productsService;
	private final CartService cartService;	
	private final UsersService usersService;
	private final OrderService orderService;
	
	@GetMapping("/product")
	public String getProducts(Model model) {
		List<Products> products = productsService.showproduct();
    	System.out.print(products);
        model.addAttribute("products", products);
        return "product";  // product.jsp 페이지로 이동
	}

	@GetMapping("/booking")
	public String getBookingItem(Model model, HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		 if (userId == null) {
		        userId = usersService.getDefaultUserId();; // 기본 사용자 ID 설정
		        session.setAttribute("userId", userId);
		    }
		 
		List<BookingList> bookingItems = cartService.showbookingitem(userId);
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        String cartItemsJson = objectMapper.writeValueAsString(bookingItems);
	        model.addAttribute("cartItemsJson", cartItemsJson); // JSON 문자열로 전달
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
//		model.addAttribute("cartItems", bookingItems);
		return "booking";
	}
	
	 @PostMapping("/cart/delete")
	    public String deleteSelected(@RequestParam String productIds, HttpSession session) {
		 Integer userId = (Integer) session.getAttribute("userId");
		 if(userId == null) {
			 return "redirect:/login";
		 }
		 List<Integer> productIdList = Arrays.stream(productIds.split(","))
			        .map(Integer::parseInt) // 문자열을 Integer로 변환
			        .collect(Collectors.toList());


			    // 삭제하려는 상품이 있다면
			    if (!productIdList.isEmpty()) {
			        cartService.removeProductsFromCart(userId, productIdList);
			    } else {
			        System.out.println("삭제할 상품이 없습니다.");
			    }
		 return "redirect:/booking";
	    }
	 
	 @PostMapping("/order/create")
	 public String createOrder(@RequestBody List<Orders> selectedProducts, HttpSession session) {
		 Integer userId = (Integer) session.getAttribute("userId");
		 if(userId == null) {
			 return "redirect:/login";
		 }
		 
		 orderService.saveOrder(userId, selectedProducts);
		 return "redirect:/order";
	 }
	 
	 @GetMapping("/order")
	 public String showOrderPage(Model model) {
		 List<Orders> orders = orderService.getOrders();

		    try {
		        ObjectMapper objectMapper = new ObjectMapper();
		        String orderItemsJson = objectMapper.writeValueAsString(orders);  // orders를 JSON으로 변환
		        model.addAttribute("orderItemsJson", orderItemsJson);  // JSON 문자열을 모델에 추가
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    model.addAttribute("orderItems", orders);  // 기존대로, JSON 이외의 값도 전달
		    return "order";
	 }
}


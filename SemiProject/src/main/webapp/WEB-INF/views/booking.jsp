<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>장바구니</title>
    <link rel="stylesheet" href="static/ljmcss/booking.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="static/ljmjs/booking.js"></script>
</head>
<body>
    <div class="booking">
        <h2>Cart</h2>
        <br/>
        <table class="booking-table">
            <tr>
                <th><input type="checkbox" id="all-check" onclick="allCheck()"/></th>
                <th>상품명</th>
                <th>상세설명</th>
                <th>배송비</th>
                <th>수량</th>
                <th>가격</th>
            </tr>
            
            <c:forEach var="item" items="${cartItems}">
                <tr>
                    <td><input type="checkbox" name="cartItem" value="${item.id}"></td>
                    <td>${item.productName}</td>
                    <td>${item.productDescription}</td>
                    <td>${item.shippingFee}</td>
                    <td><input type="number" name="quantity" value="${item.quantity}" min="1"></td>
                    <td>${item.price}</td>
                </tr>
            </c:forEach>
        </table>
        
        <div class="allprice" colspan="6">
            배송비 ${totalShippingFee} + 가격 ${totalPrice} = ${totalPrice + totalShippingFee}원
        </div>
        
        <br/>
        <br/>
        <hr/>
        
        <button class="orderbtn" id="order" type="submit" onclick="order()">주문하러가기</button>
        <button class="orderbtn" id="delete" onclick="deleteSelected()">선택 삭제</button>
    </div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="static/ljmcss/order.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="static/ljmjs/order.js"></script>
</head>

<body>
    <h2>Order</h2>
    <!-- 주문 목록을 표시할 div -->
    <div id="orderlist">
        <!-- JSTL 반복문을 사용하여 orderItems를 동적으로 출력 -->
        <c:forEach var="item" items="${orderItems}">
            <div class="order-item">
                <p>상품명: ${item.productName}</p>
                <p>가격: ${item.price}</p>
                <p>수량: ${item.quantity}</p>
            </div>
        </c:forEach>
    </div>
    
    <br>
    
    <!-- 가격 표시 -->
    <div id="price">
        총 가격: ${totalPrice} 원
    </div>
    
    <!-- 결제 버튼 -->
    <div class="buy">
        <button type="submit">결제하기</button>
    </div>

</body>
</html>

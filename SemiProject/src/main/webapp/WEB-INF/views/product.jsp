<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Page</title>
    <link rel="stylesheet" href="static/ljmcss/product.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Hahmlet:wght@100..900&display=swap" rel="stylesheet">
    <script src="static/ljmjs/product.js"></script>
</head>
<body>
    <div class="product">
        <div id="headers"></div>
        <div class="product-page">
            <div class="slider">
                <div class="slides">
                    <img src="static/images/kpop1.jpeg" alt="Image 1">
                    <img src="static/images/kpop2.jpeg" alt="Image 2">
                    <img src="static/images/kpop3.jpeg" alt="Image 3">
                </div>
            </div>
            <div class="container">
                <main>
                    <div class="productpage-list">
                        <ul class="products">
                            <c:forEach var="product" items="${products}">
                                <li class="product-each">
                                    <span><img src="${product.imageUrl}" width="250px" height="350px" alt="${product.name}"></span>
                                    <br/>
                                    <br/>
                                    <span class="name">${product.name}</span>
                                    <br/>
                                    <span class="price"><span class="pricecolor">${product.price}</span><span class="won">원</span></span>
                                    <span class="cart">
                                        <form action="/cart/add" method="post">
                                            <input type="hidden" name="productId" value="${product.id}">
                                            <button type="submit"><img src="static/images/cart.jpeg" alt="Add to Cart"></button>
                                        </form>
                                    </span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </main>
            </div>
        </div>
        <div id="footers"></div>
    </div>
</body>
</html>

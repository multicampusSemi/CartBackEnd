window.onload = function(){
    var orderData = JSON.parse(localStorage.getItem('orderData'));

     if(orderData && Array.isArray(orderData)){
        var orderListHTML = '';
        var totalPrice = 0;
        var totalShippingFee = 0;
        

        orderData.forEach(function(item){
            var itemPrice = parseInt(item.price) || 0; 
            var itemShippingFee = parseInt(item.shippingFee) || 0; 
            var itemQuantity = parseInt(item.quantity) || 0;

            var itemTotalPrice = itemPrice * itemQuantity;
            var itemTotalShippingFee = itemShippingFee * itemQuantity;


            orderListHTML += `
            <div class="order-item-main">
            <img src="${item.image}" alt="상품 이미지" width="200">
            <table class="order-item-table">
                <tr>
                    <td><strong>상품명 : </strong></td>
                    <td>${item.productName}</td>
                </tr>
                <tr>
                    <td><strong>상품설명 : </strong></td>
                    <td>${item.productDetail}</td>
                </tr>
                <tr>
                    <td><strong>수량 : </strong></td>
                    <td>${item.quantity}</td>
                </tr>
                <tr>
                    <td><strong>가격 : </strong></td>
                    <td>${item.price}</td>
                </tr>
            </table>
            </div>
             `;

           totalPrice += itemTotalPrice;
            totalShippingFee += itemTotalShippingFee;
           
        })
        document.getElementById('orderlist').innerHTML = orderListHTML;

        var totalAmount = totalPrice + totalShippingFee;
        document.getElementById('price').innerHTML = `
        <div class='price'>
            <p>총 가격: ${totalPrice}원</p>
            <p>총 배송비: ${totalShippingFee}원</p>
            <p>총 결제 금액: ${totalAmount}원</p>
        </div>
        `;
   
     }else{
        document.getElementById('orderlist').innerHTML = `<p>주문되는 상품이 없습니다.<p>`;
     }
}
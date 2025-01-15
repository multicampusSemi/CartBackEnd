

function generateProductRows(cartItems) {
    const table = document.querySelector('.booking-table tbody');
    cartItems.forEach((item, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td><input type="checkbox" class="select-check" data-id="${item.id}" onchange="updateShippingFee()"/></td>
            <td><img src="${item.imageUrl}" alt="${item.productName}"/><br/>${item.productName}</td>
            <td><p>${item.productDescription}</p></td>
            <td>${item.shippingFee}</td>
            <td>
                <div class="count">
                    <button onclick="decrease(${index})">-</button>
                    <input type="text" id="numberInput${index}" value="${item.quantity}" onchange="updateShippingFee()" min="0"/>
                    <button onclick="increase(${index})">+</button>
                </div>
                <p>수량</p>
            </td>
            <td>${item.price}</td>
        `;
        table.appendChild(row);
    });
    updateShippingFee();
}



function updateShippingFee() {
    let totalShippingFee = 0;
    let totalPrice = 0;
    const checkboxes = document.querySelectorAll('.select-check');
    checkboxes.forEach(function(checkbox, index) {
        if (checkbox.checked) {
            const shippingFee = products[index].shippingFee;
            const price = products[index].price;
            const quantity = parseInt(document.getElementById(`numberInput${index}`).value);

            if (quantity > 0) {  
                totalShippingFee += shippingFee;
                totalPrice += price * quantity;
            }
        }
    });
    const totalAmount = totalShippingFee + totalPrice;
    document.querySelector('.allprice').textContent = `배송비 ${totalShippingFee} + 가격 ${totalPrice} = ${totalAmount}원`;
}

function calculateTotalPrice() {
    let totalPrice = 0;
    const checkboxes = document.querySelectorAll('.select-check');
    checkboxes.forEach(function(checkbox, index) {
        if (checkbox.checked) {
            const quantity = document.getElementById(`numberInput${index}`).value;
            const price = products[index].price;
            totalPrice += price * quantity;
        }
    });
    return totalPrice;
}




function increase(index){
    var input = document.getElementById("numberInput" + index);
    var currentValue = parseInt(input.value);
    input.value = currentValue + 1;
    updateShippingFee();
}

function decrease(index){
    var input = document.getElementById("numberInput" + index);
    var currentValue = parseInt(input.value);
    if(currentValue > 0){
        input.value = currentValue - 1;
    }else{
        alert("0보다 작을 수 없습니다.")
    }
    updateShippingFee();
}

function allCheck(){
    var allCheckbox = document.getElementById("all-check");
    var checkboxes = document.querySelectorAll(".select-check");
    checkboxes.forEach(function(checkbox){
        checkbox.checked = allCheckbox.checked;
    })
    updateShippingFee();
}

function order(){
    var selectedProduct = [];
    var row = document.querySelectorAll('.booking-table tbody tr');
    row.forEach(function(row,index){
        if(index>0){
            var checkbox = row.querySelector('.select-check');
            if(checkbox && checkbox.checked){
                var productName = row.querySelector('td:nth-child(2) img + br').nextSibling.nodeValue.trim();
                var productDetail = row.querySelector('td:nth-child(3) p').textContent.trim();
                var price = row.querySelector('td:nth-child(6)').textContent.trim();
                var input = row.querySelector('input[id^="numberInput"]');
                var image = row.querySelector('td:nth-child(2) img').src;
                var shippingFee = row.querySelector('td:nth-child(4)').textContent.trim();
                if(input){
                    var quantity = input.value;

                    var orderData = {
                        productName : productName,
                        productDetail : productDetail,
                        quantity : quantity,
                        price : price,
                        image : image,
                        shippingFee : shippingFee
                    }
                }
                selectedProduct.push(orderData);
            }
        }
    })

    if(selectedProduct.length>0){
        localStorage.setItem('orderData',JSON.stringify(selectedProduct));
        window.location.href = 'order.html';
    }else{
        alert('선택한 상품이 없습니다.')
    }

}

function deleteSelected(){
    const checkboxes = document.querySelectorAll('.select-check');
    const tableBody = document.querySelector('.booking-table tbody');
    let rowsToDelete = [];

    checkboxes.forEach(function (checkbox, index){
        if(checkbox.checked){
            const row = checkbox.closest('tr');
            if(row.parentNode === tableBody){
                rowsToDelete.push(row.rowIndex);
                products.splice(index,1);
            }
        }
    });

    rowsToDelete.reverse().forEach(function(index){
        const row = tableBody.rows[index];
        tableBody.deleteRow(row.rowIndex);
    });

    updateShippingFee();

}
window.onload = generateProductRows;
function addToCart(id) {
    let amount = document.getElementById("productAmount_" + id).value;

    if (amount < 1) {
        alert("Отрицательное количество товара!");
        return;
    }

    let object = {
        amount : amount,
        id : id
    };

    let json = JSON.stringify(object);

    $.ajax({
        url:"/addToCart",
        method:"POST",
        data : json
    }).done(function (resultAmount) {
        $("#amountProductsInCart").text(resultAmount.amount)
    })
}
function clearCart() {
    var cart = document.getElementById('cart');
    var mascot = document.getElementById('mascot');
    while(cart.hasChildNodes()) {
        cart.removeChild(cart.firstChild);
    }
    mascot.src = '';
    mascot.alt = '';
}

function checkout() {
    var total = parseInt(document.getElementById('total').innerHTML);
    //alert(typeof(total) + " " + total);
    var balance = getBalance();
    //alert(typeof(balance) + " " + balance);
    if(parseInt(balance.innerHTML) >= total) {
        var success = document.getElementById('message');
        var newBalance = parseInt(balance.innerHTML) - total;
        //alert(typeof(newBalance) + " " + newBalance);
        balance.innerHTML = newBalance;
        clearCart();
        success.innerHTML = "Items successfully ordered!";
        return true;
    } else {
        var failure = document.getElementById('message');
        failure.innerHTML = "Sorry, not enough funds.";
        return false;
    }
}

function addMoney() {
    var balance = getBalance();
    //alert(typeof(balance) + " " + balance);
    var amount = document.getElementById('amount');
    //alert(typeof(amount) + " " + amount);
    var sum = parseInt(balance.innerHTML + amount.value);
    //alert(typeof(sum));
    balance.innerHTML = sum;
    //amount.value = "";
}

function getBalance() {
    var balance = document.getElementById('balance');
    return balance;
}
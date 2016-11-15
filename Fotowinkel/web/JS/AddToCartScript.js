//door Martijn

function AddToCart(code)
{
   // if (ValidateElementAsNumber(document.getElementById("amount"))) werkt niet in chrome ofzo? dus we cheaten ff
   if(true)
    {
        id = window.location.search.substr(1)
        var amount = document.getElementById("amount").value;
        window.location = "AddToCartServlet.jsp?code=" + id + "?am=" + amount;
    }
    else
    {
        alert("invalid number");s
    }
}


function ValidateElementAsNumber(element)
{
    if ((element.validity) && (!element.validity.valid))
    {
        //if html5 validation says it's bad: it's bad
        return false;
    }

    //Fallback to browsers that don't yet support html5 input validation
    //Or we maybe want to perform additional validations
    var value = StrToInt(element.value);
    if (value != null)
        return true;
    else
        return false;
}

$(document).on("click", "#login", function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
        }

    };
    var name = document.getElementById("name").value;
    var lastname = document.getElementById("lastname").value;
    var country = document.getElementById("country").value;
    var city = document.getElementById("city").value;
    var street = document.getElementById("street").value;
    var housenr = document.getElementById("housenr").value;
    var postcode = document.getElementById("postcode").value;
    var paymentmethod = document.getElementById("paymentmethod").value;
    
    xhttp.open("POST", "DetailsServlet?" + "name=" + name + "&lastname=" + lastname + "&country=" + country + "&city=" + city + "&street=" + street + "&housenr=" + housenr + "&postcode=" + postcode + "&paymentmethod=" + paymentmethod, true);
    xhttp.send();
    return false;
});



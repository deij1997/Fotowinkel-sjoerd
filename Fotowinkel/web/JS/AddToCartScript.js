//door Martijn

function AddToCart(hash)
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



/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on("click", ".addtocart", function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
        }
    };
    
    //TODO
    //Edit later on with modal input.
    var amnt = document.getElementById(this.id + "_amnt").value;
    if (amnt != 0)
    {
        document.getElementById("cart").src = "Images/cartwi.png";
    }
    var item = this.id;
    var color = document.getElementById("color_"+this.id).value;
    
    xhttp.open("POST", "AddToCartServlet?" + "amnt=" + amnt + "&it=" + item + "&color=" + color, true);
    xhttp.send();
    return false;
});

$(document).on("click", ".showdetails", function loadDoc() {
    //Stuff....
    var item = this.id;
    
    var image = document.getElementsByClassName(item)[0];
    var text = image.alt;
    //innerText for IE. Fuck IE.
    var title = document.getElementsByClassName(item)[0].parentElement.nextSibling.nextSibling.getElementsByTagName("h4")[1].textContent;
    document.getElementById("modalimage").src = image.src;
    document.getElementById("modaldescription").textContent = text;
    document.getElementById("modaltitle").textContent = title;
});
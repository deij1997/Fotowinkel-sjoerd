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
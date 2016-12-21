/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tid;
var tcolor;

//For quick add
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
    tcolor = document.getElementById("color_" + this.id).value;

    xhttp.open("POST", "AddToCartServlet?" + "amnt=" + amnt + "&it=" + item + "&color=" + tcolor, true);
    xhttp.send();
    return false;
});

//To open and revalidate the modal window
$(document).on("click", ".showdetails", function loadDoc() {
    var item = this.id;
    tid = item;
    var image = document.getElementsByClassName(item)[0];
    var text = image.alt;
    //innerText for IE. Fuck IE tho.
    var title = document.getElementsByClassName(item)[0].parentElement.nextSibling.nextSibling.getElementsByTagName("h4")[1].textContent;

    document.getElementById("modalcolour").value = "#000000";
    document.getElementById("modalimage").src = "imgServlet?type=000000&id=" + tid;
    document.getElementById("modaldescription").textContent = text;
    document.getElementById("modaltitle").textContent = title;
});

//To show direct feedback to the user regarding colour
$(document).on("change", "#modalcolour", function loadDoc() {
    tcolor = this.value;
    tcolor = tcolor.replace('#', '');
    var image = document.getElementById("modalimage");
    image.src = "imgServlet?type=" + tcolor + "&id=" + tid;
});

//Get the new articles to order
$(document).on("click", "#modalbutton", function (event) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("modalcontent").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "ProductArticlesServlet?id=" + tid + "&color=" + tcolor, true);
    xhttp.send();
});
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tid;
var tcolor;
var ft;

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
    tcolor = document.getElementById("modalcolour").value;
    document.getElementById("modalimage").src = "imgServlet?type=000000&id=" + tid;
    document.getElementById("modaldescription").textContent = text;
    document.getElementById("modaltitle").textContent = title;

    //Fill the content with the shopping cart where the ids match
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("modalcontent").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "ProductArticlesServlet?id=" + tid + "&ft=true", true);
    xhttp.send();
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
            //Filter innerHTML so boxes with an amount of higher than 0 stay in here
            filterAndAddModalContent(this.responseText);
        }
    };
    xhttp.open("POST", "ProductArticlesServlet?id=" + tid + "&color=" + tcolor, true);
    xhttp.send();
});

//Order the new items from the modal window
$(document).on("click", "#modalpreview", function (event) {

    //Add all things to the shopping cart
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            console.log(this.responseText);
        }
    };

    var children = document.getElementById("modalcontent").children;

    var totalamnt = 0;
    //Loop through all items
    for (var i = 0; i < children.length; i++) {
        var Child = children[i];

        //Get the amount
        var amnt = Child.getElementsByTagName("input")[0].value;

        var img = Child.children[0].getElementsByTagName("img")[0];

        //Get the product name
        var type = img.alt;

        //Get the colour from the url
        var color = getColor(img.src);

        //Send a request to the server to add it
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState === 4 && this.status === 200) {
                console.log(this.responseText);
            }
        };

        xhttp.open("POST", "AddToCartServlet?" + "amnt=" + amnt + "&it=" + tid + "&color=" + color + "&type=" + type, true);
        xhttp.send();
        totalamnt += amnt;
    }
    if (totalamnt != 0)
    {
        document.getElementById("cart").src = "Images/cartwi.png";
    }

    return false;
});

function filterAndAddModalContent(toAdd) {
    var parent = document.getElementById("modalcontent");
    var children = document.getElementById("modalcontent").children;

    for (var i = 0; i < children.length; i++) {
        var Child = children[i];
        // Check if the input in the child has a value larger than 0
        if (Child.getElementsByTagName("input")[0].value <= 0)
        {
            parent.removeChild(Child);
            //Move the pointer 1 back, since an item was removed
            i--;
        }
    }

    $("#modalcontent").append(toAdd);
}

function getColor(item)
{
    var color = item.match(/color=(.{6})/)[1];
    if (color == null)
    {
        color = "000000";
    }
    return color;
}
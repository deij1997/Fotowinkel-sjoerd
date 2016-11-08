/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on("click", "#login", function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            if (this.responseText == 0) {
                document.getElementById("loginErrorMsg").classList.remove("hide");
            }
            if (this.responseText == 1) {
                window.location.replace("index.jsp");
            }
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
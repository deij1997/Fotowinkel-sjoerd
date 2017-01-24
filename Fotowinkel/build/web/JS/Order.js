/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.onload = function loadDoc() {
    Load();
};

function Load() 
{
    //Load in order details
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "OrderServlet", true);
    xhttp.send();
    
    
    //Load in user details
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("detailbox").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "UserInformationServlet", true);
    xhttp.send();
};
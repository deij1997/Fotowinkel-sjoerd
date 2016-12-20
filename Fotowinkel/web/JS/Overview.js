/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("total").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "OverviewTotalGainServlet", true);
    xhttp.send();
    
    var xhttp2 = new XMLHttpRequest();
    xhttp2.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("articles").innerHTML = this.responseText;
        }
    };
    xhttp2.open("POST", "OverviewArticleServlet", true);
    xhttp2.send();
});

function getNew()
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("fill").innerHTML = this.responseText;
        }
    };

    var photograph = document.getElementById("selectedFotograaf").value;

    xhttp.open("POST", "OverviewProductServlet?" + "selection=" + photograph, true);
    xhttp.send();

    var xhttp2 = new XMLHttpRequest();
    xhttp2.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            document.getElementById("totalcurrent").innerHTML = this.responseText;
        }
    };
    xhttp2.open("POST", "OverviewGainServlet?" + "selection=" + photograph, true);
    xhttp2.send();
}
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).on("click", "#myImg", function () {
    $('#myModal')[0].style.display = "block";
    $('#img01')[0].src = this.src;
    $('#caption')[0].innerHTML = this.alt;
});

$(document).on("click", ".close", function () {
    $('#myModal')[0].style.display = "none";
});

$(document).on("click", "#myModal", function (e) {
    e = e || event;
    var target = e.target || e.srcElement;
    var innerId = target.id;
    if (innerId !== "img01")
    {
        $('#myModal')[0].style.display = "none";
    }
});
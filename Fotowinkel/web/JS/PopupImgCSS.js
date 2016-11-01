/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).on("click", "#myImg", function () {
    $('#img01')[0].src = this.style.backgroundImage.split("\"")[1];
    $('#myModal')[0].style.display = "block";
    $('#caption')[0].innerHTML = jQuery(this).next().find("div")[0].innerHTML;
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
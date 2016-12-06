/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function removeButtons(event) {   
    var element = event.target;
    element.src='Images/notfound.png';
    element.nextSibling.nextSibling.innerHTML = "";
}
;
$(document).on("click", ".custombutt", function (event)
{
    var element = event.target;
    var classlist = element.classList;

    if (element.tagName === 'LI')
    {
        var doAdd = false;
        if (!classlist.contains("active"))
        {
            //Note down to add
            doAdd = true;
        }
        //If needs to add:
        if (doAdd)
        {
            var img = this.previousSibling.previousSibling;
            
            var selection = this.querySelector('.active');
            var id = img.classList[1];
            var type = element.classList[0];
            document.getElementById("color_"+id).value = type;
            selection.classList.remove("active");
            //Add "active" to clicked
            element.classList.add("active");
            if (!img.src.endsWith("Images/notfound.png"))
            {
                img.src = "imgServlet?type=" + type + "&id=" + id +"&color=";
            }
        }
    }
});
String.prototype.endsWith = function (suffix) {
    return this.indexOf(suffix, this.length - suffix.length) !== -1;
};
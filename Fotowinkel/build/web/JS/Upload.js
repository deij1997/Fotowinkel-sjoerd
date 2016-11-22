/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Catch the form submit and upload the files
$(function () {
    $('#upload-form').ajaxForm({
        success: function (response)
        {
            console.log('RESULT: ' + response);
            $('#sbmbtn').innerHTML = "Upload";
            $('#sbmbtn').removeProp('disabled');
        },
        error: function (response)
        {
            // Handle errors here
            console.log('ERRORS: ' + response);
            $('#sbmbtn').innerHTML = "Upload";
            $('#sbmbtn').removeProp('disabled');
        }
    });
});

var open = true;
var imagecount = 0;
$(document).ready(function ()
{
    $('#fileupload').on('change', function () {
        var invalidImages = [];
        var imageCheck = 0;
        var fileList = this.files;
        for (var i = 0; i < fileList.length; i++)
        {
            if (isImage(fileList[i])) {
                imageCheck++;
                addcolomn(imagecount);
                var output = document.getElementById('preview' + imagecount);
                output.src = URL.createObjectURL(fileList[i]);
                addImageDetails(fileList[i], imagecount);
                //Verhoog de image count 
                imagecount++;
            } else {
                //Als image geen image is onthoud deze in een array
                invalidImages.push(fileList[i].name);
            }
        }
        ;
        if (imageCheck > 0) {
            //Geef een melding wanneer er images zijn die niet herkent waren
            if (imageCheck != fileList.length)
            {
                alert('De volgende images worden niet herkend: ' + invalidImages);
            }
            //Laat de preview fotos zien
            hide($(".UploadBox"));
            open = false;
            $("#uploadedImages").fadeIn(200);
        }
    });
});

function addcolomn(count) {
    var div = document.createElement('div');

    div.id = 'previewdiv' + count;
    div.className = 'standardImageBox';

    div.innerHTML = '           <img style="width:100%; height:100%;"  id="' + 'preview' + count + '"/>';

    document.getElementById('imageRow').appendChild(div);
}
;

$(document).on("click", "#morephotos", function () {
    if (!open)
    {
        hide($(".UploadBox"));
        var div = document.createElement('div');
        div.id = 'UploadBox';
        div.className = 'UploadBox';
        div.innerHTML = '<div class="form-group">'
                + '<input type="file" class="inputfile" id="fileupload" data-multiple-caption="{count} files selected" multiple="multiple" name="file" />'
                + '<label for="fileupload"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg></figure><span>Choose a file…</span></label>'
                + '<span id="upload-error" class="error"></span>'
                + '<br>'
                + ' </div>';
        document.getElementById('atch').appendChild(div);
        open = true;
    }
});

//HIER
var selected;

$(document).on("click", ".standardImageBox", function () {
    var thisid = this.id.replace(/^\D+/g, '');

    hide($("#ImageDetails" + selected));
    show($("#ImageDetails" + thisid));
    selected = thisid;
});
function hide(elementList) {
    if (elementList != undefined)
    {
        for (i = 0; i < elementList.length; i++) {
            if (!elementList[i].classList.contains("hidden"))
                elementList[i].classList.add("hidden");
        }
    }
}
function show(elementList) {
    elementList[0].classList.remove("hidden");
}

function addImageDetails(image, imageCount) {

    var input = document.createElement('input');
    input.id = "img" + imageCount;
    input.name = "image";
    input.className = 'hidden';
    input.type = 'text';

    var div = document.createElement('div');
    var autoTitle = image.name;

    div.id = 'ImageDetails' + imageCount;
    div.className = 'imageDetails hidden';

    div.innerHTML = '<span><h4>Product details</h4></span><br/><br/><span> <label for="imgtitle">Titel:</label></span><br><span> <input class="form-control" id="imagetitle" name="imgtitle" type="text" placeholder="Titel is vereist..." value="' + autoTitle + '" ></span>  <br/>  <span> <label for="imgDesc">Beschrijving:</label></span><br> <span> <textarea class="form-control"  id="imageDesc" name="imgdesc" type="text" placeholder="" contenteditable="true"></textarea></span>  <br/><span> <label for="imgPrice">Prijs:</label></span><br> <span>   <input class="form-control" type="number" id="imageprice" name="imgprice" min="0.00" step="0.01" value="0.00"></span>';

    document.getElementById('imageDataWrapper').appendChild(input);
    document.getElementById('imageDataWrapper').appendChild(div);

    $("#img" + imageCount).val(URL.createObjectURL(image));

}

function isImage(i) {
    return i['type'].split('/')[0] == 'image';
}
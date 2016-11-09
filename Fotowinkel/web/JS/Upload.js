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
            if (typeof response === 'Upload has been done successfully!')
            {
                // Success so call function to process the form
                // submitForm(event, data);
                console.log(response);

            } else
            {
                // Handle errors here
                console.log('ERRORS: ' + response);
            }
        },
        error: function (response)
        {
            // Handle errors here
            console.log('ERRORS: ' + response);
        }
    });
});


$(document).ready(function () {
    var imageArray = [];
    var imagecount = 0;
    var inputs = document.querySelectorAll('.inputfile');
    var images = null;
    Array.prototype.forEach.call(inputs, function (input)
    {

        var label = input.nextElementSibling,
                labelVal = label.innerHTML;

        input.addEventListener('change', function (e)
        {
            var invalidImages = [];
            var imageCheck = 0;
            var fileName = '';
            if (this.files && this.files.length > 1)
                fileName = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
            else
                fileName = e.target.value.split('\\').pop();

            if (fileName)
                label.querySelector('span').innerHTML = fileName;
            else
                label.innerHTML = labelVal;
            for (var i = 0; i < this.files.length; i++)
            {
                if (isImage(this.files[i])) {
                    imageCheck++;
                    imageArray.push(this.files[i]);
                    addcolomn(imagecount);
                    var output = document.getElementById('preview' + imagecount);
                    output.src = URL.createObjectURL(imageArray[imagecount]);
                    addImageDetails(this.files[i], imagecount);
                    //Verhoog de image count 
                    imagecount++;
                } else {
                    //Als image geen image is onthoud deze in een array
                    invalidImages.push(this.files[i].name);
                }
            }
            ;
            if (imageCheck != this.files.length) {
                label.querySelector('span').innerHTML = ' <span  style="color:red !important"  >De volgende foto\'s worden niet herkend: ' + invalidImages + '</span>';
                    
            }
            if (imageCheck > 0) {
                //Geef een melding wanneer er images zijn die niet herkent waren
                if (imageCheck != this.files.length)
                    alert('De volgende images worden niet herkend: ' + invalidImages);
                //Laat de preview fotos zien
                $("#UploadBox").fadeOut(200);
                $("#uploadedImages").fadeIn(200);
            }




        });
    });
});
function addcolomn( count) {
    var div = document.createElement('div');

    div.id = 'previewdiv' + count;
    div.className = 'standardImageBox';

    div.innerHTML = '           <img style="width:100%; height:100%;"  id="' + 'preview' + count + '"/>';

    document.getElementById('imageRow').appendChild(div);
}
;

//HIER
var selected;

$(document).on("click", ".standardImageBox", function () {
    var thisid = this.id.replace( /^\D+/g, '');

    hide($("#ImageDetails"+selected));
    show($("#ImageDetails"+thisid));
    selected =thisid;
});
function hide(elementList) {
    if( elementList[0] != undefined)
  elementList[0].classList.add("hidden");
}
function show(elementList) {
  elementList[0].classList.remove("hidden");
}

function addImageDetails(image, imageCount){
    
        var div = document.createElement('div');
        var autoTitle = image.name;

    div.id = 'ImageDetails' + imageCount;
    div.className = 'imageDetails hidden';

    div.innerHTML = '<span><h4>Product details</h4></span><br/><br/><span> <label for="imgtitle">Titel:</label></span><br><span> <input class="form-control" id="imagetitle" name="imgtitle" type="text" placeholder="Titel is vereist..." value="'+autoTitle+'" ></span>  <br/>  <span> <label for="imgDesc">Beschrijving:</label></span><br> <span> <textarea class="form-control"  id="imageDesc" name="imgdesc" type="text" placeholder="" contenteditable="true"></textarea></span>  <br/><span> <label for="imgPrice">Prijs:</label></span><br> <span>   <input class="form-control" type="number" id="imageprice" name="imgprice" min="0.00" step="0.01" value="0.00"></span>';

    document.getElementById('imageDataWrapper').appendChild(div);
                            
}

function isImage(i) {
    return i['type'].split('/')[0] == 'image';
}
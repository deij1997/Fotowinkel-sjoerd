<%-- 
    Document   : index
    Created on : 20-Sep-2016, 09:26:26
    Author     : Rowan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="http://malsup.github.com/jquery.form.js"></script> 
    <script src="JS/Upload.js"></script>
	<link rel="stylesheet" href="CSS/Uploadpage.css">


	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <link rel="icon" href="Images/icon.gif">



        <title>Upload pagina</title>
    </head>
    <body>
                <%@include file="header.jsp" %>
        <div class="container">
        
        <div id="UploadBox">
<form id="upload-form" class="upload-box" action="UploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" class="inputfile" id="fileupload" data-multiple-caption="{count} files selected" multiple="multiple" name="file" />
    <label for="fileupload"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg></figure><span>Choose a file…</span></label>

    <span id="upload-error" class="error">${uploadError}</span>
    <br>
    <input type="submit" id="upload-button" value="Bevestig" />
</form>
    </div>
    
    <div id="uploadedImages" >
        <div id="imageRow"><div id="leftArrow"class="arrow"></div>
            <div class="standardImageBox">
                        </div>

            <div id="rightArrow" class="arrow"></div>
        
    </div>
        
        <div id="imageData">
            <span> <label for="imagename">Bestandnaam:</label></span> <span> <input type="text" ></span>
        </div>
    </div>
    <script > 
    var inputs = document.querySelectorAll( '.inputfile' );
Array.prototype.forEach.call( inputs, function( input )
{

	var label	 = input.nextElementSibling,
		labelVal = label.innerHTML;

	input.addEventListener( 'change', function( e )
	{

		var fileName = '';
		if( this.files && this.files.length > 1 )
			fileName = ( this.getAttribute( 'data-multiple-caption' ) || '' ).replace( '{count}', this.files.length );
		else
			fileName = e.target.value.split( '\\' ).pop();

		if( fileName )
			label.querySelector( 'span' ).innerHTML = fileName;
		else
			label.innerHTML = labelVal;
	});
});
</script>
         </div>
        <%@include file="footer.jsp" %>
    </body>
</html>

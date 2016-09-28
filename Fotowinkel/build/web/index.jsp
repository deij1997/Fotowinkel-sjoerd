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
            <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.js"></script> 
    <script src="http://malsup.github.com/jquery.form.js"></script> 
            <script src="JS/Upload.js"></script>


        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
<form id="upload-form" class="upload-box" action="UploadServlet" method="post" enctype="multipart/form-data">
    <input type="file" id="fileupload" multiple="multiple" name="file" />
    <span id="upload-error" class="error">${uploadError}</span>
    <input type="submit" id="upload-button" value="upload" />
</form>

 
    </body>
</html>

<%-- 
    Document   : index
    Created on : 20-Sep-2016, 09:26:26
    Author     : Rowan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <%@include file="WEB-INF/head.jspf" %>
        <script src="JS/Upload.js"></script>
        <link rel="stylesheet" href="CSS/Uploadpage.css">
        <script src="https://1000hz.github.io/bootstrap-validator/assets/js/application.js"></script>
        <title>Upload pagina</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>

        <div class="container">
            <form id="upload-form" data-toggle="validator" role="form" class="upload-box" action="UploadServlet" method="post" enctype="multipart/form-data">

                <div id="atch">
                    <div id="UploadBox" class="UploadBox">
                        <div class="form-group">

                            <input type="file" class="inputfile" id="fileupload" data-multiple-caption="{count} files selected" multiple="multiple" name="file" />
                            <label for="fileupload"><figure><svg xmlns="http://www.w3.org/2000/svg" width="20" height="17" viewBox="0 0 20 17"><path d="M10 0l-5.2 4.9h3.3v5.1h3.8v-5.1h3.3l-5.2-4.9zm9.3 11.5l-3.2-2.1h-2l3.4 2.6h-3.5c-.1 0-.2.1-.2.1l-.8 2.3h-6l-.8-2.2c-.1-.1-.1-.2-.2-.2h-3.6l3.4-2.6h-2l-3.2 2.1c-.4.3-.7 1-.6 1.5l.6 3.1c.1.5.7.9 1.2.9h16.3c.6 0 1.1-.4 1.3-.9l.6-3.1c.1-.5-.2-1.2-.7-1.5z"></path></svg></figure><span>Choose a file…</span></label>

                            <span id="upload-error" class="error">${uploadError}</span>
                            <br>
                        </div>
                    </div>
                </div>

                <div id="uploadedImages" style="display: none;">
                    <h4 id="morephotos" onclick="$('#UploadBox').fadeIn(200);" >&uarr;	Upload andere foto's</h4>

                    <div  id="imageRow"><div id="leftArrow"class="arrow"></div>


                        <div id="rightArrow" class="arrow"></div>

                    </div>
                    <div class="form-group">

                        <div id="imageDataWrapper">


                        </div></div>

                    <br>
                    <hr><br>
                    <div class="form-group has-feedback">
                        <label for="inputEmail" class="control-label">Klant mail</label>
                        <input type="email" class="form-control" name="inputEmail" id="inputEmail" placeholder="Klant e-mail" data-error="Het e-mailadres van de klant is vereist." required>
                        <span class="glyphicon form-control-feedback" aria-hidden="true"></span>
                        <div class="help-block with-errors"></div>
                    </div><br>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </div>

            </form>
        </div>
        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

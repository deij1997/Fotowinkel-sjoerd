<%-- 
    Document   : Order
    Created on : 04-Oct-2016, 10:31:33
    Author     : Jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fotowinkel Sjoerd</title>
        <link rel="stylesheet" href="CSS/stylesheet.css">
        <link rel="stylesheet" href="CSS/bootstrap.min.css">
        <script src="JS/Order.js"></script>
        <link rel="stylesheet" href="CSS/PopupImg.css">
        <script src="JS/PopupImg.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-3" id="detailbox">
                    <p class="lead">Details</p>
                    <p class="pull-right">test</p><p>Name: </p>
                    <p class="pull-right">test</p><p>Last name: </p>
                    <p class="pull-right">test</p><p>Country: </p>
                    <p class="pull-right">test</p><p>City: </p>
                    <p class="pull-right">test</p><p>Street: </p>
                    <p class="pull-right">test</p><p>House Nr: </p>
                    <p class="pull-right">test</p><p>Postcode: </p>
                    <p class="pull-right">test</p><p>Payment method: </p>
                </div>
                <div class="col-md-9">
                    <div class="row" id="demo">
                    </div>
                </div>
            </div>

            <div id="myModal" class="modal">
                <span class="close" onclick="document.getElementById('myModal').style.display = 'none'">&times;</span>

                <img class="modal-content" id="img01">

                <div id="caption"></div>

            </div>

        </div>
        <%@include file="footer.jsp" %>
        <script>
            // Get the modal
            var modal = document.getElementById('myModal');

            // Get the image and insert it inside the modal - use its "alt" text as a caption
            var img = document.getElementById('myImg');
            var modalImg = document.getElementById("img01");
            var captionText = document.getElementById("caption");
            img.onclick = function () {
                modal.style.display = "block";
                modalImg.src = this.src;
                captionText.innerHTML = this.alt;
            };

            // Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];

            // When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            };
        </script>
    </body>
</html>

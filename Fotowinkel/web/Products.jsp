<%-- 
    Document   : Products
    Created on : 4-okt-2016, 8:50:50
    Author     : Tu
--%>

<%@page import="Managers.UserHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fotowinkel Sjoerd</title>

        <script src="JS/PopupImgCSS.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        
        <div class="container">

            <div class="row">

                <div class="col-md-3">
                    <p class="lead">Category</p>
                    <div class="list-group">
                        <a href="#" class="list-group-item">Individuel pictures</a>
                        <a href="#" class="list-group-item">Group pictures</a>
                        <a href="#" class="list-group-item">Accessoires</a>
                    </div>
                </div>

                <div class="col-md-9">

                    <div class="row carousel-holder">

                        <div class="col-md-12">
                            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                                <ol class="carousel-indicators">
                                    <li data-target="#carousel-example-generic" data-slide-to="0" ></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="1" class="active"></li>
                                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                                </ol>
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <img class="slide-image" src="https://lh3.googleusercontent.com/-Undk1_jbkk4/UZYlE8A1D1I/AAAAAAAD1yQ/yyqmYgHvnvY/w800-h800/uOYFkKS+%281%29.gif" alt="">
                                    </div>
                                    <div class="item">
                                        <img class="slide-image" src="http://media.dods.co.uk/sites/media.dods.co.uk/files/image/Drupal%20top%20image%20for%20inside%20page%20800x300_5.jpg" alt="">
                                    </div>
                                    <div class="item">
                                        <img class="slide-image" src="http://lispine.com/wp-content/uploads/2016/06/Friends-jumping-into-the-water-from-a-jetty-000091153275_XXXLarge-800x300.jpg" alt="">
                                    </div>
                                </div>
                                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                </a>
                                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                </a>
                            </div>
                        </div>

                    </div>

                    <div class="row" id="demo">

                        <script>
                            <%
                                UserHandler.setUser(request.getParameter("id"), request, response);
                            %>
                            window.onload = function loadDoc() {
                                var xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState === 4 && this.status === 200) {
                                        document.getElementById("demo").innerHTML = this.responseText;
                                    }
                                };
                                xhttp.open("GET", "ProductsServlet", true);
                                xhttp.send();
                            };
                        </script>
                    </div>
                    <div id="myModal" class="modal" style="z-index: 16;">
                        <span class="close">&times;</span>

                        <img class="modal-content" id="img01" onerror="this.src='Images/notfound.png';">

                        <div id="caption"></div>

                    </div>

                </div>

            </div>

        </div>
        <%@include file="footer.jsp" %>

    </body>
</html>

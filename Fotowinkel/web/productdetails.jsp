<%-- 
    Document   : index
    Created on : 2-nov-2016, 09:26:26
    Author     : Martijn
--%>

<%@page import="Managers.ParameterHolder"%>
<%@page import="Managers.UserHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Fotowinkel Sjoerd</title>

        <link rel="stylesheet" href="CSS/stylesheet.css">
        <link rel="stylesheet" href="CSS/bootstrap.min.css">
        <script src="JS/AddToCartScript.js"></script>
        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
        <script>
            var id = window.location.search.substr(1)
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Product</h1>

            <div class="row" id="demo">

                <script>
                    <%
                        ParameterHolder.setViewingProduct(request.getParameter("id"), request, response);
                    %>
                            window.onload = function loadDoc() {
                                var xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState === 4 && this.status === 200) {
                                        document.getElementById("demo").innerHTML = this.responseText;
                                    }
                                };
                                xhttp.open("GET", "ProductDetailServlet", true);
                                xhttp.send();
                            };
                </script>
            </div>
        </div>
        <%@include file="footer.jsp" %>

        <!-- jQuery -->
        <script src="JS/jquery.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="JS/bootstrap.min.js"></script>

    </body>
</html>

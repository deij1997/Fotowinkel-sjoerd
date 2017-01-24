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
        <title>Fotowinkel Sjoerd</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
        <div class="container">

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

            <div class="row">
                <div class="col-sm-4">
                    <h1><a href="">Home</a></h1>
                </div>
                <div class="col-sm-4">
                    <h1><a href=""><fmt:message key="Over" /></a></h1>
                </div>
                <div class="col-sm-4">
                    <h1><a href="">Contact</a></h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <h3><fmt:message key="Index_home" /></h3></div>
                <div class="col-sm-4">
                    <h3><fmt:message key="Index_about" /></h3></div>
                <div class="col-sm-4">
                    <h3><fmt:message key="Index_contact" /></h3></div>
            </div>



        </div>
        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

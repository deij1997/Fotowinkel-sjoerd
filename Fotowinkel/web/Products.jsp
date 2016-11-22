<%-- 
    Document   : Products
    Created on : 4-okt-2016, 8:50:50
    Author     : Tu
--%>

<%@page import="Managers.UserHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/head.jspf" %>
        <script src="JS/CartScript.js" type="text/javascript"></script>
        <script src="JS/PopupImg.js" type="text/javascript"></script>
        <script src="JS/ProductSwitcher.js" type="text/javascript"></script>
        <title>Fotowinkel Sjoerd</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
        <div class="container">

            <div class="row">

                <div class="col-md-3">
                    <p class="lead">Category</p>
                    <div class="list-group">
                        <a href="#" class="list-group-item">Individuele pictures</a>
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
                                        if (this.responseText !== "Er ging iets fout, probeer het (later) opnieuw.")
                                        {
                                            //Set header stuff 
                                            document.getElementById('userinfo').innerHTML = "<li>Welcome Klant</li> <li><a href=\"#\" id=\"logout\" onclick=\"deleteAllCookies()\">Uitloggen<img src=\"http://flaticons.net/gd/makefg.php?i=icons/Mobile%20Application/Logout.png&r=255&g=255&b=255\" alt=\"\" width=\"30\" height=\"30\"></a></li> <li><a href=\"Order.jsp\" >Winkelmandje<img src=\"http://www.vestsforservicedogs.com/product_images/cart.png\" alt=\"\" width=\"50\" height=\"50\"></a></li>";
                                        }
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
        <%@include file="WEB-INF/footer.jsp" %>

    </body>
</html>

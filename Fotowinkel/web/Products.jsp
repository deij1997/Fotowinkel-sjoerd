<%-- 
    Document   : Products
    Created on : 4-okt-2016, 8:50:50
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Fotowinkel Sjoerd</title>

	<link rel="stylesheet" href="CSS/stylesheet.css">
        <link rel="stylesheet" href="CSS/bootstrap.min.css">

	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
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
                                    <img class="slide-image" src="http://www.puurvandaag.nl/wp/wp-content/uploads/2016/05/Together-800x300.gif" alt="">
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
                        window.onload = function loadDoc() {
                            var xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function() {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("demo").innerHTML = this.responseText;
                                }
                            };
                            xhttp.open("GET", "ProductsServlet", true);
                            xhttp.send();
                        }
                    </script>
<%-- 
                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://www.ceramics-sa-cape.co.za/wp-content/uploads/2015/07/profile3-320x150.jpg" alt="">
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4><a href="#">Oma foto</a>
                                </h4>
                                <p>Mooi foto van mijn oma in de bibliotheek.</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right"><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                <p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://depolophotography.com/wp-content/uploads/2013/05/CorporatePortraitPhotography.jpg" alt="">
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4><a href="#">Sara Kroos</a>
                                </h4>
                                <p>Deze foto is gemaakt in het schone oerwoud van Thailand.</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right"><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                <p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4><a href="#">Third Product</a>
                                </h4>
                                <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right"><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                <p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4><a href="#">Fourth Product</a>
                                </h4>
                                <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                            <div class="ratings">
                                   <p class="pull-right"><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                <p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4><a href="#">Fifth Product</a>
                                </h4>
                                <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right"><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                <p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4 col-lg-4 col-md-4">
                        <div class="thumbnail">
                            <img src="http://placehold.it/320x150" alt="">
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4><a href="#">Fifth Product</a>
                                </h4>
                                <p>This is a short description. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                            <div class="ratings">
                                <p class="pull-right"><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                <p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>
                            </div>
                        </div>
                    </div> --%> 

                </div>

            </div>

        </div>

    </div>
    <!-- /.container -->
    <%@include file="footer.jsp" %>
		    <!-- jQuery -->
    <script src="JS/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="JS/bootstrap.min.js"></script>

    </body>
</html>

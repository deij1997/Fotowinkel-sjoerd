<<<<<<< HEAD
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

        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
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
        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>
=======
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
        
        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            
            <div class="row">
                
                <div class="col-md-3">
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
                
<<<<<<< HEAD
                <div class="row">
                    
=======
                <div class="row" id="demo">
                    <h2>test</h2>
                    <script>
                        window.onload = function loadDoc() {
                            var xhttp = new XMLHttpRequest();
                            xhttp.onreadystatechange = function() {
                                if (this.readyState == 4 && this.status == 200) {
                                    document.getElementById("demo").innerHTML = this.responseText;// + "<h2>test1</h2>";
                                }
                            };
                            xhttp.open("GET", "OrderServlet", true);
                            xhttp.send();
                        }
                    </script>
                    
                    <!--
>>>>>>> 24838d45e6971b248a53eba1a6fc5de878fd3cd7
                    <div class="col-sm-4 col-md-12">
                        
                        <div class="thumbnail">
                            <img src="http://www.smart-promotions.nl/upload/800x600/Chocolate_Chip_Koekje.jpg" style="max-width: 15%" class="pull-left" alt="">
                            
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4>
                                    <a href="#">koekske</a>
                                </h4>
                                <p>lekker koekske.</p>
                                
                                <div class="ratings">
                                    <p class="pull-right"> Stuks </p>
                                    <p class="pull-right">3&nbsp&nbsp</p>
                                </div>
                                
                            </div>
                            
                        </div>
                        
                    </div>
                    
                    <div class="col-sm-4 col-md-12">
                        
                        <div class="thumbnail">
                            <img src="http://www.smart-promotions.nl/upload/800x600/Chocolate_Chip_Koekje.jpg" style="max-width: 15%" class="pull-left" alt="">
                            
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4>
                                    <a href="#">koekske</a>
                                </h4>
                                <p>lekker koekske.</p>
                                
                                <div class="ratings">
                                    <p class="pull-right"> Stuks </p>
                                    <p class="pull-right">3&nbsp&nbsp</p>
                                </div>
                                
                            </div>
                        
                        </div>
                    
                    </div>
                    
                    <div class="col-sm-4 col-md-12">
                        
                        <div class="thumbnail">
                            <img src="http://www.smart-promotions.nl/upload/800x600/Chocolate_Chip_Koekje.jpg" style="max-width: 15%" class="pull-left" alt="">
                            
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4>
                                    <a href="#">koekske</a>
                                </h4>
                                <p>lekker koekske.</p>
                                
                                <div class="ratings">
                                    <p class="pull-right"> Stuks </p>
                                    <p class="pull-right">3&nbsp&nbsp</p>
                                </div>
                            
                            </div>
                        
                        </div>
                    
                    </div>
                    
                    <div class="col-sm-4 col-md-12">
                        
                        <div class="thumbnail">
                            <img src="http://www.smart-promotions.nl/upload/800x600/Chocolate_Chip_Koekje.jpg" style="max-width: 15%" class="pull-left" alt="">
                            
                            <div class="caption">
                                <h4 class="pull-right">€4.99</h4>
                                <h4>
                                    <a href="#">koekske</a>
                                </h4>
                                <p>lekker koekske.</p>
                                
                                <div class="ratings">
                                    <p class="pull-right"> Stuks </p>
                                    <p class="pull-right">3&nbsp&nbsp</p>
                                </div>
                            
                            </div>
                        
                        </div>
                    
                    </div>              
<<<<<<< HEAD
                
=======
                -->
>>>>>>> 24838d45e6971b248a53eba1a6fc5de878fd3cd7
                </div>
                    
                    <div class="row">
                        
                        <div class="col-sm-4 col-lg-12 col-md-4">
                            
                            <div class="thumbnail">
                                
                                <div class="caption">
                                    <h4 class="pull-left">Total</h4>
                                    <h4 class="pull-right">€49.99</h4>
                                </div>
                                
                                <div class="ratings">
                                    <p class=""><a class="btn btn-primary" target="_blank" href="">Buy</a></p>
                                    <!--<p> Quantity: <input type="number" name="aantal"style="width:50px;height:30px;"></p>-->
                                </div>
                            
                            </div>
                        
                        </div>
                    
                    </div>
                
                </div>
            
            </div>
        
        </div>
        
        <%@include file="footer.jsp" %>
        
        <script src="JS/jquery.js"></script>
        <script src="JS/bootstrap.min.js"></script>
    </body>
</html>
>>>>>>> c9d9d57a21ac0504272455ba945ede5a81e36922

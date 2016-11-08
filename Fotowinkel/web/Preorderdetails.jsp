<%-- 
    Document   : Preorderdetails
    Created on : 08-Nov-2016, 10:47:08
    Author     : Jesse
--%>

<%@page import="javax.mail.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fotowinkel Sjoerd</title>
        <link rel="stylesheet" href="CSS/stylesheet.css">
        <link rel="stylesheet" href="CSS/bootstrap.min.css">
        <link rel="stylesheet" href="CSS/PopupImg.css">
	<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
        <script src="JS/Details.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row">
                <div class="col-md-3" id="detailbox">
                    <form>
                    <p class="lead">Details</p>
                    <p class="pull-right"></p><p>Name: </p> <input type="text" name="name" id="name"><br>
                    <p class="pull-right"></p><p>Last name: </p> <input type="text" name="lastname" id="lastname"><br>
                    <p class="pull-right"></p><p>Country: </p> <input type="text" name="country" id="country"><br>
                    <p class="pull-right"></p><p>City: </p> <input type="text" name="city" id="city"><br>
                    <p class="pull-right"></p><p>Street: </p> <input type="text" name="street" id="street"><br>
                    <p class="pull-right"></p><p>House Nr: </p> <input type="text" name="housenr" id="housenr"><br>
                    <p class="pull-right"></p><p>Postcode: </p> <input type="text" name="postcode" id="postcode"><br>
                    <p class="pull-right"></p><p>Payment method: </p> <input type="text" name="paymentmethod" id="paymentmethod"><br>
                    <p class="pull-right"></p><p></p> <button  id="login" class="btn btn-success btn-block" style="margin:3px">Next</button>
                    </form>
                </div>
            </div>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>

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
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
        <script src="https://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>

        <link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="pageone" class="container">
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

            <div data-role="popup" id="myPopup">
                <p>This is my picture!</p>
                <a href="#pageone" data-rel="back" class="ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right">Close</a><img src=" + imgurl + " style="width:800px;height:400px;" alt="Skaret View">
            </div>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>

<%-- 
    Document   : Order
    Created on : 04-Oct-2016, 10:31:33
    Author     : Jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/head.jspf" %>
        <title>Fotowinkel Sjoerd</title>
        <script src="JS/Order.js"></script>
        <script src="JS/PopupImg.js"></script>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
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
                <span class="close">&times;</span>

                <img class="modal-content" id="img01">

                <div id="caption"></div>

            </div>

        </div>
        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

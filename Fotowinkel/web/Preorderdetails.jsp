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
        <%@include file="WEB-INF/head.jspf" %>
        <title>Fotowinkel Sjoerd</title>
        <script src="JS/Details.js"></script>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
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
        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

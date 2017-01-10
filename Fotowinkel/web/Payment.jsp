<%-- 
    Document   : Payment
    Created on : 22-Nov-2016, 14:24:38
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
        <h1>Je gaat betalen LELELELELEL</h1>

        <div>
            <form style="padding-left: 50px">
                <table>
                    <tr><td><p>Name: </p></td><td><input type="text" name="name" id="name"></td></tr>
                    <tr><td><p>Last name: </p></td><td><input type="text" name="lastname" id="lastname"></td></tr>
                    <tr><td><p>Country: </p></td><td><input type="text" name="country" id="country"></td></tr>
                    <tr><td><p>City: </p></td><td><input type="text" name="city" id="city"></td></tr>
                    <tr><td><p>Street: </p></td><td><input type="text" name="street" id="street"></td></tr>
                    <tr><td><p>House Nr: </p></td><td><input type="text" name="housenr" id="housenr"></td></tr>
                    <tr><td><p>Postcode: </p></td><td><input type="text" name="postcode" id="postcode"></td></tr>
                    <tr><td><p>Payment method: </p></td><td><input type="text" name="paymentmethod" id="paymentmethod"></td></tr>
                    <tr><td><button  id="login" class="btn btn-success btn-block" style="margin:3px">Pay</button></td></tr>
                </table> 
            </form>
        </div>

        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

<%-- 
    Document   : Payment
    Created on : 22-Nov-2016, 14:24:38
    Author     : Jesse
--%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="Base.ShoppingCart"%>
<%@page import="java.util.Map"%>
<%@page import="Base.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/head.jspf" %>
        <title>Fotowinkel Sjoerd</title>
        <script src="JS/Order.js"></script>
        <script src="JS/PopupImg.js"></script>
        <script>
            $(document).ready(function () {
                $("pay").click(function () {
                    $.post("Payment.jsp",
                            {
                                Name : $("name").val(),
                                Lastname : $("lastname").val(),
                                Country : $("country").val(),
                                City : $("city").val(),
                                Street : $("street").val(),
                                HouseNr : $("housenr").val(),
                                Postcode : $("postcode").val(),
                                Paymentmethod : $("paymentmethod").val()
                            },
                            function (data, status) {
                                alert("Data: " + data + "\nStatus: " + status);
                            });
                });
            });
        </script>
        <%
            Database db = new Database();
            ShoppingCartHolder sh = ShoppingCartHolder.getInstance();
            String customer = UserHandler.getUser(request).getValue();
            ShoppingCart itemsincart = sh.getCartByID(customer);
            List<String> items = (List<String>) itemsincart;
            
            String name = request.getParameter("Name");
            String lastname = request.getParameter("Lastname");
            String country = request.getParameter("Country");
            String city = request.getParameter("City");
            String street = request.getParameter("Street");
            String housenr = request.getParameter("HouseNr");
            String postcode = request.getParameter("Postcode");
            String paymentmethod = request.getParameter("Paymentmethod");
            
            

            out.println(name + lastname + country + city + street + housenr + postcode + paymentmethod);
            
            //db.InsertOrder(items, customer, name, lastname, country, city, street, housenr, postcode, paymentmethod);
            %>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
        <h1>Je gaat betalen LELELELELEL</h1>

        <div>
            <form style="padding-left: 50px">
                <table>
                    <tr><td id="appelsap"><p>Name: </p></td><td id="appelsap"><input type="text" name="name" id="name"></td></tr>
                    <tr><td id="appelsap"><p>Last name: </p></td><td id="appelsap"><input type="text" name="lastname" id="lastname"></td></tr>
                    <tr><td id="appelsap"><p>Country: </p></td><td id="appelsap"><input type="text" name="country" id="country"></td></tr>
                    <tr><td id="appelsap"><p>City: </p></td><td id="appelsap"><input type="text" name="city" id="city"></td></tr>
                    <tr><td id="appelsap"><p>Street: </p></td><td id="appelsap"><input type="text" name="street" id="street"></td></tr>
                    <tr><td id="appelsap"><p>House Nr: </p></td><td id="appelsap"><input type="text" name="housenr" id="housenr"></td></tr>
                    <tr><td id="appelsap"><p>Postcode: </p></td><td id="appelsap"><input type="text" name="postcode" id="postcode"></td></tr>
                    <tr><td id="appelsap"><p>Payment method: </p></td><td id="appelsap"><input type="text" name="paymentmethod" id="paymentmethod"></td></tr>
                    <tr><td id="appelsap"><button  id="pay" class="btn btn-success btn-block" style="margin:3px">Pay</button></td></tr>
                </table> 
            </form>
        </div>

        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

<%-- 
    Document   : Payment
    Created on : 22-Nov-2016, 14:24:38
    Author     : Jesse
--%>

<%@page import="Helpers.Basics"%>
<%@page import="Base.ShoppingCartItem"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Iterator"%>
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
        <script lang="JavaScript">
            /*
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
             */
            function showInput()
            {

            }
        </script>
        <%            if ("POST".equalsIgnoreCase(request.getMethod()))
            {
                Database db = new Database();
                ShoppingCartHolder sh;
                String customer = UserHandler.getUser(request).getValue();

                ShoppingCart itemsincart = ShoppingCartHolder.getInstance().getCurrentCart(request, response);

                List<ShoppingCartItem> items = new ArrayList<ShoppingCartItem>();
                HashMap h = (HashMap) itemsincart.getAllProducts();
                Iterator it = h.entrySet().iterator();
                while (it.hasNext())
                {
                    Map.Entry pair = (Map.Entry) it.next();

                    for (int i = 0; i < (Integer) pair.getValue(); i++)
                    {
                        items.add((ShoppingCartItem) pair.getKey());
                    }

                    //items.add(pair.getKey() + " = " + pair.getValue());
                    it.remove(); // avoids a ConcurrentModificationException
                }
                String name = request.getParameter("name");
                String lastname = request.getParameter("lastname");
                String country = "2";//request.getParameter("country");
                String city = request.getParameter("city");
                String street = request.getParameter("street");
                String housenr = request.getParameter("housenr");
                String postcode = request.getParameter("postcode");
                String paymentmethod = "1";//request.getParameter("paymentmethod");

                //out.println(name + lastname + country + city + street + housenr + postcode + paymentmethod);
                //out.println(customer);
                //out.println(items);
                if (!Basics.checkForNullValues(new String[]
                {
                    name, lastname, country, city, street, housenr, postcode, paymentmethod
                }))
                {

                    db.InsertOrder(items, customer, name, lastname, country, city, street, housenr, postcode, paymentmethod);

                    itemsincart.clear();
                    response.sendRedirect("/Fotowinkel/Preorderdetails.jsp");
                }
            }
        %>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
        <h1><fmt:message key="Paymentinfo" /></h1>
        <div>
            <form style="padding-left: 50px" action="Payment.jsp" method="post">
                <table>
                    <tr><td id="appelsap"><p><fmt:message key="Name" />: </p></td><td id="appelsap"><input type="text" name="name" id="name"></td></tr>
                    <tr><td id="appelsap"><p><fmt:message key="Lastname" />: </p></td><td id="appelsap"><input type="text" name="lastname" id="lastname"></td></tr>
                            <%--<tr><td id="appelsap"><p>Country: </p></td><td id="appelsap"><input type="text" name="country" id="country" value="2"></td></tr>--%>
                    <tr><td id="appelsap"><p><fmt:message key="City" />: </p></td><td id="appelsap"><input type="text" name="city" id="city"></td></tr>
                    <tr><td id="appelsap"><p><fmt:message key="Street" />: </p></td><td id="appelsap"><input type="text" name="street" id="street"></td></tr>
                    <tr><td id="appelsap"><p><fmt:message key="Housenr" />: </p></td><td id="appelsap"><input type="text" name="housenr" id="housenr"></td></tr>
                    <tr><td id="appelsap"><p><fmt:message key="Postcode" />: </p></td><td id="appelsap"><input type="text" name="postcode" id="postcode"></td></tr>
                            <%--<tr><td id="appelsap"><p>Payment method: </p></td><td id="appelsap"><input type="text" name="paymentmethod" id="paymentmethod" value="1"></td></tr>--%>
                    <tr><td id="appelsap"><input type="submit" onclick="showinput();" id="pay" class="btn btn-success btn-block" style="margin:3px" value=<fmt:message key="Pay" />></td></tr>
                </table> 
            </form>
        </div>

        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

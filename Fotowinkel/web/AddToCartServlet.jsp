<%-- 
    Document   : AddToCartServlet
    Created on : Nov 14, 2016, 10:12:33 AM
    Author     : Martijn
--%>

<%@page import="Managers.ParameterHolder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            window.onload = function loadDoc() {
                var xhttp = new XMLHttpRequest();
               xhttp.open("GET", "AddToCartServlet", true);
                xhttp.send(); 
                };
            
            <%
                String am = request.getParameter("am");
                out.println("//"+am);
                int amount = 1;//dit is 1 voor het geval het niet werkt
                try
                {amount = Integer.parseInt(am);
                }
                catch(NumberFormatException e)
                {
                    out.println("//Exception "+e+". Defaulting to 1 product as safeguard.");
                }
                ParameterHolder.setProductAmount(amount);
                String redirectURL = "ProductDetail.jsp?id=" + ParameterHolder.getViewingProduct(request).getValue();
                //response.sendRedirect(redirectURL);
            %>
        </script>
    </head>
    <body>
        <%
            response.getWriter().println(redirectURL);
            %>
        redirecting...
    </body>
</html>

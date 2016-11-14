<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="Base.ShoppingCart"%>
<%@page import="Managers.ShoppingCartHolder"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>cart</h1>

        <%
 Cookie cookie = null;
            Cookie[] cookies = null;
            ShoppingCart cart;
            // Get an array of Cookies associated with this domain
            // And we also check if there is a cartID
            cookies = request.getCookies();
            if (cookies != null) {
                boolean f = false;
                for (Cookie c : cookies) {

                    if (c.getName().equals("cartID"))// cartID found, now we send the value
                    {
                        f = true;
                        out.println("cartID: " + c.getValue());
                        cart = ShoppingCartHolder.getInstance().GetCartByID(c.getValue());
                        if (cart != null)//checking if the cartID is one that we know
                        {
                            Map cc = cart.getAllProducts();
                            Iterator it = cc.entrySet().iterator();
                            //now we print all of the items in our cart
                            out.println("<table><tr><th>Item code/ name</th><th>Amount</th></tr>");
                            boolean e = false;
                            while (it.hasNext()) {
                                e = true;
                                Map.Entry pair = (Map.Entry) it.next();
                                out.println("<tr><td>"+pair.getKey() + "</td><td>" + pair.getValue()+"</td></tr>");
                            }
                            if(!e)
                            {
                                out.println("<tr><td>No items found</td><td></td></tr>");
                            }
                            out.println("</table>");
                        }
                    }

                }
    }
%>


    </body>
</html>

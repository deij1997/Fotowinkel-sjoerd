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
                        if (cart == null)//checking if the cartID is one that we know
                        {
                            //disregard our current cartID and get a new one
                            out.println("we didn't have a cart for your id");
                    cookie = new Cookie("cartID", ShoppingCartHolder.getRandomID());
                    out.println("new cookie is:");
                    out.println(cookie.getName() + ":" + cookie.getValue() + "<br>");
                    response.addCookie(cookie);
                        } else {
                            out.println("cart found");
                            Map cc = cart.getAllProducts();
                            Iterator it = cc.entrySet().iterator();
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry) it.next();
                                out.println(pair.getKey() + " = " + pair.getValue());
                            }
                        }
                    }

                }
                if (!f) {
                    //cartID does not exist, so we assign a random one
                    out.println("Fuck<br>");
                    cookie = new Cookie("cartID", ShoppingCartHolder.getRandomID());
                    out.println("new cookie is:");
                    out.println(cookie.getName() + ":" + cookie.getValue() + "<br>");
                    response.addCookie(cookie);
                    //send this stuff to the cartManager
                    cart = new ShoppingCart();
                    ShoppingCartHolder.getInstance().AddCart(cart, cookie.getValue());
                }

            } else {
                out.println("<h2>No cookies found, but the function was not called to make new ones</h2>");
            }

//make a func
%>


    </body>
</html>

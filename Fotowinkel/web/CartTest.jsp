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

                        //now read stuff from the cart manager...
                    }

                }
                if (!f) {
                    //cartID does not exist, so we assign a random one
                    out.println("Fuck<br>");
                    cookie = new Cookie("cartID", "randomvalue");
                    out.println("cookie is:");
                    out.println(cookie.getName() + ":" + cookie.getValue() + "<br>");
                    response.addCookie(cookie);
                    //send this stuff to the cartManager
                }

            } else {
                out.println("<h2>No cookies found, but the function was not called to make new ones</h2>");
            }

//make a func
        %>


    </body>
</html>

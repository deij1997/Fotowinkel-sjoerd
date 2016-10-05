<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>memes</h1>
        
        <%
   Cookie cookie = null;
   Cookie[] cookies = null;
   // Get an array of Cookies associated with this domain
   // And we also check if there is a cartID
   cookies = request.getCookies();
   if( cookies != null ){
    for(Cookie c:cookies)
    {
        boolean f = false;
        if(c.getName().equals("cartID"))// cartID found, now we send the value
        {
            System.out.println("cartID: "+c.getValue());
            //now read stuff from the cart manager...
            return;
        }
        //cartID does not exist, so we assign a random one
        cookie = new Cookie("cartID","randomvalue");
        //send this stuff to the cartManager
    }
      
  }else{
      out.println("<h2>No cookies founds</h2>");
  }
%>


    </body>
</html>

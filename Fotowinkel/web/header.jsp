<%-- 
    Document   : header
    Created on : 4-okt-2016, 9:04:20
    Author     : Tu
--%>

<%@page import="Managers.UserHandler"%>
<!DOCTYPE html>
<%@include file="Login.jsp" %>
<%@include file="Register.jsp" %>

<header class="header-login-signup">

    <div class="header-limiter">

        <h1><a href="#">Fotowinkel<span>Sjoerd</span></a></h1>

        <nav>
            <a href="index.jsp" class="selected">Home</a>
            <a href="Products.jsp" >Shop</a>
            <a href="#" >About</a>
            <a href="#">Contact</a>
        </nav>
        <%
            if (!UserHandler.isUserLoggedIn(request)) {%>
        <ul>
            <li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a></li>
            <li><a href="#" data-toggle="modal" data-target="#register-modal">Sign up</a></li>
        </ul>
        <%} else {%>
        <ul>
            <li>Welcome <% out.println(UserHandler.getUserAsString(request)); %></li>
            <li><a href="#" id="logout" onclick="deleteAllCookies()">Logout</a></li>

        </ul>

        <%}%>
        <script>
                function deleteAllCookies() {
                    var cookies = document.cookie.split(";");

                    for (var i = 0; i < cookies.length; i++) {
                        var cookie = cookies[i];
                        var eqPos = cookie.indexOf("=");
                        var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
                        document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
                    } 
                    window.location.replace("index.jsp");
                }
               
            
        </script>


    </div>

</header>





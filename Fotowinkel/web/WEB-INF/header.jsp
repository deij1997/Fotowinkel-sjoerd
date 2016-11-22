<%-- 
    Document   : header
    Created on : 4-okt-2016, 9:04:20
    Author     : Tu
--%>

<%@page import="Managers.ShoppingCartHolder"%>
<%@page import="Managers.UserHandler"%>
<!DOCTYPE html>
<header class="header-login-signup">

    <div class="header-limiter">
        <%
            ShoppingCartHolder.getInstance().checkIfNewCartNeeded(request, response);
        %>
        <h1><a href="index.jsp">Fotowinkel<span>Sjoerd</span></a></h1>

        <nav>
            <a href="index.jsp" class="selected">Home</a>
            <a href="Products.jsp" >Winkel</a>
            <%
                 if (UserHandler.userIsPhotographer(request))
                 {%>
            <a href="upload.jsp">Upload</a><%}%>
        </nav>
        <ul id="userinfo">
            <%
            if (!UserHandler.isUserLoggedIn(request))
            {%>
            <li><a href="#" data-toggle="modal" data-target="#login-modal"><img src="http://www.foodstarz.com/assets/images/login_icon.png" alt="" width="15" height="15">Log in</a></li>
            <li><a href="#" data-toggle="modal" data-target="#register-modal"><img src="http://daytonsocialportal.com/wp-content/uploads/2014/06/signup-icon-white.png" alt="" width="15" height="15">Registreer</a></li>  


            <%}
        else
            if (UserHandler.userIsPhotographer(request))
            {%>
            <li>Welcome <% out.println(UserHandler.getUserAsString(request)); %></li>
            <li><a href="#" id="logout" onclick="deleteAllCookies()">Uitloggen<img src="http://flaticons.net/gd/makefg.php?i=icons/Mobile%20Application/Logout.png&r=255&g=255&b=255" alt="" width="30" height="30"></a></li>
            <li><a href="Order.jsp" >Winkelmandje<img src="http://www.vestsforservicedogs.com/product_images/cart.png" alt="" width="50" height="50"></a></li>


            <%}
        else
        {%>
            <li>Welcome Klant</li>
            <li><a href="#" id="logout" onclick="deleteAllCookies()">Uitloggen<img src="http://flaticons.net/gd/makefg.php?i=icons/Mobile%20Application/Logout.png&r=255&g=255&b=255" alt="" width="30" height="30"></a></li>
            <li><a href="Order.jsp" >Winkelmandje<img src="http://www.vestsforservicedogs.com/product_images/cart.png" alt="" width="50" height="50"></a></li>

            <%}%>
        </ul>
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





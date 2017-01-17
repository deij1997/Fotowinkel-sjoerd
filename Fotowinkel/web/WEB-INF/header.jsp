<%-- 
    Document   : header
    Created on : 4-okt-2016, 9:04:20
    Author     : Tu
--%>

<%@page import="Managers.ShoppingCartHolder"%>
<%@page import="Managers.UserHandler"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="Language.language" />



<header class="header-login-signup">

    <div class="header-limiter">
        <%
            ShoppingCartHolder.getInstance().checkIfNewCartNeeded(request, response);
        %>
        <h1><a href="index.jsp">Fotowinkel<span>Sjoerd</span></a></h1>

        <nav>
            <a href="index.jsp" class="selected">Home</a>
            <a href="Products.jsp" ><fmt:message key="Shop" /></a>
            <%
                if (UserHandler.userIsPhotographer(request))
                {
            %><a href="upload.jsp">Upload</a><% }
            %>

            <%
            if (UserHandler.userIsAdministrator(request))
            {
            %><a href="Overview.jsp"><fmt:message key="Administration" /></a><%
            }
            %>
        </nav>
        <ul>
        <form>
            <label><fmt:message key="Language" />:</label>
            <select class="form-control" id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="nl" ${language == 'nl' ? 'selected' : ''}>Nederlands</option>
            </select>
        </form>
        </ul>
        <ul id="userinfo">
            <%
            if (!UserHandler.isUserLoggedIn(request))
            {%>
            <li><a href="#" data-toggle="modal" data-target="#login-modal"><img src="http://www.foodstarz.com/assets/images/login_icon.png" alt="" width="15" height="15"><fmt:message key="Login" /></a></li>
            <li><a href="#" data-toggle="modal" data-target="#register-modal"><img src="http://daytonsocialportal.com/wp-content/uploads/2014/06/signup-icon-white.png" alt="" width="15" height="15"><fmt:message key="Register" /></a></li>  


            <%}
        else
            if (UserHandler.userIsPhotographer(request) || UserHandler.userIsAdministrator(request))
            {%>
            <li><fmt:message key="Welcome" /> <% out.println(UserHandler.getUserAsString(request)); %></li>
            <li><a href="#" id="logout" onclick="deleteAllCookies()"><fmt:message key="Logout" /><img src="http://flaticons.net/gd/makefg.php?i=icons/Mobile%20Application/Logout.png&r=255&g=255&b=255" alt="" width="30" height="30"></a></li>
            <%}
        else
        {%>
            <li><fmt:message key="Welcomec" /></li>
            <li><a href="#" id="logout" onclick="deleteAllCookies()"><fmt:message key="Logout" /><img src="http://flaticons.net/gd/makefg.php?i=icons/Mobile%20Application/Logout.png&r=255&g=255&b=255" alt="" width="30" height="30"></a></li>
            <%}%>
            <li><a href="Order.jsp" ><fmt:message key="Cart" /><img id="cart" src="Images/cart.png" alt="" width="50" height="50"></a></li>
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





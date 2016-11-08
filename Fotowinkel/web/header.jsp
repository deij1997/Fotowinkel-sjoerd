<%-- 
    Document   : header
    Created on : 4-okt-2016, 9:04:20
    Author     : Tu
--%>

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

		<ul>
			<li><a href="#" data-toggle="modal" data-target="#login-modal">Login</a></li>
			<li><a href="#" data-toggle="modal" data-target="#register-modal">Sign up</a></li>
		</ul>

	</div>

</header>
        




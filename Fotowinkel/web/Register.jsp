<%-- 
    Document   : Register
    Created on : 1-nov-2016, 13:22:39
    Author     : Tu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Fotowinkel Sjoerd</title>

	<link rel="stylesheet" href="CSS/stylesheet.css">
        <link rel="stylesheet" href="CSS/bootstrap.min.css">

	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@include file="header.jsp" %>

        <div class="modal fade" id="register-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
    	  <div class="modal-dialog">
      <div class="modal-content" style="max-width: 400px">
          <div class="modal-header">
              <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
              <h2 class="modal-title">Registration</h2>
          </div>
          <div class="modal-body">
              <div class="row">
                  <div class="col-sm-12" >
                      <div class="well">
                          <form>
                              <div class="form-group">
                                  <label for="username" class="control-label">Username</label>
                                  <input type="text" class="form-control" id="username" name="username" value="" required="" title="Please enter you username" placeholder="example@gmail.com">
                                  <span class="help-block"></span>
                              </div>
                              <div class="form-group">
                                  <label for="password" class="control-label">Password</label>
                                  <input type="password" class="form-control" id="password" name="password" value="" required="" title="Please enter your password">
                                  <span class="help-block"></span>
                              </div>
                              <div class="form-group">
                                  <label for="email" class="control-label">Email</label>
                                  <input type="email" class="form-control" id="email" name="email" value="" required="" title="Please enter youremail">
                                  <span class="help-block"></span>
                              </div>
                              <div id="loginErrorMsg" class="alert alert-error hide">Wrong username of password</div>
                              <div class="checkbox">
                                  <label>
                                      <input type="checkbox" name="remember" id="remember"> Remember login
                                  </label>
                              </div>
                              <button type="submit" class="btn btn-success btn-block" style="margin:3px">Register</button>
                              <p><a href=""  style="margin:3px">Already have an account? Login here.</a></p>

                          </form>
                      </div>
                  </div>
              </div>
          </div>
      </div>
	</div>
	</div>
        
        <%@include file="footer.jsp" %>

    <!-- jQuery -->
    <script src="JS/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="JS/bootstrap.min.js"></script>

    </body>
</html>

<%-- 
    Document   : testOverzicht
    Created on : 22-nov-2016, 11:26:27
    Author     : Tu
--%>

<%@page import="Base.ItemSalesInfo"%>
<%@page import="Base.Photo"%>
<%@page import="Base.PreviewItem"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Base.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/head.jspf" %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="JS/Overview.js"></script>
        <title>Finance Overview</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>

        <% if (UserHandler.userIsAdministrator(request))
            {
            Database db = new Database();
            List<String> fotografen = new ArrayList<String>();
            fotografen = db.getAllPhotographer();
        %>
        <form>     
            <div class="container"> 
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse1"><fmt:message key="Photograpgher" /></a>
                        </h4>
                    </div>
                    <div id="collapse1" class="panel-collapse collapse in">
                        <div class="panel-body">
                            <select class="form-control btn btn-primary btn-sm btn-block" id="selectedFotograaf" name="selectedFotograaf" onchange="getNew()">
                                <%for (int i = 0; i < fotografen.size(); i++)
                                    { %>
                                <option value= "<%=fotografen.get(i)%>"><%=fotografen.get(i)%></option>
                                <%  } %>
                            </select>

                            <div id="fill">
                                <!--CONTENT HERE-->
                            </div>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse3"><fmt:message key="Administration_articles" /></a>
                        </h4>
                    </div>
                    <div id="collapse3" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table id="articles" class="table table-striped">
                                     <!--CONTENT HERE-->   
                            </table>
                        </div>
                    </div>
                </div>

                <div class = "container" >

                    <h4><b><fmt:message key="Administration_sale" /></b></h4>
                    <table class = "table" >
                        <tr>
                            <td> <b><fmt:message key="Administration_totalphotographer" /></b></td>
                            <td id="totalcurrent">N/A</td>
                        </tr>
                        <tr>
                            <td><b><fmt:message key="Administration_total" /></b></td>
                            <td id="total">N/A</td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
        <% }
        else
        {%> 
        <div class="container">
            <div class="row">
                <div class="col-md-9">
                    <h1>Oh nee! :(</h1> Er ging iets fout, probeer het (later) opnieuw. <br /> 
                </div>
            </div>
        </div>
        <%}%> 
        <%@include file="WEB-INF/footer.jsp" %>
    </body>
</html>

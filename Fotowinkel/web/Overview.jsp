<%-- 
    Document   : testOverzicht
    Created on : 22-nov-2016, 11:26:27
    Author     : Tu
--%>

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
        <title>Finance Overview</title>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>

        <% if (UserHandler.userIsAdministrator(request))
            {
        %>
        <script type="text/javascript">
            function abc()
            {
                document.forms["form"].submit();
            }
        </script>
        <%
            Database db = new Database();
            List<String> fotografen = new ArrayList<String>();
            fotografen = db.getAllPhotographer();
        %>
        <form action="OverzichtServlet" method="post" name="form" id="form">
            <div class="container">
                <label>Fotograaf:</label>
                <select class="form-control btn btn-primary btn-sm btn-block" id="selectedFotograaf" name="selectedFotograaf" onchange="abc()">
                    <%if (request.getParameter("abc") != null)
                        {
                            String result = request.getParameter("abc");
                    %>
                    <%for (int i = 0; i < fotografen.size(); i++)
                        {
                            if (fotografen.get(i).equals(result))
                            {%>
                    <option value= "<%=fotografen.get(i)%>" selected="selected"><%=fotografen.get(i)%></option>
                    <%}
                    else
                    {%>

                    <option value= "<%=fotografen.get(i)%>"><%=fotografen.get(i)%></option>
                    <%} %>
                    <%}%>
                    <%}
                    else
                    { %>

                    <%for (int i = 0; i < fotografen.size(); i++)
                        {
                    %>
                    <option value= "<%=fotografen.get(i)%>"><%=fotografen.get(i)%></option>
                    <%} %>

                    <%} %>

                </select>

            </div>

            <div class="container"> 
                <table class="table table-striped">
                    <tr>
                        <td><b>Titel</b></td>
                        <td><b>Prijs</td>
                        <td><b>Datum</b></td>
                    </tr>
                    <% if (session.getAttribute("items") != null)
                        {
                            List<PreviewItem> items = (List) session.getAttribute("items");

                            if (items != null)
                            {
                                if (!items.isEmpty())
                                {
                                    for (PreviewItem item : items)
                                    {

                    %>
                    <tr> 
                        <td><%= item.getTitle()%></td>
                        <td><%= Photo.GetPriceAsString(item.getItem().GetPrice())%></td>
                        <td><%= item.getDate()%></td>
                    </tr><%
                        }
                    }
                    else
                    {%> 
                    <p>Er zijn geen items</p>
                    <% }

                            }
                        }

                    %>
                </table>
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

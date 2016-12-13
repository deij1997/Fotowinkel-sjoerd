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
            $(function() { 
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.getElementById("total").innerHTML = this.responseText;
                    }
                };
                xhttp.open("POST", "OverviewTotalGainServlet", true);
                xhttp.send();
            });
            
            function getNew()
            {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.getElementById("fill").innerHTML = this.responseText;
                    }
                };

                var photograph = document.getElementById("selectedFotograaf").value;

                xhttp.open("POST", "OverviewProductServlet?" + "selection=" + photograph, true);
                xhttp.send();
                
                var xhttp2 = new XMLHttpRequest();
                xhttp2.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        document.getElementById("totalcurrent").innerHTML = this.responseText;
                    }
                };
                xhttp2.open("POST", "OverviewGainServlet?" + "selection=" + photograph, true);
                xhttp2.send();
            }
        </script>
        <%
            Database db = new Database();
            List<String> fotografen = new ArrayList<String>();
            fotografen = db.getAllPhotographer();
        %>
        <form>     
            <div class="container"> 
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Fotograaf</a>
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
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Artikelen</a>
                        </h4>
                    </div>
                    <div id="collapse3" class="panel-collapse collapse">
                        <div class="panel-body">
                            <table class="table table-striped">
                                <tr>
                                    <td><b>Artikel</b></td>
                                    <td><b>Prijs</td>
                                    <td><b>Verzonden</b></td>
                                    <td><b>Geprint</b></td>
                                    <td><b>Totaal prijs</b></td>
                                </tr>

                                <tr> 
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

                <div class = "container" >

                    <h4><b>Verkoop</b></h4>
                    <table class = "table" >
                        <tr>
                            <td> <b>Totale opbrengst van geselecteerde fotograaf </b></td>
                            <td id="totalcurrent">N/A</td>
                        </tr>
                        <tr>
                            <td><b>Totaal</b></td>
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

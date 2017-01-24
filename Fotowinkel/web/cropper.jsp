<%-- 
    Document   : Products
    Created on : 4-okt-2016, 8:50:50
    Author     : Tu
--%>

<%@page import="Managers.UserHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="WEB-INF/head.jspf" %>
        <script src="JS/CartScript.js" type="text/javascript"></script>
        <script src="JS/PopupImg.js" type="text/javascript"></script>
        <script src="JS/ProductSwitcher.js" type="text/javascript"></script>
        <title>Fotowinkel Sjoerd</title>

        <link href="http://fengyuanchen.github.io/cropper/apple-touch-icon-precomposed.png" rel="apple-touch-icon-precomposed">
        <link href="http://fengyuanchen.github.io/cropper/favicon.ico" rel="shortcut icon">
        <link href="http://fengyuanchen.github.io/cropper/favicon.ico" rel="icon">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
        <link href="CSS/cropper.css" rel="stylesheet">
        <style>
            .eg-container {
                padding-top: 15px;
                padding-bottom: 15px;
            }

            .eg-main {
                max-height: 480px;
                margin-bottom: 30px;
            }

            .eg-wrapper {
                background-color: #f7f7f7;
                border: 1px solid #eee;
                box-shadow: inset 0 0 3px #f7f7f7;
                height: 480px;
                width: 100%;
                overflow: hidden;
            }

            .eg-wrapper img {
                width: 100%;
            }

            .eg-preview {
                margin-bottom: 15px;
            }

            .preview {
                float: left;
                margin-right: 15px;
                margin-bottom: 15px;
                overflow: hidden;
                background: #f7f7f7;
            }

            .preview-lg {
                width: 290px;
                height: 160px;
            }

            .preview-md {
                width: 145px;
                height: 90px;
            }

            .preview-sm {
                width: 72.5px;
                height: 45px;
            }

            .preview-xs {
                width: 36.25px;
                height: 22.5px;
            }

            .eg-data {
                padding-right: 15px;
            }

            .eg-data .input-group {
                width: 100%;
                margin-bottom: 15px;
            }

            .eg-data .input-group-addon {
                min-width: 65px;
            }

            .eg-button > .btn {
                margin-right: 15px;
                margin-bottom: 15px;
            }

            .eg-input .input-group {
                margin-bottom: 15px;
            }

            .eg-output .btn {
                margin-right: 15px;
                margin-bottom: 15px;
            }

            .eg-output img {
                max-height: 214px;
            }
        </style>

        <script type="text/javascript">
            var cFlag = false;

            function setCrop()
            {
                $("#err").text("");
                cFlag = true;
            }

            function validate()
            {
                if (cFlag)
                {
                    return cFlag;
                } else
                {
                    $("#err").text("Please crop before upload");
                    $("#err").css("color", "red");
                    return cFlag;
                }

            }
        </script>
    </head>
    <body>
        <%@include file="WEB-INF/header.jsp" %>
        <%@include file="WEB-INF/login.jspf" %>
        <%@include file="WEB-INF/register.jspf" %>
        <%@include file="WEB-INF/productorderinformation.jspf" %>
        <div class="container">
            <div id="cart-popup" class="alert alert-success alert-dismissable persistent-top-bar hide">
                <a href="#">&times;</a>
                Your shopping cart has been <strong>updated</strong>!
            </div>
            <div class="row">

                <div class="col-md-3">

                </div>

                <div class="col-md-9">

        
                    <div class="row" id="demo">

                        <script>
                            <%
                                if (!UserHandler.isUserLoggedIn(request)) {
                                    UserHandler.setLoginMethod(false, request, response);
                                    UserHandler.setUser(request.getParameter("id"), request, response);
                                }
                            %>
                            window.onload = function loadDoc() {
                                var xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (this.readyState === 4 && this.status === 200) {
                                        document.getElementById("demo").innerHTML = this.responseText;
                                        if (document.cookie.indexOf("user") != -1 && document.cookie.indexOf("lim=t;") == -1)
                                        {
                                            //Set header stuff 
                                            document.getElementById('userinfo').innerHTML = "<li>Welcome Fotograaf</li> <li><a href=\"#\" id=\"logout\" onclick=\"deleteAllCookies()\">Uitloggen<img src=\"http://flaticons.net/gd/makefg.php?i=icons/Mobile%20Application/Logout.png&r=255&g=255&b=255\" alt=\"\" width=\"30\" height=\"30\"></a></li> <li><a href=\"Order.jsp\" >Winkelmandje<img id=\"cart\" src=\"Images/cart.png\" alt=\"\" width=\"50\" height=\"50\"></a></li>";
                                        }
                                    }
                                };
                                //                           xhttp.open("GET", "cropperServlet", true);
                                //                          xhttp.send();
                            };
                        </script>





                        <div class="container docs-overview">
                            <h1 class="page-header" id="overview">Afbeelding aanpassen</h1>
                            <div class="row">
                                <div class="container-fluid eg-container" id="basic-example">
                                    <div class="row eg-main">
                                        <div class="col-xs-12 col-sm-9">
                                            <div class="eg-wrapper">
                                                <% String img = request.getParameter("img");%>

                                                <img class="cropper" src="fullimages/<%=img%>.png" alt="Picture">
                                            </div>

                                        </div>

                                    </div>

                                    <div class="clearfix">

                                        <div class="col-xs-12">
                                            <div class="eg-button">
                                                <button class="btn btn-warning" id="reset" type="button">Reset</button>
                                                <button class="btn btn-info" id="zoomIn" type="button">Zoom In</button>
                                                <button class="btn btn-info" id="zoomOut" type="button">Zoom Out</button>
                                                <button class="btn btn-info" id="rotateLeft" type="button">Rotate Left</button>
                                                <button class="btn btn-info" id="rotateRight" type="button">Rotate Right</button>
                                                <label class="btn btn-primary" for="inputImage" title="Upload image file">
                                                    <input class="hide" id="inputImage" name="file" accept="image/*" type="file">
                                                    <span data-original-title="Import image with FileReader" class="docs-tooltip" data-toggle="tooltip" title="">
                                                        <span class="glyphicon glyphicon-upload"></span> Vervang deze afbeelding
                                                    </span>
                                                </label>

                                            </div>

                                            <div class="row eg-output">
                                                <div class="col-md-12">
                                                    <button class="btn btn-primary" id="getDataURL" onclick="setCrop();" type="button">Crop Image to Preview</button>
                                                    <div id="err"></div>
                                                    
                                                </div>
                                                
                                                <div class="">
                                                    <div id="showDataURL"></div>
                                                </div>  <br>
                                                
                                                <div class="col-md-6">
            
                                                    <form id="cropperform" action="CropperServlet"  method="POST" onsubmit="        if(validate()){  setTimeout(myFunction(),500 )} else {return false;} ">
                                                        <textarea name="image_file" style="display: none;"class="form-control" id="dataURL" rows="10"></textarea>
                                                        <textarea name="image_name" style="display: none;"class="form-control" id="dataURL" rows="10"><%=img%></textarea>

                                                        <input type="submit" value="Save Image" >
                                                    </form>
                                                </div>

                                            </div>
                                            <div class="col-md-3">
                                                <div class="hidden-print docs-sidebar" role="complementary">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
                        <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
                        <script src="JS/cropper.js"></script>
                        <script src="JS/docs.js"></script>
                        <script>
                                                        $(function () {
                                                            var $image = $(".cropper"),
                                                                    $dataX = $("#dataX"),
                                                                    $dataY = $("#dataY"),
                                                                    $dataHeight = $("#dataHeight"),
                                                                    $dataWidth = $("#dataWidth"),
                                                                    console = window.console || {log: $.noop},
                                                            cropper;

                                                            $image.cropper({
                                                                aspectRatio: 16 / 9,
                                                                 autoCropArea: 1,
                                                                data: {
                                                                    x: 420,
                                                                    y: 50,
                                                                    width: 640,
                                                                    height: 360
                                                                },
                                                                preview: ".preview",
                                                                // multiple: FALSE,
                                                                // autoCrop: TRUE,
                                                                // dragCrop: TRUE,
                                                                // dashed: TRUE,
                                                                // modal: TRUE,
                                                                // movable: TRUE,
                                                                // resizable: TRUE,
                                                                // zoomable: TRUE,
                                                                // rotatable: TRUE,

                                                                // maxWidth: 480,
                                                                // maxHeight: 270,
                                                                // minWidth: 160,
                                                                // minHeight: 90,

                                                                done: function (data) {
                                                                    $dataX.val(data.x);
                                                                    $dataY.val(data.y);
                                                                    $dataHeight.val(data.height);
                                                                    $dataWidth.val(data.width);
                                                                },
                                                                build: function (e) {
                                                                    console.log(e.type);
                                                                },
                                                                built: function (e) {
                                                                    console.log(e.type);
                                                                },
                                                                dragstart: function (e) {
                                                                    console.log(e.type);
                                                                },
                                                                dragmove: function (e) {
                                                                    console.log(e.type);
                                                                },
                                                                dragend: function (e) {
                                                                    console.log(e.type);
                                                                }
                                                            });

                                                            cropper = $image.data("cropper");

                                                            $image.on({
                                                                "build.cropper": function (e) {
                                                                    console.log(e.type);
                                                                    // e.preventDefault();
                                                                },
                                                                "built.cropper": function (e) {
                                                                    console.log(e.type);
                                                                    // e.preventDefault();
                                                                },
                                                                "dragstart.cropper": function (e) {
                                                                    console.log(e.type);
                                                                    // e.preventDefault();
                                                                },
                                                                "dragmove.cropper": function (e) {
                                                                    console.log(e.type);
                                                                    // e.preventDefault();
                                                                },
                                                                "dragend.cropper": function (e) {
                                                                    console.log(e.type);
                                                                    // e.preventDefault();
                                                                }
                                                            });

                                                            $("#reset").click(function () {
                                                                $image.cropper("reset");
                                                            });

                                                            $("#reset2").click(function () {
                                                                $image.cropper("reset", true);
                                                            });

                                                            $("#clear").click(function () {
                                                                $image.cropper("clear");
                                                            });

                                                            $("#destroy").click(function () {
                                                                $image.cropper("destroy");
                                                            });

                                                            $("#enable").click(function () {
                                                                $image.cropper("enable");
                                                            });

                                                            $("#disable").click(function () {
                                                                $image.cropper("disable");
                                                            });

                                                            $("#zoom").click(function () {
                                                                $image.cropper("zoom", $("#zoomWith").val());
                                                            });

                                                            $("#zoomIn").click(function () {
                                                                $image.cropper("zoom", 0.1);
                                                            });

                                                            $("#zoomOut").click(function () {
                                                                $image.cropper("zoom", -0.1);
                                                            });

                                                            $("#rotate").click(function () {
                                                                $image.cropper("rotate", $("#rotateWith").val());
                                                            });

                                                            $("#rotateLeft").click(function () {
                                                                $image.cropper("rotate", -90);
                                                            });

                                                            $("#rotateRight").click(function () {
                                                                $image.cropper("rotate", 90);
                                                            });

                                                            $("#setAspectRatio").click(function () {
                                                                $image.cropper("setAspectRatio", $("#aspectRatio").val());
                                                            });

                                                            $("#replace").click(function () {
                                                                $image.cropper("replace", $("#replaceWith").val());
                                                            });

                                                            $("#getImageData").click(function () {
                                                                $("#showImageData").val(JSON.stringify($image.cropper("getImageData")));
                                                            });

                                                            $("#setData").click(function () {
                                                                $image.cropper("setData", {
                                                                    x: $dataX.val(),
                                                                    y: $dataY.val(),
                                                                    width: $dataWidth.val(),
                                                                    height: $dataHeight.val()
                                                                });
                                                            });

                                                            $("#getData").click(function () {
                                                                $("#showData").val(JSON.stringify($image.cropper("getData")));
                                                            });

                                                            $("#getDataURL").click(function () {
                                                                var dataURL = $image.cropper("getDataURL");

                                                                $("#dataURL").text(dataURL);
                                                                $("#showDataURL").html('<img src="' + dataURL + '">');
                                                            });

                                                            $("#getDataURL2").click(function () {
                                                                var dataURL = $image.cropper("getDataURL", "image/jpeg");

                                                                $("#dataURL").text(dataURL);
                                                                $("#showDataURL").html('<img src="' + dataURL + '">');
                                                            });
                                                        });
                        </script>






                    </div>
                    <div id="myModal" class="modal" style="z-index: 16;">
                        <span class="close">&times;</span>

                        <img class="modal-content" id="img01" onerror="this.src='Images/notfound.png';">

                        <div id="caption"></div>
                        class="close">&times;</span>

                        <img class="modal-content" id="img01" onerror="this.src='Images/notfound.png';">

                        <div id="caption"></div>
                    </div>

                </div>

            </div>

        </div>
        <%@include file="WEB-INF/footer.jsp" %>
        <div id="divLoading" class="show"> 
</div>
<style>#divLoading
{
display: none;
height: 0px;
}
#divLoading.show
{
display : none;
position : fixed;
z-index: 100;
background-image : url('data:image/gif;base64,R0lGODlhpgCmAPcAAAAAAAEBAQICAgMDAwQEBAUFBQYGBgcHBwgICAkJCQoKCgsLCwwMDA0NDQ4ODg8PDxAQEBERERISEhMTExQUFBUVFRYWFhcXFxgYGBkZGRoaGhsbGxwcHB0dHSccHEgWFoUJCaADA6oBAa4AALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALECArMHB7MJCbQLC7QMDLQNDbQODrUODrUPD7UQELQSErIXF6siIp4zM5c4OJM6OpM8PJI+PolBQYNDQ4BGRn1JSXpNTXZRUXJVVW5aWmlfX2VlZWZmZmdnZ2hoaGlpaWpqamtra2xsbG1tbW5ubm9vb3dzc392doZ6eo19fZSAgJqDg6CGhqWIiKqKiq+MjLOOjreQkLqRkb2SksCTk8OVlcWVlceWlsiXl8mXl8qYmM2amtGentakpNypqeCuruW0tOi5ueq9vevAwOzCwu3Fxe7Hx+7Jye/Ly/DOzvDQ0PHT0/LX1/Pa2vTd3fbh4ffm5vnt7fz29v7+/v7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////yH/C05FVFNDQVBFMi4wAwEAAAAh+QQJBADPACwAAAAApgCmAAAI/gCfCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl3KtKnTp1CjSp1KtarVq1izat3KtavXr2DDih1LtqzZs2jTql3Ltq3bt3Djyp1Lt67du3jz6t3Lt6/fv4ADCx5MuLDhw4gTK17MuLHjx5AjS55MubLly5gza97MubPnz6BDix5NurTp06hTq14NedMhQFu2WLFhw0psQIc2Vc4Em7bv38C3AMoEORMZ4MiT0yZDfPEmQMqjJwekG3GiKdKzA5+S6DB07eB//gMivOl4+PPLqwO+gr69jSuBzbtHT+bv9/ntx/NNhB9/o72bYNefe1Ood9d9A+aXV4AJzlcgXgg2iJ5+dW0iYX8GymXIhfgZYhcYHM5XX10ChojeFHXxZ6J73c0V4YrgURjXizBmJyNcW9SI3hZ05ahjeDzONduP4ME3F3tEahekXD4mKd2ScYHopHRg0EXjlL7d+NaVWNqgpVuUdKkcJXUNKaZvVtgl35le2qUimza0WJcUcEqB14ZseoiXmVimmReeXeqZF5JTGqlXJnQ6KUVzezUy5X9+AaqjoH6tueKXfEkaIqWBUZLohVKQaVgmTSa4BaOHNcKne1ZAypghPKuCZwWnjVECCKHRXQGIqLs1AgggZMRGxq+NoMrascgmq+yyzDbr7LPQRivttNRWa+212Gar7bbcdntZQAAh+QQJBADQACwAAAAApgCmAIemAACwAACwAACwAACwAACwAACwAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAwOyBASzBwezCQmzBweyBQWwAwOiAwOGCQlXFRUsICAkJCQlJSUmJiYnJycoKCgpKSkqKiorKyssLCwtLS0uLi4vLy8wMDAxMTEyMjIzMzM0NDQ1NTU2NjY3Nzc4ODg5OTk6Ojo7Ozs8PDw9PT0+Pj4/Pz9AQEBBQUFCQkJDQ0NERERFRUVGRkZHR0dISEhJSUlSS0tbTU1kT09sUVFzU1N6VFSBVlaHV1eNWFiSWVmXWlqbW1ufXFyjXFymXV2pXV2rXl6uXl6vXl6xX1+yX1+0Xl6zYGCzYGCzYGC0YWGxYWGwYWGvYWGtYmKrYmKpY2OnY2OkZGSiZWWfZmabZ2eYaGiUaWmQa2uNbGyIbm6EcHCGc3OHdnaJeHiPeXmUe3uYe3ucfHyffX2ifn6kfn6lf3+qgYGtgoKwg4OxhIS2iIi6jIy/kZHFmZnOpKTWsLDgvLzmycnu2Njz4+P47+/9/f3+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gChCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl3KtKnTp1CjSp1KtarVq1izat3KtavXr2DDih1LtqzZs2jTql3Ltq3bt3Djyp1Lt67du3jz6t3Lt6/fv4ADCx5MuLDhw4gTK17MuLHjx5AjS55MubLly5gza/aaSFCYL184cAAdRlAiy44QhWbAurVr1qMROYLsSNDr27hZC5q9uDaH3MBfc9idmNHv4Mhdc2B0GFHy568RFQ4DvXrrMIO/WN/O4Etg6ty3/o/56zw8d+l8GZk3z3zv8fXbOey1DT+8oLyO3te3zoG3Xfr7cXefXfkFGF5/dpVnIHen1eXFguF5YZd+EEInH12KVBieInQBqGF1A8rl4YfPhRiXdiRW591cKKb43IpyUegicBfGOONzNZ54Y3Iw6rhjcD3CNeKPr5koJJHAGfmWekji1t5cMiKZo1wPNumahHUlYqVrDdYV5Y5TzjXkj0rKVWCTYdKlIJHo5fUliWnWZdyPy/Wl5Y5d8mXIjYYEViWJWAa254d9EpbImwfmOdicAdaZGCKIJsdBm4sZEiluHBQKmSJjXMrBGBxaxoghgowBGmhjCGLIk5u16uqrMrDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWe1BAACH5BAkEANMALAAAAACmAKYAh6YAALAAALEBAbEBAbECArEDA7IFBbIGBrIHB7MICLMICLMJCbMJCbMJCbMJCbMJCbMICLIFBbEDA7EAALEAALEAALEAALEAALEAALAAALAAALAAALAAALAAALAAALAAALAAALAAAKcCAoYKCiQkJCUlJSYmJicnJygoKCkpKSoqKisrKywsLC0tLS4uLi8vLzAwMDExMTIyMjMzMzQ0NDU1NTY2Njc3Nzg4ODk5OTo6Ojs7Ozw8PD09PT4+Pj8/P0BAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISFFKSlpMTGNOTmtQUHJSUnpTU4BVVYZWVoxXV5FYWJZZWZtaWp9bW6NbW6ZcXKlcXKtdXa1dXa9dXbBeXrJeXrJeXrNfX7NfX7NfX7JfX7FgYLBgYK9gYK1hYathYaliYqdiYqRjY6FkZJ5lZZtmZpdnZ5RoaJBqaoxra4htbYNvb39xcXpzc3Z2dnd3d3h4eH95eYV6epV+fqGBgaCEhJ+IiKGMjKKQkKeSkqyVlbCXl7SZmbebm7qcnL2enr+fn8OgoMOhocekpMyqqtWyst68vOXDw+nIyOzLy+7OzvDQ0PHU1PLX1/Tb2/Xf3/bi4vfl5ffn5/jq6vnt7frv7/vy8vz29v35+f36+v36+v37+/78/P78/P79/f///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AKcJHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXcq0qdOnUKNKnUq1qtWrWLNq3cq1q9evYMOKHUu2rNmzaNOqXcu2rdu3cOPKnUu3rt27ePPq3cu3r9+/gAMLHky4sOHDiBNbncSHT5cuDBg8bjxJMcNOjcYwwMC5s2fODMY06mS5YCc+mz+rVs2AD+nSiVKvnv2ZQSLFk2TT3u2ZQWXDsXkLr32bMJ/hyD/zGTwmufPOYwIff059ud9E1LNjKL43t3bqvvn+6v6enMFe7OSzc7fbaXx65Axe253+vvrd9vWzm7fbKL/2RnaF4V92Ydjl3oDC7TfXJAhm95tc9DWYnHUQSvgchXF1YaFzXdCl4YbIdTjXgSCupmBcJJZYm4cqCieiXB+2SNuLcUUoo2oYwmXjjZ7l+BaDPK72oFwptniiXAIG6VmBdfWnZGcA1oXfk0fOtaOMPso1JY/x5RWIkuvdVWSDVdrlnZFD5oWeimHqdSWCWeqVpIVMBtachHH2FZx/th125nvhJbandn2WdtqYJrpWGkGYhYEoA2GMtmhCjDkGmWRdUDbpppx26umnoIYq6qiklmrqqaimquqqrLbq6qsvsMYq66y01mrrrbjmquuuvPbq66/ABivssMQWa+yxyCar7LLMNuvss9BGK22wAQEAIfkECQQA1AAsAAAAAKYApgCHAAAAIAAAdAAAmwAAqgAArgAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsQICsQUFsQkJrgwMohMTjB4ebywsXjQ0VDc3Tzk5SDw8QEBAQUFBQkJCQ0NDRERERUVFRkZGR0dHSEhISUlJSkpKS0tLTExMTU1NTk5OT09PUFBQUVFRUlJSU1NTVFRUVVVVVlZWV1dXWFhYYFpaaVxccV5eeGBgf2JihmNjjGVlkmZml2dnnGhoo2ZmqWRkrWJisWBgtV9fuFtbu1hYvFZWvlRUv1NTv1JSv1NTv1RUv1VVv1dXvlpavV5evGBgu2JiuWVlt2hotGtrsW9vrnNzq3R0qHV1pnZ2o3d3n3h4nHp6mXt7mn9/nIODnYeHn4qKoI2NpY+PqZCQrZKSsJOTs5SUtZWVt5aWuZeXvZubxKGh0Kys2La24L+/58fH7M3N79HR8dXV89nZ9d7e9uPj9+fn+e3t+/Ly/fj4/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CP4AqQkcSLCgwYMIEypcyLChw4cQI0qcSLGixYsYM2rcyLGjx48gQ4ocSbKkyZMoU6pcybKly5cwY8qcSbOmzZs4c+rcybOnz59AgwodSrSo0aNIkypdyrSp06dQo0qdSrWq1atYs2rdyrWr169gw4odS7as2bNo06pdy7at27dw48qdS7eu3bt481paJChPm79t8ghaZCmvR0l5dGhYzLjxYh15JBnGuAiH48uYcSyaPBGSYsygL+uAxNmhpc+hUztuU7h0QkmWVctujEOya4OLZut2vPn2QDq7gzOm45tabuHIe5eWhLy5BtuTLcV2HhxHa8OoqQfXMRmSduek8f5yyv59N3e8gso7F4R3uvrqd5m/bw597pz5zefYdY9f9/m5lvTX3HVxpSegcOzNZeCBuyUoVxsMBtcGXRBGqNuEc1VooWwYPrjhbB3GpeGHoYUI14gkYmbiWwumiJmDBboYGoxwTSIjaJPUxd+NONh1342N6VeXd0AyVp9cnOyYIg6c3NWijDTOlSSQTOb1JIlR1kXehv/pJSOBeRH5YXil5fFhHsWh2N+Kpf0ooJDFCSTmfGTGKZB07+kApp3UcCKIkrvhIEiTfB7kZ3OCElpoQpxA0gagtLUBiaKLNiRJX3/hgMNfgh1Z6aeghirqqKSWauqpqKaq6qqsturqq0SwxirrrLTWauutuOaq66689urrr8AGK+ywxBZr7LHIJqvsssw26+yz0EYr7bTUVmvttdhmq+223Hbr7bfghivuuLYGBAAh+QQJBADWACwAAAAApgCmAIcAAAABAQGoAACvAwOyBgazCAi0Cgq0DAy1Dg62EhK3Fha4GBi6Hh66ICC6Hh63Fha0DAyyBgaxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACuAACpAgKbBwd8ERE+KCgvLy8wMDAxMTEyMjIzMzM0NDQ1NTU2NjY3Nzc4ODg5OTk6Ojo7Ozs8PDw9PT0+Pj4/Pz9AQEBBQUFCQkJDQ0NERERFRUVGRkZHR0dISEhJSUlKSkpLS0tMTExNTU1OTk5PT09QUFBRUVFSUlJTU1NUVFRVVVVWVlZXV1dYWFhZWVlaWlpbW1tcXFxdXV1eXl5oX19sYGBqYGBiYmJjY2NkZGRlZWVmZmZnZ2doaGhpaWlqampra2tsbGxtbW1ubm5vb29wcHBxcXFycnJzc3N0dHR1dXV2dnZ3d3d4eHh5eXl6enp7e3t8fHx9fX1+fn6Ef3+AgICBgYGCgoKDg4OEhISLiIiSjY2ekpKolpawmZm2nJy8nZ2+oKDAoaHCo6PDpaXDpqbCqKjEra3Ls7PVvb3exsbmzs7r1NTv2dny3d304OD14uL25OT35+f46en57Oz67+/78/P89vb9+vr+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gCtCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl3KtKnTp1CjSp1KtarVq1izat3KtavXr2DDih1LtqzZs2i/amL050+Dtw3aTtKU1qUlOA046N3LV28DOJbqomSUt69hww0YCR5J+LDjww0mLfaoqfDjy30b0J2ckRICzKD7IqDE+SKj0Kj7Ki49EUzq13vBsI64CLZtDqtnM6R0+zZp3Qotfe4NG0Fg4AgtE3/dAPnB08tv53YuUHn01M2pC6x9/fYi7daG/ncvrn3S+N6Snbs+b1u2c+vsQ2cHbin+7eO6/9i3/Qe5/v2v9QccfABeNt9sBBbo2IGsJaggYsg5+CBfDJYm4YR+RYghZhVy9t+GjgmYH4iPiTibeSQe9htw4qWoFwLUreeiXu4hB92MuFEHSoskIgCKdh+maCJyO7roI3jWBLnhkM4VCWIDPyJpDW8grijljQ9OJ6U1MhZY45YDXRhdh2B2yd6XYBbEHXtaplmQZ+ON5uZCoPzBY3F/RDknnWamBoaeezakySJi+rXIZoFKBMokbr3l11t/TAJoopRWaumlmGaq6aacdurpp6CGKuqopJZq6qmopqrqqqy26uqrRLDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWa+212Gar7bbcduvtt+CGK+64UAUEACH5BAkEANYALAAAAACmAKYAh6YAALAAALAAALAAALAAALAAALAAALEAALEAALEAALEAALEBAbEBAbEBAbEBAbEAALEAALEAALEAALEAALEAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAAK0AAKMDA4UJCVAWFiwgICQkJCUlJSYmJicnJygoKCkpKSoqKisrKywsLC0tLS4uLi8vLzAwMDExMTIyMjMzMzQ0NDU1NTY2Njc3Nzg4ODk5OTo6Ojs7Ozw8PD09PT4+Pj8/P0BAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISElJSUpKSktLS0xMTE1NTU5OTk9PT1BQUFFRUVJSUlNTU1RUVFVVVVZWVldXV1hYWFlZWVpaWltbW1xcXGNbW3FbW31aWohaWpFZWZlZWZ9YWKVXV6pWVq5VVbFUVLRTU7ZSUrhSUrlRUbpRUbtRUbxNTbxQULxRUbxRUbxSUrtSUrtTU7xWVrxZWbxcXLxeXrxhYbtjY7plZbhoaLZqarNtbbBvb6tzc6V2dp57e5h9fZZ/f5OAgJCDg4yFhYiIiImJiYqKiouLi4yMjJKOjpmQkJ6RkaeUlK2VlbSWlrmcnMSlpdCxsdq8vOLIyOnU1PLl5ffu7vr19f7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AK0JHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXcq0qdOnUKNKnUq1qtWrWLPizHSJEKE8btzk8Xopk9aemAi5kcC2rVu2biRhOnuT0oK3ePMuoERXpqW1eQPjdWOpb8tMgAUrfuvGrGGUmO4unux2wdzHJSlR3vyWL2aRfziLbvvnM0jNo1N7Nr3RUurXEi6zxphJMmzRCxzPtpj4tmg3uy2i9q06OEXbxHEblzg8+ejVyxsid855QXSHlajDrnSdYWjtqUv+d1c4HTxl6+MRYjL/WnZ6goTYp5b03mB8+aIJ1S/4Br/oN/sR1Jt/iwEXoEADEiiYgQeWp2BeDAaY4IODHYgghQVaaM19GAamn4UcdojXhwdmJ2Je3GnoYIfoadjfiW7hoaFAzcEI3YGawOiWJjMK9B2MJM6oyYoELsBjjwKFiGGQPQ4popFIEmQihoVFSZAkGNJnZUF4PCjjlgZNqF2EYA6kSZfy4XFkmQZhaZ6WbCJUCZGjLZBinAlpouRrhKyJp0KZoJkaHrr92VAmkog5mCSFGgqRJpT8EZZkC4T1ByV+Oqrpppx26umnoIYq6qiklmrqqaimquqqrLbq6qtUsMYq66y01mrrrbjmquuuvPbq66/ABivssMQWa+yxyCar7LLMNuvss9BGK+201FZr7bXYZqvtttx26+234IYr7rjklmvuueimq+667Lbr7rvwMhUQACH5BAkEANkALAAAAACmAKYAh6YAALAAALECArEDA7IEBLMICLQLC7QNDbYTE7cXF7gaGrkbG7kcHLkcHLgZGbcUFLUPD7MICLIGBrEDA7EAALEAALEAALEAALEAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAAKwBAZcGBnUPDyYmJicnJygoKCkpKSoqKisrKywsLC0tLS4uLi8vLzAwMDExMTIyMjMzMzQ0NDU1NTY2Njc3Nzg4ODk5OTo6Ojs7Ozw8PD09PT4+Pj8/P0BAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISElJSUpKSktLS0xMTE1NTU5OTk9PT1BQUFFRUVJSUlNTU1RUVFVVVVZWVldXV1hYWFlZWVpaWltbW1xcXF1dXV5eXmZfX2BgYGFhYWJiYmNjY2RkZGVlZWZmZmdnZ2hoaGlpaWpqamtra2xsbG1tbW5ubm9vb3BwcHFxcXJycnNzc3R0dHV1dXZ2dnd3d3h4eHl5eXp6ent7e3x8fH19fYSAgIuDg5KFhZiIiJ6KiqOMjKiOjq2QkLGRkbWTk7mUlL2VlcCWlsKXl8WYmMeZmcqZmc2Zmc6Zmc+ZmdOcnNahodmmpt6vr+K1tea6uui9verAwOzCwuzExO3Gxu7Jye/MzPDOzvDR0fHT0/HU1PLX1/PZ2fPa2vTc3PTd3fXf3/Xh4fbl5ffm5vjo6Pjr6/nu7vrx8fv09P34+P36+v78/P///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+ALMJHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXRrTVaZIUBkwgBopkyumS1FFYoChq9evXaeiwlp0ElewaNEyqEQWaKazaeOCZZCp7U5XcOXq/crgql2bnyDsHQwWwqe/NCcJJsy4K4RJiGNGakzZa6TILidV3owBMmaVnzhzPvz5JKrFoilDGFu6ZN7UjRm0JqkZNmfPs0G+ts1Ydu6PtXlvxv17427hhH0X11gJuWi2yzNOdr75cnSMx6nvVX6dYmjtm0n+d584HTxl6+MlljfPGH16iNnZx/3yXmJ8+WrrR7yPn69++P0l999D/AWIAXcDKlRggAgmiNB6BqblnoMPRqjXhBQa1JyFcUGXYUKocegVBB8utKB8DZZIUHAidkWcigW50uJXfsF40BczYoChjQO5EmKEENTIo0EQGrjjkAL5yGGQSCa0YYQeNnlQkewdKeVAOPZH35UK4YVfX1wu5KV5XwgZZkJU2mblmQYphtxjbELkSpawlRmnRKjQWdkXrN05kVYniuVnRqhM8oVUfDHwxSRmDuroo5BGKumklFZq6aWYZqrpppx26umnoIYq6qiklmrqqaimquqqrLbq6qtOsMYq66y01mrrrbjmquuuvPbq66/ABivssMQWa+yxyCar7LLMNuvss9BGK+201FZr7bXYZqvtttx26+234IYr7rjklmvuueimq+66tQYEACH5BAkEANYALAAAAACmAKYAh6YAALAAALAAALAAALAAALAAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALIDA7MHB7QNDbgYGLsiIrsjI7wlJbwnJ7knJ7EoKJ4qKoQuLnExMW4xMWowMGYwMGAwMFsvL1UvL04vL0cuLj8uLjYuLi4uLi8vLzAwMDExMTIyMjMzMzQ0NDU1NTY2Njc3Nzg4ODk5OTo6Ojs7Ozw8PD09PT4+Pj8/P0BAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISElJSUpKSktLS0xMTE1NTU5OTk9PT1BQUFFRUVJSUlNTU1RUVFVVVVZWVldXV1hYWFlZWVpaWltbW1xcXF1dXV5eXl9fX2BgYGFhYWJiYmNjY2RkZGVlZWZmZmdnZ2hoaGlpaWpqamtra2xsbG1tbW5ubm9vb3BwcHFxcXJycnNzc3p0dIJ2doh3d454eJN6eph6ept7e6J6eqd5eap4eK53d6x5eat7e6h9faWAgKOBgaeEhKmGhq6NjbOSkrmYmL2dnb2jo7yqqr+ursGzs8a7u8rBwdDFxdTHx9nMzN7R0ebZ2ezf3/Hk5PTp6ffu7vny8vrz8/v09Pv09Pv19fz19fz29vz29vz29vz39/z4+P34+P35+f36+v37+/77+/78/P79/f7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AK0JHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fNTE9UiRIkAcPRRU9wgS0qcVKhzwsmEq16lQPhyo53dqQkVSrYMF6eMS1bMFHX8OqtTrWLFdMadfKreqBqdufkzLM3Wt1w6S7PRPp5Ut4aoZEgHUeKsyY6qHENxM1nrwAMeSZkyhT/nsZJqbBmhlnsNu5ZdzQhT2UbikZNWVGq1ducE15Q+yUrWlPtny75Gzdk233JskIuGayw0X6MU7ZT3KRv5kzFv7cY2bpkzlX57gYe+PH2zn+BvLeOFB4jqfJz3V+XmN69WtVt8/4Hn5Y+fMvRrcvl3r+ivXxR9d/FwUo4FUEWjTegWuZlyBFCzIYloMPSlSchGHBVuFEoGFo2IYUGcgffiBClJuHlZUoESYoUkWaig8thyJ7MEL0mYej1ShRhAxSqONDNzKY448RXXighkRGxCN8PiYJkYzw0ehkRHDBV9eUFFXp3ZVYVgSlcU12OZFgwB0mZkaVfKmZH1qdqVGaa7bpJkeTBCIiVR4Eot2cH1WSyFFxAZqInHwWauihiCaq6KKMNuroo5BGKumklFZq6aWYZqrpppx26umnoIYq6qiklmrqqaimquqqrLbq6qtNsMYq66y01mrrrbjmquuuvPbq66/ABivssMQWa+yxyCar7LLMNuvss9BGK+201FZr7bXYZqvtttx26+234IYr7rjklmvuueimqy6mAQEAIfkECQQA1gAsAAAAAKYApgCHAAAAAQEBAgICAwMDBAQEBQUFBgYGBwcHCAgICQkJCgoKCwsLDAwMDQ0NDg4ODw8PEBAQEREREhISExMTFBQUFRUVFhYWFxcXGBgYGRkZVw8PhgcHngMDqQEBrwAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQICsgQEsgUFswcHswgIswoKswsLsw8PshMTsR4eqy8vpjo6okFBoENDnkVFnUlJnE1Nm1FRmFZWlVtblF5elmBgmGJimmVlm2hooGlppGpqqGpqrGtrr2xssmxstG1ttm1tuG1tum5uu25uvW1tvmxsvm1tvm9vvW9vvG9vu29vu3BwuXBwuHBwtnFxtXFxs3JysHNzrnNzrHR0qXV1pnZ2o3h4oHl5nXp6mXx8ln5+koCAjoKCioSEh4eHjoqKlI2NoJGRqZSUs5qavJ+fwKOjxKamx6qqyK2tz7Ky1Li43MDA48fH6MzM7NDQ79TU8dfX89ra9Nzc9d/f9uLi9+Xl+Orq+vHx/fj4/fv7/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CP4ArQkcSLCgwYMIEypcyLChw4cQI0qcSLGixYsYM2rcyLGjx48gQ4ocSbKkyZMoU6pcybKly5cwY8qcSbOmzZsfQVFyBAeOFi09HVEChbOoSU6LtMxYyrTpUi2LOBmd2pHSFKdYs06hRLVrRUpVsorNWoWr17MMQYUdyxarFqJo4xa8JKWtXadSLsnd2wjK3b9MoTTai3YR4MNMFxHu2gix4xmDFxe99NgxFL2SbYLyWxkxFLiZZ67tjLhK6JmSSFeWdDpmXdWOpbR+2Rj248izV762jVh2bpWpeT82+/skHOGP4RRHuRs5YN/LSVJ27hhzdJGGqSNWfF3kce2Hlf53D5kF/GE340OONm/XdPqP69mzdf++Y3P5Y6HX3xgfP9n9HfXnn1P0AZjRdwOKJZ6BByY41oIMXhScg1ixFmFGFGIFxYUalZchU1lwmFFtH0ImIkaglLgUaCdW5EaJ6LV4ESecOfiZjBghmCCEOFK0mY0s9kgRif5ZKORFOrLH45Eu4hcjkyheZd4UQUJZESgeUpdFlVZalKRtS3aJUV+8CSbmR5y8qJobUp0JEidZIpZFm26KdAkcUto1BRzW1VkSUllUkecMU1SRRVR+Jqrooow26uijkEYq6aSUVmrppZhmqummnHbq6aeghirqqKSWauqpqKaq6qqsturqq1iwxirrrLTWauutuOaq66689urrr8AGK+ywxBZr7LHIJqvsssw26+yz0EYr7bTUVmvttdhmq+223Hbr7bfghivuuOSWa+656Kar7rrstuvuu/DGK++8zgYEACH5BAkEANYALAAAAACmAKYAh6YAALAAALAAALMICLMKCrQLC7QMDLQNDbUPD7UQELUREbYSErUQELQNDbIGBrECArEAALEAALEAALEAALEAALEAALEAALEAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAAKsBAZYGBngODjYgICYmJicnJygoKCkpKSoqKisrKywsLC0tLS4uLi8vLzAwMDExMTIyMjMzMzQ0NDU1NTY2Njc3Nzg4ODk5OTo6Ojs7Ozw8PD09PT4+Pj8/P0BAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISElJSUpKSktLS0xMTE1NTU5OTk9PT1BQUFFRUVJSUltUVGNXV2xZWXRcXHteXoJgYIlhYZBjY5ZkZJtmZqFnZ6ZoaKppabBoaLZnZ7pmZr1lZcBkZMJkZMRjY8ZjY8hiYsliYspiYspjY8tjY8tlZctmZsxnZ8xpacxqasxsbMxvb8pvb8lvb8hvb8ZwcMVwcMNwcMFxcb9xcb1ycrtycrhzc7V0dLN1dbB2dq13d6p5ead6eqh/f6mEhK+KirOPj7aSkryZmcKensSjo8impsuqqs+trc+xsdKzs9a3t9u7u+HBwebGxurKyu3Nze7Q0PDS0vHT0/LV1fLX1/PZ2fTb2/Te3vXg4Pbk5Pjo6Pnu7vz19f34+P37+/7+/v7+/v7+/v7+/v7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AK0JHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnElz4yhKkObMSZNGJyRKo2oKZQkqUZoLSJMqRZomEaihUEdSMrO0qlUzlKJq3TjVqterWbeKlTiK6tezVdMEHct2IaYFaOMuXYCprV2DixLI3Zs0waK7gBHxHZwUEeC2iwgrvvD38FZMehcPTlDXMdRRkSVPXmu5plnNhM10rgkJtGRIo2fCNa14QeqYiVkvbvy65WrZhF3XZlka9+Kwu1PO8b14TnCVt4nz1X3cJCbliys3JykYOmHD00kOtz7YePaRR7n+81XzfeRn8XFFlw95Hv1Z9es/Jnf/lXn8ju3pX73/Mb/+pfDxt9F2/3nlnYADFvjVgQhm1JuCVaHW4EYQVoXAhByFVyFTGG4U24aMdajRKCAixZmIF6kBInkoZgQKAhUicGKLFhFYIIM0WjQKjAXKmKNGH+pH248Y2YgejkRepKF4LCY54nzKLTCjkxeNsiRxalHpkZGyIaklkL4hMOSXHYGiomlqPEWmSKBcyVcaaq5JEiZzQOnVAnNIJ+dJoCCShhnJLWBGGojEueehiCaq6KKMNuroo5BGKumklFZq6aWYZqrpppx26umnoIYq6qiklmrqqaimquqqrLbq6qtYsMYq66y01mrrrbjmquuuvPbq66/ABivssMQWa+yxyCar7LLMNuvss9BGK+201FZr7bXYZqvtttx26+234IYr7rjklmvuueimq+667Lbr7rvwxivvvJgGBAAh+QQJBADWACwAAAAApgCmAIcAAAABAQECAgIDAwMEBAQFBQUGBgYHBwcICAgJCQkKCgoLCwsMDAwNDQ0ODg4PDw8QEBARERESEhITExMUFBQVFRUWFhYXFxcYGBgZGRlXDw+GBweeAwOpAQGvAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAgKyBASyBQWzBwezCAizCgqzCwuzDw+yExOxHh6rLy+mOjqiQUGgQ0OeRUWdSUmcTU2bUVGYVlaVW1uUXl6WYGCYYmKaZWWbaGigaWmkamqoamqsa2uvbGyybGy0bW22bW24bW26bm67bm69bW2+bGy+bW2+b2+9b2+8b2+7b2+7cHC5cHC4cHC2cXG1cXGzcnKwc3Ouc3OsdHSpdXWmdnajeHigeXmdenqZfHyWfn6SgICOgoKKhISHh4eOioqUjY2gkZGplJSzmpq8n5/Ao6PEpqbHqqrIra3PsrLUuLjcwMDjx8fozMzs0NDv1NTx19fz2tr03Nz139/24uL35eX46ur68fH9+Pj9+/v+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gCtCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmx9BUXIEB44WLT0dUQKFs6hJTou0zFjKtOlSLYs4GZ3akdIUp1izTqFEtWtFSlWyis1ahavXswxBhR3LFqsWomjjFrwkpa1dp1Iuyd3bCMrdv0yhNNqLdhHgw0wXEe7aCLHjGYMXF7302DEUvZJtgvJbGTEUuJlnru2MuEromZJIV5Z0OmZd1Y6ltH7ZGPbjyLNXvraNWHZulal5Pzb7+yQc4Y/hFEe5Gzlg38tJUnbuGHN0kYapI1Z8XeRx7YeV/ncPmQX8YTfjQ442b9d0+o/r2bN1/75jc/ljodffGB8/2f0d9eefU/QBmNF3A4olnoEHJjjWggxeFJyDWLEWYUYUYgXFhRqVlyFTWXCYUW0fQiYiRqCUuBRoJ1bkRonotXgRJ5w5+JmMGCGYIIQ4UrSZjSz2SBGJ/lko5EU6ssfjkS7iFyOTKF5l3hRBQlkRKB5Sl0WVVlqUpG1LdolRX7wJJuZHnLyomhtSnQkSJ1kilkWbbop0CRxS2jUFHNbVWRJSWVSR5wxTVJFFVH4mquiijDbq6KOQRirppJRWaumlmGaq6aacdurpp6CGKuqopJZq6qmopqrqqqy26uqrWLDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWa+212Gar7bbcduvtt+CGK+645JZr7rnopqvuuuy26+678MYr77zOBgQAIfkECQQA1gAsAAAAAKYApgCHpgAAsAAAsAAAsAAAsAAAsAAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsQAAsgMDswcHtA0NuBgYuyIiuyMjvCUlvCcnuScnsSgonioqhC4ucTExbjExajAwZjAwYDAwWy8vVS8vTi8vRy4uPy4uNi4uLi4uLy8vMDAwMTExMjIyMzMzNDQ0NTU1NjY2Nzc3ODg4OTk5Ojo6Ozs7PDw8PT09Pj4+Pz8/QEBAQUFBQkJCQ0NDRERERUVFRkZGR0dHSEhISUlJSkpKS0tLTExMTU1NTk5OT09PUFBQUVFRUlJSU1NTVFRUVVVVVlZWV1dXWFhYWVlZWlpaW1tbXFxcXV1dXl5eX19fYGBgYWFhYmJiY2NjZGRkZWVlZmZmZ2dnaGhoaWlpampqa2trbGxsbW1tbm5ub29vcHBwcXFxcnJyc3NzenR0gnZ2iHd3jnh4k3p6mHp6m3t7onp6p3l5qnh4rnd3rHl5q3t7qH19pYCAo4GBp4SEqYaGro2Ns5KSuZiYvZ2dvaOjvKqqv66uwbOzxru7ysHB0MXF1MfH2czM3tHR5tnZ7N/f8eTk9Onp9+7u+fLy+vPz+/T0+/T0+/X1/PX1/Pb2/Pb2/Pb2/Pf3/Pj4/fj4/fn5/fr6/fv7/vv7/vz8/v39/v7+/v7+////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CP4ArQkcSLCgwYMIEypcyLChw4cQI0qcSLGixYsYM2rcyLGjx48gQ4ocSbKkyZMoU6pcybKly5cwY8qcSbOmzZs4c+rcybOnz581MT1SJEiQBw9FFT3CBLSpxUqHPCyYSrXqVA+HKjnd2pCRVKtgwXp4xLVswUdfw6q1OtYsV0xp18qt6oGp25+TMszda3XDpLs9E+nlS3hqhkSAdR4qzJjqocQ3EzWevAAx5JmTKFP+exkmpsGaGWew27ll3NCFPZRuKRk1ZUarV25wTXlD7JStaU+2fLvkbN2TbfcmyQi4ZrLDRfoxTtlPcpG/mTMW/txjZumTOVfnuBh748fbOf4G8t44UHiOp8nPdX5eY3r1a1W3z/geflj58y9Gty+Xev6K9fFH138XBSjgVQRaNN6Ba5mXIEULMhiWgw9KVJyEYcFW4USgYWjYhhQZyB9+IEKUm4eVlSgRJihSRZqKDy2HInswQvSZh6PVKFGEDFKo40M3MpjjjxFdeKCGREbEI3w+JgmRjPDR6GREcMFX15QUVendlVhWBKVxTXY5kWDAHSZmRpV8qZkfWp2pUZprtukmR5MEIiJVHgSi3ZwfVZLIUXEBmoicfBZq6KGIJqrooow26uijkEYq6aSUVmrppZhmqummnHbq6aeghirqqKSWauqpqKaq6qqsturqq02wxirrrLTWauutuOaq66689urrr8AGK+ywxBZr7LHIJqvsssw26+yz0EYr7bTUVmvttdhmq+223Hbr7bfghivuuOSWa+656KarLqYBAQAh+QQJBADZACwAAAAApgCmAIemAACwAACxAgKxAwOyBASzCAi0Cwu0DQ22ExO3Fxe4Ghq5Gxu5HBy5HBy4GRm3FBS1Dw+zCAiyBgaxAwOxAACxAACxAACxAACxAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACsAQGXBgZ1Dw8mJiYnJycoKCgpKSkqKiorKyssLCwtLS0uLi4vLy8wMDAxMTEyMjIzMzM0NDQ1NTU2NjY3Nzc4ODg5OTk6Ojo7Ozs8PDw9PT0+Pj4/Pz9AQEBBQUFCQkJDQ0NERERFRUVGRkZHR0dISEhJSUlKSkpLS0tMTExNTU1OTk5PT09QUFBRUVFSUlJTU1NUVFRVVVVWVlZXV1dYWFhZWVlaWlpbW1tcXFxdXV1eXl5mX19gYGBhYWFiYmJjY2NkZGRlZWVmZmZnZ2doaGhpaWlqampra2tsbGxtbW1ubm5vb29wcHBxcXFycnJzc3N0dHR1dXV2dnZ3d3d4eHh5eXl6enp7e3t8fHx9fX2EgICLg4OShYWYiIieioqjjIyojo6tkJCxkZG1k5O5lJS9lZXAlpbCl5fFmJjHmZnKmZnNmZnOmZnPmZnTnJzWoaHZpqber6/itbXmurrovb3qwMDswsLsxMTtxsbuycnvzMzwzs7w0dHx09Px1NTy19fz2dnz2tr03Nz03d3139/14eH25eX35ub46Oj46+v57u768fH79PT9+Pj9+vr+/Pz///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gCzCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl0a01WmSFAZMIAaKZMrpktRRWKAoavXr12nosJadBJXsGjRMqhEFmims2njgmWQqe1OV3Dl6v3K4Kpdm58g7B0MFsKnvzQnCSbMuCuESYhjRmpM2WukyC4nVd6MATJmlZ84cz78+SSqxaIpQxhbumTe1I0ZtCapGTZnz7NBvrbNWHbuj7V5b8b9e+Nu4YR9F9dYCblotsszTna++XJ0jMep71V+nWJo7ZtJ/nefOB08ZevjJZY3zxh9eojZ2cf98l5ifPlq60e8j5+vfvj9JfffQ/wFiAF3AypUYIAIJojQegam5Z6DD0ao14QUGtSchXFBl2FCqHHoFQQfLrSgfA2WSFBwInZFnIoFudLiV37BeNAXM2KAoY0DuRJihBDUyKNBEBq445AC+chhkEgmtGGEHjZ5UJHsHSnlQDj2R9+VCuGFX19cLuSleV8IGWZCVNpm5ZkGKYbcY2xC5EqWsJUZp0So0FnZF6zdOZFWJ4rlZ0aoTPKFVHwx8MUkZg7q6KOQRirppJRWaumlmGaq6aacdurpp6CGKuqopJZq6qmopqrqqqy26uqrTrDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWa+212Gar7bbcduvtt+CGK+645JZr7rnopqvuurUGBAAh+QQJBADWACwAAAAApgCmAIemAACwAACwAACwAACwAACwAACwAACxAACxAACxAACxAACxAQGxAQGxAQGxAQGxAACxAACxAACxAACxAACxAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACtAACjAwOFCQlQFhYsICAkJCQlJSUmJiYnJycoKCgpKSkqKiorKyssLCwtLS0uLi4vLy8wMDAxMTEyMjIzMzM0NDQ1NTU2NjY3Nzc4ODg5OTk6Ojo7Ozs8PDw9PT0+Pj4/Pz9AQEBBQUFCQkJDQ0NERERFRUVGRkZHR0dISEhJSUlKSkpLS0tMTExNTU1OTk5PT09QUFBRUVFSUlJTU1NUVFRVVVVWVlZXV1dYWFhZWVlaWlpbW1tcXFxjW1txW1t9WlqIWlqRWVmZWVmfWFilV1eqVlauVVWxVFS0U1O2UlK4UlK5UVG6UVG7UVG8TU28UFC8UVG8UVG8UlK7UlK7U1O8Vla8WVm8XFy8Xl68YWG7Y2O6ZWW4aGi2amqzbW2wb2+rc3Oldnaee3uYfX2Wf3+TgICQg4OMhYWIiIiJiYmKioqLi4uMjIySjo6ZkJCekZGnlJStlZW0lpa5nJzEpaXQsbHavLziyMjp1NTy5eX37u769fX+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gCtCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl3KtKnTp1CjSp1KtarVq1iz4sx0iRChPG7c5PF6KZPWnpgIuZHAtq1btm4kYTp7k9KCt3jzLqBEV6altXkD43VjqW/LTIAFK37rxqxhlJjuLp7sdsHcxyUpUd78li9mkX84i2775zNIzaNTeza90VLq1xIus8aYSTJs0Qscz7aY+LZoN7stovatOjhF28RxG5c4PPno1csbInfOeUF0h5Wow650nWFo7alL/ndXOB08ZevjEWIy/1p2eoKE2KeW9N5gfPmiCdUv+Aa/6Df7EdSbf4sBF6BAAxIomIEHlqdgXgwGmOCDgx2IIIUFWmjNfRgGpp+FHHaI14cHZidiXtxp6GCH6GnY34lu4aGhQM3BCN2BmsDoliYzCvQdjCTOqMmKBC7AY48ChYhhkD0OKaKRSBJkIoaFRUmQJBjSZ2VBeDwo45YGTahdhGAOpEmX8uFxZJkGYWmelmwiVAmRoy2QYpwJaaLka4SsiadCmaCZGh66/dlQJpKIOZgkhRoKkSaU/BGWZAuE9Qclfjqq6aacdurpp6CGKuqopJZq6qmopqrqqqy26uqrVLDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWa+212Gar7bbcduvtt+CGK+645JZr7rnopqvuuuy26+678DIVEAAh+QQJBADWACwAAAAApgCmAIcAAAABAQGoAACvAwOyBgazCAi0Cgq0DAy1Dg62EhK3Fha4GBi6Hh66ICC6Hh63Fha0DAyyBgaxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACwAACuAACpAgKbBwd8ERE+KCgvLy8wMDAxMTEyMjIzMzM0NDQ1NTU2NjY3Nzc4ODg5OTk6Ojo7Ozs8PDw9PT0+Pj4/Pz9AQEBBQUFCQkJDQ0NERERFRUVGRkZHR0dISEhJSUlKSkpLS0tMTExNTU1OTk5PT09QUFBRUVFSUlJTU1NUVFRVVVVWVlZXV1dYWFhZWVlaWlpbW1tcXFxdXV1eXl5oX19sYGBqYGBiYmJjY2NkZGRlZWVmZmZnZ2doaGhpaWlqampra2tsbGxtbW1ubm5vb29wcHBxcXFycnJzc3N0dHR1dXV2dnZ3d3d4eHh5eXl6enp7e3t8fHx9fX1+fn6Ef3+AgICBgYGCgoKDg4OEhISLiIiSjY2ekpKolpawmZm2nJy8nZ2+oKDAoaHCo6PDpaXDpqbCqKjEra3Ls7PVvb3exsbmzs7r1NTv2dny3d304OD14uL25OT35+f46en57Oz67+/78/P89vb9+vr+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gCtCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl3KtKnTp1CjSp1KtarVq1izat3KtavXr2DDih1LtqzZs2i/amL050+Dtw3aTtKU1qUlOA046N3LV28DOJbqomSUt69hww0YCR5J+LDjww0mLfaoqfDjy30b0J2ckRICzKD7IqDE+SKj0Kj7Ki49EUzq13vBsI64CLZtDqtnM6R0+zZp3Qotfe4NG0Fg4AgtE3/dAPnB08tv53YuUHn01M2pC6x9/fYi7daG/ncvrn3S+N6Snbs+b1u2c+vsQ2cHbin+7eO6/9i3/Qe5/v2v9QccfABeNt9sBBbo2IGsJaggYsg5+CBfDJYm4YR+RYghZhVy9t+GjgmYH4iPiTibeSQe9htw4qWoFwLUreeiXu4hB92MuFEHSoskIgCKdh+maCJyO7roI3jWBLnhkM4VCWIDPyJpDW8grijljQ9OJ6U1MhZY45YDXRhdh2B2yd6XYBbEHXtaplmQZ+ON5uZCoPzBY3F/RDknnWamBoaeezakySJi+rXIZoFKBMokbr3l11t/TAJoopRWaumlmGaq6aacdurpp6CGKuqopJZq6qmopqrqqqy26uqrRLDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWa+212Gar7bbcduvtt+CGK+64UAUEACH5BAkEANQALAAAAACmAKYAhwAAACAAAHQAAJsAAKoAAK4AALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALECArEFBbEJCa4MDKITE4weHm8sLF40NFQ3N085OUg8PEBAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISElJSUpKSktLS0xMTE1NTU5OTk9PT1BQUFFRUVJSUlNTU1RUVFVVVVZWVldXV1hYWGBaWmlcXHFeXnhgYH9iYoZjY4xlZZJmZpdnZ5xoaKNmZqlkZK1iYrFgYLVfX7hbW7tYWLxWVr5UVL9TU79SUr9TU79UVL9VVb9XV75aWr1eXrxgYLtiYrllZbdoaLRra7Fvb65zc6t0dKh1daZ2dqN3d594eJx6epl7e5p/f5yDg52Hh5+KiqCNjaWPj6mQkK2SkrCTk7OUlLWVlbeWlrmXl72bm8ShodCsrNi2tuC/v+fHx+zNze/R0fHV1fPZ2fXe3vbj4/fn5/nt7fvy8v34+P7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AKkJHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXcq0qdOnUKNKnUq1qtWrWLNq3cq1q9evYMOKHUu2rNmzaNOqXcu2rdu3cOPKnUu3rt27ePNaWiQoT5u/bfIIWmQpr0dJeXRoWMy48WIdeSQZxrgIh+PLmHEsmjwRkmLMoC/rgMTZoaXPoVM7blO4dEJJllXLboxDsmuDi2brdrz59kA6u4MzpuObWm7hyHuXloS8uQbbky3Fdh4cR2vDqKkH1zEZknbnpPH+csr+fTd3vILKOxeEd7r66neZv28Ofe6c+c3n2HWPX/f5uZb019x1caUnoHDszWXggbslKFcbDAbXBl0QRqjbhHNVaKFsGD644WwdxqXhh6GFCNeIJGJm4lsLpoiZgwW6GBqMcE0iI2iT1MXfjTjYdd+NjelXl3dAMlafXJzsmCIOnNzVoow0zpUkkEzm9SSJUdZF3ob/6SUjgXkR+WF4peXxYR7FodjfiqX9KKCQxQkk5nxkximQdO/pAKad1HAiiJK74SBIk3we5GdzghJaaEKcQNIGoLS1AYmiizYkSV9/4YDDX4IdWemnoIYq6qiklmrqqaimquqqrLbq6qtEsMYq66y01mrrrbjmquuuvPbq66/ABivssMQWa+yxyCar7LLMNuvss9BGK+201FZr7bXYZqvtttx26+234IYr7ri2BgQAIfkECQQA0wAsAAAAAKYApgCHpgAAsAAAsQEBsQEBsQICsQMDsgUFsgYGsgcHswgIswgIswkJswkJswkJswkJswkJswgIsgUFsQMDsQAAsQAAsQAAsQAAsQAAsQAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAAsAAApwIChgoKJCQkJSUlJiYmJycnKCgoKSkpKioqKysrLCwsLS0tLi4uLy8vMDAwMTExMjIyMzMzNDQ0NTU1NjY2Nzc3ODg4OTk5Ojo6Ozs7PDw8PT09Pj4+Pz8/QEBAQUFBQkJCQ0NDRERERUVFRkZGR0dHSEhIUUpKWkxMY05Oa1BQclJSelNTgFVVhlZWjFdXkVhYlllZm1pan1tbo1tbplxcqVxcq11drV1dr11dsF5esl5esl5es19fs19fs19fsl9fsWBgsGBgr2BgrWFhq2FhqWJip2JipGNjoWRknmVlm2Zml2dnlGhokGpqjGtriG1tg29vf3FxenNzdnZ2d3d3eHh4f3l5hXp6lX5+oYGBoISEn4iIoYyMopCQp5KSrJWVsJeXtJmZt5ubupycvZ6ev5+fw6Cgw6Ghx6SkzKqq1bKy3ry85cPD6cjI7MvL7s7O8NDQ8dTU8tfX9Nvb9d/f9uLi9+Xl9+fn+Orq+e3t+u/v+/Ly/Pb2/fn5/fr6/fr6/fv7/vz8/vz8/v39////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CP4ApwkcSLCgwYMIEypcyLChw4cQI0qcSLGixYsYM2rcyLGjx48gQ4ocSbKkyZMoU6pcybKly5cwY8qcSbOmzZs4c+rcybOnz59AgwodSrSo0aNIkypdyrSp06dQo0qdSrWq1atYs2rdyrWr169gw4odS7as2bNo06pdy7at27dw48qdS7eu3bt48+rdy7ev37+AAwseTLiw4cOIE1udxIdPly4MGDxuPEkxw06NxjDAwLmzZ84MxjTqZLlgJz6bP6tWzYAP6dKJUq+e/ZlBIsWTZNPe7ZlBZcOxeQuvfZswn+HIP/MZPCa5885jAh9/Tn2530TUs2Movje3duq++f7q/p6cwV7s5LNzt9tpfHrkDF7bnf6++t329bObt9sov/ZGdoXhX3Zh2OXegMLtN9ckCGb3m1z0NZicdRBK+ByFcXVhoXNd0KXhhsh1ONeBIK6mYFwkllibhyoKJ6JcH7ZI24txRSijahjCZeONnuX4FoM8rvagXCm2eKJcAgbpWYF19adkZwDWhd+TR861o4w+yjUlj/HlFYiS691VZINV2uWdkUPmhZ6KYep1JYJZ6pWkhUwG1pyEcfYVnH+2HXbme+Eltqd2fZZ22pgmulYaQZiFgSgDYYy2aEKMOQaZZF1QNummnHbq6aeghirqqKSWauqpqKaq6qqsturqqy+wxirrrLTWauutuOaq66689urrr8AGK+ywxBZr7LHIJqvsssw26+yz0EYrbbABAQAh+QQJBADQACwAAAAApgCmAIemAACwAACwAACwAACwAACwAACwAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAACxAwOyBASzBwezCQmzBweyBQWwAwOiAwOGCQlXFRUsICAkJCQlJSUmJiYnJycoKCgpKSkqKiorKyssLCwtLS0uLi4vLy8wMDAxMTEyMjIzMzM0NDQ1NTU2NjY3Nzc4ODg5OTk6Ojo7Ozs8PDw9PT0+Pj4/Pz9AQEBBQUFCQkJDQ0NERERFRUVGRkZHR0dISEhJSUlSS0tbTU1kT09sUVFzU1N6VFSBVlaHV1eNWFiSWVmXWlqbW1ufXFyjXFymXV2pXV2rXl6uXl6vXl6xX1+yX1+0Xl6zYGCzYGCzYGC0YWGxYWGwYWGvYWGtYmKrYmKpY2OnY2OkZGSiZWWfZmabZ2eYaGiUaWmQa2uNbGyIbm6EcHCGc3OHdnaJeHiPeXmUe3uYe3ucfHyffX2ifn6kfn6lf3+qgYGtgoKwg4OxhIS2iIi6jIy/kZHFmZnOpKTWsLDgvLzmycnu2Njz4+P47+/9/f3+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////8I/gChCRxIsKDBgwgTKlzIsKHDhxAjSpxIsaLFixgzatzIsaPHjyBDihxJsqTJkyhTqlzJsqXLlzBjypxJs6bNmzhz6tzJs6fPn0CDCh1KtKjRo0iTKl3KtKnTp1CjSp1KtarVq1izat3KtavXr2DDih1LtqzZs2jTql3Ltq3bt3Djyp1Lt67du3jz6t3Lt6/fv4ADCx5MuLDhw4gTK17MuLHjx5AjS55MubLly5gza/aaSFCYL184cAAdRlAiy44QhWbAurVr1qMROYLsSNDr27hZC5q9uDaH3MBfc9idmNHv4Mhdc2B0GFHy568RFQ4DvXrrMIO/WN/O4Etg6ty3/o/56zw8d+l8GZk3z3zv8fXbOey1DT+8oLyO3te3zoG3Xfr7cXefXfkFGF5/dpVnIHen1eXFguF5YZd+EEInH12KVBieInQBqGF1A8rl4YfPhRiXdiRW591cKKb43IpyUegicBfGOONzNZ54Y3Iw6rhjcD3CNeKPr5koJJHAGfmWekji1t5cMiKZo1wPNumahHUlYqVrDdYV5Y5TzjXkj0rKVWCTYdKlIJHo5fUliWnWZdyPy/Wl5Y5d8mXIjYYEViWJWAa254d9EpbImwfmOdicAdaZGCKIJsdBm4sZEiluHBQKmSJjXMrBGBxaxoghgowBGmhjCGLIk5u16uqrMrDGKuustNZq66245qrrrrz26uuvwAYr7LDEFmvsscgmq+yyzDbr7LPQRivttNRWe1BAACH5BAkEAM8ALAAAAACmAKYAhwAAAAEBAQICAgMDAwQEBAUFBQYGBgcHBwgICAkJCQoKCgsLCwwMDA0NDQ4ODg8PDxAQEBERERISEhMTExQUFBUVFRYWFhcXFxgYGBkZGRoaGhsbGxwcHB0dHSccHEgWFoUJCaADA6oBAa4AALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALEAALECArMHB7MJCbQLC7QMDLQNDbQODrUODrUPD7UQELQSErIXF6siIp4zM5c4OJM6OpM8PJI+PolBQYNDQ4BGRn1JSXpNTXZRUXJVVW5aWmlfX2VlZWZmZmdnZ2hoaGlpaWpqamtra2xsbG1tbW5ubm9vb3dzc392doZ6eo19fZSAgJqDg6CGhqWIiKqKiq+MjLOOjreQkLqRkb2SksCTk8OVlcWVlceWlsiXl8mXl8qYmM2amtGentakpNypqeCuruW0tOi5ueq9vevAwOzCwu3Fxe7Hx+7Jye/Ly/DOzvDQ0PHT0/LX1/Pa2vTd3fbh4ffm5vnt7fz29v7+/v7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AJ8JHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXcq0qdOnUKNKnUq1qtWrWLNq3cq1q9evYMOKHUu2rNmzaNOqXcu2rdu3cOPKnUu3rt27ePPq3cu3r9+/gAMLHky4sOHDiBMrXsy4sePHkCNLnky5suXLmDNr3sy5s+fPoEOLHk26tOnTqFOrXg150yFAW7ZYsWHDSmxAhzZVzgSbtu/fwLcAygQ5ExngyJPTJkN88SZAyqMnB6QbcaIp0rMDn5LoMHTt4H/+AyK86Xj488urA76Cvr2NK4HNu0dP5u/3+e3H802EH3+jvZtg1597U6h3130D5pdXgAnOVyBeCDaInn51bSJhfwbKZciF+BliFxgczldfXQKGiN4UdfFnonvdzRXhiuBRGNeLMGYnI1xb1IjeFnTlqGN4PM4124/gwTcXe0RqF6RcPiYp3ZJxgeikdGDQReOUvt341pVY2qClW5R0qRwldQ0ppm9W2CXfmV7apSKbNrRYlxRwSoHXhmx6iJeZWKaZF55d6pkXklMaqVcmdDopRXN7NTLlf34BqqOgfq254pd8SRoipYFRkuiFUpBpWCZNJrgFo4c1wqd7VkDKmCE8q4JnBaeNUQIIodFdAYiouzUCCCBkxEbGr42gytqxyCar7LLMNuvss9BGK+201FZr7bXYZqvtttx2e1lAACH5BAkEAM8ALAAAAACmAKYAh6EAALAAALAAALAAALAAALAAALAAALEBAbECArECArEDA7IEBLIEBLIEBLEDA7EAALEAALEAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAALAAAKoBAZIGBmsPDzsbGyIiIiMjIyQkJCUlJSYmJicnJygoKCkpKSoqKisrKywsLC0tLS4uLi8vLzAwMDExMTIyMjMzMzQ0NDU1NTY2Njc3Nzg4ODk5OTo6Ojs7Ozw8PD09PT4+Pj8/P0BAQEFBQUJCQkNDQ0REREVFRUZGRkdHR0hISElJSUpKSktLS0xMTE1NTU5OTk9PT1BQUFFRUVJSUlNTU1RUVFVVVVZWVldXV1hYWFlZWVpaWltbW1xcXF1dXWVfX21hYXViYoBiYophYZNfX5peXqJaWqlWVq5TU7FQULROTrZMTLhLS7lKSrpJSbtISLtJSbtJSbtLS7pMTLtOTrlRUbdUVLZYWLNcXLBiYq1lZapoaKZsbKFwcJx0dJZ5eZh9fZ+GhqONjaiVla6enreqqsK3t87CwtvOzuXY2Ozf3/Hl5fXr6/jw8Prz8/v29vz4+P35+f35+f36+v37+/78/P78/P79/f7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v7+/v///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wj+AJ8JHEiwoMGDCBMqXMiwocOHECNKnEixosWLGDNq3Mixo8ePIEOKHEmypMmTKFOqXMmypcuXMGPKnEmzps2bOHPq3Mmzp8+fQIMKHUq0qNGjSJMqXcq0qdOnUKNKnUq1qtWrWLNq3cq1q9evYMOKHUu2rNmzaNOqXcu2rdu3cOPKnUu3rt27ePPq3cu3r9+/gAMLHky4sOHDiBMrXsy4sePHkCNLnky5suXLmDNr3sy5s+fPoEOLHk26tOnTqFOrXs26tevXsGPLnk27tu3buHPr3s27d8xEgui4cROheIThdAQlqnyID3Hj0KMf53MIcqLn0rNHp7N8cSI82sP+Z8fT/fAg8eilLxh0mE/699H5EGZEB75943QYBWaE/b59N/r9VZ9/BOLx1x8EJhjBH32dp2CC7OnFyAIPJrhAgHghWGGCDOKVCIUbEnghXneEqOAddzFi4oMYzuXgigRGSNccMCY4h101WlhXITkmWAhdGvZ4X4dyBSkkfETG1d+R6blB15JMiufkXCBGmd4CT1r53pRyQamldFzGReOX4qE4l5FkSpckXIGkGV4gdB3ipnbV0VXlnMVhWdceeEK3h12G9GmcIXfd6aaedr04p4x2GfolonfxOCehennJZJh4TUjmiHwd4miPC9TZoJaM9tUmk3AKNsinGy7wI2E8ntYY6mGMjBniHC0aZoil8LlBKWO7EuirZIkEYmt4cwRSHmWMGFLIH3/MMQe0hRiSq2/YZqvtttx2G1NAADsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA=');
background-color:white;
opacity : 1;
background-repeat : no-repeat;
background-position : center;
left : 0;
bottom : 0;
right : 0;
top : 0;
}
#loadinggif.show
{
left : 50%;
top : 50%;
position : absolute;
z-index : 101;
width : 32px;
height : 32px;
margin-left : -16px;
margin-top : -16px;
}

</style>
<script>function myFunction() {
 $("#divLoading").animate({height:900},800);

}</script>

        </div>
    </body>
</html>

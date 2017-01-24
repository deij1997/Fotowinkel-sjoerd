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
            
                                                    <form action="CropperServlet" method="POST" onsubmit="return validate();">
                                                        <textarea name="image_file" style="display: none;"class="form-control" id="dataURL" rows="10"></textarea>
                                                        <textarea name="image_name" style="display: none;"class="form-control" id="dataURL" rows="10"><%=img%></textarea>

                                                        <input type="submit" value="Save Image">
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

    </body>
</html>

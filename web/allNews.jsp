<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>

<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <meta charset="UTF-8">
        <meta name="description" content="Male_Fashion Template">
        <meta name="keywords" content="Male_Fashion, unica, creative, html">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Betrolimex</title>
        <link rel="icon" href="img/favicon.png" />   
        <!-- Google Font -->
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;400;600;700;800;900&display=swap"
              rel="stylesheet">

        <!-- Css Styles -->
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
        <link rel="stylesheet" href="css/elegant-icons.css" type="text/css">
        <link rel="stylesheet" href="css/magnific-popup.css" type="text/css">
        <link rel="stylesheet" href="css/nice-select.css" type="text/css">
        <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css">
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="css/cart.css" rel="stylesheet" type="text/css"/>
    </head>

    <body onload="changePara()">
        <%@include file="header.jsp" %>
        <div id="wrapper">

            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h4 style="  font-family: Arial, sans-serif;
                                font-size: 25px;
                                font-weight: bold;
                                color: #ffFFFF;margin-top:2%;background-color: #F26F21;
                                padding: 0.5%; text-align: left; padding-left: 2%"><i class="fa fa-newspaper-o" aria-hidden="true"></i> All News</h4>
                        </div>
                    </div>
                </div>


                <div class="row">
                    <c:forEach items="${requestScope.listnew}" var="n">
                        <div style="padding: 3% 1%;display: flex;" class="col-6">
                            <div class="col-md-4">
                                <div class="post-media">
                                    <a href="newsdetail?newId=${n.getNewID()}">
                                        <img class="img-fluid" src="data:image/jpeg;base64,${n.encodedImage}">                                                                                                  
                                    </a>
                                </div>
                            </div>
                            <div class="col-md-8">
                                <h4><a href="newsdetail?newId=${n.getNewID()}" title="">${n.getNewsTitle()}</a></h4>
                                <small>${n.newsHeading}</small>
                                <small><a href="#">by Minh Bùi</a></small>
                                <h6 id="content">${n.getNewsContent()}</h6>
                            </div>                                          
                        </div>
                    </c:forEach>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <nav aria-label="Page navigation">
                            <ul class="pagination justify-content-start">
                                <li class="page-item"><a class="page-link" href="#">1</a></li>
                                <li class="page-item"><a class="page-link" href="#">2</a></li>
                                <li class="page-item"><a class="page-link" href="#">3</a></li>
                                <li class="page-item">
                                    <a class="page-link" href="#">Next</a>
                                </li>
                            </ul>
                        </nav>
                    </div><!-- end col -->
                </div><!-- end row -->
            </div>

            <footer style="margin-top: 3%" class="footer ">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-3 col-md-6 col-sm-6">
                            <div class="footer__about">
                                <div class="footer__logo">
                                    <a href="#"><img src="img/footer-logo.png" alt=""></a>
                                </div>
                                <p>The customer is at the heart of our unique business model, which includes design.</p>
                                <a href="#"><img src="img/payment.png" alt=""></a>
                            </div>
                        </div>

                        <div class="col-lg-2 offset-lg-1 col-md-3 col-sm-6">
                            <div class="footer__widget">
                                <h6>Shopping</h6>
                                <ul>
                                    <c:forEach items="${sessionScope.footerlist}" var="c" varStatus="loop">
                                        <li><a style="
                                               a{
                                                   color: black;
                                               }
                                               " href="/swp${c.navigationDescription}">${c.navigationName}</a></li>
                                            <c:if test="${loop.index} gt 7">

                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>

                        <div class="col-lg-3 offset-lg-1 col-md-6 col-sm-6">
                            <div class="footer__widget">
                                <h6>NewLetter</h6>
                                <div class="footer__newslatter">
                                    <p>Be the first to know about new arrivals, look books, sales & promos!</p>
                                    <form action="#">
                                        <input type="text" placeholder="Your email">
                                        <button type="submit"><span class="icon_mail_alt"></span></button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-12 text-center">
                            <div class="footer__copyright__text">
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                                <p>Copyright ©
                                    All rights reserved | This template is made with <i class="fa fa-heart-o"
                                                                                        aria-hidden="true"></i> by <a style="color: red">Group 6</a>
                                </p>
                                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            </div>
                        </div>
                    </div>
                </div>
            </footer>



        </div><!-- end wrapper -->
        <script>
            function changePara() {
                console.log(1);
                var paragraphs = document.querySelectorAll("[id='content']");
                paragraphs.forEach(function (paragraph) {
                    var text = paragraph.innerHTML;
                    paragraph.innerHTML = text.substring(0, 100);
                    console.log(text);
                });
            };
        </script>
        <!-- Core JavaScript
        ================================================== -->
        <script src="js/jquery.min.js"></script>
        <script src="js/tether.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/custom.js"></script>

    </body>
</html>
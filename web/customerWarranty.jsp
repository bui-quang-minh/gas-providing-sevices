<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<!-- 
    Document   : Cart
    Created on : May 10, 2023
    Author     : An
-->
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
        <link rel="stylesheet" href="css/slicknav.min.css" type="text/css">
        <link href="css/cart.css" rel="stylesheet" type="text/css"/>
    </head>

    <body>
        <!-- header include -->
        <%@include file="header.jsp" %> 
        <c:set var="account" value="${requestScope.ID}"/>
        <div>

            <a href="/swp/cart" class="shopping-cart" id="cart-icon">
                <img  src="img/icon/cart.png" alt="">
                <div class="quantity" style="z-index:100;margin-top: -5rem;margin-left: 1.5rem;border: 2px solid black; background-color: red;border-radius: 50px;
                     height: 2rem;width: 2rem;font-size: 15px;text-align: center;line-height: 2rem;
                     ">
                    1<!-- dua quantity vao day -->
                </div>
            </a>
        </div><!-- comment -->
        <style>
            .shopping-cart {
                position: fixed;
                z-index: 10;
                right: 1rem;
                bottom: 2rem;
                width: 4rem;
                height: 4rem;
                background-color: wheat;
                border-radius: 50%;
                outline: .5rem solid transparent;
                text-align:center;
                line-height: 4rem;
                cursor: pointer;
                box-shadow: 0 .2rem .6rem black;
                transition: .5s ease-in-out;
            }
            .shopping-cart.active {
                width: 8rem;
                border-radius: .8rem;
            }
            .shopping-cart.active > span{
                margin-left: -4rem;
            }
        </style>
        <div class="container" style="display: flex; padding-top: 1% ">
            <!-- customer profile -->
            <div class="catagories col-2" style="background-color: inherit">
                <div class="profile" style="display: flex; padding-bottom: 20%;padding-top: 20%">
                    <div class="col-5">
                        <img class="rounded-circle" src="data:image/jpeg;base64,${account.getEncodedImage()}">
                    </div>
                    <div class="username">
                        <div class="name">
                            ${account.firstname} ${account.lastname}
                        </div>
                    </div>
                </div>
                <div>
                    <a style="color: black" href="allproduct">All Product</a></br>
                    <a style="color: black" href="vieworder">Your Order</a></br>
                    <a style="color: black" href="cart">Cart</a></br>
                    <a style="color: black" href="promotion">Promotion</a></br>
                    <a style="color: black" href="warranty">Warranty</a></br>
                    <a style="color: black" href="profile">Profile</a></br>
                </div>
                <div id="shipment-detail" style="margin-top: 50%;width: 10%">
                </div>
            </div>
            <div class="cart_content col-11" style="background: #F5F5F5; border-radius:10px;">
                <div class="information-title" style="padding:2%; border-bottom: 1px solid lightgray ">
                    <h3>Warranty</h3>
                    <h4>Manage items's insurance Expiration Date</h4>
                    <br/>
                    <div class="row header_cart" style="border-top: 2px solid gold">
                        <div class="col-md-2">
                            Image
                        </div>
                        <div class="col-md-2">
                            Product Name
                        </div>
                        <div class=" col-md-2">
                            Purchased date 
                        </div>
                        <div class=" col-md-2">
                            Expiration date 
                        </div>
                        <div class=" col-md-2">
                            Status
                        </div>
                    </div>
                    <br/>
                    <c:set var="totalAn" value="0"/>
                    <div class="row">
                        <fmt:formatDate var="today" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />
                        <c:forEach items="${requestScope.warrantylist}" var="c">
                            <div class="box-content_cart row">
                                <div class=" col-md-2 img">
                                    <c:forEach items="${requestScope.productImageList}" var="pi">
                                        <c:if test="${pi.productID eq c.getProductID() and (pi.imageType eq 'thumbnail')}">
                                            <img class="" style="margin-top: 7%;width: 100%;height: 100%" 
                                                 src="data:image/jpeg;base64,${pi.encodedProductImage}" alt="Image">
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="col-md-2 name">
                                    <c:forEach items="${requestScope.productList}" var="k">
                                        <c:if test="${k.productID eq c.getProductID()}">
                                            <a style="color:black" href="productdetail?productId=${k.getProductID()}">${k.getProductName()}</a> 
                                            
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="col-md-2 purchaseddate">
                                    <p>${c.getPurchasedDate()}</p>
                                </div>
                                <div class="col-md-2 getExpirationDate">
                                    <p> ${c.getExpirationDate()} </p>
                                </div>
                                <div class="col-md-2 stat">
                                    <c:if test="${today eq c.getExpirationDate()}">
                                        <p style="color: orange">The warranty expires today.</p>
                                    </c:if>
                                    <c:if test="${today lt c.getExpirationDate()}">
                                        <p style="font-style: inherit;color: green"> The warranty is still valid.</p>
                                    </c:if>
                                    <c:if test="${today gt c.getExpirationDate()}">
                                        <b style="font-style: oblique;color: red"> The warranty has expired.</b>
                                    </c:if>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
        <br/>



        <!-- Footer Section Begin -->
        <%@include file="footer.jsp" %> 
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.nice-select.min.js"></script>
        <script src="js/jquery.nicescroll.min.js"></script>
        <script src="js/jquery.magnific-popup.min.js"></script>
        <script src="js/jquery.countdown.min.js"></script>
        <script src="js/jquery.slicknav.js"></script>
        <script src="js/mixitup.min.js"></script>
        <script src="js/owl.carousel.min.js"></script>
        <script src="js/main.js"></script>
    </body>

</html>
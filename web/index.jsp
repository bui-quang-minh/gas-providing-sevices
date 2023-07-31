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
        <link href="assets/css/chat.css" rel="stylesheet" type="text/css"/>
        <link href="css/chat.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
        <link href="assets/css/typing.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            function toggleDisplay(elementId) {
                var element = document.getElementById(elementId);

                element.addEventListener("click", function () {
                    if (element.style.display === "block") {
                        element.style.display = "none";
                    } else {
                        element.style.display = "block";
                    }
                });
            }
        </script>
        <script>
            setInterval(loadmessage, 1000);
            function loadmessage() {
                $.get("customerchat", function (data, status) {
                    document.getElementsByClassName("ex1")[0].innerHTML = data;

                });
            }


            $(document).ready(function () {
                var inputbox = document.getElementById("message");
                inputbox.addEventListener('keyup', function (event) {
                    if (event.keyCode == 13) {
                        $("#send").click();
                    }
                });
                $("#send").click(function () {
                    var message = $("#message").val();
                    $.post("customerchat", {message: message}, function (data, status) {
                        document.getElementsByClassName("ex1")[0].innerHTML = data;
                        document.getElementsByClassName("ex1").scrollTop = ex1.scrollHeight;
                    });
                    $("#message").val("");


                });
            });
        </script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div>

            <a href="/swp/cart" class="shopping-cart" id="cart-icon">
                <img  src="img/icon/cart.png" alt="">
                <div id="quantity" class="quantity" style="z-index:100;margin-top: -4rem;margin-left: 2.5rem;border: 2px solid black; background-color: red;border-radius: 50px;
                     height: 1rem;width: 1rem;font-size: 15px;text-align: center;line-height: 1rem;
                     ">
                </div>
            </a>
        </div>

        <fmt:formatDate var="today" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />
        <div class="container1">
            <div class="chatbox" style="margin-right: 100px;">
                <div class="chatbox__support">
                    <div class="chatbox__header">
                        <div class="chatbox__image--header">

                            <img src="assets/images/image.png" alt="image"/>
                        </div>
                        <div class="chatbox__content--header">
                            <h4 class="chatbox__heading--header">Chat support</h4>
                        </div>
                    </div>

                    <div class="chatbox__messages" >
                        <div class="ex1">   
                        </div>
                    </div>

                    <div class="chatbox__footer">

                        <img src="assets/images/icons/emojis.svg" alt="">
                        <img src="assets/images/icons/microphone.svg" alt="">
                        <input type="text"  id="message" placeholder="Write a message...">
                        <input type="submit" value="send" id="send"/>

                    </div>

                </div>

                <div class="chatbox__button infor-icon"

                     style="position: fixed;z-index: 100;margin-left: 95%;margin-bottom: 85px;
                     font-size: 25px;border-radius: 50%;color: orange;">

                    <img src = "https://cdn.icon-icons.com/icons2/1150/PNG/512/1486504827-comment-speech-bubble-chat-support-talk_81377.png" alt>
                </div>
            </div>
        </div>
        <!-- Hero Section Begin -->
        <section class="hero" style="padding:0;text-align: center;height:640px">


            <div class="hero__slider owl-carousel" style="padding-top: 0;height:auto">
                <c:forEach items="${sessionScope.Banner}" var="c" varStatus="status">


                    <div class="hero__items set-bg" data-setbg="" style="padding-top: 0;text-align:center;height: 640px">
                        <img src="data:image/jpeg;base64,${c.encodedImage}">
                    </div>

                </c:forEach>
            </div>


        </section> 
        <!-- Latest Blog Section Begin -->
        <section class="latest">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h4 style="  font-family: Arial, sans-serif;
                                font-size: 25px;
                                font-weight: bold;
                                color: #ff3c00;">Best Discount</h4>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach items="${requestScope.productlist3BestDiscount}" var="product">
                        <div class="product__item ${product.getProductID()} col-4">


                            <div class="product__item__pic ${product.getProductID()} set-bg set-bg3 first"
                                 data-setbg="data:image/jpeg;base64,${product.thumbnail}"
                                 id='f${product.getProductID()}'
                                 >

                                <ul class="product__hover">
                                    <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/heart.png" alt=""></a></li>
                                    <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/compare.png" alt=""> <span>Compare</span></a></li>
                                    <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/search.png" alt=""></a></li>
                                </ul>
                            </div>
                            <div class="product__item__text ">
                                <h6>${product.getProductName()}</h6>
                                <a class="add_cart ${product.getProductID()}"onclick="addToCart('${product.getProductID()}');
                                        AddToCartAnimation('${product.getProductID()}', 'first', 'f${product.getProductID()}')"
                                   style="cursor: pointer">
                                    Add To Cart
                                </a>


                                <div class="rating">
                                    <c:if test="${product.rate!=0}">
                                        <div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold ${product.rate/5*100}%,transparent 30%,transparent 100%);"></div>
                                    </c:if>
                                    <c:if test="${product.rate==0}">
                                        <div>No rate yet</div>
                                    </c:if>  
                                </div>
                                <c:forEach var="discount" items="${requestScope.allDiscount}">
                                    <c:if test="${discount.productID eq product.productID and discount.startDate <= today and discount.endDate >= today}">
                                        <h5><del><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</del></h5>
                                        <h5 style="color: red;">
                                            <fmt:formatNumber value="${product.price - product.price * discount.discountValue / 100}" type="number" maxFractionDigits="0" />&nbsp;VND
                                        </h5>
                                        <c:set var="discounted" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${discounted eq false}">
                                    <h5><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</h5>
                                </c:if>
                                <c:set var="discounted" value="false" />
                                <div class="product__color__select">
                                    <label for="pc-1">
                                        <input type="radio" id="pc-1">
                                    </label>
                                    <label class="active black" for="pc-2">
                                        <input type="radio" id="pc-2">
                                    </label>
                                    <label class="grey" for="pc-3">
                                        <input type="radio" id="pc-3">
                                    </label>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>        
            </div>

        </section>
        <!-- Latest Blog Section End -->

        <section class="product" style="padding: 0;margin-top: 5%">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <ul class="filter__controls">
                            <li id="acctive" class="active" onclick="fillter('best-seller')">All</li>
                            <li onclick="fillter('hot-sales')">Best price</li>
                            <li onclick="fillter('new-arrivals')">Latest Product</li>
                        </ul>
                    </div>
                </div>
                <!-- Product Section begins -->       
                <div class="row">
                    <!-- Product list --> 
                    <c:forEach items="${requestScope.productList}" var="product">
                        <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix best-seller list">
                            <div class="product__item ${product.getProductID()}">
                                <div class="product__item__pic ${product.getProductID()} set-bg set-bg3 third"
                                     data-setbg="data:image/jpeg;base64,${product.thumbnail}"
                                     id='t${product.getProductID()}'
                                     >
                                    <ul class="product__hover">
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/heart.png" alt=""></a></li>
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/compare.png" alt=""> <span>Compare</span></a></li>
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/search.png" alt=""></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text ">
                                    <h6>${product.getProductName()}</h6>
                                    <a class="add_cart ${product.getProductID()}" onclick="addToCart('${product.getProductID()}');
                                            AddToCartAnimation('${product.getProductID()}', 'third', 't${product.getProductID()}')"

                                       style="cursor: pointer">
                                        Add To Cart
                                    </a>

                                    <div class="rating">
                                        <c:if test="${product.rate!=0}">
                                            <div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold ${product.rate/5*100}%,transparent 30%,transparent 100%);"></div>
                                        </c:if>
                                        <c:if test="${product.rate==0}">
                                            <div>No rate yet</div>
                                        </c:if>  
                                    </div>
                                    <c:forEach var="discount" items="${requestScope.allDiscount}">
                                        <c:if test="${discount.productID eq product.productID and discount.startDate <= today and discount.endDate >= today}">
                                            <h5><del><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</del></h5>
                                            <h5 style="color: red;">
                                                <fmt:formatNumber value="${product.price - product.price * discount.discountValue / 100}" type="number" maxFractionDigits="0" />&nbsp;VND
                                            </h5>
                                            <c:set var="discounted" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${discounted eq false}">
                                        <h5><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</h5>
                                    </c:if>
                                    <c:set var="discounted" value="false" />
                                    <div class="product__color__select">
                                        <label for="pc-1">
                                            <input type="radio" id="pc-1">
                                        </label>
                                        <label class="active black" for="pc-2">
                                            <input type="radio" id="pc-2">
                                        </label>
                                        <label class="grey" for="pc-3">
                                            <input type="radio" id="pc-3">
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <c:forEach items="${requestScope.productListDiscount}" var="product">
                        <div id="hot-sales"class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix hot-sales list">
                            <div class="product__item ${product.getProductID()}">
                                <div class="product__item__pic ${product.getProductID()} set-bg set-bg3 forth"
                                     data-setbg="data:image/jpeg;base64,${product.thumbnail}"
                                     id='forth${product.getProductID()}'
                                     >
                                    <ul class="product__hover">
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/heart.png" alt=""></a></li>
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/compare.png" alt=""> <span>Compare</span></a></li>
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/search.png" alt=""></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text ">
                                    <h6>${product.getProductName()}</h6>
                                    <a class="add_cart ${product.getProductID()}"onclick="addToCart('${product.getProductID()}');
                                            AddToCartAnimation('${product.getProductID()}', 'forth', 'forth${product.getProductID()}')"

                                       style="cursor: pointer">
                                        Add To Cart
                                    </a>


                                    <div class="rating">
                                        <c:if test="${product.rate!=0}">
                                            <div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold ${product.rate/5*100}%,transparent 30%,transparent 100%);"></div>

                                        </c:if>
                                        <c:if test="${product.rate==0}">
                                            <div>No rate yet</div>
                                        </c:if>  
                                    </div>
                                    <c:forEach var="discount" items="${requestScope.allDiscount}">
                                        <c:if test="${discount.productID eq product.productID and discount.startDate <= today and discount.endDate >= today}">
                                            <h5><del><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</del></h5>
                                            <h5 style="color: red;">
                                                <fmt:formatNumber value="${product.price - product.price * discount.discountValue / 100}" type="number" maxFractionDigits="0" />&nbsp;VND
                                            </h5>
                                            <c:set var="discounted" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${discounted eq false}">
                                        <h5><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</h5>
                                    </c:if>
                                    <c:set var="discounted" value="false" />
                                    <div class="product__color__select">
                                        <label for="pc-1">
                                            <input type="radio" id="pc-1">
                                        </label>
                                        <label class="active black" for="pc-2">
                                            <input type="radio" id="pc-2">
                                        </label>
                                        <label class="grey" for="pc-3">
                                            <input type="radio" id="pc-3">
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                    <c:forEach items="${requestScope.newProductList}" var="product">
                        <div id="new-arrivals" class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix new-arrivals list">
                            <div class="product__item ${product.getProductID()}">
                                <div class="product__item__pic ${product.getProductID()} set-bg set-bg3 firth"
                                     data-setbg="data:image/jpeg;base64,${product.thumbnail}"
                                     id='firth${product.getProductID()}'
                                     >
                                    <ul class="product__hover">
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/heart.png" alt=""></a></li>
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/compare.png" alt=""> <span>Compare</span></a></li>
                                        <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/search.png" alt=""></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text ">
                                    <h6>${product.getProductName()}</h6>
                                    <a class="add_cart ${product.getProductID()}" onclick="addToCart('${product.getProductID()}');
                                            AddToCartAnimation('${product.getProductID()}', 'firth', 'firth${product.getProductID()}')"

                                       style="cursor: pointer">
                                        Add To Cart
                                    </a>

                                    <div class="rating">
                                        <c:if test="${product.rate!=0}">
                                            <div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,${product.rate/5*100}%,transparent 30%,transparent 100%);"></div>
                                        </c:if>
                                        <c:if test="${product.rate==0}">
                                            <div>No rate yet</div>
                                        </c:if>  
                                    </div>
                                    <c:forEach var="discount" items="${requestScope.allDiscount}">
                                        <c:if test="${discount.productID eq product.productID and discount.startDate <= today and discount.endDate >= today}">
                                            <h5><del><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</del></h5>
                                            <h5 style="color: red;">
                                                <fmt:formatNumber value="${product.price - product.price * discount.discountValue / 100}" type="number" maxFractionDigits="0" />&nbsp;VND
                                            </h5>
                                            <c:set var="discounted" value="true" />
                                        </c:if>
                                    </c:forEach>
                                    <c:if test="${discounted eq false}">
                                        <h5><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</h5>
                                    </c:if>
                                    <c:set var="discounted" value="false" />
                                    <div class="product__color__select">
                                        <label for="pc-1">
                                            <input type="radio" id="pc-1">
                                        </label>
                                        <label class="active black" for="pc-2">
                                            <input type="radio" id="pc-2">
                                        </label>
                                        <label class="grey" for="pc-3">
                                            <input type="radio" id="pc-3">
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </div>
    </section>

    <section class="latest spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <span style="  font-family: Arial, sans-serif;
                              font-size: 25px;
                              font-weight: bold;
                              color: #ff3c00;">Latest News</span>
                    </div>
                </div>
            </div>
            <div class="row">
                <c:forEach items="${requestScope.listnew}" var="n" begin="0" end="2" >
                    <div class="col-lg-4 col-md-6 col-sm-6">
                        <div class="blog__item">
                            <div class="blog__item__pic set-bg" data-setbg="data:image/jpeg;base64,${n.encodedImage}"></div>
                            <div class="blog__item__text">
                                <span><img src="img/icon/calendar.png" alt=""> ${n.getCreatedDate()}</span>
                                <a href="newsdetail?newId=${n.getNewID()}"><h5>${n.getNewsHeading()}</h5></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- Latest Blog Section End -->




    <!-- Product Section End -->
    <section>
        <div class="row">
            <div class="col-12">
                <div class="section-title">
                    <h4 style="  font-family: Arial, sans-serif;
                        font-size: 25px;
                        font-weight: bold;
                        color: #ff3c00;">Where are we</h4>
                </div>
            </div>
        </div>
        <div class="container" style="display: flex;margin-bottom: 5%">
            <div class="col-3" style="border-radius: 10px;background-color: #F5F5F5">
                <h3 class="mb-4 mt-md-4">Contact us</h3>
                <p class="text">We are open for any suggestion!</p>
                <div class="dbox w-100 d-flex align-items-start">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="fa fa-map-marker"></span>
                    </div>
                    <div class="text pl-3">
                        <p><span>Address:</span> Đất Thổ Cư Hòa Lạc, Km29, ĐCT08, Thạch Hoà, Thạch Thất, Hà Nội</p>
                    </div>
                </div>
                <div class="dbox w-100 d-flex align-items-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="fa fa-phone"></span>
                    </div>
                    <div class="text pl-3">
                        <p><span>Phone:</span> <a href="tel: +84399955598">+84399955598</a></p>
                    </div>
                </div>
                <div class="dbox w-100 d-flex align-items-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="fa fa-paper-plane"></span>
                    </div>
                    <div class="text pl-3">
                        <p><span>Email:</span> <a href="mailto:info@yoursite.com">swpgroup6@gmail.com</a></p>
                    </div>
                </div>
                <div class="dbox w-100 d-flex align-items-center">
                    <div class="icon d-flex align-items-center justify-content-center">
                        <span class="fa fa-globe"></span>
                    </div>
                    <div class="text pl-3">
                        <p><span>Website</span> <a href="localhost:9999/swp/">localhost:9999/swp/</a></p>
                    </div>
                </div>

            </div>
            <div class="col-9" ><iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.485534710303!2d105.52448401104708!3d21.01324998055151!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31345b465a4e65fb%3A0xaae6040cfabe8fe!2zVHLGsOG7nW5nIMSQ4bqhaSBI4buNYyBGUFQ!5e0!3m2!1svi!2s!4v1686795049910!5m2!1svi!2s" width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe></div>

        </div>
    </section>
    <!-- Footer Section Begin -->
    <%@include file="footer.jsp" %>
    <!-- Footer Section End -->
    <style>
        .star-rating::before {
            /* What also works: "⭐⭐⭐⭐⭐" or "💛💛💛💛💛" or other characters. */
            content: "⭐⭐⭐⭐⭐";
        }

        .star-rating {
            display: inline-block;
            background-clip: text;
            -webkit-background-clip: text;
            color: rgba(0, 0, 0, 0.1);
        }
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
        .animate{
            animation: fly_to_cart 1s ease-in;
            z-index: 100;
            border-radius: inherit;
        }
        /* left then top */
        @keyframes fly_to_cart {
            0% {
                transform: translate(0, 0) scale(1);
            }
            100%{
                transform:translate(var(--left), var(--top)) scale(0);
            }
        }
        .disapear{
            animation: fadeIn 2s;
        }
        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }
    </style>
    <script>
        $.fn.stars = function () {
            return $(this).each(function () {
                // Get the value
                var val = parseFloat($(this).html());
                // Make sure that the value is in 0 - 5 range, multiply to get width
                var size = Math.max(0, (Math.min(5, val))) * 16;
                // Create stars holder
                var $span = $('<span />').width(size);
                // Replace the numerical value with stars
                $(this).html($span);
            });
        };
    </script>
    <script>
        function loadfirst() {
            fillter('best-seller');
        }

        window.addEventListener("load", loadfirst, {once: true});
        function fillter(fillter) {
            var elementshide = document.querySelectorAll('[class*="list"]');
            for (var i = 0; i < elementshide.length; i++) {
                elementshide[i].classList.add("disapear");
                elementshide[i].style.display = "none";
            }
            //            console.log(fillter);
            var elementshow = document.querySelectorAll('[class*="' + fillter + '"]');

            for (var j = 0; j < elementshow.length; j++) {
                elementshow[j].style.display = "block";
            }
        }
        var quantity = ${sessionScope.quantity}
        function AddToCartAnimation(target, index, pic) {
            var img = document.getElementsByClassName('product__item__pic ' + target + ' set-bg set-bg3 ' + index);

            var cart = document.getElementById("cart-icon");
            var cartsize = document.getElementById("cart-icon");
            cartsize.classList.add("active");
            quantity++;
            var firstImg = document.getElementById(pic);
            let top = 0;
            let left = 0;
            left = cart.getBoundingClientRect().left - (cart.offsetWidth / 2 + firstImg.getBoundingClientRect().left + firstImg.offsetWidth) + 200;
            top = cart.getBoundingClientRect().top - (cart.offsetHeight / 2 + firstImg.getBoundingClientRect().top + firstImg.offsetHeight) + 250;
            for (var i = 0; i < img.length; i++) {
                img[i].classList.add("animate");

                img[i].style.cssText += '--left: ' + left + 'px;--top: ' + top + 'px;';
            }

            var item = document.getElementsByClassName('product__item ' + target);
            for (var i = 0; i < item.length; i++) {
                item[i].style.overflow = "visible";
            }
            setTimeout(function () {
                cartsize.classList.remove("active");

                for (var k = 0; k < img.length; k++) {
                    img[k].classList.remove('animate');
                }
                for (var i = 0; i < item.length; i++) {
                    item[i].style.overflow = "hidden";
                }
            }, 1000);
        }
        function addToCart(pid) {

            const xhttp = new XMLHttpRequest();
            // You can add additional code here to perform other actions, such as updating the cart counter or making an AJAX request.
            var elements = document.getElementsByClassName('add_cart ' + pid);
            for (var i = 0; i < elements.length; i++) {
                elements[i].innerHTML = "ADDED";
            }


            xhttp.open("POST", "allproduct", true);
            xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xhttp.send("productID=" + pid);
        }
    </script>
    <!-- Search Begin -->
    <div class="search-model">
        <div class="h-100 d-flex align-items-center justify-content-center">
            <div class="search-close-switch">+</div>
            <form class="search-model-form">
                <input type="text" id="search-input" placeholder="Search here.....">
            </form>
        </div>
    </div>
    <!-- Search End -->

    <script src="assets/js/chat.js" type="text/javascript"></script>
    <script src="assets/js/app.js" type="text/javascript"></script>
    <!-- Js Plugins -->
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
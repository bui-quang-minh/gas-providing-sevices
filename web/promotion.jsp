<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>

<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.LocalDate"%>
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
        <link href="css/promotion.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <fmt:formatDate var="today" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />
        <!-- Latest Blog Section Begin -->
        <section class="latest">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h4 style="  font-family: Arial, sans-serif;
                                font-size: 25px;
                                font-weight: bold;
                                color: #ffFFFF;margin-top:2%;background-color: #F26F21;
                                padding: 0.5%; text-align: left; padding-left: 2%"><i class="fa fa-tag"></i> All Promotion</h4>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <c:forEach var="d" items="${requestScope.listDiscount}">
                        <div style="padding: 3% 1%;display: flex;" class="col-6">
                            <div class="col-md-4">
                                <div class="post-media">
                                    <a href="<%request.getRequestURL();%>productdetail?productId=${d.productID}">
                                        <img class="img-fluid" src="img/favicon.png">                                                                                                  
                                    </a>
                                </div>
                            </div>
                            <div class="col-8 text-display">
                                    ${d.discountName}</br>
                                    <span class="tag-display"><i class="fa fa-tag"></i> ${d.discountValue}% off</span>
                                    <span class="tag-display"><i class="fa fa-tag"></i><fmt:formatDate value="${d.startDate}" pattern="dd-MM-yyyy" /></span>
                                    <span class="tag-display"><i class="fa fa-tag"></i> <fmt:formatDate value="${d.endDate}" pattern="dd-MM-yyyy" /></span></br>
                                    <c:set var="startDateMillis" value="${d.startDate.time}" />
                                    <c:set var="endDateMillis" value="${d.endDate.time}" />
                                    <c:set var="diffInMillis" value="${endDateMillis - startDateMillis}" />
                                    <c:set var="diffInDays" value="${diffInMillis / (1000 * 60 * 60 * 24)}" />
                                    Expired In: <span class="time-display"><i class="fa fa-clock-o"></i> <fmt:formatNumber value="${diffInDays}" pattern="#0" /> day(s).</span></br>
                                    <a href="<%request.getRequestURL();%>productdetail?productId=${d.productID}"><i class="fa fa-link" aria-hidden="true"></i> Get to it!</a>
                                </div>                                         
                        </div>
                    </c:forEach>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
        <!-- Footer Section End -->
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
                document.getElementById("quantity").innerHTML = quantity;
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
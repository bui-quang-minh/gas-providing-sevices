<%-- 
    Document   : listProduct
    Created on : May 15, 2023, 9:11:02 AM
    Author     : adm
--%>

<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <link href="css/cart.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <style>
            .button{
                border-radius: 10px;
            }
            #search {
                margin-bottom: 10px;
            }
            .fit-button {
                width: 100%;
                padding: 6px;
                text-align: center;
                text-decoration: none;
                display: block;
                bottom: 0;
                border: none;
                background-color: #F26F21;
                color: #fff;
                cursor: pointer;
            }
            .search-button{
                background-color: #F26F21;
                color: #fff;
                width: 50%;
                text-align: center;
                text-decoration: none;
                display: block;
            }
            .div_css {
                -webkit-text-size-adjust: 100%;
                -webkit-tap-highlight-color: rgba(0,0,0,0);
                --font-main: 'Quicksand', sans-serif;
                --font-r: 'Open Sans', sans-serif;
                --font-f: 'Open Sans', sans-serif;
                --color-r: #06834a;
                --color-f: #fff;
                color: #333;
                font-family: var(--font-r);
                font-size: 14px;
                line-height: 22px;
                text-rendering: optimizeLegibility;
                padding: 0px;
                -webkit-box-sizing: border-box;
                display: flex;
                -webkit-flex-flow: row wrap;
                margin: 0px -5px;
            }
            .row {
                margin-right: -15px;
                margin-left: -15px;
                flex-wrap: wrap;
            }

            .item {
                -webkit-text-size-adjust: 100%;
                -webkit-tap-highlight-color: rgba(0,0,0,0);
                --font-main: 'Quicksand', sans-serif;
                --font-r: 'Open Sans', sans-serif;
                --font-f: 'Open Sans', sans-serif;
                --color-r: #06834a;
                --color-f: #fff;
                color: #333;
                font-family: var(--font-r);
                font-size: 14px;
                line-height: 22px;
                text-rendering: optimizeLegibility;
                margin: 0px;
                -webkit-box-sizing: border-box;
                display: block;
                padding: 0px 5px;
                width: calc(100% / 3);
                margin-bottom: 0px;
                margin-top: 16px;
            }
            .item:hover {
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.2);
            }
            .content{
                -webkit-text-size-adjust: 100%;
                -webkit-tap-highlight-color: rgba(0,0,0,0);
                --font-main: 'Quicksand', sans-serif;
                --font-r: 'Open Sans', sans-serif;
                --font-f: 'Open Sans', sans-serif;
                --color-r: #06834a;
                --color-f: #fff;
                color: #333;
                font-family: var(--font-r);
                font-size: 14px;
                line-height: 22px;
                text-rendering: optimizeLegibility;
                margin: 0px;
                padding: 0px;
                -webkit-box-sizing: border-box;
            }
            .price {
                font-size: 14px;
                color: #888;
                margin-bottom: 10px;
            }
            .details {
                background: #f5f5f5;
                border-radius: 10px;
                padding: 2%;
                width: 80%;
            }
            .infomation{
                width: 100%;
            }
            .add{
                bottom: 100%
            }
            #search {
                margin-bottom: 10px;
                width: 100%;
                margin-top: 2%;
                padding: 0 2%;
            }

            #searchInput {
                width: 100%;
                border-radius: 4px 0 0 4px;
                border: 1px solid #ccc;
                outline: none;
                padding: 1% 0;
            }
            .search-button {
                display: inline-block;
                width: 100%;
                border-radius: 0 4px 4px 0;
                border: none;
                padding: 6.5% 0;
            }

            /* Adjust the width of each item based on screen size */
            @media (min-width: 768px) {
                .col-md-3 {
                    flex: 0 0 25%;
                    max-width: 25%;
                }


                @media (max-width: 767px) {
                    .col-sm-6 {
                        flex: 0 0 50%;
                        max-width: 50%;
                    }
                }
                .navbar {
                    background-color: #f5f5f5;
                    padding: 10px;
                    border-radius: 5px;
                }
                .best-price-img{
                    padding:20px;
                    width: 100%;
                }
                #hello{
                    background-color: #F26F21;
                    padding: 0.75rem 1.25rem;
                    margin-bottom: 0;
                    border-bottom: 1px solid rgba(0,0,0,.125);
                }

            </style>
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
        </head>
        <body>
            <fmt:formatDate var="today" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />
            <%@include file="header.jsp" %>
            <div class="container" style="display: flex;
                 padding-top: 1% ">
                <div class="catagories col-sm-3" style="background-color: inherit">

                    <div class="card bg-light mb-3">
                        <div class="card-header text-white text-uppercase " id="hello"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <li class="list-group-item"><a href="allproduct" style="color:grey">All Product</a></li>
                                <c:forEach items="${requestScope.listCategory}" var="c" varStatus="index">
                                <li class="list-group-item ${tag == c.categoryID ? "active":""}"><a href="<%=request.getContextPath()%>/allproduct?categoryID=${c.categoryID}" style="color: grey">${c.categoryName}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header text-white text-uppercase" id="hello">Price range</div>
                        <div class="card-body">
                            <form method="get" action="allproduct">
                                <div class="slidecontainer">
                                    <p>Price: <span id="demo"></span>&nbsp;VND < </p>
                                    <%
                                        String rangeR = request.getParameter("range");
                                        if (rangeR==null){
                                            rangeR="0";
                                        }
                                        int range = Integer.parseInt(rangeR);
                                    %>
                                    <input style="width:100%" name="range" type="range" min="0" max="10000000" step="10000" value="<%=range%>" class="slider" id="myRange">
                                </div>
                                <input style="width: 100%" class="btn btn-primary" type="submit" value="Go!">
                            </form>
                        </div>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header text-white text-uppercase" id="hello">Best price</div>
                        <div class="card-body">
                            <c:forEach items="${requestScope.productImageList}" var="pi">
                                <c:if test="${pi.productID eq pro.productID and (pi.imageType eq 'thumbnail')}">
                                    <img src="data:image/jpeg;base64,${pi.encodedProductImage}" alt="" class="best-price-img"/>
                                </c:if></c:forEach>
                            <h5 class="card-title">${pro.productName}</h5>

                            <p class="bloc_left_price prices">${pro.price}</p>

                        </div>
                    </div>
                </div>
                <div class="details col-sm-9 " id="productTable" style="background: #F5F5F5;
                     border-radius:10px">
                    <div class="information-title" style="padding:2%;
                         border-bottom: 1px solid lightgray ">
                        <h3>All Product</h3>
                    </div>

                    <div class="content">
                        <div class="row" id="search">
                            <div class=" col-10">
                                <input oninput="searchByName(this)"  type="text" name="searchString" id="searchInput" placeholder="Search by product name" />
                            </div>
                            <div class=" col-2">
                                <button type="submit" href class="search-button" id="search-button">Search</button>
                            </div>
                        </div>

                        <div id="product__filter" class="row product__filter">
                            <c:forEach items="${requestScope.listProduct}" var="product">
                                <div  class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix all-product">
                                    <div class="product__item" id="item${product.getProductID()}">
                                        <c:forEach items="${requestScope.productImageList}" var="pi">
                                            <c:if test="${pi.productID eq product.productID and (pi.imageType eq 'thumbnail')}">
                                                <div class="product__item__pic set-bg set-bg3"
                                                     data-setbg="data:image/jpeg;base64,${pi.encodedProductImage}"
                                                     id="img${product.getProductID()}">
                                                </c:if>
                                            </c:forEach>
                                            <ul class="product__hover">
                                                <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/heart.png" alt=""></a></li>
                                                <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/compare.png" alt=""> <span>Compare</span></a></li>
                                                <li><a href="productdetail?productId=${product.getProductID()}"><img src="img/icon/search.png" alt=""></a></li>
                                            </ul>
                                        </div>
                                        <div class="product__item__text">
                                            <h6>${product.getProductName()}</h6>
                                            <a class="add-cart" style="cursor: pointer" id="${product.getProductID()}" 
                                               onclick="AddToCartAnimation('${product.getProductID()}');addToCart('${product.getProductID()}')" >+ Add To Cart</a>
                                            <div class="rating">
                                                <i class="fa fa-star-o"></i>
                                                <i class="fa fa-star-o"></i>
                                                <i class="fa fa-star-o"></i>
                                                <i class="fa fa-star-o"></i>
                                                <i class="fa fa-star-o"></i>
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
                                        </div>
                                    </div>

                                </div>
                            </c:forEach>
                        </div>
                        <a href="/swp/cart" class="shopping-cart" id="cart-icon">
                            <img  src="img/icon/cart.png" alt="">
                            <div class="quantity" style="z-index:100;
                                 margin-top: -5rem;
                                 margin-left: 1.5rem;
                                 border: 2px solid black;
                                 background-color: red;
                                 border-radius: 50px;
                                 height: 2rem;
                                 width: 2rem;
                                 font-size: 15px;
                                 text-align: center;
                                 line-height: 2rem;
                                 ">
                                1<!-- dua quantity vao day -->
                            </div>
                        </a>

                    </div>

                </div>
            </div>
            <script>
                var slider = document.getElementById("myRange");
                var output = document.getElementById("demo");
                slider.oninput = function () {
                    output.innerHTML = formatter.format(this.value);
                }
                const formatter = new Intl.NumberFormat('en-US', {
                    style: 'currency',
                    currency: 'VND',
                });
                output.innerHTML = formatter.format(slider.value);
            </script>
            <script>
                function searchProduct() {
                    var input = document.getElementById("searchInput").value.toLowerCase();
                    var articles = document.querySelectorAll(".product__item");
                    for (var i = 0; i < articles.length; i++) {
                        var productName = articles[i].querySelector("h6").textContent.toLowerCase();
                        if (productName.includes(input)) {
                            articles[i].style.display = "block";
                        } else {
                            articles[i].style.display = "none";
                        }
                    }
                }
            </script>
            <script>
                function AddToCartAnimation(target) {
                    document.getElementById("img" + target).classList.add("animate");
                    document.getElementById("item" + target).style.overflow = "visible";

                    console.log("item" + target);
                    var ele = document.getElementsByClassName("product__item__pic");
                    var element = ele[0];
                    const possition_of_first = element.getBoundingClientRect();
                    const possition_of_img = document.getElementById("img" + target).getBoundingClientRect();
                    const possition_of_cart_left = 1268.76;
                    const possition_of_cart_top = 75.20;
                    const top = -357 - (possition_of_first.top - possition_of_img.top);
                    const left = 777 + (possition_of_first.left - possition_of_img.left);
                    console.log("img left:" + left.toFixed(2));
                    console.log("img top:" + top.toFixed(2));

                    document.getElementById("img" + target).style.cssText += '--left: ' + left + 'px;--top: ' + top + 'px;';

                    setTimeout(function () {
                        document.getElementById("img" + target).classList.remove('animate');
                        document.getElementById("item" + target).style.overflow = "hidden";
                    }, 1000);
                }


                function addToCart(pid) {
                    const xhttp = new XMLHttpRequest();
                    // You can add additional code here to perform other actions, such as updating the cart counter or making an AJAX request.

                    xhttp.onload = function () {
                        document.getElementById(pid).innerHTML = "ADDED";
                    };

                    xhttp.open("POST", "allproduct", true);
                    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                    xhttp.send("productID=" + pid);
                }
            </script>
            <script>

                function searchByName(param) {
                    var searchString = param.value;
                    $.ajax({
                        url: "/swp/searchallproduct",
                        type: "get",
                        data: {
                            searchString: searchString
                        },
                        success: function (data) {
                            var row = document.getElementById("product__filter");
                            row.innerHTML = data;
                        }

                    });
                }
            </script>

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

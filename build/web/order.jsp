<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>

<%@page import="model.Product"%>
<%@page import="model.OrderDetail"%>
<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
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
    </head>
    <style>
        input[type=text] {
            width: 70%;
            padding: 8px 20px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            -webkit-transition: 0.5s;
            transition: 0.5s;
            outline: none;
        }
        input[type=text]:focus {
            border: 1px solid #555;
        }
        button{
            border: 1px solid black;
            background: #F26F21;
        }
        table tr td{
            padding: 10px;
        }
        .profile-item li,.noti-item li{
            margin-left: 10%;
        }
        .order-nav, .allorder-details{
            background: #FFFFFF;
            border-radius:10px;
            margin: 2%;
        }

        .order_item{
            background: #F5F5F5;
            border-radius:10px;
            margin: 1% 0;
            padding-top:1%;
            margin-bottom: 2%;
        }
        .header__menu{
            background: #F5F5F5;

        }
        .item-details{
            background: #FFFFFF;
            border-radius: 10px;
            border:1px solid #F69E69;
            border-top:5px solid #F69E69;
            padding: 1% 0;
            margin: 1% 1.5%;
        }
        .order-description{
            display: flex;
        }
        .col-2{
            padding: 0;
        }
        .filter{
            padding: 2%;
        }
        .order-option{
            text-align: right;
            margin-bottom: 10%;
        }
        .none-list{
            text-align: center;
        }
        .rate{
            text-align: center;
            padding: 2%;
        }
        .product-name{
            font-size: larger;
            font-weight: bold;
        }
        <%
            ArrayList<Order> orderList = (ArrayList<Order>) request.getAttribute("orderList");
            ArrayList<OrderDetail> detailList = (ArrayList<OrderDetail>) request.getAttribute("detailList");
            ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");

        %>
    </style>
    <body onload="load()">
        <%@include file="header.jsp" %>
        <div class="container" style="display: flex; padding-top: 1% ">
            <div class="catagories col-2" style="background-color: inherit">
                <div class="profile" style="display: flex; padding-bottom: 20%;padding-top: 20%">
                    <div class="col-5">
                        <img class="rounded-circle" src="data:image/jpeg;base64,${sessionScope.account.getEncodedImage()}">
                    </div>
                    <div class="username">
                        <div class="name">
                            ${sessionScope.account.firstname} ${sessionScope.account.lastname}
                        </div>
                    </div>
                </div>
                <div>
                    <a style="color: black" href="allproduct">All Product</a></br>
                    <a style="color: black" href="vieworder">Your Order</a></br>
                    <a style="color: black" href="cart">Cart</a></br>
                    <a style="color: black" href="promotion">Promotion</a></br>
                    <a style="color: black" href="profile">Profile</a></br>
                </div>
            </div>



            <div class="details col-10">
                <div class="order-nav">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li data-li="allorder"><a>All Order</a></li>
                            <li data-li="pays"><a>Pending</a></li>
                            <li data-li="trans"><a>Delivering</a></li>
                            <li data-li="complete"><a>Complete</a></li>
                            <li data-li="cancelled"><a>Cancelled</a></li>
                        </ul>
                    </nav>
                </div>
                <div class="allorder-details">
                    <div class="filter pays">
                        <div class="" style="margin-bottom: 10% ">
                            <%for (Order order : orderList) {
                            double total = 0;
                                if (order.getOrderStatus() == 1){%>
                            <div  class="order_item">
                                <%
                                    for (OrderDetail detail : detailList) {
                                %>
                                <form action="cancel" method="get">
                                    <%
                                        if (order.getOrderID() == detail.getOrderID()) {
                                            for (Product product : productList) {
                                                if (product.getProductID() == detail.getProductID()) {
                                                    total += product.getPrice() * detail.getQuantity();
                                    %>
                                    <div>
                                        <div class="item-details">
                                            <div class="order-description">
                                                <div class="col-2">    
                                                    <img class="img-window" src="data:image/jpeg;base64,<%=product.getThumbnail()%>">
                                                </div>
                                                <div class="col-8"><h3 class="product-name"><%=product.getProductName()%></h3>
                                                    <p>Type: <%=product.getType()%></p>
                                                    <p>x<%=detail.getQuantity()%></p>
                                                </div>
                                                <div class="col-2"><fmt:formatNumber value="<%=product.getPrice()%>" type="number" maxFractionDigits="0" />&nbsp;VND</div> 
                                            </div>
                                        </div>
                                    </div>
                                    <%
                                                        }
                                                    }
                                                }
                                            }}
                                        if (total != 0) {
                                    %>
                                    <div class="order-option">
                                        <div style="padding-top:2%; padding-right: 2%"><b>Total:<span style="color: red"> <fmt:formatNumber value="<%=total%>" type="number" maxFractionDigits="0" />&nbsp;VND</span></b></div>
                                        <div class="order-nav">
                                            <nav class="header__menu" style="text-align: right">
                                                <ul>
                                                    <input type="hidden" name="orderID" value="<%=order.getOrderID()%>">
                                                    <li><a >View Details</a></li>
                                                    <input style="background-color: #F69E69; border: 1px solid #F69E69" class="btn btn-primary cancel-button " type="submit" name="cancel" value="Cancel Order">
                                                </ul>
                                                
                                            </nav>

                                        </div>

                                    </div>
                                </form> 
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>





                    <div class="filter trans">
                        <div>
                            <%for (Order order : orderList) {
                            double total = 0;
                                    if (order.getOrderStatus() == 2){%>
                            <div  class="order_item">
                                <%
                                        for (OrderDetail detail : detailList) {
                                            if (order.getOrderID() == detail.getOrderID()) {
                                                for (Product product : productList) {
                                                    if (product.getProductID() == detail.getProductID()) {
                                                        total += product.getPrice() * detail.getQuantity();
                                %>
                                <div>
                                    <div class="item-details">
                                        <div class="order-description">
                                            <div class="col-2">
                                                <img class="img-window" src="data:image/jpeg;base64,<%=product.getThumbnail()%>">
                                            </div>
                                            <div class="col-8"><h3 class="product-name"><%=product.getProductName()%></h3>
                                                <p>Type: <%=product.getType()%></p>
                                                <p>x<%=detail.getQuantity()%></p>
                                            </div>
                                            <div class="col-2"><fmt:formatNumber value="<%=product.getPrice()%>" type="number" maxFractionDigits="0" />&nbsp;VND</div> 
                                        </div>
                                    </div>
                                </div>
                                <%
                                                    }
                                                }
                                            }
                                        }}
                                    if (total != 0) {
                                %>
                                <div class="order-option">
                                    <div style="padding-top:2%; padding-right: 2%"><b>Total:<span style="color: red"> <fmt:formatNumber value="<%=total%>" type="number" maxFractionDigits="0" />&nbsp;VND</span></b></div>
                                    <div class="order-nav">
                                        <nav class="header__menu" style="text-align: right">
                                            <ul>
                                                <li><a>Repurchase</a></li>
                                                <li><a>View Details</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>

                        </div>
                    </div>






                    <div class="filter complete">
                        <div>
                            <%for (Order order : orderList) {
                            double total = 0;
                                    if (order.getOrderStatus() == 3){
                            %>
                            <div class="order_item">
                                <%
                                            for (OrderDetail detail : detailList) {
                                                if (order.getOrderID() == detail.getOrderID()) {
                                                    for (Product product : productList) {
                                                        if (product.getProductID() == detail.getProductID()) {
                                                            total += product.getPrice() * detail.getQuantity();
                                %>
                                <div>
                                    <div class="item-details">
                                        <div class="order-description">
                                            <div class="col-2">
                                                <img class="img-window" src="data:image/jpeg;base64,<%=product.getThumbnail()%>">
                                            </div>
                                            <div class="col-8"><h3 class="product-name"><%=product.getProductName()%></h3>
                                                <p>Type: <%=product.getType()%></p>
                                                <p>x<%=detail.getQuantity()%></p>
                                            </div>
                                            <div class="col-2">
                                                <p><fmt:formatNumber value="<%=product.getPrice()%>" type="number" maxFractionDigits="0" />&nbsp;VND</p>
                                            </div> 
                                        </div>
                                        <form action="vieworder" method="post">
                                            <div class="rate" name="rate<%=order.getOrderID()%>" style="display: none;text-align: right">
                                                <input type="hidden" name="productID" value="<%=product.getProductID()%>">
                                                <input name="content" type="text" placeholder="Your comment here..." style="width: 80%">
                                                <select name="rate" id="Comment" style="width: 20%">
                                                    <option value="1">1</option>
                                                    <option value="2">2</option>
                                                    <option value="3">3</option>
                                                    <option value="4">4</option>
                                                    <option value="5">5</option>
                                                </select>
                                                <button class="btn btn-primary" type="submit" name="rate" style="height: 45px">Rate</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <%
                                                    }
                                                }
                                            }
                                        }}
                                    if (total != 0) {
                                %>
                                <div class="order-option">
                                    <div style="padding-top:2%; padding-right: 2%"><b>Total:<span style="color: red"> <fmt:formatNumber value="<%=total%>" type="number" maxFractionDigits="0" />&nbsp;VND</span></b></div>
                                    <div class="order-nav">
                                        <nav class="header__menu" style="text-align: right">
                                            <ul>
                                                <li><a>View Details</a></li>
                                                <li><a onclick="Rate('rate<%=order.getOrderID()%>')" >Rating</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                            <%
                                    }

                                }
                            %>
                        </div>
                    </div>






                    <div class="filter cancelled">
                        <div>
                            <%for (Order order : orderList) {
                            double total = 0;
                                if (order.getOrderStatus() == 4){
                            %>
                            <div class="order_item">
                                <%for (OrderDetail detail : detailList) {
                                        if (order.getOrderID() == detail.getOrderID()) {
                                            for (Product product : productList) {
                                                if (product.getProductID() == detail.getProductID()) {
                                                    total += product.getPrice() * detail.getQuantity();
                                %>
                                <div class="item-details">
                                    <div class="order-description">
                                        <div class="col-2">
                                            <img class="img-window" src="data:image/jpeg;base64,<%=product.getThumbnail()%>">
                                        </div>
                                        <div class="col-8"><h3 class="product-name"><%=product.getProductName()%></h3>
                                            <p>Type: <%=product.getType()%></p>
                                            <p>x<%=detail.getQuantity()%></p>
                                        </div>
                                        <div class="col-2">
                                            <p><fmt:formatNumber value="<%=product.getPrice()%>" type="number" maxFractionDigits="0" />&nbsp;VND</p>
                                        </div> 
                                    </div>
                                </div>

                                <%
                                                    }
                                                }
                                            }
                                        }}
                                    if (total != 0) {
                                %>
                                <div class="order-option">
                                    <div style="padding-top:2%; padding-right: 2%"><b>Total:<span style="color: red"> <fmt:formatNumber value="<%=total%>" type="number" maxFractionDigits="0" />&nbsp;VND</span></b></div>
                                    <div class="order-nav">
                                        <nav class="header__menu" style="text-align: right">
                                            <ul>
                                                <li><a>View Details</a></li>
                                            </ul>
                                        </nav>
                                    </div>
                                </div>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </div>



                </div>
            </div>
        </div>

    </body>
    <script>
        function Rate(input) {

            var element = document.getElementsByName(input);
            for (var i = 0; i < element.length; i++) {
                console.log(element[i]);
            }
            for (var i = 0; i < element.length; i++) {
                if (element[i].style.display === "block") {
                    element[i].style.display = "none";
                } else {
                    element[i].style.display = "block";
                }
            }
        }
        var li_elements = document.querySelectorAll(".mobile-menu ul li");
        var filter_elements = document.querySelectorAll(".filter");
        for (var i = 0; i < li_elements.length; i++) {
            li_elements[i].addEventListener("click", function () {
                li_elements.forEach(function (li) {
                    li.classList.remove("active");
                });
                this.classList.add("active");
                sessionStorage.setItem('orderpage', this.getAttribute("data-li"));
                filter_elements.forEach(function (item) {
                    item.style.display = "none";
                });
                if (sessionStorage.getItem('orderpage') === "allorder") {
                    filter_elements.forEach(function (item) {
                        item.style.display = "block";
                    });
                } else if (sessionStorage.getItem('orderpage') === "pays") {
                    document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
                } else if (sessionStorage.getItem('orderpage') === "trans") {
                    document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
                } else if (sessionStorage.getItem('orderpage') === "complete") {
                    document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
                } else if (sessionStorage.getItem('orderpage') === "cancelled") {
                    document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
                } else {
                    console.log("");
                }
            });
        }
        function load() {
            var filter_elements = document.querySelectorAll(".filter");
            if (sessionStorage.getItem('orderpage').length !== null) {
                sessionStorage.setItem('orderpage', "allorder");
            }
            filter_elements.forEach(function (item) {
                item.style.display = "none";
            });
            if (sessionStorage.getItem('orderpage') === "allorder") {
                filter_elements.forEach(function (item) {
                    item.style.display = "block";
                });
            } else if (sessionStorage.getItem('orderpage') === "pays") {
                document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
            } else if (sessionStorage.getItem('orderpage') === "trans") {
                document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
            } else if (sessionStorage.getItem('orderpage') === "complete") {
                document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
            } else if (sessionStorage.getItem('orderpage') === "cancelled") {
                document.querySelector("." + sessionStorage.getItem('orderpage')).style.display = "block";
            } else {
                console.log("");
            }
        }
        load();


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

</html>

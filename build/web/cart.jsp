<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
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
    <style>
        .col-md-2{
            text-align: center;
            align-content: center;
            align-items: center;
        }
    </style>
    <body onload="setQuantity(), totalIt()">
        <!-- header include -->
        <%@include file="header.jsp" %> 
        <c:set var="account" value="${requestScope.ID}"/>
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
                    <h3>Your cart</h3>
                    <h4>Manage items to purchase without actually completing the payment</h4>

                    <div class="row header_cart" id="header_cart box-content_cart">

                        <div class="col-md-2"id="cart_img">
                            <input type="checkbox" onchange="checkAll();totalIt()" id="checkall">
                            Image
                        </div>
                        <div class="col-md-2"id="cart_Name">
                            Product Name
                        </div>
                        <div class=" col-md-2"id="cart_price">
                            Price
                        </div>
                        <div class=" col-md-2"id="cart_quantity" >
                            Quantity
                        </div>
                        <div class=" col-md-2"id="cart_TotalPrice" >
                            Total Price
                        </div>
                        <div class=" col-md-2"id="cart_TotalPrice" >
                            Discard
                        </div>
                    </div>
                    <c:forEach items="${sessionScope.listCart}" var="c">
                        <div id="detail${c.getProductID()}" style="align-content: center">
                            <div class="row box-content_cart" id=" ">


                                <input type="hidden" value="${c.getProductID()}" id="id${c.getProductID()}">
                                <input type="hidden" value="${c.getPrice()}" id="price${c.getProductID()}">
                                <div class=" col-md-2"id="cart_img">
                                    <input type="checkbox" id="${c.getProductID()}" onchange="totalIt()" name="product">
                                    <c:forEach items="${requestScope.productImageList}" var="pi">
                                        <c:if test="${pi.productID eq c.productID and (pi.imageType eq 'thumbnail')}">
                                            <img class="" style="margin-top: 7%;width: 85%;height: 100%" src="data:image/jpeg;base64,${pi.encodedProductImage}" alt="Image">
                                        </c:if>
                                    </c:forEach>
                                </div>
                                <div class="col-md-2" id="cart_Name">
                                    <a href="productdetail?productId=${c.getProductID()}">${c.getProductName()}</a> 
                                </div>
                                <div class="col-md-2"id="cart_price">
                                    <fmt:formatNumber value="${c.getPrice()}" type="number" maxFractionDigits="0" />&nbsp;VND
                                </div>
                                <div class=" col-md-2"id="cart_quantity" >
                                    <input type="number" id="quantity${c.getProductID()}" name="${c.getPrice()}"
                                           value="${c.quantity}"min="1" max="99" step="1"
                                           onchange ="calculation(this.value, '${c.getProductID()}',${c.getPrice()})">
                                </div><!-- comment -->
                                <div class="col-md-2"id="cart_TotalPrice" >
                                    <p id="total${c.getProductID()}">
                                        <fmt:formatNumber value="${c.quantity*c.price}" type="number" maxFractionDigits="0" />&nbsp;VND

                                    </p>
                                </div>




                                <div class=" col-md-2" style="text-align: right">
                                    <button class="btn btn-primary" onclick="myFunction(${c.getProductID()}, '${c.getProductID()}')">Discard!</button>
                                </div>

                            </div>

                        </div>
                    </c:forEach>
                </div>

                <div class="totalall" >
                    <form id="myForm" action="confirmorder" method="post">

                        <div class="row">
                            <div class="col-md-3">
                                Receiver's Name:
                                <input type="text" id="receiver" required name="receiver">
                            </div>

                            <div class="col-md-5">
                                Deliver Location: <button style="background-color: #FFFFFF;border: none; text-decoration: underline" onclick="getLocation()">*Use my current location</button><br>
                                <input type="text" id="location" required name="location">
                            </div>
                            <div class="col-md-2" style="font-weight: bold">Total price: 
                                <input style="width: 80%" value="0" readonly="readonly" type="hidden" name="total">
                                <p name="totally" id="totally">0 VND</p>
                            </div>
                            <button type="button" style="margin-top:2%;border: none; width: 100%;background-color: #F26F21;" class="col-md-2 btn btn-primary fit-button" value="submit" onclick="sendOrder()">Confirm order  </button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
        <br/>



        <!-- Footer Section Begin -->
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
                                <li><a href="#">Clothing Store</a></li>
                                <li><a href="#">Trending Shoes</a></li>
                                <li><a href="#">Accessories</a></li>
                                <li><a href="#">Sale</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-3 col-sm-6">
                        <div class="footer__widget">
                            <h6>Shopping</h6>
                            <ul>
                                <li><a href="#">Contact Us</a></li>
                                <li><a href="#">Payment Methods</a></li>
                                <li><a href="#">Delivary</a></li>
                                <li><a href="#">Return & Exchanges</a></li>
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
        <!-- Footer Section End -->  
        <script>
            function getLocation() {
                if (navigator.geolocation) {
                    if (navigator.geolocation) {
                        // Get the current position
                        navigator.geolocation.getCurrentPosition(function (position) {
                            // Extract the latitude and longitude from the position object
                            var latitude = position.coords.latitude;
                            var longitude = position.coords.longitude;

                            // Fill the input field with the coordinates
                            document.getElementById("location").value = latitude + ", " + longitude;
                        }, function (error) {
                            // Handle any errors that occur while retrieving the geolocation
                            console.error(error);
                        });
                    }
                }
            }
        </script>
        <script>
            const inputElements = document.querySelectorAll('input[type="number"]');

            // Step 2: Define the function to run when the input value changes
            function handleInputChange(event) {
                const name = event.target.name;
                const id = event.target.id;
                const value = event.target.value;
                const formatID = id.substring(8, id.length);
                calculation(value, formatID, name);
            }

            // Step 3: Attach the event listener to each input element
            inputElements.forEach(inputElement => {
                inputElement.addEventListener('input', handleInputChange);
            });

            function sendOrder() {
                const list = new Map();
                var input = document.getElementsByName("product");
                for (var i = 0; i < input.length; i++) {
                    if (input[i].checked) {
                        list.set(input[i].id, document.getElementById("quantity" + input[i].id).value);
                    }
                }
                var form = document.getElementById("myForm");
                list.forEach(function (value, key) {
                    var input = document.createElement("input");
                    input.type = "hidden";
                    input.name = key;
                    input.value = value;
                    form.appendChild(input);
                });
                sessionStorage.clear();
                form.submit();

            }
            function setQuantity() {
                var storage = {};
                for (var i = 0; i < sessionStorage.length; i++) {
                    var key = sessionStorage.key(i);
                    var value = sessionStorage.getItem(key);
                    var json = JSON.parse(value);
                    storage[key] = json;
                }
                for (var i in storage) {
                    calculation(storage[i][0], i, storage[i][1], storage[i][2]);
                    document.getElementById("quantity" + i).value = storage[i][0];
                    document.getElementById(i).innerHTML = (storage[i][1] * storage[i][0]).toLocaleString().replace(/,/g, ".") + " VND";
                    document.getElementById(i).value = storage[i][1] * storage[i][0];
                }
            }
            function calculation(quantity, pid, price) {
                var sum = quantity * price;
                const list = [];
                list[0] = quantity;
                list[1] = price;
                var json = JSON.stringify(list);

                document.getElementById("total" + pid).innerHTML = sum.toLocaleString().replace(/,/g, ".") + " VND";
                document.getElementById(pid).value = sum;
                sessionStorage.setItem(pid, json);
                totalIt();
            }


            function myFunction(pa, name) {
                const xhttp = new XMLHttpRequest();
                xhttp.onload = function () {
                    document.getElementById(pa).style.display = "none";
                    sessionStorage.removeItem(name);
                    location.reload();
                };

                xhttp.open("POST", "cart", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("deleted=" + pa);

            }
            function totalIt() {
                var input = document.getElementsByName("product");
                var total = 0;
                for (var i = 0; i < input.length; i++) {
                    if (input[i].checked) {
                        total += parseInt(input[i].value);
                    }
                }
                document.getElementsByName("total")[0].value = total;
                document.getElementById("totally").innerHTML = total.toLocaleString().replace(/,/g, ".") + " VND";
                document.getElementById("totally").value = total;
            }
            function checkAll() {
                var input = document.getElementById("checkall");
                if (input.checked) {
                    var ele = document.getElementsByName('product');
                    for (var i = 0; i < ele.length; i++) {
                        ele[i].checked = true;
                    }
                } else {
                    var ele = document.getElementsByName('product');
                    for (var i = 0; i < ele.length; i++) {
                        ele[i].checked = false;
                    }
                }
            }
        </script>
        <script>
            function selects() {
                var ele = document.getElementsByName('chk');
                for (var i = 0; i < ele.length; i++) {
                    if (ele[i].type == 'checkbox')
                        ele[i].checked = true;
                }
            }
            function deSelect() {
                var ele = document.getElementsByName('chk');
                for (var i = 0; i < ele.length; i++) {
                    if (ele[i].type == 'checkbox')
                        ele[i].checked = false;

                }
            }
            var numberElements = document.getElementsByClassName("prices");

            for (var i = 0; i < numberElements.length; i++) {
                var numberElement = numberElements[i];
                var number = parseFloat(numberElement.innerHTML);
                numberElement.innerHTML = number.toLocaleString().replace(/,/g, ".") + " VND";
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
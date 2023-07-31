<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>


<%@page import="java.util.ArrayList"%>
<%@page import="model.Comment"%>
<%@page import="dal.CustomerDAO"%>
<%@page import="model.Customer"%>
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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />

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

        .image{
            text-align: center;
        }
        .productDetail{
            padding: 1%;
            display: flex;
            margin-top: 2%;
            border: 1px solid lightgray;
            border-radius: 10px;
        }
        .productOwner{
            padding: 1%;
            display: flex;
            margin-top: 2%;
            border: 1px solid lightgray;
            border-radius: 10px;
            background: #eee;
        }
        .prices{
            padding: 1% 5% 5% 1%;
            border-radius: 10px;

        }
        .option{
            position: absolute;
            right: 0;
            bottom: 0;
            width: 300px;
            text-align: left;
        }
        .option button{
            background-color: #f26f21;
            border-radius: 5px;
            margin: 2%;
        }
        .content{
            padding: 1%;
        }
        .comments{
            padding: 1%;
        }
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
    </style>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row" style="margin-top: 5%">
                <div class="col-sm-3">
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-primary text-white text-uppercase"><i class="fa fa-list"></i> Categories</div>
                        <ul class="list-group category_block">
                            <c:forEach items="${cateList}" var="o">
                                <li class="list-group-item ${tag == o.categoryID ? "active":""}"><a style="color: #f26f21" href="allproduct?cid=${o.categoryID}">${o.categoryName}</a></li>
                                </c:forEach>

                        </ul>
                    </div>
                    <div class="card bg-light mb-3">
                        <div class="card-header bg-success text-white text-uppercase">Best price</div>
                        <div class="card-body">

                            <h5 class="card-title">${pro.productName}</h5>

                            <p class="bloc_left_price prices">${pro.price}</p>
                        </div>
                    </div>
                </div>
                <div class="col-sm-9">
                    <div class="container">
                        <div class="card">
                            <div class="row">

                                <div class="col-sm-5" style="padding-left: 5%;padding-top: 5%">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <c:forEach items="${requestScope.listImage}" var="pi">
                                                <c:if test="${(pi.imageType eq 'thumbnail')}">
                                                    <img id="thumbnailImage" style="width: 100%" src="data:image/jpeg;base64,${pi.encodedProductImage}">
                                                </c:if>
                                            </c:forEach>
                                        </div> <!-- slider-product.// -->
                                        <div class="img-small-wrap">
                                            <c:forEach items="${requestScope.listImage}" var="pi">
                                                <img onclick="changeImage('data:image/jpeg;base64,${pi.encodedProductImage}')" style="margin-top: 2%;width: 18%" src="data:image/jpeg;base64,${pi.encodedProductImage}">
                                            </c:forEach>
                                        </div>
                                    </article> 
                                </div>

                                <div class="col-sm-7" style="padding:2%">
                                    <article class="card-body p-5">
                                        <h3 style="font-size: x-large; ">${product.productName}</h3>
                                        <div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold ${product.getRate()/5*100}%,transparent 30%,transparent 100%);"></div>

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-warning"> 
                                                <fmt:formatDate var="today" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />
                                                <c:forEach var="discount" items="${requestScope.allDiscount}">
                                                    <c:if test="${discount.productID eq product.productID and discount.startDate <= today and discount.endDate >= today}">
                                                        <b><h5><del><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</del></h5>
                                                            <h5 style="color: red;">
                                                                <fmt:formatNumber value="${product.price - product.price * discount.discountValue / 100}" type="number" maxFractionDigits="0" />&nbsp;VND
                                                            </h5></b>
                                                        <span style="border: 2px solid red;color: red; padding: 0% 1%;"><i class="fa fa-tag"></i> ${discount.discountValue}% off</span>
                                                        <c:set var="discounted" value="true" />
                                                    </c:if>
                                                </c:forEach>
                                                <c:if test="${discounted eq false}">
                                                    <b><h5><fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0" />&nbsp;VND</h5></b>
                                                </c:if>
                                                <c:set var="discounted" value="false" />                   
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <h5> </h5>
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td>Manufactured By </td>
                                                    <td>&nbsp;: ${requestScope.manufacture.getManufacturerName()}</td>
                                                </tr>
                                                <tr>
                                                    <td>Type</td>
                                                    <td>&nbsp;: ${product.type}</td>
                                                </tr>
                                                <tr>
                                                    <td>Warranty Period</td>
                                                    <td>&nbsp;: ${product.warrantyPeriod} months</td>
                                                </tr>
                                                <tr>
                                                    <td>Overall Condition</td>
                                                    <td>&nbsp;: ${product.productStatus}</td>
                                                </tr>
                                            </tbody>
                                        </table>


                                        <hr>
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div><b>Quantity:</b> <span> &nbsp;| In stock: ${product.stockQuantity} </span></div>
                                                <input style="width: 15%" value="1" min="1" max="${product.stockQuantity}" type="number" name="quantity">
                                                <!-- item-property .// -->
                                            </div> <!-- col.// -->

                                        </div> <!-- row.// -->
                                        <hr>
                                        <a class="add-cart" id="${product.getProductID()}" onclick="addToCart('${product.getProductID()}')"><button style="border:1px solid #F69E69;background-color: #F69E69" class="btn btn-primary"> <i class="fa fa-shopping-cart"></i> Add To Cart</button></a>
                                    </article> <!-- card-body.// -->
                                </div> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->


                    </div>
                </div>
            </div>
            <div class="reference" style="padding-top:2%">
                <h3 style="font-weight: bolder; color:red">You may want: </h3>
                <div class="productDetail" style="overflow-x: auto">
                    <c:forEach items="${requestScope.productList}" var="pro">
                        <c:if test="${pro.manufacturerID==product.manufacturerID}">
                            <div class="col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix">
                                <div class="product__item">
                                    <div class="product__item__pic set-bg set-bg3" data-setbg="data:image/jpeg;base64,${pro.getThumbnail()}">
                                        <ul class="product__hover">
                                            <li><a href="productdetail?productId=${pro.getProductID()}"><img src="img/icon/heart.png" alt=""></a></li>
                                            <li><a href="productdetail?productId=${pro.getProductID()}"><img src="img/icon/compare.png" alt=""> <span>Compare</span></a></li>
                                            <li><a href="productdetail?productId=${pro.getProductID()}"><img src="img/icon/search.png" alt=""></a></li>
                                        </ul>
                                    </div>
                                    <div class="product__item__text">
                                        <h6>${pro.getProductName()}</h6>
                                        <a class="add-cart" id="${pro.getProductID()}" onclick="addToCart('${pro.getProductID()}')"> Add To Cart</a>
                                        <div class="rating">
                                            <c:if test="${pro.rate!=0}">
                                                <div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,${pro.rate/5*100}%,transparent 30%,transparent 100%);"></div>
                                            </c:if>
                                            <c:if test="${pro.getRate()==0}">
                                                <div>No rate yet</div>
                                            </c:if>  
                                        </div>
                                        <c:forEach var="discount" items="${requestScope.allDiscount}">
                                            <c:if test="${discount.productID eq pro.productID and discount.startDate <= today and discount.endDate >= today}">
                                                <b><h5><del><fmt:formatNumber value="${pro.price}" type="number" maxFractionDigits="0" />&nbsp;VND</del></h5>
                                                    <h5 style="color: red;">
                                                        <fmt:formatNumber value="${pro.price - pro.price * discount.discountValue / 100}" type="number" maxFractionDigits="0" />&nbsp;VND
                                                    </h5></b>
                                                <span style="border: 2px solid red;color: red; padding: 0% 1%;"><i class="fa fa-tag"></i> ${discount.discountValue}% off</span>
                                                <c:set var="discounted" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${discounted eq false}">
                                            <b><h5><fmt:formatNumber value="${pro.price}" type="number" maxFractionDigits="0" />&nbsp;VND</h5></b>
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
                        </c:if>
                    </c:forEach>

                </div>
            </div>
            <div style="margin-top: 2%;
                 border: 1px solid lightgray;
                 border-radius: 10px;
                 padding: 1%">
                <div class="comment-nav">
                    <nav class="header__menu mobile-menu">
                        <ul>
                            <li data-li="all" class="active"><a>All</a></li>
                            <li data-li="five"><a>5 Star</a></li>
                            <li data-li="four"><a>4 Star</a></li>
                            <li data-li="three"><a>3 Star</a></li>
                            <li data-li="two"><a>2 Star</a></li>
                            <li data-li="one"><a>1 Star</a></li>
                        </ul>
                    </nav>
                </div>
                <%
                    CustomerDAO dao = new CustomerDAO();
                    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
                %>
                <div class="filter five">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getRate() == 5) {
                    %>
                    <div style="display: flex;border-bottom: 1px solid lightgray">
                        <div style="text-align: center;width: 4%"><img class="rounded-circle" style="width: 100%" src="data:image/jpeg;base64,<%=dao.getCusByID(comment.getCustomerID()).getEncodedImage()%>"></div>
                        <div class="col-11">
                            <h4 style="margin-bottom: 2%"><%=dao.getCusByID(comment.getCustomerID()).getUsername()%></h4>
                            <h6><div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold <%=(float) comment.getRate() / 5 * 100%>%,transparent 30%,transparent 100%);"></div>
                            </h6>
                            <br>
                            <p><%=comment.getContent()%></p>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <div class="filter four">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getRate() == 4) {
                    %>
                    <div style="display: flex;border-bottom: 1px solid lightgray">
                        <div style="text-align: center;width: 4%"><img class="rounded-circle" style="width: 100%" src="data:image/jpeg;base64,<%=dao.getCusByID(comment.getCustomerID()).getEncodedImage()%>"></div>
                        <div class="col-11">
                            <h4 style="margin-bottom: 2%"><%=dao.getCusByID(comment.getCustomerID()).getUsername()%></h4>
                            <h6><div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold <%=(float) comment.getRate() / 5 * 100%>%,transparent 30%,transparent 100%);"></div>
                            </h6>
                            <br>
                            <p><%=comment.getContent()%></p>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <div class="filter three">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getRate() == 3) {
                    %>
                    <div style="display: flex;border-bottom: 1px solid lightgray">
                        <div style="text-align: center;width: 4%"><img class="rounded-circle" style="width: 100%" src="data:image/jpeg;base64,<%=dao.getCusByID(comment.getCustomerID()).getEncodedImage()%>"></div>
                        <div class="col-11">
                            <h4 style="margin-bottom: 2%"><%=dao.getCusByID(comment.getCustomerID()).getUsername()%></h4>
                            <h6><div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold <%=(float) comment.getRate() / 5 * 100%>%,transparent 30%,transparent 100%);"></div>
                            </h6>
                            <br>
                            <p><%=comment.getContent()%></p>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <div class="filter two">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getRate() == 2) {
                    %>
                    <div style="display: flex;border-bottom: 1px solid lightgray">
                        <div style="text-align: center;width: 4%"><img class="rounded-circle" style="width: 100%" src="data:image/jpeg;base64,<%=dao.getCusByID(comment.getCustomerID()).getEncodedImage()%>"></div>
                        <div class="col-11">
                            <h4 style="margin-bottom: 2%"><%=dao.getCusByID(comment.getCustomerID()).getUsername()%></h4>
                            <h6><div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold <%=(float) comment.getRate() / 5 * 100%>%,transparent 30%,transparent 100%);"></div>
                            </h6>
                            <br>
                            <p><%=comment.getContent()%></p>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
                <div class="filter one">
                    <%
                        for (Comment comment : comments) {
                            if (comment.getRate() == 1) {
                    %>
                    <div style="display: flex;border-bottom: 1px solid lightgray">
                        <div style="text-align: center;width: 4%"><img class="rounded-circle" style="width: 100%" src="data:image/jpeg;base64,<%=dao.getCusByID(comment.getCustomerID()).getEncodedImage()%>"></div>
                        <div class="col-11">
                            <h4 style="margin-bottom: 2%"><%=dao.getCusByID(comment.getCustomerID()).getUsername()%></h4>
                            <h6><div class="star-rating" style="background-image: linear-gradient(to right,gold 0%,gold <%=(float) comment.getRate() / 5 * 100%>%,transparent 30%,transparent 100%);"></div>
                            </h6>
                            <br>
                            <p><%=comment.getContent()%></p>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
        </div>
        <script>
            var li_elements = document.querySelectorAll(".mobile-menu ul li");
            var filter_elements = document.querySelectorAll(".filter");
            for (var i = 0; i < li_elements.length; i++) {
                li_elements[i].addEventListener("click", function () {
                    li_elements.forEach(function (li) {
                        li.classList.remove("active");
                    });
                    this.classList.add("active");
                    var li_value = this.getAttribute("data-li");
                    filter_elements.forEach(function (item) {
                        item.style.display = "none";
                    });
                    if (li_value === "all") {
                        filter_elements.forEach(function (item) {
                            item.style.display = "block";
                        });
                    } else if (li_value === "five") {
                        document.querySelector("." + li_value).style.display = "block";
                    } else if (li_value === "four") {
                        document.querySelector("." + li_value).style.display = "block";
                    } else if (li_value === "three") {
                        document.querySelector("." + li_value).style.display = "block";
                    } else if (li_value === "two") {
                        document.querySelector("." + li_value).style.display = "block";
                    } else if (li_value === "one") {
                        document.querySelector("." + li_value).style.display = "block";
                    } else {
                        console.log("");
                    }
                });
            }
            var numberElements = document.getElementsByClassName("prices");

            for (var i = 0; i < numberElements.length; i++) {
                var numberElement = numberElements[i];
                var number = parseFloat(numberElement.innerHTML);
                numberElement.innerHTML = number.toLocaleString() + " đồng";
            }
        </script>
        <script>
            function changeImage(image) {
                document.getElementById('thumbnailImage').src = image;
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

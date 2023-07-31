<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpServletRequest"%>
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
        <link rel="icon" type="image/x-icon" href="/img/favicon.png">
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
        .addvise{
            position: absolute;
            left: calc(10% + 530px);
            transform: translate(-50%);
        }
        .add{
            display: block;
            min-width: 100%;
            height: 100%;
        }
        .add span{
            display: inline-block;
            color: red;
            padding-left: 100%;
            font-family: sans-serif;
            text-align: center;
            min-width: 100%;
            height: 100%;
            font-size: 125%;
            white-space: nowrap;
            animation: add 5s linear infinite;
        }

        @keyframes add {
            0%
            {
                transform: translate(0,0);
            }

            100%{
                transform: translate(-100%,0);
            }
        }
    </style>
    <!-- Page Preloder -->

    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-7">
                        <div class="header__top__left">
                            <c:forEach items="${sessionScope.Facts}" var="c">
                                <p>${c.newsHeading}, ${c.newsContent}</p>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-5">
                        <div class="header__top__right">
                            <div class="header__top__links">
                                <a href="<%=request.getContextPath()%>"><img style="width: 3em;" src="img/favicon.png">Betrolimex
                                    </a>
                                <c:if test="${sessionScope.account!=null}">
                                    <a href="profile">${sessionScope.account.firstname}  ${sessionScope.account.lastname}</a>
                                    <a href="logout">Log Out</a>
                                </c:if>
                                <c:if test="${sessionScope.account==null}">
                                    <a href="login">Sign in</a>
                                </c:if>

                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section class="addvise">
            <div class="add">
                <span style="display: flex">
                    <c:forEach items="${sessionScope.Running_text}" var="c">
                        <p style="color: red;">${c.newsHeading}, ${c.newsContent}</p>
                            </c:forEach>
                </span>
            </div>
        </section>
        <div class="container">
            <div class="row">
                <div class="col-lg-2 col-md-2">
                    <div class="header__logo">
                    </div>
                </div>
                <%String script = "function getURL() { return window.location.href; }";
    
    // Call the getURL() JavaScript function and store the result as a Java string
    String url = "<script>var url = getURL(); url;</script>";%>
                <div class="col-lg-8 col-md-8">
                    <nav class="header__menu mobile-menu">


                        <ul class="dropdown">
                            <c:forEach items="${sessionScope.menu}" var="c">
                                <c:if test="${c.parentID == 0}">
                                    <li><a href="${c.newsContent}">${c.newsTitle}</a>
                                        <c:set var="childCount" value="0" />
                                        <c:forEach items="${sessionScope.menu}" var="k">
                                            <c:if test="${c.getNewID()==k.getParentID()}">
                                                <c:set var="childCount" value="${childCount + 1}" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${childCount > 0}">
                                            <ul class="dropdown">
                                                <c:forEach items="${sessionScope.menu}" var="k">
                                                    <c:if test="${c.getNewID()==k.getParentID()}">
                                                        <li><a href="${k.newsContent}">${k.newsTitle}</a></li>
                                                        </c:if>
                                                    </c:forEach>
                                            </ul>
                                        </c:if>
                                    </li>
                                </c:if>
                            </c:forEach>
                        </ul>


                    </nav>
                </div>
                <div class="col-lg-2 col-md-2">
                    <div class="header__nav__option">

                    </div>
                </div>
            </div>
            <div class="canvas__open"><i class="fa fa-bars"></i></div>
        </div>


        <div class="hover-box infor-icon"
             style="position: fixed;z-index: 100;margin-left: 95%;margin-top: 0;
             font-size: 25px;border-radius: 50%;color: orange;">
            ⓘ
        </div>

        <style>
            .hover-box:after {
                content: "Hotline: +84099955598 Email:swpgroup6@gmail.com";
                display: none;
                padding: 1% 1%;
                border-radius: 10px;
                color: red;
                font-size: 20px;
                position: absolute;
                right: calc(100% + 30px);
                bottom: 2%;
                white-space: nowrap;
                background-color: wheat;
            }

            .hover-box:hover:after,
            .hover-box.show:after {
                display: flex;
            }
            .infor-icon {
                position: fixed;
                z-index: 1000;
                right: 1rem;
                bottom: 7rem;
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

            .infor-icon.active {
                width: 8rem;
                border-radius: .8rem;
            }

            .infor-icon.active > span{
                margin-left: -4rem;
            }
        </style>
        <script>
            var hoverBox = document.querySelector(".hover-box");

            hoverBox.addEventListener("click", function () {
                hoverBox.classList.toggle("show");
            });
        </script>
        <script>
            function getURL() {
                return window.location.href;
            }
        </script>




    </header>
    <!-- Header Section End -->
</html>



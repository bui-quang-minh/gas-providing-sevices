<%-- 
    Document   : footer
    Created on : Jun 15, 2023, 9:02:34 AM
    Author     : An
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <link href="css/footer.css" rel="stylesheet" type="text/css"/>
    </head>
    <footer style="margin-top: 3%" class="footer ">
        <div class="container">
                    <div class="row">
                        <c:forEach items="${sessionScope.footerlist}" var="c">
                            <c:if test="${c.parentID == 0}">
                                <div class="col">
                                    <!-- CSS cai nay to hon la okay cai nay la parent -->
                                    <p style="font-size: x-large;border-bottom: 1px solid gray;width: fit-content"><a class="link-format" href="${c.newsContent}">${c.newsTitle}</a></p>
                                    <c:set var="childCount" value="0" />
                                        <c:forEach items="${sessionScope.footerlist}" var="k">
                                            <c:if test="${c.getNewID()==k.getParentID()}">
                                                <c:set var="childCount" value="${childCount + 1}" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${childCount > 0}">
                                            <c:forEach items="${sessionScope.footerlist}" var="k">
                                                <c:if test="${c.getNewID()==k.getParentID()}">
                                                        <a class="link-format" href="${k.newsContent}">${k.newsTitle}</a></br>
                                                </c:if>
                                            </c:forEach>
                                    </c:if>
                                </div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                    <div class="footer__copyright__text">
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
</html>

<%-- 
    Document   : login
    Created on : May 15, 2023, 4:30:10 PM
    Author     : admin
--%>
<%@include file="header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="login/fonts/icomoon/style.css">
        <script src="js/owl.carousel.min.js" type="text/javascript"></script>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>       
        <!-- Style -->
        <link rel="stylesheet" href="css/style_1.css">

        <title>Betrolimex</title> 
        <link rel="icon" href="img/favicon.png" />   
        
    </head>
    <body>
        <c:if test="${ not empty sessionScope.status}">
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">×</button>
                ${sessionScope.status}
            </div>
        </c:if>
        
        <c:if test="${ not empty requestScope.message}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">×</button>
                ${requestScope.message}
            </div>
        </c:if>
        <c:if test="${ not empty sessionScope.message}">
            <div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">×</button>
                ${sessionScope.message}
            </div>
        </c:if>
        
        <div class="d-md-flex half">
            <div class="contents">
                <div class="container">
                    <div class="row align-items-center justify-content-center mt-4 ">
                        <div class="  col-md-7">

                            <div class="text-center mb-5">
                                <h3 class="text-uppercase">Login to <a class="text-uppercase" style="color: red;" ><strong>Betrolimex</strong></a></h3>
                            </div>
                            <c:if test="${ not empty sessionScope.warning}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${sessionScope.warning}
                                </div>
                            </c:if>
                            <c:set var="cookie" value="${pageContext.request.cookies}"/>
                            <form action="login" method="post">
                                <div class="form-group first">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control" placeholder="Your Username" name="username" id="username" value="${cookie.cusername.value}">
                                </div>
                         
                                <div class="form-group last mb-3">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control" placeholder="Your Password" name="password" id="password" value="${cookie.cpassword.value}">
                                </div>
                                <c:if test="${ not empty requestScope.notice}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.notice}
                                </div>
                                </c:if>
                                
                                <div class="d-sm-flex mb-3 align-items-center">
                                    <label class="control control--checkbox mb-3 mb-sm-0"><span class="caption">Remember me</span>
                                        <input type="checkbox" ${(cookie.cremember != null?'checked':'')} name="rem" value="ON"/>
                                        <div class="control__indicator"></div>
                                    </label>
                                    <span class="ml-auto"><a href="forgotpassword" class="forgot-pass">Forgot Password</a></span>
                                </div>

                                <div class="d-sm-flex mb-5 align-items-center">
                                    <span class="forgot-pass">You don't have account?</span> &nbsp;&nbsp; <span><a href="signup" class="forgot-pass">Sign Up Now</a></span>
                                </div>


                                <input type="submit" value="Log In" class="btn btn-block py-2 btn-primary">

                                <span class="text-center my-3 d-block">or</span>


                                <div class="">
                                    <a href="#" class="btn btn-block py-2 btn-facebook">
                                        <span class="icon-facebook mr-3"></span> Login with facebook
                                    </a>
                                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:9999/swp/LoginGoogleHandler&response_type=code
                                       &client_id=105776943890-uhcf2qiqnmndrl022hgdsqkchr2jo7pp.apps.googleusercontent.com&approval_prompt=force" class="btn btn-block py-2 btn-google"><span class="icon-google mr-3"></span> Login with Google</a>
                                </div>

                            </form>

                        </div>
                    </div>
                </div>
            </div>


        </div>



        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
    </body>
</html>

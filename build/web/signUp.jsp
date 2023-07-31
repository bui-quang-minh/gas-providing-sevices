<%-- 
    Document   : login
    Created on : May 15, 2023, 4:30:10 PM
    Author     : admin
--%>
<%@include file="header3.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
    <head>

        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="login/fonts/icomoon/style.css">

        <link rel="stylesheet" href="css/owl.carousel.min.css">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <!-- Style -->
        <link rel="stylesheet" href="css/style_1.css">
        <link href="css/image.css" rel="stylesheet" type="text/css"/>
        <title>Betrolimex</title>
         <link rel="icon" href="img/favicon.png" />   
    </head>


    <body>
        
        <div class="d-md-flex half">
            <div class="contents">
                <div class="container">
                    <div class="row align-items-center justify-content-center mt-4 ">
                        <div class="  col-md-7">

                            <div class="text-center mb-5">
                                <h3 class="text-uppercase">Sign up for <a class="text-uppercase" style="color: red;" ><strong>Betrolimex</strong></a></h3>
                            </div>
                            <form enctype="multipart/form-data" action="signup" method="post">
                                <div class="form-group first">
                                    <label for="username">Username</label>
                                    <input type="text" class="form-control"  placeholder="Your Username" name="username" id="username" value="${sessionScope.username}">
                                </div>
                                <c:if test="${ not empty requestScope.message1}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message1}
                                </div>
                                </c:if>
                                
                                <div class="form-group last mb-3">
                                    <label for="password">Password</label>
                                    <input type="password" class="form-control"  placeholder="Your Password" name="password" id="password" value="${sessionScope.password}">
                                </div>
                                
                                <c:if test="${ not empty requestScope.message2}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message2}
                                </div>
                                </c:if>
                                
                                <div class="form-group last mb-3">
                                    <label for="passwordcf">Confirm Password</label>
                                    <input type="password" class="form-control" placeholder="Confirm Password" name="passwordcf" id="passwordcf" value="${sessionScope.passwordcf}">
                                </div>
                                
                                <c:if test="${ not empty requestScope.message3}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message3}
                                </div>
                                </c:if>
                                
                                <div class="form-group last mb-3">
                                    <label for="firstname">First Name</label>
                                    <input type="text" class="form-control" placeholder="Your First Name" name="firstname" id="firstname" value="${sessionScope.firstname}">
                                </div><!-- comment -->
                                <c:if test="${ not empty requestScope.message4}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message4}
                                </div>
                                </c:if>
                                <div class="form-group last mb-3">
                                    <label for="lastname">Last Name</label>
                                    <input type="text" class="form-control" placeholder="Your Last Name" name="lastname" id="lastname" value="${sessionScope.lastname}">
                                </div>
                                <c:if test="${ not empty requestScope.message5}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message5}
                                </div>
                                </c:if>
                                <div class="form-group last mb-3">
                                    <label for="address">Address</label>
                                    <input type="text" class="form-control"  placeholder="Your address" name="address" id="address" value="${sessionScope.address}">
                                </div>
                                <c:if test="${ not empty requestScope.message6}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message6}
                                </div>
                                </c:if>
                                <div class="form-group last mb-3">
                                    <label for="phonenumber">Phone Number</label>
                                    <input type="text" class="form-control"  placeholder="Your Phone Number" name="phonenumber" id="phonenumber" value="${sessionScope.phone}">
                                </div>
                                <c:if test="${ not empty requestScope.message7}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message7}
                                </div>
                                </c:if>
                                <div class="form-group last mb-3">
                                    <label for="email">Email</label>
                                    <input type="text" class="form-control"  placeholder="Your Email" name="email" id="email" value="${sessionScope.email}">
                                </div>
                                <c:if test="${ not empty requestScope.message8}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message8}
                                </div>
                                </c:if>
                                <div class="form-group last mb-3">
                                    <label for="dob">Date of Birth</label>
                                    <input type="date" class="form-control" name="dob" id="dob" value="${sessionScope.dob}"/>
                                </div>
                                <c:if test="${ not empty requestScope.message9}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message9}
                                </div>
                                </c:if>
                                <div class="form-group last mb-3">
                                    <label>Image</label><br/><!-- comment -->
                                    <input type="file" class="form-control"  name="image" id="image_input"  />                  
                                    <div id="display_image"></div>
                                    <script src="js/uploaded.js" type="text/javascript"></script>
                                </div>
                                
                                <c:if test="${ not empty requestScope.message10}">
                                <div class="alert alert-danger alert-dismissible">
                                    <button type="button" class="close" data-dismiss="alert">×</button>
                                    ${requestScope.message10}
                                </div>
                                </c:if>
                                <div class="d-sm-flex mb-5 align-items-center">
                                    <span class="ml-auto forgot-pass">You have account?</span> &nbsp;&nbsp; <span><a href="login" class="forgot-pass">Login</a></span>
                                </div>
                                <div class="d-sm-flex mb-3 align-items-center">
                                    <label class="control control--checkbox mb-3 mb-sm-0"><span class="caption">By registering, you agree with Betrolimex about <a style="color: orange;" >Terms of Service</a>  and <a style="color: orange;" >Privacy Policies</a> </span>
                                        <input type="checkbox" checked="checked"/>
                                        <div class="control__indicator"></div>
                                    </label>

                                </div>

                                <input type="submit" value="Register" class="btn btn-block py-2 btn-primary">

                                <span class="text-center my-3 d-block"></span>

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

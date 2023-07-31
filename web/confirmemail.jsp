<%-- 
    Document   : EnterOTP
    Created on : May 17, 2023, 6:18:08 PM
    Author     : admin
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset='utf-8'>
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <link href="css/forgotpassword.css" rel="stylesheet" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="login/fonts/icomoon/style.css">
        <link rel="stylesheet" href="css/owl.carousel.min.css">
        <link rel="stylesheet" href="css/style_1.css">

        <title>Betrolimex</title>
        <link rel="icon" href="img/favicon.png" />   
    </head>

    <body>
        <div class="d-md-flex half">
            <div class="contents ">
                <div class="container">
                    <div class="row align-items-center justify-content-center mt-4 ">
                        <div class="  col-md-7">
                            <div class="text-center mb-5">
                                <h3 class="text-uppercase">Sign Up To <a class="text-uppercase" style="color: red;" ><strong>Betrolimex</strong></a></h3>
                            </div>
                      
                            <div class="forgot">
                                <form action="confirmemail" method="post">
                                    <div class="form-group last mb-3">
                                        <c:if test="${not empty requestScope.message}">
                                            <p class="text-danger ml-1">${requestScope.message}</p>
                                        </c:if>
                                        <label for="opt">Enter OTP</label>                                       
                                        <input
                                            id="opt" name="otp" placeholder="Enter OTP"
                                            class="form-control" type="text" required="required"/>
                                    </div>                                   
                                    <input type="submit" value="Confirm Email" class="btn btn-block py-2 btn-primary">
                                    <a class="btn btn-block py-2 btn-danger" href="signup">Back To Sign Up</a>

                                </form>

                            </div>
                        </div>
                    </div>
                </div>


            </div>                 
        </div>

        <script type='text/javascript'
        src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>

    </body>
</html>



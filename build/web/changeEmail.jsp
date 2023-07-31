<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>

<%@page import="model.Customer"%>
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
        
        #confirm{
            position: absolute;
            display: none;
            background: white;
            left: 0; 
            right: 0; 
            margin-left: auto; 
            margin-right: auto; 
            text-align: center;
            width: 20%;
            height: 50px;
            border: 1px solid lightgray;
            border-radius: 10px;
        }
    </style>
    <body>
        <%
            Customer cus = (Customer)request.getSession().getAttribute("account");
        %>
        <%@include file="header.jsp" %>
        <div class="container" style="display: flex; padding-top: 1% ">
            <div class="catagories col-2" style="background-color: inherit">
                <div class="profile" style="display: flex; padding-bottom: 20%">
                    <div class="col-5">
                        <img class="rounded-circle" src="https://scontent.fhan14-1.fna.fbcdn.net/v/t39.30808-6/269836818_137257935386458_2614156832333942766_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=174925&_nc_ohc=pFao4AWYGNwAX9d8q44&_nc_ht=scontent.fhan14-1.fna&oh=00_AfDlTUd7RVv8G9RWaAE40yzSb-Hx3wU1R_r7xQaJXdkQnQ&oe=64648BCE">
                    </div>
                    <div class="username">
                        <div class="name">
                            <%=cus.getUsername()%>
                        </div>
                        <div>
                            <a href="profile" style="color: gainsboro">Edit Profile</a>
                        </div>
                    </div>
                </div>
                <div>
                    <a style="color: black" href="profile.jsp">My Account</a>
                    <ul style="list-style: none" class="profile-item mobile-menu">
                        <li><a href="profile" style="color: grey">Profile</a></li>
                        <li><a href="#" style="color: grey">Bank</a></li>
                        <li><a href="#" style="color: grey">Address</a></li>
                        <li><a href="changePassword.jsp" style="color: grey">Change Password</a></li>
                    </ul>
                </div>
                <div>
                    <a style="color: black" href="vieworder">Order</a>
                </div>
                <div>
                    <a style="color: black" href="#">Notification</a>
                </div>
            </div>
            <div class="details col-10" style="background: #F5F5F5; border-radius:10px">
                <div class="information-title" style="padding:2%; border-bottom: 1px solid lightgray ">
                    <h3>My Profile</h3>
                    <h4>Manage profile information for account security</h4>
                </div>
                <div style="display: flex">
                    <div style="padding: 2%" class="col-12">
                        <form action="changeemail" method="post">
                            <table style="width: 100%;">
                                <tr>
                                    <td colspan="3">Please Enter the pin we send to your Email:</td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input type="text" name="pin"></td>
                                    <td><a class="btn" href="changeemail">Send</a></td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td><input type="text" name="newEmail" value="<%=cus.getEmail()%>"></td>
                                    
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><button type="button" onclick="confirmSave()">Save</button></td>
                                    <td><input style="background: #F26F21;border: 1px solid black; border-radius: 3px" type="submit" name="submit" value="Sign Out"></td>
                                </tr>
                            </table> 
                                ${requestScope.message}
                                <div id="confirm">
                                    <input style="margin:5% " type="submit" name="submit" value="Yes">
                                    <button style="margin:5% " onclick="confirmSave()" type="button">No</button>
                                </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
<script>
function confirmSave() {
  var x = document.getElementById("confirm");
  if (x.style.display === "block") {
    x.style.display = "none";
  } else {
    x.style.display = "block";
  }
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

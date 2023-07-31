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
        form{
            width: 100%;
            display: flex;
        }
    </style>
    <body>
        <%
            Customer cus = (Customer)request.getSession().getAttribute("account");
        %>
        <%@include file="header.jsp" %>
        <div class="container" style="display: flex; padding-top: 1% ">
            <div class="catagories col-2" style="background-color: inherit">
                <div class="profile" style="display: flex; padding-bottom: 20%;padding-top:20%">
                    <div class="col-5">
                        <img class="rounded-circle" src="data:image/jpeg;base64,<%=cus.getEncodedImage()%>">
                    </div>
                    <div class="username">
                        <div class="name">
                            <%=cus.getFirstname()%> <%=cus.getLastname()%>
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
                        <li><a href="changePassword.jsp" style="color: grey">Change Password</a></li>
                    </ul>
                </div>
                <a style="color: black" href="allproduct">All Product</a></br>
                <a style="color: black" href="vieworder">Your Order</a></br>
                <a style="color: black" href="cart">Cart</a></br>
                <a style="color: black" href="promotion">Promotion</a></br>
                <a style="color: black" href="warranty">Warranty</a></br>
            </div>
            <div class="details col-10" style="background: #F5F5F5; border-radius:10px">
                <div class="information-title" style="padding:2%; border-bottom: 1px solid lightgray ">
                    <h3>My Profile</h3>
                    <h4>Manage profile information for account security</h4>
                </div>
                <div>
                    <form action="saveprofile"  enctype="multipart/form-data"  method="post">
                        <div style="padding: 2%" class="col-9">

                            <table style="width: 100%; border-right: 1px solid lightgray">
                                <tr>
                                    <td>User Name </td>
                                    <td colspan="3"><%=cus.getUsername()%><input type="hidden" value="<%=cus.getUsername()%>" name="username"></td>
                                </tr>
                                <tr>
                                    <td>Last Name</td>
                                    <td colspan="3"><input type="text" value="<%=cus.getLastname()%>" name="lastname"></td>
                                </tr>
                                <tr>
                                    <td>First Name</td>
                                    <td colspan="3"><input type="text" value="<%=cus.getFirstname()%>" name="firstname"></td>
                                </tr>
                                <tr>
                                    <td>Email</td>
                                    <td><%=cus.getEmail()%></td>
                                    <td><a href="changeEmail.jsp" style="color: grey">Change</a></td>
                                </tr>
                                <tr>
                                    <td>Phone</td>
                                    <td><%=cus.getPhone()%></td>
                                    <td><a href="#" style="color: grey">Change</a></td>
                                </tr>
                                <tr>
                                    <td>Birth Date</td>
                                    <td><input type="date" value="<%=cus.getDob()%>" name="dob"></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><button type="button" onclick="confirmSave()">Save</button></td>
                                    <td><input style="background: #F26F21;border: 1px solid black; border-radius: 3px" type="submit" name="submit" value="Sign Out"></td>
                                </tr>

                            </table> 
                            <div id="confirm">
                                <input style="margin:5% " type="submit" name="submit" value="Yes">
                                <button style="margin:5% " onclick="confirmSave()" type="button">No</button>
                            </div>

                        </div>

                        <div class="col-3" style="padding: 2%; text-align: center">
                            <img class="img-window" src="data:image/jpeg;base64,<%=cus.getEncodedImage()%>">
                            <input type="file" name="image" id="image_input"/><br>
                            <p>Size: < 1 Mb<br>Type: .JPEG, .PNG</p>
                        </div>
                    </form>
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

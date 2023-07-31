<%-- 
    Document   : chat
    Created on : Jun 12, 2023, 11:40:42 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="errorPage.jsp" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html
    lang="en"
    class="light-style layout-menu-fixed"
    dir="ltr"
    data-theme="theme-default"
    data-assets-path="assets/"
    data-template="vertical-menu-template-free"
    >
    <head>
        <meta charset="utf-8" />
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            />

        <title>Betrolimex</title>
        <link rel="icon" href="img/favicon.png" />   
        <meta name="description" content="" />

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com" />
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
        <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
            />

        <!-- Icons. Uncomment required icon fonts -->
        <link rel="stylesheet" href="assets/vendor/fonts/boxicons.css" />

        <!-- Core CSS -->
        <link rel="stylesheet" href="assets/vendor/css/core.css" class="template-customizer-core-css" />
        <link rel="stylesheet" href="assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
        <link rel="stylesheet" href="assets/css/demo.css" />

        <!-- Vendors CSS -->
        <link rel="stylesheet" href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

        <link rel="stylesheet" href="assets/vendor/libs/apex-charts/apex-charts.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- Page CSS -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <!-- Helpers -->
        <script src="assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="assets/js/config.js"></script>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/chat.css" rel="stylesheet" type="text/css"/>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
        <style>
            .msb-reply:focus {
                outline: 2px solid blue; /* Thay đổi màu sắc và kiểu viền tùy ý */
            }
        </style>
    </head>
    <body>
        <!-- Layout wrapper -->
        <div class="layout-wrapper layout-content-navbar">
            <div class="layout-container">
                <!-- Menu -->

                <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
                    <div class="app-brand demo">
                        <a href="statistic.jsp" class="app-brand-link">
                            <span class="app-brand-text demo menu-text fw-bolder ms-2"><img style="width: 1.5em; margin-left: 0px;" src="img/favicon.png">
                                Betrolimex</span>

                        </a>

                        <a href="javascript:void(0);" class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                            <i class="bx bx-chevron-left bx-sm align-middle"></i>
                        </a>
                    </div>

                    <div class="menu-inner-shadow"></div>

                    <ul class="menu-inner py-1">
                        <!-- Dashboard -->
                        <li class="menu-item ">
                            <a href="statistic" class="menu-link">
                                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                                <div data-i18n="Analytics">Dashboard</div>
                            </a>
                        </li>
                        <li class="menu-item">

                            <a href="javascript:void(0);" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-layout"></i>
                                <div data-i18n="Layouts">Menu Layouts</div>
                            </a>

                            <ul class="menu-sub">

                                <li class="menu-item active open">

                                    <ul class="menu-sub">
                                        <c:forEach items="${sessionScope.Navigation}" var="c">
                                            <li class="menu-item<c:if test="${not empty requestScope.News}">
                                                    <c:forEach items="${requestScope.News}" var="n">
                                                        <c:if test="${n.navigationID == c.navigationID}"> active</c:if>
                                                    </c:forEach>
                                                </c:if>">
                                                <a href="changedirection?change=${c.navigationID}" class="menu-link">
                                                    <div data-i18n="Without navbar">${c.navigationName}</div>
                                                </a>
                                            </li>
                                        </c:forEach>

                                    </ul>
                                </li>

                            </ul>
                        </li>
                        <!-- Components -->
                        <li class="menu-header small text-uppercase"><span class="menu-header-text">Product Management</span></li>
                        <!-- Cards -->
                        <li class="menu-item">
                            <a href="javascript:void(0)" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-box"></i>
                                <div data-i18n="User interface">Stock</div>
                            </a>
                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a href="<%=request.getContextPath()%>/stock" class="menu-link">
                                        <div data-i18n="AllCategory">All category</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-item">
                            <a href="<%=request.getContextPath()%>/addproduct" class="menu-link">
                                <i class="menu-icon tf-icons bx bx-collection"></i>
                                <div data-i18n="Basic">Add Product</div>
                            </a>
                        </li>

                        <c:if test="${account.role eq 'Admin'}">
                            <li class="menu-item">
                                <a href="<%=request.getContextPath()%>/adddiscount" class="menu-link">
                                    <i class="menu-icon tf-icons bx bx-collection"></i>
                                    <div data-i18n="Basic">Add Discount</div>
                                </a>
                            </li>
                        </c:if>
                        <li class="menu-item">
                            <a href="javascript:void(0);" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-collection"></i>
                                <div data-i18n="Form Layouts">Manufacturer</div>
                            </a>
                            <ul class="menu-sub">
                                <li class="menu-item ">
                                    <a href="allmanufacturer" class="menu-link">
                                        <div data-i18n="Vertical Form">Manufacturer List</div>
                                    </a>
                                </li>
                                <li class="menu-item ">
                                    <a href="addmanufacturer" class="menu-link">
                                        <div data-i18n="Horizontal Form">Add Manufacturer</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-item">
                            <a href="<%=request.getContextPath()%>/allcategory" class="menu-link">
                                <i class="menu-icon tf-icons bx bx-collection"></i>
                                <div data-i18n="Basic">Add Category</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a href="javascript:void(0);" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-detail"></i>
                                <div data-i18n="Form Layouts">Warranty</div>
                            </a>
                            <ul class="menu-sub">

                                <li class="menu-item">
                                    <a href="checkwarranty" class="menu-link">
                                        <div data-i18n="Horizontal Form">Warranty list</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <li class="menu-header small text-uppercase"><span class="menu-header-text">Order</span></li>
                        <li class="menu-item">
                            <a href="<%=request.getContextPath()%>/checkorder" class="menu-link">
                                <i class="menu-icon tf-icons bx bx-collection"></i>
                                <div data-i18n="Basic">Check Order</div>
                            </a>
                        </li>
                        <li class="menu-header small text-uppercase"><span class="menu-header-text">News Management</span></li>
                        <li class="menu-item ">
                            <a href="javascript:void(0);" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-detail"></i>
                                <div data-i18n="Form Layouts">News</div>
                            </a>

                            <ul class="menu-sub">
                                <li class="menu-item ">
                                    <a href="addnews" class="menu-link">
                                        <div data-i18n="Vertical Form">Add News</div>
                                    </a>
                                </li>
                                <c:if test="${account.role eq 'Admin'}">
                                    <li class="menu-item ">
                                        <a href="newsmanagement" class="menu-link">
                                            <div data-i18n="Without navbar">News Management</div>
                                        </a>
                                    </li>

                                </c:if>
                            </ul>

                        </li>

                        <!-- Forms & Tables -->

                        <!-- Tables -->
                        <c:if test="${account.role eq 'Admin'}">
                            <li class="menu-header small text-uppercase"><span class="menu-header-text">User management</span></li>
                            <li class="menu-item ">
                                <a href="javascript:void(0);" class="menu-link menu-toggle">
                                    <i class="menu-icon tf-icons bx bx-detail"></i>
                                    <div data-i18n="Form Layouts">Employees</div>
                                </a>
                                <ul class="menu-sub">
                                    <li class="menu-item">
                                        <a href="listemployee" class="menu-link">
                                            <div data-i18n="Vertical Form">Employee List</div>
                                        </a>
                                    </li>
                                    <li class="menu-item ">
                                        <a href="addemployee" class="menu-link">
                                            <div data-i18n="Horizontal Form">Add Employee</div>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="menu-item">
                                <a href="<%=request.getContextPath()%>/listcustomer" class="menu-link">
                                    <i class="menu-icon tf-icons bx bx-detail"></i>
                                    <div data-i18n="Basic">Customer</div>
                                </a>
                            </li>
                            <li class="menu-header small text-uppercase"><span class="menu-header-text">Statistic</span></li><!-- comment -->
                            <li class="menu-item">
                                <a href="<%=request.getContextPath()%>/customerstatistic" class="menu-link">
                                    <i class="menu-icon tf-icons bx bx-detail"></i>
                                    <div data-i18n="Basic">Customer Statistic</div>
                                </a>
                            </li>
                            <li class="menu-item">
                                <a href="<%=request.getContextPath()%>/employeestatistic" class="menu-link">
                                    <i class="menu-icon tf-icons bx bx-detail"></i>
                                    <div data-i18n="Basic">Employee Statistic</div>
                                </a>
                            </li>
                        </c:if>
                        <li class="menu-header small text-uppercase"><span class="menu-header-text">Misc</span></li>
                        <li class="menu-item active">
                            <a href="<%=request.getContextPath()%>/employeechat" class="menu-link">
                                <i class="menu-icon tf-icons bx bx-support"></i>
                                <div data-i18n="Support">Support</div>
                            </a>
                        </li>
                    </ul>
                </aside>
                <!-- / Menu -->
                <div class="layout-page">     
                    <div class="content-wrapper">
                        <div class="container-xxl flex-grow-1 container-p-y">

                            <div class="tile tile-alt" id="messages-main">
                                <div class="ms-menu">
                                    <div class="ms-user clearfix">
                                        <img
                                            src="data:image/jpeg/png/jpg;base64,${employee.encodedImage}"
                                            alt
                                            class="img-avatar pull-left"                                                
                                            />
                                        <div>Signed in as <br> <a  class="__cf_email__" >${employee.username}</a></div>
                                    </div>

                                    <script>
                                        setInterval(loadconversation, 1000);
                                        function loadconversation() {
                                            $.post("employeechat", function (data, status) {
                                                document.getElementsByClassName("list")[0].innerHTML = data;

                                            });
                                        }
                                    </script>

                                    <div class="message">
                                        <div class="list-group lg-alt">
                                            <div class="list">
                                                <c:forEach items="${requestScope.customers}" var="c" varStatus="loop">
                                                    <c:forEach items="${messages}" var="message">
                                                        <c:if test="${message.conversation.id eq c.conversation.id and message.senderRole eq 'Customer'}">
                                                            <c:set var="customerMessage" value="${message.content}" />
                                                        </c:if>
                                                    </c:forEach>
                                                    <c:if test="${not empty customerMessage}">
                                                        <a class="list-group-item media"  href="employeeconversation?conversationid=${c.conversation.id}" style="background-color: #ffcf78;">
                                                        </c:if>
                                                        <c:if test="${ empty customerMessage}">
                                                            <a class="list-group-item media"  href="employeeconversation?conversationid=${c.conversation.id}">
                                                            </c:if>
                                                            <div class="lv-avatar pull-left">
                                                                <img
                                                                    src="data:image/jpeg/png/jpg;base64,${c.encodedImage}"
                                                                    alt
                                                                    class="img-avatar"                                                
                                                                    />
                                                            </div>
                                                            <div class="media-body">

                                                                <c:if test="${not empty customerMessage}">
                                                                    <div class="list-group-item-heading"><strong>${c.firstname} ${c.lastname}</strong></div>
                                                                </c:if>
                                                                <c:if test="${ empty customerMessage}">
                                                                    <div class="list-group-item-heading">${c.firstname} ${c.lastname}</div>
                                                                </c:if>

                                                                <c:if test="${not empty messages}">
                                                                    <c:forEach items="${messages}" var="message">
                                                                        <c:if test="${message.conversation.id eq c.conversation.id}">
                                                                            <c:set var="selectedMessage" value="${message}" />
                                                                        </c:if>
                                                                    </c:forEach>
                                                                    <c:if test="${not empty selectedMessage}">
                                                                        <c:set var="content" value="${selectedMessage.content}" />
                                                                        <c:set var="words" value="${fn:split(content, ' ')}" />
                                                                        <c:forEach items="${words}" var="word" varStatus="status" begin="0" end="3">
                                                                            <c:if test="${not empty customerMessage}">
                                                                                <small class="list-group-item-text c-gray"><strong>${word}</strong></small>
                                                                                    </c:if>
                                                                                    <c:if test="${ empty customerMessage}">
                                                                                <small class="list-group-item-text c-gray">${word}</small>
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </c:if>
                                                            </div>
                                                        </a>
                                                    </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="ms-body">
                                    <div id="conversationContainer">
                                        <div class="action-header clearfix">
                                            <div class="visible-xs" id="ms-menu-trigger">
                                                <i class="fa fa-bars"></i>
                                            </div>
                                            <div class="pull-left hidden-xs">
                                                <c:set var="printed" value="false" />
                                                <c:forEach items="${requestScope.customers}" var="c" varStatus="loop">
                                                    <c:forEach items="${messageses}" var="message">
                                                        <c:if test="${message.conversation.id eq c.conversation.id && printed eq false}">
                                                            <c:set var="printed" value="true" />
                                                            <c:set var="conversationid" value="${c.conversation.id}"/>
                                                            <img src="data:image/jpeg/png/jpg;base64,${c.encodedImage}" alt class="img-avatar m-r-10" />
                                                            <div class="lv-avatar pull-left"></div>
                                                            <span><strong>${c.firstname} ${c.lastname}</strong></span>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </div>
                                        </div>

                                        <div class="message" >
                                            <div id="messageContainer" data-conversationid="${conversationid}">

                                                <script>

                                                    // Hàm để gửi yêu cầu AJAX và cập nhật tin nhắn
                                                    function updateMessages() {
                                                        $.ajax({
                                                            url: 'employeeconversation',
                                                            method: 'get',
                                                            data: {conversationid: conversationId},
                                                            dataType: 'html',
                                                            success: function (data) {
                                                                // Cập nhật nội dung của phần chứa tin nhắn
                                                                $("#messageContainer").html(data);
                                                            },
                                                            error: function () {
                                                                console.log("Lỗi khi gửi yêu cầu AJAX");
                                                            }
                                                        });
                                                    }
                                                    function sendMessage() {
                                                        var inputbox = document.getElementById("message");
                                                        inputbox.addEventListener('keyup', function (event) {
                                                            if (event.keyCode == 13) {
                                                                $("#send").click();
                                                            }
                                                        });
                                                        $("#send").click(function () {
                                                            var message = $("#message").val();
                                                            $.ajax({
                                                                url: 'employeeconversation',
                                                                method: 'post',
                                                                data: {message: message, conversationid: conversationId},
                                                                success: function (data) {
                                                                    $(".messageContainer").html(data);
                                                                    $(".messageContainer").scrollTop($(".messageContainer")[0].sscrollHeight);
                                                                },
                                                                error: function () {
                                                                    console.log("Lỗi khi gửi yêu cầu AJAX");
                                                                }
                                                            });
                                                            $("#message").val("");
                                                        });
                                                    }
                                                    messageInterval = setInterval(updateMessages, 1000);
                                                    var conversationId = $("#messageContainer").data("conversationid");
                                                    $(document).ready(function () {
                                                        sendMessage();
                                                        $("body").on("click", ".list-group-item.media", function (event) {
                                                            event.preventDefault();
                                                            clearInterval(messageInterval);
                                                            conversationId = $(this).attr("data-conversationid");
                                                            $.ajax({
                                                                url: "getconversation?conversationid=" + conversationId,
                                                                method: "GET",
                                                                success: function (data) {

                                                                    $("#conversationContainer").html(data);
                                                                },
                                                                error: function () {
                                                                    console.log("Lỗi khi gửi yêu cầu AJAX");
                                                                }
                                                            });
                                                            messageInterval = setInterval(updateMessages, 1000);

                                                        });
                                                    });
                                                </script>

                                            </div>
                                        </div><!-- comment -->
                                    </div>
                                    <div class="msb-reply" style="margin-top: 0px;">
                                        <textarea name="content" id="message" placeholder="What's on your mind..."></textarea>
                                        <button type="submit" id="send"><i class="fa fa-paper-plane-o"></i></button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div><!-- comment -->
                </div><!-- comment -->
            </div>
        </div><!-- comment -->
    </div>
    <script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script><script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script>
                                                    $(function () {
                                                        if ($('#ms-menu-trigger')[0]) {
                                                            $('body').on('click', '#ms-menu-trigger', function () {
                                                                $('.ms-menu').toggleClass('toggled');
                                                            });
                                                        }
                                                    });

    </script>
    <script src="assets/vendor/libs/jquery/jquery.js"></script>
    <script src="assets/vendor/libs/popper/popper.js"></script>
    <script src="assets/vendor/js/bootstrap.js"></script>
    <script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->
    <script src="assets/vendor/libs/apex-charts/apexcharts.js"></script>

    <!-- Main JS -->
    <script src="assets/js/main.js"></script>

    <!-- Page JS -->
    <script src="assets/js/dashboards-analytics.js"></script>

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>



</body>
</html>
<%-- 
    Document   : addCustomer
    Created on : Jun 9, 2023, 2:07:35 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page errorPage="errorPage.jsp" %> 
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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

        <!-- Helpers -->
        <script src="assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="assets/js/config.js"></script>
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
                        <li class="menu-item">
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
                        <li class="menu-item active open">
                            <a href="javascript:void(0);" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-collection"></i>
                                <div data-i18n="Form Layouts">Manufacturer</div>
                            </a>
                            <ul class="menu-sub">
                                <li class="menu-item active">
                                    <a href="allmanufacturer" class="menu-link">
                                        <div data-i18n="Vertical Form">Manufacturer List</div>
                                    </a>
                                </li>
                                <li class="menu-item">
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
                        <li class="menu-header small text-uppercase"><span class="menu-header-text">News Management</span></li>
                        <li class="menu-item">
                            <a href="javascript:void(0);" class="menu-link menu-toggle">
                                <i class="menu-icon tf-icons bx bx-detail"></i>
                                <div data-i18n="Form Layouts">News</div>
                            </a>

                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a href="addnews" class="menu-link">
                                        <div data-i18n="Vertical Form">Add News</div>
                                    </a>
                                </li>
                                <c:if test="${account.role eq 'Admin'}">
                                    <li class="menu-item">
                                        <a href="newsmanagement" class="menu-link">
                                            <div data-i18n="Without navbar">News Management</div>
                                        </a>
                                    </li>
                                </ul>

                            </li>
                        </c:if>

                        <!-- Forms & Tables -->

                        <!-- Tables -->
                        <c:if test="${account.role eq 'Admin'}">
                            <li class="menu-header small text-uppercase"><span class="menu-header-text">User management</span></li>
                            <li class="menu-item">
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
                                    <li class="menu-item">
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
                            <li class="menu-item ">
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
                        <li class="menu-item">
                            <a href="<%=request.getContextPath()%>/employeechat" class="menu-link">
                                <i class="menu-icon tf-icons bx bx-support"></i>
                                <div data-i18n="Support">Support</div>
                            </a>
                        </li>
                    </ul>
                </aside>
                <!-- / Menu -->
                <!-- Layout container -->
                <div class="layout-page">
                    <!-- Navbar -->

                    <nav
                        class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                        id="layout-navbar"
                        >
                        <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                            <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                                <i class="bx bx-menu bx-sm"></i>
                            </a>
                        </div>

                        <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                            <!-- Search -->
                            <div class="navbar-nav align-items-center">
                                <div class="nav-item d-flex align-items-center">
                                    <i class="bx bx-search fs-4 lh-0"></i>
                                    <input
                                        type="text"
                                        class="form-control border-0 shadow-none"
                                        placeholder="Search..."
                                        aria-label="Search..."
                                        />
                                </div>
                            </div>
                            <!-- /Search -->

                            <ul class="navbar-nav flex-row align-items-center ms-auto">
                                <!-- Place this tag where you want the button to render. -->
                                <li class="nav-item lh-1 me-3">
                                    <a
                                        class="github-button"
                                        href="https://github.com/themeselection/sneat-html-admin-template-free"
                                        data-icon="octicon-star"
                                        data-size="large"
                                        data-show-count="true"
                                        aria-label="Star themeselection/sneat-html-admin-template-free on GitHub"
                                        >Star</a
                                    >
                                </li>

                                <!-- User -->
                                <li class="nav-item navbar-dropdown dropdown-user dropdown">
                                    <a class="nav-link dropdown-toggle hide-arrow" href="javascript:void(0);" data-bs-toggle="dropdown">
                                        <div class="avatar avatar-online">
                                            <img src="data:image/jpeg/png/jpg;base64,${account.encodedImage}" alt class="w-px-40 h-auto rounded-circle" />
                                        </div>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li>
                                            <a class="dropdown-item" href="employeeprofile">
                                                <div class="d-flex">
                                                    <div class="flex-shrink-0 me-3">
                                                        <div class="avatar avatar-online">
                                                            <img src="data:image/jpeg/png/jpg;base64,${account.encodedImage}" alt class="w-px-40 h-auto rounded-circle" />
                                                        </div>
                                                    </div>
                                                    <div class="flex-grow-1">
                                                        <span class="fw-semibold d-block">${account.firstname} ${account.lastname}</span>
                                                        <small class="text-muted">${account.role}</small>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="dropdown-divider"></div>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="employeeprofile">
                                                <i class="bx bx-user me-2"></i>
                                                <span class="align-middle">My Profile</span>
                                            </a>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="#">
                                                <i class="bx bx-cog me-2"></i>
                                                <span class="align-middle">Settings</span>
                                            </a>
                                        </li>
                                        
                                        <li>
                                            <div class="dropdown-divider"></div>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="logout">
                                                <i class="bx bx-power-off me-2"></i>
                                                <span class="align-middle">Log Out</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <!--/ User -->
                            </ul>
                        </div>
                    </nav>

                    <!-- / Navbar -->
                    <!-- Content wrapper -->

                    <div class="d-md-flex half">

                        <div class="container">
                            <div class="row align-items-center justify-content-center mt-4 ">
                                <div class="  col-md-7">

                                    <div class="text-center mb-5">
                                        <h3 class="text-uppercase">Register Customer for <a class="text-uppercase" style="color: red;" ><strong>Betrolimex</strong></a></h3>
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
                                        <input type="submit" value="Register" class="btn btn-block py-2 btn-primary">
                                        <span class="text-center my-3 d-block"></span>

                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>   
                    <div class="content-backdrop fade"></div>
                </div>
                <!-- Content wrapper -->
            </div>
            <!-- / Layout page -->
        </div>

        <!-- Overlay -->
        <div class="layout-overlay layout-menu-toggle"></div>

        <!-- Core JS -->
        <!-- build:js assets/vendor/js/core.js -->
        <script src="assets/vendor/libs/jquery/jquery.js"></script>
        <script src="assets/vendor/libs/popper/popper.js"></script>
        <script src="assets/vendor/js/bootstrap.js"></script>
        <script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

        <script src="assets/vendor/js/menu.js"></script>
        <!-- endbuild -->

        <!-- Vendors JS -->

        <!-- Main JS -->
        <script src="assets/js/main.js"></script>

        <!-- Page JS -->

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
        <script src="js/jquery-3.3.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>

    </body>
</html>


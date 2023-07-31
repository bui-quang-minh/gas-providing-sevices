<%-- 
    Document   : information
    Created on : Apr 22, 2023, 5:39:33 PM
    Author     : admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="assets/img/favicon/favicon.ico" />

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css" />
        <!-- font awesome 5.13.1 -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css" />
        <!-- Vendors CSS -->
        <link rel="stylesheet" href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

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
                            <li class="menu-item active open">
                                <a href="javascript:void(0);" class="menu-link menu-toggle">
                                    <i class="menu-icon tf-icons bx bx-detail"></i>
                                    <div data-i18n="Form Layouts">Employees</div>
                                </a>
                                <ul class="menu-sub">
                                    <li class="menu-item active">
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
                    <div class="content-wrapper">
                        <!-- Content -->

                        <div class="container-xxl flex-grow-1 container-p-y">
                            <h4 class="fw-bold py-3 mb-4"><span class="text-muted fw-light">Account Settings /</span> ${employee.username}</h4>

                            <div class="row">
                                <div class="col-md-12">
                                    <ul class="nav nav-pills flex-column flex-md-row mb-3">
                                        <li class="nav-item">
                                            <a class="nav-link active" href="javascript:void(0);"><i class="bx bx-user me-1"></i> Account</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#"
                                               ><i class="bx bx-bell me-1"></i> Notifications</a
                                            >
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="#"
                                               ><i class="bx bx-link-alt me-1"></i> Connections</a
                                            >
                                        </li>
                                    </ul>
                                    <c:if test="${ not empty requestScope.status1}">
                                        <div class="alert alert-success alert-dismissible">
                                            <button type="button" class="close" data-dismiss="alert">×</button>
                                            ${requestScope.status1}
                                        </div>
                                    </c:if>
                                    <div class="card mb-4">
                                        <h5 class="card-header">Profile Details</h5>
                                        <!-- Account -->     
                                        <form enctype="multipart/form-data" action="changeimage?change=1&employeeid=${employee.id}" method="post">
                                            <div class="card-body">
                                                <div class="d-flex align-items-start align-items-sm-center gap-4">
                                                    <img
                                                        src="data:image/jpeg/png/jpg;base64,${employee.encodedImage}"
                                                        alt="user-avatar"
                                                        class="d-block rounded"
                                                        height="100"
                                                        width="100"
                                                        id="uploadedAvatar"
                                                        />
                                                    <div class="button-wrapper">
                                                        <label for="upload" class="btn btn-primary me-2 mb-4" tabindex="0">
                                                            <span class="d-none d-sm-block">Upload new photo</span>
                                                            <i class="bx bx-upload d-block d-sm-none"></i>
                                                            <input
                                                                type="file"
                                                                id="upload"
                                                                class="account-file-input"
                                                                hidden
                                                                name="image"
                                                                accept="image/png, image/jpeg, image/jpg"
                                                                />
                                                        </label>
                                                        <button type="button" class="btn btn-outline-secondary account-image-reset mb-4">
                                                            <i class="bx bx-reset d-block d-sm-none"></i>
                                                            <span class="d-none d-sm-block">Reset</span>
                                                        </button>
                                                        <button type="submit" class="btn btn-primary me-2 mb-4">
                                                            <i class="bx bx-reset d-block d-sm-none"></i>
                                                            <span class="d-none d-sm-block">Submit</span>
                                                        </button>

                                                        <p class="text-muted mb-0">Allowed JPG, JPEG, PNG. Max size of 20 MB</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                        <hr class="my-0" />
                                        <form method="POST" action="editemployee?employeeid=${employee.id}">
                                            <input type="hidden" name="employeeid" value="${employee.id}" />
                                            <div class="card-body">
                                                <div class="row">
                                                    <div class="mb-3 col-md-6">

                                                        <label for="firstname" class="form-label">First Name</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="firstName"
                                                            name="firstname"
                                                            value="${employee.firstname}"
                                                            placeholder="First Name"
                                                            autofocus
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">

                                                        <label for="lastname" class="form-label">Last Name</label>
                                                        <input class="form-control" type="text" name="lastname" id="lastname" value="${employee.lastname}" placeholder="Last Name" />
                                                    </div>
                                                    <div class="mb-3 col-md-6">

                                                        <label for="email" class="form-label">E-mail</label>
                                                        <input
                                                            class="form-control"
                                                            type="text"
                                                            id="email"
                                                            name="email"
                                                            value="${employee.email}"
                                                            placeholder="Email"
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">

                                                        <label for="dob" class="form-label">D.O.B</label>
                                                        <input
                                                            type="date"
                                                            class="form-control"
                                                            id="dob"
                                                            name="dob"
                                                            value="${employee.dob}"
                                                            placeholder="D.O.B"
                                                            />
                                                    </div>
                                                    <div class="mb-3 col-md-6">

                                                        <label class="form-label" for="phonenumber">Phone Number</label>
                                                        <div class="input-group input-group-merge">
                                                            <span class="input-group-text">Vietnam (+84)</span>
                                                            <input
                                                                type="text"
                                                                id="phonenumber"
                                                                name="phonenumber"
                                                                class="form-control"
                                                                value="${employee.phone.replace("+", "").replace("84", "0")}"
                                                                placeholder="Phone Number"
                                                                />
                                                        </div>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <c:if test="${ not empty requestScope.message6}">
                                                            <div class="alert alert-danger alert-dismissible">
                                                                <button type="button" class="close" data-dismiss="alert">×</button>
                                                                ${requestScope.message6}
                                                            </div>
                                                        </c:if>
                                                        <label for="address" class="form-label">Address</label>
                                                        <input type="text" class="form-control" id="address" name="address" placeholder="Address" value="${employee.address}" />
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <label for="hireddate" class="form-label">Hired Date</label>
                                                        <input class="form-control" type="date" id="hireddate" name="hireddate" placeholder="Hired Date" value="${employee.hiredDate}"/>
                                                    </div>
                                                    <div class="mb-3 col-md-6">
                                                        <c:if test="${ not empty requestScope.message1}">
                                                            <div class="alert alert-danger alert-dismissible">
                                                                <button type="button" class="close" data-dismiss="alert">×</button>
                                                                ${requestScope.message1}
                                                            </div>
                                                        </c:if>
                                                        <label for="username" class="form-label">Username</label>
                                                        <input
                                                            type="text"
                                                            class="form-control"
                                                            id="username"
                                                            name="username"
                                                            placeholder="Username"
                                                            maxlength="30"
                                                            minlength="8"
                                                            value ="${employee.username}"
                                                            />
                                                    </div>


                                                    <div class="mb-3 col-md-6">

                                                        <label for="password" class="form-label">Password</label>
                                                        <div class="input-group">
                                                            <input
                                                                type="password"
                                                                class="form-control"
                                                                id="ipnPassword"
                                                                name="password"
                                                                placeholder="Password"
                                                                value="${employee.password}"
                                                                />
                                                            <div class="input-group-append">
                                                                <button class="btn btn-outline-secondary" type="button" id="btnPassword">
                                                                    <span class="fas fa-eye"></span>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div>



                                                    <div class="mb-3 col-md-6">
                                                        <label for="status" class="form-label">Status</label>
                                                        <div class="input-group">
                                                            <label class="btn  col-4">
                                                                <input type="radio" name="status" value="Active" <c:if test="${employee.desciption == 'Active'}">checked </c:if>> Active
                                                                </label>
                                                                <label class="btn  col-4">
                                                                    <input type="radio" name="status" value="Inactive" <c:if test="${employee.desciption == 'Inactive'}">checked</c:if>> Inactive
                                                                </label>
                                                                <label class="btn col-4">
                                                                    <input type="radio" name="status" value="Suspended" <c:if test="${employee.desciption == 'Suspended'}">checked</c:if>> Suspended
                                                                </label>
                                                            </div>
                                                        </div>
                                                        <div class="mt-2">
                                                            <button type="submit" class="btn btn-primary me-2">Save changes</button>
                                                            <button type="reset" class="btn btn-outline-secondary">Cancel</button>
                                                        </div>
                                                    </div><!-- comment -->
                                                </div>

                                            </form>

                                            <!-- /Account -->
                                        </div>
                                        <div class="card">
                                            <h5 class="card-header">Delete Account</h5>
                                            <div class="card-body">
                                                <div class="mb-3 col-12 mb-0">
                                                    <div class="alert alert-warning">
                                                        <h6 class="alert-heading fw-bold mb-1">Are you sure you want to delete your account?</h6>
                                                        <p class="mb-0">Once you delete your account, there is no going back. Please be certain.</p>
                                                    </div>
                                                </div>

                                                <div class="form-check mb-3">
                                                    <input
                                                        class="form-check-input"
                                                        type="checkbox"
                                                        name="accountActivation"
                                                        id="accountActivation"
                                                        />
                                                    <label class="form-check-label" for="accountActivation"
                                                           >I confirm my account deactivation</label
                                                    >
                                                </div>
                                                <a class="btn btn-danger deactivate-account" onclick="deleteEmployee(${employee.id})">Deactivate Account</a>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- / Content -->

                        <div class="content-backdrop fade"></div>
                    </div>
                    <!-- Content wrapper -->
                </div>
                <!-- / Layout page -->
            </div>

            <!-- Overlay -->
            <div class="layout-overlay layout-menu-toggle"></div>
        </div>

        <!-- / Layout wrapper -->
        <script>

            function deleteEmployee(id)
            {
                var result = confirm("Are you sure?");
                if (result)
                {
                    window.location.href = "deleteemployee?employeeid=" + id;
                }
            }

        </script>

        <script src="js/password.js" type="text/javascript"></script>
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
        <script src="assets/js/pages-account-settings-account.js"></script>

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
    </body>
</html>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Site Metas -->
    <title>Betrolimex</title>
     <link rel="icon" href="img/favicon.png" />   
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

    <!-- Design fonts -->
    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet"> 

    <!-- Bootstrap core CSS -->
    <link href="css/allnews-boostrap.css" rel="stylesheet">

    <!-- FontAwesome Icons core CSS -->
    <link href="css/font-awesome.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive styles for this template -->
    <link href="css/responsive.css" rel="stylesheet">

    <!-- Colors for this template -->
    <link href="css/colors.css" rel="stylesheet">

    <!-- Version Tech CSS for this template -->
    <link href="css/allNews.css" rel="stylesheet">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

    <div id="wrapper">
        <%@include file="header.jsp" %>

        <section class="section single-wrapper">
            <div class="container">

                <div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">
                    <div class="page-wrapper">
                        <div class="blog-title-area text-center">
                            <ol class="breadcrumb hidden-xs-down">
                                <li class="breadcrumb-item"><a href=allNews>News</a></li>
                                
                                <li class="breadcrumb-item active">${po.getNewsTitle()}</li>
                            </ol>

                            <!--                                <span class="color-orange"><a href="tech-category-01.html" title="">Technology</a></span>-->

                            <h3>${po.getNewsTitle()}</h3>

                            <div class="blog-meta big-meta">
                                <small>${po.getCreatedDate()}</small>
                                <small>by Minh Bui</a</small>
                                <small><i class="fa fa-eye"></i> 2344</small>
                            </div><!-- end meta -->
                            <img src="data:image/jpeg;base64,${po.encodedImage}" alt="" class="img-fluid img-fullwidth">
                        </div><!-- end title -->


                        <div class="blog-content">  

                            <p>${po.getNewsContent()}</p>



                        </div><!-- end content -->

                        <div class="blog-title-area">
                            <div class="tag-cloud-single">
                                <span>Tags</span>
                                <small><a href="#" title="">gas</a></small>
                                <small><a href="#" title="">Small</a></small>
                                <small><a href="#" title="">Big</a></small>
                                <small><a href="#" title="">another tag</a></small>
                            </div><!-- end meta -->


                        </div><!-- end title -->
                        <div class="custombox clearfix">

                        </div><!-- end custom-box -->

                        <hr class="invis1">


                    </div><!-- end page-wrapper -->
                </div><!-- end col -->

                <div class="col-lg-4">

                    <div class="blog-box">
                        <h4 class="small-title">You may also like</h4>


                        <div class="blog-box">
                            <c:forEach items="${requestScope.ln}" var="l" begin="0" end="1">
                                <c:if test="${l.getCreatedBy()==po.getCreatedBy()}">
                                    <div class="post-media">

                                        <a href="newsdetail?newId=${l.getNewID()}" >
                                            <img class="img-fluid " src="data:image/jpeg;base64,${l.encodedImage} ">
                                            <div class="hovereffect">
                                                <span class=""></span>
                                            </div><!-- end hover -->
                                        </a>
                                    </div><!-- end media -->
                                    <div class="blog-meta">
                                        <h4><a href="newsdetail?newId=${l.getNewID()}" title="" style="color:black ">${l.getNewsTitle()}</a></h4>
                                        <small><a href="newsdetail?newId=${l.getNewID()}" title="">${l.getCreatedDate()}</a></small>
                                    </div><!-- end meta -->
                                </c:if>
                            </c:forEach>
                        </div><!-- end blog-box -->



                    </div>

                </div>
                <!-- end col -->
                <!-- end row -->
            </div><!-- end container -->
        </section>





    </div><!-- end wrapper -->

    <!-- Core JavaScript
    ================================================== -->
    <script src="js/jquery.min.js"></script>
    <script src="js/tether.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>

</body>
</html>
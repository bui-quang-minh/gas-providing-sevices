<%-- 
    Document   : index
    Created on : May 10, 2023, 3:03:05 PM
    Author     : Minh Bui
--%>

<%@page import="model.Product"%>
<%@page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
        <link href="css/promotion.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <fmt:formatDate var="today" value="<%=new java.util.Date()%>" pattern="yyyy-MM-dd" />
        <!-- Latest Blog Section Begin -->
        <section class="latest">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="section-title">
                            <h4 style="  font-family: Arial, sans-serif;
                                font-size: 25px;
                                font-weight: bold;
                                color: #ffFFFF;margin-top:2%;background-color: #F26F21;
                                padding: 0.5%; text-align: left; padding-left: 2%"><i class="fa fa-tag"></i> About Us</h4>
                        </div>
                    </div>
                </div>
                <div class="row" style="padding: 2%">
                    <p>Betrolimex cung cấp đến công trình và dự án các sản phẩm, dịch vụ chất lượng cao và an toàn trên tiêu chí “Nhanh chóng – An toàn – Chất lượng – Hiệu quả”</br>

                        Tầm nhìn và sứ mệnh:</br>

                        Khách hàng của Betrolimex sẽ được hưởng những dịch vụ và chế độ hậu mãi nhanh chóng, kịp thời tương xứng với hình ảnh thương hiệu của Betrolimex. Xây dựng và phát triển đội ngũ nhân viên chuyên nghiệp, cơ sở vật chất hiện đại nhằm cung ứng sản phẩm an toàn và tiện lợi hàng đầu trên thị trường. Đạt được sự tín nhiệm của khách hàng và các đối tác kinh doanh chính là nhân tố quan trọng góp phần vào sự thành công của Công ty chúng tôi.</br><!-- comment -->
                        

                        Lĩnh vực kinh doanh:</br>
                        Hệ thống nhà hàng, khách sạn:</br>

                        Tư vấn, hướng dẫn miễn phí cho khách hàng</br>

                        Lắp đặt hệ thống LPG an toàn, phù hợp cho từng khách hàng để đạt hiệu quả tối đa nhất với chi phí hợp lý</br>

                        Bảo trì, kiểm tra hệ thống định kỳ.</br>

                        Đội ngũ kĩ thuật nhiều kinh nghiệm …</br>

                        Một số đối tác đã làm việc thành công với chúng tôi:</br>

                        Hệ thống trực thuộc Gas:</br>

                        Khách hàng của Betrolimex sẽ được hưởng những dịch vụ và chế độ hậu mãi nhanh chóng, kịp thời tương xứng với hình ảnh thương hiệu của Betrolimex. Xây dựng và phát triển đội ngũ nhân viên chuyên nghiệp, cơ sở vật chất hiện đại nhằm cung ứng sản phẩm an toàn và tiện lợi hàng đầu trên thị trường. Đạt được sự tín nhiệm của khách hàng và các đối tác kinh doanh chính là nhân tố quan trọng góp phần vào sự thành công của Công ty chúng tôi.</br>

                        Hệ thống chuỗi cửa hàng bán lẻ:</br>

                        Hệ thống cửa hàng bán lẻ trải rộng khắp TPHCM.</br>
                        Cam kết đảm bảo chất lượng, bán hàng đúng giá theo quy định của Pháp luật.</br>
                        Đội ngũ nhân viên nhiệt tình, được đào tạo bài bản, chuyên nghiệp để phục vụ khách hàng nhanh chóng, xử lý kịp thời những tình huống phát sinh.</br>

                        1/ Cửa hàng Betrolimex Quận 1</br>
                        Địa chỉ: 65 Cách Mạng Tháng 8, Q1</br>

                        ________________________________</br>

                        2/ Cửa hàng Betrolimex Quận 2</br>
                        Địa chỉ: Đang mở rộng</br>

                        ________________________________</br>

                        3/ Cửa hàng Betrolimex Quận 3</br>
                        Địa chỉ:  Đang mở rộng</br>

                        ________________________________</br>

                        4/ Cửa hàng Betrolimex Quận 4</br>
                        Địa chỉ: 68/3 Hoàng Diệu, Q4</br>

                        ________________________________</br>

                        5/ Cửa hàng Betrolimex Quận 5</br>
                        Địa chỉ: Đang mở rộng</br>

                        ________________________________</br>

                        6/ Cửa hàng Betrolimex Quận 6</br>
                        Địa chỉ:</br>

                        ________________________________</br>

                        7/ Cửa hàng Betrolimex Quận 7</br>
                        Địa chỉ:</br>

                        ________________________________</br>

                        8/ Cửa hàng Betrolimex Quận 8</br>
                        Địa chỉ: 80A Da Nam</br>

                        Địa chỉ: 526 Hưng Phu</br>

                        ________________________________</br>

                        9/ Cửa hàng Betrolimex Quận 9</br>
                        Địa chỉ: Lê Văn Việt, Q9</br>

                        ________________________________</br>

                        10/ Cửa hàng Betrolimex Quận 10</br>
                        Địa chỉ: 74 Nguyễn Chí Thanh, Q10</br>

                        Đia chỉ: 531f Cách Mạng Tháng 8</br>

                        ________________________________</br>

                        11/ Cửa hàng Betrolimex Quận 11</br>
                        Địa chỉ:</br>

                        ________________________________</br>

                        12/ Cửa hàng Betrolimex Quận 12</br>
                        Địa chỉ: Nguyễn Văn Quá, Q12</br>

                        _______________________________________</br>

                        13/ Cửa hàng Betrolimex Quận Tân Bình</br>
                        Địa chỉ:</br>
                        ĐT:</br>

                        _______________________________________</br>

                        14/ Cửa hàng Betrolimex Quận Tân Phú</br>
                        Địa chỉ: Cây Keo, Q. Tân Phú</br>

                        _______________________________________</br>

                        15/ Cửa hàng Betrolimex Quận Bình Tân</br>
                        Địa chỉ: Bà Hom</br>
                        Địa chỉ: Tinh lo 10</br>

                        _________________________________________</br>

                        16/ Cửa hàng Betrolimex Quận Phú Nhuận</br>
                        Địa chỉ: 81 Thich Quảng Đức</br>
                        ĐT:</br>

                        __________________________________________</br>

                        17/ Cửa hàng Betrolimex Quận Bình Thạnh</br>
                        Địa chỉ: 171 Nguyễn Xi</br>
                        ĐT: Huỳnh Đinh Hai</br>

                        _____________________________________</br>

                        18/ Cửa hàng Betrolimex Quận Gò Vấp</br>
                        Địa chỉ:</br>

                        ___________________________________________</br>

                        19/ Cửa hàng Betrolimex, chi nhánh Hóc Môn</br>
                        Địa chỉ: Tô Ký</br>

                        Công ty Cổ phần Betrolimex</br>

                        Giấy Chứng nhận đăng ký Doanh nghiệp do Sở Kế hoạch và Đầu tư TPHCM cấp:</br>

                        Mã số thuế : 1900 không có</br>

                        Trụ sở chính :  74 Nguyễn Chí Thanh, phường 02, quận 10, TP Hồ Chí Minh</br>

                        Điện thoại : 0399955598</br>

                        Email     : swpgroup6@gmail.com</br>

                        Website : <a href="#">Go</a></br>

                        Chúng tôi sẽ mang đến sự hài lòng cho mọi gia đình Việt! </p>
                </div>
            </div>
        </section>
        <%@include file="footer.jsp" %>
        <!-- Footer Section End -->
        <style>
            .shopping-cart {
                position: fixed;
                z-index: 10;
                right: 1rem;
                bottom: 2rem;
                width: 4rem;
                height: 4rem;
                background-color: wheat;
                border-radius: 50%;
                outline: .5rem solid transparent;
                text-align:center;
                line-height: 4rem;
                cursor: pointer;
                box-shadow: 0 .2rem .6rem black;
                transition: .5s ease-in-out;
            }
            .shopping-cart.active {
                width: 8rem;
                border-radius: .8rem;
            }
            .shopping-cart.active > span{
                margin-left: -4rem;
            }
            .animate{
                animation: fly_to_cart 1s ease-in;
                z-index: 100;
                border-radius: inherit;
            }
            /* left then top */
            @keyframes fly_to_cart {
                0% {
                    transform: translate(0, 0) scale(1);
                }
                100%{
                    transform:translate(var(--left), var(--top)) scale(0);
                }
            }
            .disapear{
                animation: fadeIn 2s;
            }
            @keyframes fadeIn {
                0% {
                    opacity: 0;
                }
                100% {
                    opacity: 1;
                }
            }
        </style>
        <script>
            function loadfirst() {
                fillter('best-seller');
            }

            window.addEventListener("load", loadfirst, {once: true});
            function fillter(fillter) {
                var elementshide = document.querySelectorAll('[class*="list"]');
                for (var i = 0; i < elementshide.length; i++) {
                    elementshide[i].classList.add("disapear");
                    elementshide[i].style.display = "none";
                }
                //            console.log(fillter);
                var elementshow = document.querySelectorAll('[class*="' + fillter + '"]');

                for (var j = 0; j < elementshow.length; j++) {
                    elementshow[j].style.display = "block";
                }
            }
            var quantity = ${sessionScope.quantity}
            function AddToCartAnimation(target, index, pic) {
                var img = document.getElementsByClassName('product__item__pic ' + target + ' set-bg set-bg3 ' + index);

                var cart = document.getElementById("cart-icon");
                var cartsize = document.getElementById("cart-icon");
                cartsize.classList.add("active");
                quantity++;
                document.getElementById("quantity").innerHTML = quantity;
                var firstImg = document.getElementById(pic);
                let top = 0;
                let left = 0;
                left = cart.getBoundingClientRect().left - (cart.offsetWidth / 2 + firstImg.getBoundingClientRect().left + firstImg.offsetWidth) + 200;
                top = cart.getBoundingClientRect().top - (cart.offsetHeight / 2 + firstImg.getBoundingClientRect().top + firstImg.offsetHeight) + 250;
                for (var i = 0; i < img.length; i++) {
                    img[i].classList.add("animate");

                    img[i].style.cssText += '--left: ' + left + 'px;--top: ' + top + 'px;';
                }

                var item = document.getElementsByClassName('product__item ' + target);
                for (var i = 0; i < item.length; i++) {
                    item[i].style.overflow = "visible";
                }
                setTimeout(function () {
                    cartsize.classList.remove("active");

                    for (var k = 0; k < img.length; k++) {
                        img[k].classList.remove('animate');
                    }
                    for (var i = 0; i < item.length; i++) {
                        item[i].style.overflow = "hidden";
                    }
                }, 1000);
            }
            function addToCart(pid) {

                const xhttp = new XMLHttpRequest();
                // You can add additional code here to perform other actions, such as updating the cart counter or making an AJAX request.
                var elements = document.getElementsByClassName('add_cart ' + pid);
                for (var i = 0; i < elements.length; i++) {
                    elements[i].innerHTML = "ADDED";
                }


                xhttp.open("POST", "allproduct", true);
                xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                xhttp.send("productID=" + pid);
            }
        </script>
        <!-- Js Plugins -->
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
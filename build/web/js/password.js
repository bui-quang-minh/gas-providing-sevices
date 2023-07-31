
/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const ipnElement = document.querySelector('#ipnPassword');
const btnElement = document.querySelector('#btnPassword');

btnElement.addEventListener('click', function () {
    const currentType = ipnElement.getAttribute('type');
    ipnElement.setAttribute('type',
            currentType === 'password' ? 'text' : 'password'
            );
});

$(document).ready(function () {
    $('input[type="radio"]').change(function () {
        // Xóa lớp 'active' khỏi tất cả các label
        $('label.btn').removeClass('active');

        // Thêm lớp 'active' cho label được chọn
        if ($(this).is(':checked')) {
            $(this).closest('label.btn').addClass('active');
        }
    });
});
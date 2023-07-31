/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on the button, open the modal
//$('.add-cart').onclick = function () {
//    console.log("hello");
//    modal.style.display = "block";
//};

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
}
;
// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
};

$(document).ready(function () {
    console.log("hello");
    $('#myModal').on('show.bs.modal', function (event) {
        var button = $(event.relatedTarget); // Button that triggered the modal
        var id = button.data('id'); // Extract data from the button
        var name = button.data('name');
        var description = button.data('description');
        var modal = $(this);
        
        // Set the content of the modal dialog
        modal.find('.modal-title').text(name);
        modal.find('.modal-body #modal-content').text(description);
        
        
    });
    $('.add-cart').on('click', function () {
            console.log("hello");
            modal.style.display = "block";
        });
});



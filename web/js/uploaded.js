/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const image_input = document.querySelector("#image_input");
var uploaded_image = "";

image_input.addEventListener("change",function(){
    const reader = new FileReader();
    reader.addEventListener("load",() => {
        uploaded_image = reader.result;
        document.querySelector("#display_image").style.backgroundImage = `url(${uploaded_image})`;
        
    });
    reader.readAsDataURL(this.files[0]);
});



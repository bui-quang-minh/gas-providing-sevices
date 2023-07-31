/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "SearchAllProduct", urlPatterns = {"/searchallproduct"})
public class SearchAllProduct extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchString = request.getParameter("searchString");
        ProductDAO pd = new ProductDAO();
        ProductImageDAO pid = new ProductImageDAO();
        List<ProductImage> lpi = pid.getAllImage();
        List<Product> lp = pd.getByName(searchString);
        PrintWriter out = response.getWriter();
        for (Product o : lp) {
            out.println("<div class=\"col-lg-3 col-md-6 col-sm-6 col-md-6 col-sm-6 mix all-product\">\n"
                    + "                                <div class=\"product__item\">\n");
            for (ProductImage i : lpi) {
                if (i.getProductID() == o.getProductID() && i.getImageType().equals("thumbnail")){
                out.println("<div class=\"product__item__pic set-bg set-bg3\" data-setbg=\"data:image/jpeg;base64,"+i.getEncodedProductImage()+"\" style=\"background-image: url(&quot;data:image/jpeg;base64, "+i.getEncodedProductImage()+" &quot;);\">\n");
                }
            }
            out.println("                                        <ul class=\"product__hover\">\n"
                    + "                                            <li><a href=\"productdetail?productId=\"><img src=\"img/icon/heart.png\" alt=\"\"></a></li>\n"
                    + "                                            <li><a href=\"productdetail?productId=" + o.getProductID() + "\"><img src=\"img/icon/compare.png\" alt=\"\"> <span>Compare</span></a></li>\n"
                    + "                                            <li><a href=\"productdetail?productId=" + o.getProductID() + "\"><img src=\"img/icon/search.png\" alt=\"\"></a></li>\n"
                    + "                                        </ul>\n"
                    + "                                    </div>\n"
                    + "                                    <div class=\"product__item__text\">\n"
                    + "                                        <h6>" + o.getProductName() + "</h6>\n"
                    + "                                        <a class=\"add-cart\" id=\"" + o.getProductID() + "\" onclick=\"addToCart('" + o.getProductID() + "')\">+ Add To Cart</a>\n"
                    + "                                        <div class=\"rating\">\n"
                    + "                                            <i class=\"fa fa-star-o\"></i>\n"
                    + "                                            <i class=\"fa fa-star-o\"></i>\n"
                    + "                                            <i class=\"fa fa-star-o\"></i>\n"
                    + "                                            <i class=\"fa fa-star-o\"></i>\n"
                    + "                                            <i class=\"fa fa-star-o\"></i>\n"
                    + "                                        </div>\n"
                    + "                                        <h5>" + o.getPrice() + "đ</h5>\n"
                    + "                                    </div>\n"
                    + "                                </div>\n"
                    + "\n"
                    + "                            </div>");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

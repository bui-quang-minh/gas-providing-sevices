/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import dal.CustomerDAO;
import dal.DiscountDAO;
import dal.ManufacturerDAO;
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
import model.Category;
import model.Comment;
import model.Discount;
import model.Manufacturer;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name="ProductDetail", urlPatterns={"/productdetail"})
public class ProductDetail extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductDetail</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductDetail at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("productId");
        ProductDAO dao = new ProductDAO();
        CategoryDAO cateDao = new CategoryDAO();
        
        Product product = dao.getProductByID(productId);
        List<Product> productList = dao.getByCategory(Integer.toString(product.getCategoryID()));
        ProductImageDAO pid = new ProductImageDAO();
        List<ProductImage> listImage = pid.getImageByID(productId);
        List<ProductImage> listI = pid.getAllImage();
        ManufacturerDAO mandao = new ManufacturerDAO();
        List<Comment> commentList = dao.getAllComment(Integer.parseInt(productId));        
        Manufacturer man = mandao.getMan(product.getManufacturerID());
        List<Category> cateList = cateDao.getAll();
        Product pro = dao.getProductbyPrice();
        DiscountDAO dd = new DiscountDAO();
        List<Discount> discount = dd.getAll();
        request.setAttribute("allDiscount", discount);
        request.setAttribute("pro", pro);
        request.setAttribute("listI", listI);
        request.setAttribute("listImage", listImage);
        request.setAttribute("productList", productList);
        request.setAttribute("comments", commentList);
        request.setAttribute("product", product);
        request.setAttribute("manufacture", man);
        request.setAttribute("cateList", cateList);
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
    }
    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
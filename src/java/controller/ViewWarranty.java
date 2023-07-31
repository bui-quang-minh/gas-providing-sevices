/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ProductDAO;
import dal.ProductImageDAO;
import dal.WarrantyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Product;
import model.ProductImage;

/**
 *
 * @author An
 */
@WebServlet(name="ViewWarranty", urlPatterns={"/warranty"})
public class ViewWarranty extends HttpServlet {
   
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
            out.println("<title>Servlet ViewWarranty</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewWarranty at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Customer cus = (Customer) request.getSession().getAttribute("account");
        WarrantyDAO wadao = new WarrantyDAO();
        List<model.Warranty> list = new ArrayList<>();
        ProductDAO productdao = new ProductDAO();
        ProductImageDAO pid = new ProductImageDAO();
        
        List<ProductImage> productImageList = pid.getAllImage();
        request.setAttribute("productImageList", productImageList);
        List<Product> listProduct = productdao.getAll();
        request.setAttribute("productList", listProduct);
        request.setAttribute("ID", cus);
        try {
            list = wadao.getWarrantybyCustomerID(cus.getId());
            request.setAttribute("warrantylist", list);

        } catch (Exception e) {
            System.out.println("You have encounter error:"+e);
        }
        request.getRequestDispatcher("customerWarranty.jsp").forward(request, response);
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

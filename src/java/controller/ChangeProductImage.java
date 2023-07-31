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
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import model.Employee;
import model.Product;
import model.ProductImage;
import org.apache.commons.io.IOUtils;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "ChangeProductImage", urlPatterns = {"/changeproductimage"})
public class ChangeProductImage extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangeProuctImage</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeProuctImage at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        ProductImageDAO pid = new ProductImageDAO();
        ProductImage pi = new ProductImage();
        int productID = Integer.parseInt(request.getParameter("productID"));
        int change = Integer.parseInt(request.getParameter("change"));
        Employee employee = (Employee) request.getSession().getAttribute("account");
        if (change == 1) {
            pi.setProductID(productID);
            pi.setImageType("thumbnail");
            Part filePart1 = request.getPart("productImage");
            InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
            byte[] productImage = IOUtils.toByteArray(inputStream1);// Read tm inputStream1 = fileParhe input stream into a byte array
            pi.setProductImage(productImage);
            pid.deleteImage(pi);
            pid.updateImage(pi);
        } else if (change == 2) {
            int flag = 1;
            if (ServletFileUpload.isMultipartContent(request)) {
                List<Part> fileParts = (List<Part>) request.getParts(); // Get all uploaded files
                for (Part part : fileParts) {
                    if (part.getName().equals("productSizedImage")) {
                        InputStream inputStream2 = part.getInputStream();// Get the input stream from the file part
                        byte[] productSizedImage = IOUtils.toByteArray(inputStream2);// Read the input stream into a byte array
                        ProductImage pi2 = new ProductImage();
                        pi2.setProductImage(productSizedImage);
                        pi2.setProductID(productID);
                        pi2.setImageType("description");
                        if (flag == 1 ){
                            flag++;
                            pid.deleteImage(pi2);
                        } 
                        pid.updateImage(pi2);
                    }
                }
            }
        }
        
        response.sendRedirect(request.getContextPath() + "/changeproduct?productID=" + productID );
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

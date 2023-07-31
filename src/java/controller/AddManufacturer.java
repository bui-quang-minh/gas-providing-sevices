/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ManufacturerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import model.Employee;
import model.Manufacturer;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name="AddManufacturer", urlPatterns={"/addmanufacturer"})
public class AddManufacturer extends HttpServlet {
   
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
            out.println("<title>Servlet AddManufacturer</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddManufacturer at " + request.getContextPath () + "</h1>");
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
        String role = (String)request.getSession().getAttribute("role");
        
        if (role == null || role.equals("Customer")) {
            response.sendRedirect(request.getContextPath()+"/");
        }

        if (role != null && !role.equals("Admin")) {
            response.sendRedirect("statistic");
        }
        
        if (role != null && role.equals("Admin")) {
        request.getRequestDispatcher("addManufacturer.jsp").forward(request, response);
        }
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
        Employee e = (Employee) request.getSession().getAttribute("account");
        
        ManufacturerDAO md = new ManufacturerDAO();
        Manufacturer m = new Manufacturer();
        m.setCreatedBy(e.getId());
        m.setDescription(request.getParameter("description"));
        m.setManufacturerAddress(request.getParameter("address"));
        m.setManufacturerEmail(request.getParameter("email"));
        m.setManufacturerName(request.getParameter("name"));
        m.setManufacturerPhone(request.getParameter("phonenumber"));
        m.setModifiedBy(e.getId());
        Part filePart1 = request.getPart("image");
        InputStream inputStream1 = filePart1.getInputStream();
        byte[] image = IOUtils.toByteArray(inputStream1);
        m.setManufacturerImage(image);
        
        md.insertManufacturer(m);
        response.sendRedirect("allmanufacturer");
        
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

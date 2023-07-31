/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiscountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Discount;
import model.Employee;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "EditDiscount", urlPatterns = {"/editdiscount"})
public class EditDiscount extends HttpServlet {

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
            out.println("<title>Servlet EditDiscount</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditDiscount at " + request.getContextPath() + "</h1>");
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
        int discountID = Integer.parseInt(request.getParameter("discountID"));
        DiscountDAO dd = new DiscountDAO();
        dd.deleteDiscount(discountID);
        response.sendRedirect(request.getContextPath()+"/alldiscount");
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
        Employee employee = (Employee) request.getSession().getAttribute("account");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int discountID = Integer.parseInt(request.getParameter("discountID"));
        String discountName = request.getParameter("discountName");
        int discountValue = Integer.parseInt(request.getParameter("discountValue"));
        try {
            String raw1 = request.getParameter("startDate");
            Date startDate = dateFormat.parse(raw1);

            String raw2 = request.getParameter("endDate");
            Date endDate = dateFormat.parse(raw2);
            
            int productID = Integer.parseInt(request.getParameter("productID"));
            
            Discount d = new Discount(discountID, discountName, discountValue, productID, startDate, endDate, employee.getId());
            DiscountDAO dd = new DiscountDAO();
            dd.changeDiscount(d);
        } catch (NumberFormatException | ParseException e) {
        }
        response.sendRedirect(request.getContextPath()+"/alldiscount");
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

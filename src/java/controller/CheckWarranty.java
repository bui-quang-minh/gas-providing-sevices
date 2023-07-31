/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.EmployeeDAO;
import dal.ProductDAO;
import dal.WarrantyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Warranty;

/**
 *
 * @author An
 */
@WebServlet(name = "CheckWarranty", urlPatterns = {"/checkwarranty"})
public class CheckWarranty extends HttpServlet {

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
            out.println("<title>Servlet CheckWarranty</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckWarranty at " + request.getContextPath() + "</h1>");
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
        WarrantyDAO wdao = new WarrantyDAO();
        EmployeeDAO edao = new EmployeeDAO();
        ProductDAO pdao = new ProductDAO();
        CustomerDAO cdao = new CustomerDAO();
        request.setAttribute("listwarranty", wdao.getAll());
        request.setAttribute("listcustomer", cdao.getCustomers());
        request.setAttribute("listproduct", pdao.getAll());
        request.setAttribute("employees", edao.getEmployess());
        request.getRequestDispatcher("checkWarranty.jsp").forward(request, response);
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

        WarrantyDAO wa = new WarrantyDAO();
        String status = request.getParameter("status");
        String id = request.getParameter("id");
        String condition = request.getParameter("condition");
        String price = request.getParameter("price");
        wa.changestatus(Integer.parseInt(id), Integer.parseInt(status));
        wa.updateWarrantyConditionAndPrice(Integer.parseInt(id),
                condition,
                Integer.parseInt(price));
        WarrantyDAO wdao = new WarrantyDAO();
        EmployeeDAO edao = new EmployeeDAO();
        ProductDAO pdao = new ProductDAO();
        CustomerDAO cdao = new CustomerDAO();
        request.setAttribute("listwarranty", wdao.getAll());
        request.setAttribute("listcustomer", cdao.getCustomers());
        request.setAttribute("listproduct", pdao.getAll());
        request.setAttribute("employees", edao.getEmployess());
        request.getRequestDispatcher("checkWarranty.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

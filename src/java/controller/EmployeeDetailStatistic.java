/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CustomerDAO;
import dal.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import model.Employee;
import model.Order;

/**
 *
 * @author admin
 */
@WebServlet(name = "EmployeeDetailStatistic", urlPatterns = {"/employeedetailstatistic"})
public class EmployeeDetailStatistic extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("employeeid"));
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        Date from = null;
        Date to = null;
        if (raw_to == null) {
            java.util.Date day = new java.util.Date();
            Calendar c = Calendar.getInstance();
            c.setTime(day);
            c.set(Calendar.MILLISECOND, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            day = c.getTime();
            to = new java.sql.Date(day.getTime());

        }
        if (raw_from == null) {
            CustomerDAO c = new CustomerDAO();
            from = c.getMinDate();
        }
        if (raw_from != null && raw_to != null) {
            to = Date.valueOf(raw_to);
            from = Date.valueOf(raw_from);
        }

        request.setAttribute("from", from);
        request.setAttribute("to", to);
        EmployeeDAO eb = new EmployeeDAO();
        ArrayList<Order> orders = eb.getOrdersbyEmployee(id, from, to);
        Employee e = eb.getEmployeeByID(id);
        request.setAttribute("employee", e);
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("EmployeeDetailStatistic.jsp").forward(request, response);
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
        processRequest(request, response);
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

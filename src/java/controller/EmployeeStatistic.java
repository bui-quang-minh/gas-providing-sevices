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

/**
 *
 * @author admin
 */
@WebServlet(name = "EmployeeStatistic", urlPatterns = {"/employeestatistic"})
public class EmployeeStatistic extends HttpServlet {

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
        String role = (String) request.getSession().getAttribute("role");

        if (role == null || role.equals("Customer")) {
            response.sendRedirect(request.getContextPath() + "/");
        }

        if (role != null && !role.equals("Admin")) {
            response.sendRedirect("statistic");
        }

        if (role != null && role.equals("Admin")) {
            String raw_from = request.getParameter("from");
            String raw_to = request.getParameter("to");
            String raw_eid = request.getParameter("eid");
            Date from = null;
            Date to = null;
            
            if (raw_eid == null || raw_eid.length() == 0) {
                raw_eid = "-1";
            }

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
            int eid = Integer.parseInt(raw_eid);
            request.setAttribute("eid", eid);
            EmployeeDAO eb = new EmployeeDAO();
            ArrayList<Employee> employeeses = eb.getEmployess();
            request.setAttribute("employeeses", employeeses);

            ArrayList<Employee> employees = eb.getEmployeessByDates(from, to, eid);
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("EmployeeStatistic.jsp").forward(request, response);
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
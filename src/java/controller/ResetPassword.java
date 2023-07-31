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
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "ResetPassword", urlPatterns = {"/resetpassword"})
public class ResetPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String regexPassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";

    public String checkPassword(String password) {
        String message2;
        if (password == null || password.equals("")) {
            message2 = "Password must not be empty";
            return message2;
        }

        if (!password.matches(regexPassword)) {
            message2 = "Password must have at least one numeric character, one lowercase character, one uppercase character, one special symbol among @#$% and 8 - 20 characters";
            return message2;
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("newPassword.jsp").forward(request, response);
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

        HttpSession mySession = request.getSession();
        String newPassword = request.getParameter("password");
        String confPassword = request.getParameter("confpassword");
        String email = (String) mySession.getAttribute("email");

        String message2 = checkPassword(newPassword);
        request.setAttribute("message2", message2);

        String message3 = null;
        if (!confPassword.equals(newPassword) || confPassword == null || confPassword.equals("")) {
            message3 = "Password does not match!";
            request.setAttribute("message3", message3);
        }

        if (message2 != null || message3 != null) {
            request.getRequestDispatcher("newPassword.jsp").forward(request, response);
        } else {
            CustomerDAO dbc = new CustomerDAO();
            EmployeeDAO dbe = new EmployeeDAO();

            if (dbc.getCustomer(email) != null) {
                dbc.resetPassword(email, newPassword);
            }

            if (dbe.getEmployee(email) != null) {
                dbe.resetPassword(email, newPassword);
            }

            mySession.setAttribute("status", "Reset Password Successfully! Please login again!");
            response.sendRedirect("login");
        }

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
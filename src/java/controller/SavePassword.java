/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.Employee;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "SavePassword", urlPatterns = {"/savepassword"})
public class SavePassword extends HttpServlet {

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
            out.println("<title>Servlet SavePassword</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SavePassword at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    public static String toSHA1(String password) {
        String salt = "asjrlkmcoewjtjle;oxqskjhdafevoprlsvmx@123";
        String result = null;

        password = password + salt;
        try {
            byte[] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = org.apache.commons.codec.binary.Base64.encodeBase64String(md.digest(dataBytes));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
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
        EmployeeDAO ed = new EmployeeDAO();
        String msg;
        if (request.getSession().getAttribute("account") instanceof Employee) {
            Employee emp = (Employee) request.getSession().getAttribute("account");
            String oldpsw = request.getParameter("password");
            oldpsw = toSHA1(oldpsw);
            String newpsw = request.getParameter("newpassword");
            String confirmpsw = request.getParameter("confirm");
            if (!oldpsw.equals(emp.getPassword())) {
                msg = "Wrong password";
            } else {
                if (!newpsw.equals(confirmpsw)) {
                    msg = "Password miss match";
                } else {
                    msg = "Success!";
                    ed.changePassword(toSHA1(newpsw), emp.getId());
                }
            }
            request.setAttribute("success", msg);
            request.getRequestDispatcher("changeEmployeePassword.jsp").forward(request, response);
        }

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

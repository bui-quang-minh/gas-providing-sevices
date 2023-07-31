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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import model.Customer;
import model.Employee;

/**
 *
 * @author admin
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet CustomerLoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerLoginServlet at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static String toSHA1 (String password)
    {
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
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        password = toSHA1(password);
        String remember = request.getParameter("rem");

        //Set cookies: username, password, rememner
        Cookie cu = new Cookie("cusername", username);
        Cookie cp = new Cookie("cpassword", request.getParameter("password"));
        Cookie cr = new Cookie("cremember", remember);

        if (remember != null) {
            cu.setMaxAge(60 * 60 * 24 * 365);
            cp.setMaxAge(60 * 60 * 24 * 365);
            cr.setMaxAge(60 * 60 * 24 * 365);

            //Lưu vào browser
            response.addCookie(cu);
            response.addCookie(cp);
            response.addCookie(cr);
        }

        CustomerDAO dbc = new CustomerDAO();
        EmployeeDAO dbe = new EmployeeDAO();
        

        Customer customer = dbc.getCustomer(username, password);
        Employee employee = dbe.getEmployee(username, password);

        if (customer == null && employee == null) {
            request.setAttribute("username", username);
            request.setAttribute("notice", "Incorrect username or password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } else if (customer != null && employee == null ) {

            request.getSession().setAttribute("account", customer);
            request.getSession().setAttribute("role", "Customer");
            response.sendRedirect(request.getContextPath()+"/");
            
        } else if (customer == null && employee != null && employee.getDesciption().equals("Active")) {

            request.getSession().setAttribute("account", employee);
            if(employee.getRole().equals("Admin"))
            {
                request.getSession().setAttribute("role", "Admin");
            }
            else
            {
                request.getSession().setAttribute("role", "Employee");
            }
            response.sendRedirect("statistic");
        
        } else if (customer == null && employee != null && ((employee.getDesciption().equals("Inactive") || employee.getDesciption().equals("Suspended")))) {

            request.setAttribute("message", "Your account has been locked! Please contact the admin to unlock your account!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
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
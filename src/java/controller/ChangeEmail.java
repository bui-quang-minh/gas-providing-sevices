/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.EmployeeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import model.Employee;

/**
 *
 * @author minh
 */
@WebServlet(name = "ChangeEmail", urlPatterns = {"/changeemail"})
public class ChangeEmail extends HttpServlet {

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
            out.println("<title>Servlet changeEmail</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet changeEmail at " + request.getContextPath() + "</h1>");
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
    int myInt = 0;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") instanceof Customer) {
            try {
                SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                myInt = sr.nextInt(900000) + 100000;
                Customer account = (Customer) request.getSession().getAttribute("account");
                SendingMail sender = new SendingMail();
                sender.Sendmail(account.getEmail(), Integer.toString(myInt));
                request.getRequestDispatcher("changeEmail.jsp").forward(request, response);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (request.getSession().getAttribute("account") instanceof Employee) {
            try {
                SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
                myInt = sr.nextInt(900000) + 100000;
                Employee account = (Employee) request.getSession().getAttribute("account");
                SendingMail sender = new SendingMail();
                sender.Sendmail(account.getEmail(), Integer.toString(myInt));
                request.getRequestDispatcher("changeEmployeeEmail.jsp").forward(request, response);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            response.sendRedirect(request.getContextPath());
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
        if (request.getSession().getAttribute("account") != null && request.getSession().getAttribute("account") instanceof Customer) {
            String check = request.getParameter("submit");
            if (check.compareTo("Sign Out") == 0) {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Customer cus = (Customer) request.getSession().getAttribute("account");
                String pin = request.getParameter("pin");
                String nEmail = request.getParameter("newEmail");
                String message;
                if (pin.compareTo(Integer.toString(myInt)) == 0) {
                    CustomerDAO dao = new CustomerDAO();
                    try {
                        SecureRandom sr;
                        try {
                            sr = SecureRandom.getInstance("SHA1PRNG");
                            myInt = sr.nextInt(900000) + 100000;
                        } catch (NoSuchAlgorithmException ex) {
                            Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        message = "Change Email successful !";
                        Customer account = new Customer(cus.getId(), cus.getFirstname(), cus.getLastname(), cus.getAddress(), cus.getPhone(), nEmail, cus.getStatus(), cus.getImage(), cus.getEncodedImage(), cus.getDob(), cus.getDesciption(), cus.getCreatedDate(), cus.getUsername(), cus.getPassword());
                        dao.updateCustomer(account);
                        request.getSession().setAttribute("account", account);
                        request.setAttribute("message", message);
                        request.getRequestDispatcher("changeEmail.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    message = "The Pin Code is not correct !";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("changeEmail.jsp").forward(request, response);
                }
            }
        } else if (request.getSession().getAttribute("account") instanceof Employee) {
            Employee emp = (Employee) request.getSession().getAttribute("account");
            String pin = request.getParameter("pin");
            String nEmail = request.getParameter("newEmail");
            String message;
            if (pin.compareTo(Integer.toString(myInt)) == 0) {
                EmployeeDAO dao = new EmployeeDAO();
                try {
                    SecureRandom sr;
                    try {
                        sr = SecureRandom.getInstance("SHA1PRNG");
                        myInt = sr.nextInt(900000) + 100000;
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(ChangeEmail.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    message = "Change Email successful !";
                    Employee account = new Employee(emp.getId(), emp.getFirstname(), emp.getLastname(), emp.getAddress(), emp.getPhone(), nEmail, emp.getImage(), emp.getEncodedImage(), emp.getDob(), emp.getDesciption(), emp.getHiredDate(), emp.getUsername(), emp.getPassword(), emp.getRole());
                    dao.updateEmployeeEmail(account);
                    request.getSession().setAttribute("account", account);
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("changeEmployeeEmail.jsp").forward(request, response);
                } catch (ServletException | IOException e) {
                }
            } else {
                message = "The Pin Code is not correct !";
                request.setAttribute("message", message);
                request.getRequestDispatcher("changeEmail.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("index.jsp");
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

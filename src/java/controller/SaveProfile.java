/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author minh
 */
@WebServlet(name = "SaveProfile", urlPatterns = {"/saveprofile"})
public class SaveProfile extends HttpServlet {

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
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("account") != null) {
            String check = request.getParameter("submit");
            if (check.compareTo("Sign Out") == 0) {
                request.getSession().invalidate();
                response.sendRedirect("index.jsp");
            } else {
                Customer account = (Customer) request.getSession().getAttribute("account");
                String password = request.getParameter("password");
                password = toSHA1(password);
                String newPassword = request.getParameter("newpassword");
                String confirm = request.getParameter("confirm");
                if (password.compareTo(account.getPassword()) != 0) {
                    request.setAttribute("wrongpassword", "You have entered the wrong password !");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                } else {
                    if (newPassword.compareTo(confirm) != 0) {
                        request.setAttribute("wrongconfirm", "The confirm password is incorrect !");
                        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    } else {
                        CustomerDAO dao = new CustomerDAO();
                        try {
                            dao.updateCustomerPassword(new Customer(account.getId(), account.getFirstname(), account.getLastname(), account.getAddress(), account.getPhone(), account.getEmail(), account.getStatus(), account.getImage(), account.getEncodedImage(), account.getDob(), account.getDesciption(), account.getCreatedDate(), account.getUsername(), toSHA1(newPassword)));
                        } catch (SQLException ex) {

                        }
                        request.getSession().setAttribute("account", account);
                        request.setAttribute("success", "Change Password success !");
                        request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    }
                }
            }
        } else {
            response.sendRedirect("index.jsp");
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
        if (request.getSession().getAttribute("account") != null) {
            String check = request.getParameter("submit");
            if (check.compareTo("Sign Out") == 0) {
                request.getSession().invalidate();
                response.sendRedirect("/swp/");
            } else {
                Customer account = (Customer) request.getSession().getAttribute("account");
                String fname = request.getParameter("firstname");
                String lname = request.getParameter("lastname");
                CustomerDAO dao = new CustomerDAO();
                Date dob = Date.valueOf(request.getParameter("dob"));
                byte[] Image;
                if (request.getPart("image") != null) {
                    Part filePart2 = request.getPart("image");
                    InputStream inputStream2 = filePart2.getInputStream();// Get the input stream from the file part
                    Image = IOUtils.toByteArray(inputStream2);// Read the input stream into a byte array
                } else {
                    Image = dao.getCustomer(account.getUsername(), account.getPassword()).getImage();
                }

                String encodedImage = Base64.getEncoder().encodeToString(Image);

                Customer cus = new Customer(account.getId(), fname, lname, account.getAddress(), account.getPhone(), account.getEmail(), account.getStatus(), Image, encodedImage, dob, account.getDesciption(), account.getCreatedDate(), account.getUsername(), account.getPassword());

                request.getSession().setAttribute("account", cus);
                try {
                    dao.updateCustomer(cus);
                } catch (SQLException ex) {
                    Logger.getLogger(SaveProfile.class.getName()).log(Level.SEVERE, null, ex);
                }
                response.sendRedirect("profile");
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import model.Customer;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author admin
 */
@WebServlet(name = "ConfirmEmail", urlPatterns = {"/confirmemail"})
public class ConfirmEmail extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("confirmemail.jsp").forward(request, response);

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
        
        String otp_raw = request.getParameter("otp");
        int value;
        
        
            value = Integer.parseInt(otp_raw);
        
        
        int otp = (int) request.getSession().getAttribute("otp");

        if (value == otp) {
            
            HttpSession mySession = request.getSession();              
            Part filePart1 = (Part) mySession.getAttribute("image");
            InputStream inputStream1 = filePart1.getInputStream();
            byte[] image = IOUtils.toByteArray(inputStream1);
            
            CustomerDAO db = new CustomerDAO();
            Customer c = new Customer();
            c.setUsername((String)mySession.getAttribute("username"));
            c.setPassword(toSHA1((String)mySession.getAttribute("password")));          
            c.setFirstname((String)mySession.getAttribute("firstname"));
            c.setLastname((String)mySession.getAttribute("lastname"));
            c.setAddress((String)mySession.getAttribute("address"));
            c.setPhone((String)mySession.getAttribute("phone"));
            c.setEmail((String)mySession.getAttribute("email"));
            c.setDob(Date.valueOf((String)mySession.getAttribute("dob")));
            c.setImage(image);
            c.setStatus("New customer");

            db.insertCustomer(c);
            
            if(request.getSession().getAttribute("account")!= null)
            {
                response.sendRedirect("listcustomer");
            }
            else
            {
                request.getSession().setAttribute("status", "Sign Up Betrolimex successfully! Please login again to be a customer!");
                response.sendRedirect("login");
            }
            
        }
        
        else {
            request.setAttribute("message", "Wrong OTP! Please try again!");
            request.getRequestDispatcher("confirmemail.jsp").forward(request, response);

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
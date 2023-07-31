/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.EmployeeDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import model.Customer;
import model.Employee;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author admin
 */
@WebServlet(name = "AddEmployee", urlPatterns = {"/addemployee"})
public class AddEmployee extends HttpServlet {

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
        
        String role = (String)request.getSession().getAttribute("role");
        
        if (role == null || role.equals("Customer")) {
            response.sendRedirect(request.getContextPath()+"/");
        }

        if (role != null && !role.equals("Admin")) {
            response.sendRedirect("statistic");
        }
        
        if (role != null && role.equals("Admin")) {
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
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
    public static final String regularExpression = "^[A-Za-z_][A-Za-z0-9_]{7,29}$";
    public static final String regexPassword = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
    public static final String regexPhone = "^\\+(?:[0-9] ?){6,14}[0-9]$";
    public static final String regexEmail = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public String checkUsername(String username) {
        String message1;
        CustomerDAO dbc = new CustomerDAO();
        String cususername = dbc.getCustomerUsername(username);

        EmployeeDAO dbe = new EmployeeDAO();
        String emusername = dbe.getEmployeeUsername(username);

        if (cususername != null || emusername != null) {
            message1 = "Username is exist";
            return message1;
        }

        if (username == null || username.equals("")) {
            message1 = "Username must not be empty";
            return message1;
        }

        if (!username.matches(regularExpression)) {
            message1 = "Username can only contain alphanumeric character and underscores and have 8-30 characters";
            return message1;
        }
        return null;
    }

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

    public String checkPhone(String phone) {
        String message7;
        if (phone == null || phone.equals("")) {
            message7 = "Phone number must not be empty";
            return message7;
        }

        if (!phone.matches(regexPhone)) {
            message7 = "Phone number is invalid";
            return message7;
        }
        return null;
    }

    public String checkEmail(String email) {
        String message8;
        CustomerDAO dbc = new CustomerDAO();
        Customer c = dbc.getCustomer(email);

        EmployeeDAO dbe = new EmployeeDAO();
        Employee e = dbe.getEmployee(email);

        if (c != null || e != null) {
            message8 = "Email has been registered!";
            return message8;
        }

        if (email == null || email.equals("")) {
            message8 = "Email must not be empty";
            return message8;
        }

        if (!email.matches(regexEmail)) {
            message8 = "Email is invalid";
            return message8;
        }
        return null;
    }

    public String checkDOB(String dob) {
        String message9;
        if (dob == null || dob.equals("")) {
            message9 = "Date of Birth must not be empty";
            return message9;
        }

        Date dateOfBirth = Date.valueOf(dob);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateOfBirth);
        Calendar now = Calendar.getInstance();
        int age = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH) < cal.get(Calendar.MONTH)
                || (now.get(Calendar.MONTH) == cal.get(Calendar.MONTH) && now.get(Calendar.DAY_OF_MONTH) < cal.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        if (age < 18) {
            message9 = "Age must not be smaller than 18";
            return message9;
        }

        return null;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordcf = request.getParameter("passwordcf");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = request.getParameter("phonenumber");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        Part filePart1 = request.getPart("image");

        //--Validation--
        String message1 = checkUsername(username);
        request.setAttribute("message1", message1);
        request.setAttribute("username", username);

        String message2 = checkPassword(password);
        request.setAttribute("message2", message2);
        request.setAttribute("password", password);

        String message3 = null;
        if (!passwordcf.equals(password) || passwordcf == null || passwordcf.equals("")) {
            message3 = "Password does not match!";
            request.setAttribute("message3", message3);
        }
        request.setAttribute("passwordcf", passwordcf);

        String message4 = null;
        if (firstname == null || firstname.equals("")) {
            message4 = "First name must not be empty";
            request.setAttribute("message4", message4);
        }
        request.setAttribute("firstname", firstname);

        String message5 = null;
        if (lastname == null || lastname.equals("")) {
            message5 = "Last name must not be empty";
            request.setAttribute("message5", message5);
        }
        request.setAttribute("lastname", lastname);

        String message6 = null;
        if (address == null || address.equals("")) {
            message6 = "Address must not be empty";
            request.setAttribute("message6", message6);
        }
        request.setAttribute("address", address);

        String message7 = checkPhone(phone);
        request.setAttribute("message7", message7);
        request.setAttribute("phone", phone);

        String message8 = checkEmail(email);
        request.setAttribute("message8", message8);
        request.setAttribute("email", email);

        String message9 = checkDOB(dob);
        request.setAttribute("message9", message9);
        //request.setAttribute("dob", dob);
        request.setAttribute("dob", dob);

        InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
        int maxSizeInBytes = 20 * 1024 * 1024; // Kích thước tối đa: 20MB
        int fileSize = inputStream1.available();
        if (fileSize > maxSizeInBytes) {
            request.setAttribute("message10", "Size of image must be < 20MB");
        }
        request.setAttribute("image", filePart1);

        if (message1 != null || message2 != null || message3 != null || message4 != null
                || message5 != null || message6 != null || message7 != null || message8 != null
                || message9 != null) {
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
        } else {

            byte[] image = IOUtils.toByteArray(inputStream1);

            EmployeeDAO db = new EmployeeDAO();
            Employee e = new Employee();

            e.setUsername(username);
            e.setPassword(password);
            e.setFirstname(firstname);
            e.setLastname(lastname);
            e.setAddress(address);
            e.setPhone(phone);
            e.setEmail(email);
            e.setDob(Date.valueOf(dob));
            e.setImage(image);
            e.setRole("Employee");
            e.setDesciption("Active");
            db.insertEmployee(e);

            request.getSession().setAttribute("status1", "Register employee for Betrolimex successfully!");
            response.sendRedirect("listemployee");

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
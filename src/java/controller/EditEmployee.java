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
import java.sql.Date;
import java.util.Calendar;
import model.Employee;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "EditEmployee", urlPatterns = {"/editemployee"})
public class EditEmployee extends HttpServlet {

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
            out.println("<title>Servlet EditEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditEmployee at " + request.getContextPath() + "</h1>");
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
        String role = (String) request.getSession().getAttribute("role");

        if (role == null || role.equals("Customer")) {
            response.sendRedirect(request.getContextPath()+"/");
        }

        if (role != null && !role.equals("Admin")) {
            response.sendRedirect("statistic");
        }

        if (role != null && role.equals("Admin")) {
            int id = Integer.parseInt(request.getParameter("employeeid"));
            EmployeeDAO db = new EmployeeDAO();
            Employee e = db.getEmployeeByID(id);
            request.setAttribute("employee", e);
            request.getRequestDispatcher("EmployeeDetail.jsp").forward(request, response);
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

        int employeeid = Integer.parseInt(request.getParameter("employeeid"));

        Date hireddate = Date.valueOf(request.getParameter("hireddate"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String address = request.getParameter("address");
        String phone = "+84" + request.getParameter("phonenumber").substring(1);
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");

        String status = request.getParameter("status");

        EmployeeDAO db = new EmployeeDAO();
        Employee e = new Employee();

        e.setUsername(username);
        e.setPassword(password);

        e.setId(employeeid);
        e.setFirstname(firstname);
        e.setLastname(lastname);
        e.setAddress(address);
        e.setPhone(phone);
        e.setEmail(email);
        e.setDob(Date.valueOf(dob));
        e.setDesciption(status);
        e.setHiredDate(hireddate);

        db.updateEmployee(e);

        request.setAttribute("status1", "Update information successfully!");
        doGet(request, response);

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

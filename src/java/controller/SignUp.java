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
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.Customer;
import model.Employee;


/**
 *
 * @author Minh Bui
 */
@WebServlet(name="SignUp", urlPatterns={"/signup"})
public class SignUp extends HttpServlet {

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
            out.println("<title>Servlet SignupController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("signUp.jsp").forward(request, response);
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
    public static final String regexPhone = "^0[0-9]{9}$";
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

        HttpSession mySession = request.getSession();
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
        mySession.setAttribute("username", username);

        String message2 = checkPassword(password);
        request.setAttribute("message2", message2);
        mySession.setAttribute("password", password);

        String message3 = null;
        if (!passwordcf.equals(password) || passwordcf == null || passwordcf.equals("")) {
            message3 = "Password does not match!";
            request.setAttribute("message3", message3);
        }
        mySession.setAttribute("passwordcf", passwordcf);

        String message4 = null;
        if (firstname == null || firstname.equals("")) {
            message4 = "First name must not be empty";
            request.setAttribute("message4", message4);
        }
        mySession.setAttribute("firstname", firstname);

        String message5 = null;
        if (lastname == null || lastname.equals("")) {
            message5 = "Last name must not be empty";
            request.setAttribute("message5", message5);
        }
        mySession.setAttribute("lastname", lastname);

        String message6 = null;
        if (address == null || address.equals("")) {
            message6 = "Address must not be empty";
            request.setAttribute("message6", message6);
        }
        mySession.setAttribute("address", address);

        String message7 = checkPhone(phone);
        request.setAttribute("message7", message7);
        mySession.setAttribute("phone", phone);

        String message8 = checkEmail(email);
        request.setAttribute("message8", message8);
        mySession.setAttribute("email", email);

        String message9 = checkDOB(dob);
        request.setAttribute("message9", message9);
        //request.setAttribute("dob", dob);
        mySession.setAttribute("dob", dob);

        InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
        int maxSizeInBytes = 20 * 1024 * 1024; // Kích thước tối đa: 20MB
        int fileSize = inputStream1.available();
        if (fileSize > maxSizeInBytes) {
            request.setAttribute("message10", "Size of image must be < 20MB");
        }
        mySession.setAttribute("image", filePart1);

        if (message1 != null || message2 != null || message3 != null || message4 != null
                || message5 != null || message6 != null || message7 != null || message8 != null
                || message9 != null) {
            request.getRequestDispatcher("signUp.jsp").forward(request, response);
        } else {

            int otpvalue = 0;
            // sending otp
            Random rand = new Random();
            otpvalue = rand.nextInt(1255650);
            String to = email;// change accordingly
            // Get the session object
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    //return new PasswordAuthentication("ntct.tu2003@gmail.com", "vavjojzeqyqylujo");
                    return new PasswordAuthentication("swpgroup6@gmail.com", "qxhrasdnsdaylems");
                }
            });
            // compose message
            try {
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(email));// change accordingly
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("[BETROLIMEX - CONFIRM REGISTRATION");
                message.setText("Your OTP is: " + otpvalue);
                // send message
                Transport.send(message);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            request.setAttribute("message", "OTP is sent to your email!");
            mySession.setAttribute("otp", otpvalue);
            response.sendRedirect("confirmemail");

        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
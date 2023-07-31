/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ConversationDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Conversation;
import model.Customer;
import model.Employee;
import model.Messages;

/**
 *
 * @author admin
 */
@WebServlet(name = "EmployeeConversation", urlPatterns = {"/employeeconversation"})
public class EmployeeConversation extends HttpServlet {

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
            out.println("<title>Servlet EmployeeConversation</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeConversation at " + request.getContextPath() + "</h1>");
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
        ConversationDAO cb = new ConversationDAO();
        ArrayList<Messages> messages = cb.getMessagesConversation(Integer.parseInt(request.getParameter("conversationid")));
        String output = "<div>";
        ArrayList<Timestamp> sentTimes = new ArrayList<>();
        for (Messages message : messages) {
            if (message.getSenderRole().equals("Customer") && !sentTimes.contains(message.getSentTime())) {
                output += "<div class=\"message-feed media\">";
                output += "<div class=\"pull-left\">";
                output += "<img src=\"data:image/jpeg/png/jpg;base64," + message.getCustomer().getEncodedImage() + "\" alt class=\"img-avatar\">";
                output += "</div>";
                output += "<div class=\"media-body\">";
                output += "<div class=\"mf-content\">";
                output += message.getContent();
                output += "</div>";
                Timestamp sentTime = message.getSentTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm");
                String formattedTime = formatter.format(sentTime);
                output += "<small class=\"mf-date\"><i class=\"fa fa-clock-o\"></i>";
                output += (formattedTime);
                output += "</small>";
                output += "</div>";
                output += "</div>";
            }
            if (!(message.getSenderRole()).equals("Customer") && !sentTimes.contains(message.getSentTime())) {
                output += "<div class=\"message-feed right\">";
                output += "<div class=\"pull-right\">";
                output += "<img src=\"data:image/jpeg/png/jpg;base64," + message.getEmployee().getEncodedImage() + "\" alt class=\"img-avatar\">";
                output += "</div>";
                output += "<div class=\"media-body\">";
                output += "<div class=\"mf-content\">";
                output += message.getContent();
                output += "</div>";
                Timestamp sentTime = message.getSentTime();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm");
                String formattedTime = formatter.format(sentTime);
                output += "<small class=\"mf-date\"><i class=\"fa fa-clock-o\"></i>";
                output += formattedTime;
                output += "</small>";
                output += "</div>";
                output += "</div>";
            }
            sentTimes.add(message.getSentTime());
        }
        output += "</div>";
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
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
                Employee e = (Employee)request.getSession().getAttribute("account");
                int employeeid = e.getId();
                int conversationid = Integer.parseInt(request.getParameter("conversationid"));       
               String message = request.getParameter("message");
                
                Conversation co = new Conversation();
                co.setId(conversationid);
                
                Messages m = new Messages();
                m.setSenderId(employeeid);
                m.setSenderRole("Employee");
                m.setConversation(co);
                m.setContent(message);
                
                ConversationDAO cb = new ConversationDAO();
                cb.sendEmployeeMessage(m);
        
            
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

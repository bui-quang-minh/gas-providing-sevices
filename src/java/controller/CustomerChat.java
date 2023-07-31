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
import java.util.List;
import model.Conversation;
import model.Customer;
import model.Messages;

/**
 *
 * @author admin
 */
@WebServlet(name = "CustomerChat", urlPatterns = {"/customerchat"})
public class CustomerChat extends HttpServlet {

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
            out.println("<title>Servlet CustomerChat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CustomerChat at " + request.getContextPath() + "</h1>");
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
        Customer c = (Customer) request.getSession().getAttribute("account");
        ArrayList<Messages> messages = cb.getMessagesConversation(c.getConversation().getId());
        ArrayList<Timestamp> sentTimes = new ArrayList<>();
        // Chuyển đổi danh sách tin nhắn thành chuỗi HTML
        String output = "</div>";
        for (Messages message : messages) {
            if (message.getSenderRole().equals("Customer") && !sentTimes.contains(message.getSentTime())) {
                output += "<div class=\"messages__item messages__item--operator\">";
                output += (message.getContent());
                output += "</div>";
            }
            if (!(message.getSenderRole()).equals("Customer") && !sentTimes.contains(message.getSentTime())) {
                output += "<div class=\"messages__item messages__item--visitor\">";
                output += (message.getContent());
                output += "</div>";
            }
            sentTimes.add(message.getSentTime());
        }
        output += "</div>";

        response.setContentType("text/html");
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
        ConversationDAO cb = new ConversationDAO();
        Customer c = (Customer) request.getSession().getAttribute("account");
        Messages m = new Messages();
        m.setSenderRole("Customer");
        m.setSenderId(c.getId());
        m.setContent(request.getParameter("message"));
        Conversation co = new Conversation();
        co.setId(c.getConversation().getId());
        m.setConversation(co);
        cb.sendCustomerMessage(m);
        cb.sendAutoEmployeeMessage(c.getConversation().getId());

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

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
import java.util.ArrayList;
import model.Customer;
import model.Employee;
import model.Messages;

/**
 *
 * @author admin
 */
@WebServlet(name = "EmployeeChat", urlPatterns = {"/employeechat"})
public class EmployeeChat extends HttpServlet {

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
            out.println("<title>Servlet EmployeeChat</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmployeeChat at " + request.getContextPath() + "</h1>");
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
        ArrayList<Customer> customers = cb.getCustomerByTime();
        request.setAttribute("customers", customers);

        ArrayList<Messages> messages = cb.getRecentMessages();
        request.setAttribute("messages", messages);
        
        ArrayList<Messages> messageses = cb.getRecentMessages();
            request.setAttribute("messageses", messageses);
        
        Employee e = (Employee) request.getSession().getAttribute("account");
        request.setAttribute("employee", e);
        request.getRequestDispatcher("employeeChat.jsp").forward(request, response);
        
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
        ArrayList<Customer> customers = cb.getCustomerByTime();
        request.setAttribute("customers", customers);

        ArrayList<Messages> messages = cb.getRecentMessages();
        request.setAttribute("messages", messages);
        
        String output = "<div>";
        for (Customer c : customers) {

            String customerMessage = "";
            for (Messages message : messages) {
                if ((message.getConversation().getId()) == (c.getConversation().getId()) && message.getSenderRole().equals("Customer")) {
                    customerMessage = message.getContent();
                    break;
                }
            }
            if (!customerMessage.isEmpty()) {
                output += "<a class=\"list-group-item media\" href=\"#\" data-conversationid=\"" + c.getConversation().getId();
                output +="\" style=\"background-color: #ffcf78;\">";
            }
            if (customerMessage.isEmpty()) {
               output += "<a class=\"list-group-item media\" href=\"#\" data-conversationid=\"" + c.getConversation().getId() + "\">";
            }

            output += "<div class=\"lv-avatar pull-left\">";
            output += "<img src=\"data:image/jpeg/png/jpg;base64,";
            output += (c.getEncodedImage());
            output += "\" alt class=\"img-avatar\" />";
            output += "</div>";
            output += "<div class=\"media-body\">";

            if (customerMessage != null) {
                output += "<div class=\"list-group-item-heading\"><strong>";
                output += (c.getFirstname());
                output += " ";
                output += (c.getLastname());
                output += "</strong></div>";
            } else {
                output += "<div class=\"list-group-item-heading\">";
                output += (c.getFirstname());
                output += " ";
                output += (c.getLastname());
                output +="</div>";
            }

            Messages selectedMessage = null;
            for (Messages message : messages) {
                if ((message.getConversation().getId()) == (c.getConversation().getId())) {
                    selectedMessage = message;
                    break;
                }
            }
            if (selectedMessage != null) {
                String content = selectedMessage.getContent();
                String[] words = content.split(" ");
                for (int i = 0; i < 3 && i < words.length; i++) {
                    if (customerMessage != null) {
                        output += "<small class=\"list-group-item-text c-gray\"><strong>";
                        output += (words[i]);
                        output += " ";
                        output += "</strong></small>";
                    } else {
                        output +="<small class=\"list-group-item-text c-gray\">";
                        output +=(words[i]);
                        output +=" ";
                        output +="</small>";
                    }
                }

            }

            // Các xử lý khác với messages, selectedMessage và words
            output += "</div>";
            output += "</a>";
            output += "</div>";
        }

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(output);
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

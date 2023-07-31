/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CustomerDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import dal.WarrantyDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.Warranty;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "ConfirmOrder", urlPatterns = {"/confirmorder"})
public class ConfirmOrder extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ConfirmOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ConfirmOrder at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        Order o = new Order();

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
        WarrantyDAO wadao = new WarrantyDAO();
        if (request.getSession().getAttribute("account") == null) {
            response.sendRedirect("login");
        } else {
            Customer cus = (Customer) request.getSession().getAttribute("account");
            Map<Integer, Integer> myMap = new HashMap<>();
            Map<String, String> info = new HashMap<>();
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue()[0];
                try {
                    int intValue = Integer.parseInt(value);
                    myMap.put(Integer.parseInt(key), intValue);
                } catch (NumberFormatException e) {
                    info.put(key, value);
                }
            }
            LocalDate date = LocalDate.now();
            Date formatDate = Date.valueOf(date);
            String receiver = info.get("receiver");
            
            String location = info.get("location");
            String total = info.get("total");
            OrderDAO orderdao = new OrderDAO();

            if (receiver.compareTo("") == 0 || location.compareTo("") == 0) {
                response.sendRedirect("cart");
            } else {

                orderdao.confirmOrder(new Order(cus.getId(), formatDate, receiver, location, null, -1, 1, Float.parseFloat(total)));
                request.getSession().setAttribute("listCart", null);
                int orderID = orderdao.lastOrderID();
                for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
                    OrderDetailDAO detail = new OrderDetailDAO();
                    ProductDAO prodao = new ProductDAO();
                    prodao.updateStock(prodao.getProductByID(entry.getKey().toString()), entry.getValue());
                    detail.add(orderID, entry.getKey(), entry.getValue());
                    LocalDate newDate = date.plusMonths(12);
                    Date formatDate2 = Date.valueOf(newDate);
                    wadao.addwarranty(new Warranty(0,entry.getKey() ,cus.getId() , formatDate, formatDate2));
                }
                response.sendRedirect("vieworder");
            }

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
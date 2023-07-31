
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiscountDAO;
import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import model.Comment;
import model.Customer;
import model.Discount;
import model.Order;
import model.OrderDetail;
import model.Product;
import model.ProductImage;

/**
 *
 * @author minh
 */
@WebServlet(name="ViewOrder", urlPatterns={"/vieworder"})
public class ViewOrder extends HttpServlet {

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
            out.println("<title>Servlet ViewOrder</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewOrder at " + request.getContextPath() + "</h1>");
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
        if(request.getSession().getAttribute("account")==null){
            response.sendRedirect("login");
        }else{
        Customer cus = (Customer)request.getSession().getAttribute("account");
        OrderDAO orderdao = new OrderDAO();
        ProductDAO productdao = new ProductDAO();
        OrderDetailDAO detaildao = new OrderDetailDAO();
        List<Order> listOrder = orderdao.getAllByCus(cus.getId());
        List<Product> listProduct = productdao.getAll();
        List<OrderDetail> listDetail = detaildao.getAll();
        DiscountDAO disDAO = new DiscountDAO();
            List<Discount> di = disDAO.getAll();
            Date date = new Date();
            for (Product p : listProduct) {
                for (Discount d : di) {
                    if (p.getProductID() == d.getProductID() && date.after(d.getStartDate()) && date.before(d.getEndDate())) {
                        p.setPrice(p.getPrice() - p.getPrice()*d.getDiscountValue()/100);
                    }
                }
            }
        request.setAttribute("orderList", listOrder);
        request.setAttribute("detailList", listDetail);
        request.setAttribute("productList", listProduct);
        request.getRequestDispatcher("order.jsp").forward(request, response);
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
        if(request.getSession().getAttribute("account")==null){
            response.sendRedirect("login");
        }else{
        Customer cus = (Customer)request.getSession().getAttribute("account");
        String content = request.getParameter("content");
        int rate = Integer.parseInt(request.getParameter("rate"));
        int productID = Integer.parseInt(request.getParameter("productID"));
        ProductDAO dao = new ProductDAO();
        dao.AddComment(new Comment(0,productID, cus.getId(), rate, content));
        response.sendRedirect("vieworder");
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
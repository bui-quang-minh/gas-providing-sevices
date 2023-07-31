/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.DiscountDAO;
import dal.NewsDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author An
 */
@WebServlet(name="DeleteNews", urlPatterns={"/deletenews"})
public class DeleteNews extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet DeleteNews</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteNews at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doPost(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int navigationID = Integer.parseInt(request.getParameter("navigationid"));
        int id = Integer.parseInt(request.getParameter("newID"));
        NewsDAO nd = new NewsDAO();
        HttpSession session = request.getSession();
        NewsDAO ndao = new NewsDAO();
        nd.deleteNewbyID(id);
        session.setAttribute("Facts", ndao.getNewbyNavigationID(1));
        session.setAttribute("Running_text", ndao.getNewbyNavigationID(2));
        session.setAttribute("menu", ndao.getNewbyNavigationID(3));
        session.setAttribute("Banner", ndao.getNewbyNavigationID(4));
        session.setAttribute("listnew", ndao.getNewbyNavigationID(5));
        session.setAttribute("address", ndao.getNewbyNavigationID(6));
        session.setAttribute("footerlist", ndao.getNewbyNavigationID(7));
        request.getRequestDispatcher("changedirection?change="+navigationID).forward(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

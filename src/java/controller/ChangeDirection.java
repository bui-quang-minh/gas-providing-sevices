/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.EmployeeDAO;
import dal.NewsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Navigation;
import model.News;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name="ChangeDirection", urlPatterns={"/changedirection"})
public class ChangeDirection extends HttpServlet {
   
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
        doGet(request, response);
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
        EmployeeDAO edao = new EmployeeDAO();
        NewsDAO ndao = new NewsDAO();
        request.setAttribute("employees", edao.getEmployess());
        if(Integer.parseInt(request.getParameter("change"))== 1 ){
        List<News> listNews = ndao.getNewbyNavigationID(1);
        request.setAttribute("NavID", 1);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        if(Integer.parseInt(request.getParameter("change"))==2  ){
        List<News> listNews = ndao.getNewbyNavigationID(2);
        request.setAttribute("NavID", 2);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        if(Integer.parseInt(request.getParameter("change"))==3  ){
        List<News> listNews = ndao.getNewbyNavigationID(3);
        request.setAttribute("NavID",3);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        if(Integer.parseInt(request.getParameter("change"))==4  ){
        List<News> listNews = ndao.getNewbyNavigationID(4);
        request.setAttribute("NavID",4);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        if(Integer.parseInt(request.getParameter("change"))==5  ){
        List<News> listNews = ndao.getNewbyNavigationID(5);
        request.setAttribute("NavID",5);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        if(Integer.parseInt(request.getParameter("change"))==6  ){
        List<News> listNews = ndao.getNewbyNavigationID(6);
        request.setAttribute("NavID",6);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        if(Integer.parseInt(request.getParameter("change"))==7  ){
        List<News> listNews = ndao.getNewbyNavigationID(7);
        request.setAttribute("NavID",7);
        request.setAttribute("News", listNews);
        request.getRequestDispatcher("changeNews.jsp").forward(request, response);
        }
        
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
        doGet(request, response);
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
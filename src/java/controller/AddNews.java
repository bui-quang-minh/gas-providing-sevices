/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.NewsDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.News;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "AddNews", urlPatterns = {"/addnews"})
public class AddNews extends HttpServlet {

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
            out.println("<title>Servlet addNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addNews at " + request.getContextPath() + "</h1>");
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
        NewsDAO nd = new NewsDAO();
        List<News> ln = nd.getAllNews();
        request.setAttribute("listnews", ln);
        request.getRequestDispatcher("addNews.jsp").forward(request, response);
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
        Employee employee = (Employee) request.getSession().getAttribute("account");
        NewsDAO nd = new NewsDAO();
        News n = new News();
        try {
            String newsTitle = request.getParameter("newsTitle");
            String newsHeading = request.getParameter("newsHeading");

            Part filePart1 = request.getPart("newsImage");
            InputStream inputStream1 = filePart1.getInputStream();
            byte[] newsImage = IOUtils.toByteArray(inputStream1);

            String newsContent = request.getParameter("newsContent");
            int createdBy = employee.getId();
            int modifiedBy = employee.getId();
            String newsStatus = "1";
            n.setNewsTitle(newsTitle);
            n.setNewsHeading(newsHeading);
            n.setNewsContent(newsContent);
            n.setCreatedBy(createdBy);
            n.setModifiedBy(modifiedBy);
            n.setNewsStatus(newsStatus);
            n.setNewsImage(newsImage);
           
            nd.addMainNews(n);
            
        } catch (Exception ex) {
            Logger.getLogger(AddNews.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect("newsmanagement");
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

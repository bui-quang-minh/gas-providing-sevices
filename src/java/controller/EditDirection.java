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
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import model.News;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

/**
 *
 * @author An
 */
@WebServlet(name = "EditDirection", urlPatterns = {"/editDirection"})
public class EditDirection extends HttpServlet {

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
        doPost(request, response);
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
        doPost(request, response);
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
        NewsDAO ndao = new NewsDAO();
        HttpSession session = request.getSession();
        String newID = request.getParameter("newID");
        String navName = request.getParameter("navName");
        String action = request.getParameter("action");
        String navigation = request.getParameter("navigation");
        int navigationID = Integer.parseInt(navigation);
        String title = request.getParameter("title");
        String heading = request.getParameter("heading");
        String content = request.getParameter("content");
        String parent = request.getParameter("parent");
        Employee employee = (Employee) session.getAttribute("account");
        int userid = employee.getId();
        byte[] Image = null;
        if (request.getPart("image") != null) {
            Part filePart2 = request.getPart("image");
            InputStream inputStream2 = filePart2.getInputStream();// Get the input stream from the file part
            Image = IOUtils.toByteArray(inputStream2);// Read the input stream into a byte array
            String encodedImage = Base64.getEncoder().encodeToString(Image);
        }
        

        LocalDate date = LocalDate.now();
        Date formatDate = Date.valueOf(date);

        if (action == null) {
        } else if (action.trim().equalsIgnoreCase("delete")) {
            ndao.deleteNewbyID(Integer.parseInt(newID));
        } else if (action.trim().equalsIgnoreCase("create")) {
            if (parent == null && Image == null) {
                ndao.addnews(title, heading, content, navigationID, userid);
            } else if (Image != null) {
                ndao.addbanner(title, heading, Image, content, userid, navigationID);
            } else if(parent != null){
                ndao.addnewwithparent(title, heading, content, navigationID, userid,Integer.parseInt(parent));
            }
        } else if (action.equalsIgnoreCase("edit")) {
            News new1 = new News();
            if (parent == null) {
                if (navName.compareTo("Banner") == 0) {
                    new1.setApprovedDate(formatDate);
                    new1.setCreatedBy(employee.getId());
                    new1.setCreatedDate(formatDate);
                    new1.setModifiedBy(employee.getId());
                    new1.setNewID(Integer.parseInt(newID));
                    new1.setNewsImage(Image);
                    new1.setNewsContent(content);
                    new1.setNewsHeading(heading);
                    new1.setNewsTitle(title);
                } else {
                    new1.setApprovedDate(formatDate);
                    new1.setCreatedBy(employee.getId());
                    new1.setCreatedDate(formatDate);
                    new1.setModifiedBy(employee.getId());
                    new1.setNavigationID(navigationID);
                    new1.setNewID(Integer.parseInt(newID));
                    new1.setNewsContent(content);
                    new1.setNewsHeading(heading);
                    new1.setNewsTitle(title);
                }
            }
            if (parent != null) {
                new1.setApprovedDate(formatDate);
                new1.setCreatedBy(employee.getId());
                new1.setCreatedDate(formatDate);
                new1.setModifiedBy(employee.getId());
                new1.setNewID(Integer.parseInt(newID));
                new1.setNewsContent(content);
                new1.setNewsHeading(heading);
                new1.setNewsTitle(title);
                new1.setParentID(Integer.parseInt(parent));
            }try {
                ndao.updateNews(new1);
            } catch (SQLException ex) {
                Logger.getLogger(EditDirection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        session.setAttribute("Facts", ndao.getNewbyNavigationID(1));
        session.setAttribute("Running_text", ndao.getNewbyNavigationID(2));
        session.setAttribute("menu", ndao.getNewbyNavigationID(3));
        session.setAttribute("Banner", ndao.getNewbyNavigationID(4));
        session.setAttribute("listnew", ndao.getNewbyNavigationID(5));
        session.setAttribute("address", ndao.getNewbyNavigationID(6));
        session.setAttribute("footerlist", ndao.getNewbyNavigationID(7));
        request.getRequestDispatcher("changedirection?change=" + navigationID).forward(request, response);
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

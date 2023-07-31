/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployeeDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Employee;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "SearchStockProduct", urlPatterns = {"/searchstockproduct"})
public class SearchStockProduct extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String searchString = request.getParameter("searchString");
        EmployeeDAO ed = new EmployeeDAO();
        List<Employee> le = ed.getEmployess();
        ProductDAO pd = new ProductDAO();
        List<Product> lp = pd.getByName(searchString);
        PrintWriter out = response.getWriter();
        SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int i = 0;
        for (Product o : lp) {
            i++;
            out.println("<tr>\n"
                    + "                                                        <td>"+i+"</td>\n"
                    + "                                                        <td>"+o.getProductID()+"</td>\n"
                    + "                                                        <td>"+o.getProductName()+"</td>\n"
                    + "                                                        <td class=\"prices\">"+o.getPrice()+"</td>\n"
                    + "                                                        <td>"+o.getStockQuantity()+"</td>\n"
                    + "                                                        <td>"+o.getProductStatus()+"</td>\n"
                    + "                                                        <td>"+DateFormat.format(o.getExpiryDate())+"</td>\n");       
            for(Employee e: le){
                if(o.getCreatedBy()==e.getId())
                    out.println("<td>"+e.getFirstname()+" "+ e.getLastname()+"</td>\n");
                if(o.getModifiedBy()==e.getId()) 
                    out.println("<td>"+e.getFirstname()+" "+ e.getLastname()+"</td>\n");
                }     
                    out.println("                                             <td>"+DateFormat.format(o.getCreatedDate())+"</td>\n"
                    + "                                                        <td>"+DateFormat.format(o.getModifiedDate())+"</td>\n"
                    + "                                                        <td><button class=\"button_change\"><a href=\"/swp/changeproduct?productID="+o.getProductID()+"\">Change</a></button></td>\n"
                    + "                                                    </tr>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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

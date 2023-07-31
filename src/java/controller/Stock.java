/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import dal.EmployeeDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.Employee;
import model.Product;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name="Stock", urlPatterns={"/stock"})
public class Stock extends HttpServlet {
   
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
            out.println("<title>Servlet Stock</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Stock at " + request.getContextPath () + "</h1>");
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
        String categoryID = request.getParameter("categoryID");
        CategoryDAO cd = new CategoryDAO();
        ProductDAO pd = new ProductDAO();
        EmployeeDAO ed = new EmployeeDAO();
        List<Employee> listEmployee = ed.getEmployess();
        List<Category> listCategory = cd.getAll();
        List<Product> listByCategory = pd.getByCategory(categoryID);
        request.setAttribute("account", (Employee)request.getSession().getAttribute("account"));
        request.setAttribute("ping", categoryID);
        request.setAttribute("listEmployee", listEmployee);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("listByCategory", listByCategory);
        
        List<Product> list = pd.getAll();
        int numPs=list.size();
        int numperPage=10;
        int numpage=numPs/numperPage+(numPs%numperPage==0?0:1);
        int start,end;
        String tpage=request.getParameter("page");
        int page;
        try{
            page=Integer.parseInt(tpage);
        }catch(NumberFormatException e){
            page=1;
        }
        start=(page-1)*numperPage;
        if(page*numperPage>numPs){
            end=numPs;
        }else
            end=page*numperPage;
        List<Product> listProduct = pd.getStockProductByPage(list, start, end);
        request.setAttribute("num", numpage);
        request.setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("stock.jsp").forward(request, response);
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
        /*ProductDAO pd = new ProductDAO();
        List<Product> listProduct = pd.getAll();
        request.setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("stock.jsp").forward(request, response);*/
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

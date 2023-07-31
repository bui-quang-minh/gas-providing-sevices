/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CategoryDAO;
import dal.ManufacturerDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Employee;
import model.Manufacturer;
import model.Product;
import model.ProductImage;


/**
 *
 * @author Minh Bui
 */
@WebServlet(name="ChangeProduct", urlPatterns={"/changeproduct"})
public class ChangeProduct extends HttpServlet {
   
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
            out.println("<title>Servlet ChangeProduct</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeProduct at " + request.getContextPath () + "</h1>");
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
        String productID = request.getParameter("productID");
        Product p;
        ProductDAO pd = new ProductDAO();
        ProductImageDAO pid = new ProductImageDAO();
        CategoryDAO cd = new CategoryDAO();
        List<ProductImage> pi = pid.getImageByID(productID);
        List<Category> listCategory = cd.getAll();
        ManufacturerDAO md = new ManufacturerDAO();
        List<Manufacturer> listManufacturer = md.getAll();
        request.setAttribute("listManufacturer", listManufacturer);
        request.setAttribute("listCategory", listCategory);
        request.setAttribute("account", (Employee) request.getSession().getAttribute("account"));
        p = pd.getProductByID(productID);
        request.setAttribute("p", p);
        request.setAttribute("pi", pi);
        request.getRequestDispatcher("changeProduct.jsp").forward(request, response);
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
        ProductDAO pd = new ProductDAO();
        Employee employee = (Employee) request.getSession().getAttribute("account");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        int productID=0;
        try {
            productID = Integer.parseInt(request.getParameter("productID"));
            String productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int price = Integer.parseInt(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int historicalCost = Integer.parseInt(request.getParameter("historicalCost"));
            int warrantyPeriod = Integer.parseInt(request.getParameter("warrantyPeriod"));
            int  categoryID = Integer.parseInt(request.getParameter("categoryID"));
            String type = request.getParameter("type");
            int manufacturerID = Integer.parseInt(request.getParameter("manufacturerID"));

            String raw1 = request.getParameter("manufacturinngDate");
            Date manufacturinngDate = dateFormat.parse(raw1);

            String raw2 = request.getParameter("expiryDate");
            Date expiryDate = dateFormat.parse(raw2);

            String productCapacity = request.getParameter("productCapacity");
            String productStatus = request.getParameter("productStatus");
            //img bytes setup
            
            int modifiedBy = employee.getId();
            
            Product p = new Product(productID, productName, description, price, stockQuantity, warrantyPeriod, categoryID, type, manufacturerID, manufacturinngDate, expiryDate, productCapacity, productStatus, modifiedBy);
            p.setHistoricalCost(historicalCost);
            pd.updateProduct(p);
            
        } catch (NumberFormatException | ParseException e) {
            request.getRequestDispatcher("errorPage.jsp");
        }
//        request.setAttribute("e", ed);
//        request.getRequestDispatcher("test.jsp").forward(request, response);
        response.sendRedirect(request.getContextPath()+"/changeproduct?productID="+productID);
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

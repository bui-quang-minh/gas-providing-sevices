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
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Minh
 */
@WebServlet(name = "AddProduct", urlPatterns = {"/addproduct"})
public class AddProduct extends HttpServlet {

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
            out.println("<title>Servlet AddProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProduct at " + request.getContextPath() + "</h1>");
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
        CategoryDAO cd = new CategoryDAO();
        ManufacturerDAO md = new ManufacturerDAO();
        request.setAttribute("account", (Employee) request.getSession().getAttribute("account"));
        List<Manufacturer> listManufacturer = md.getAll();
        List<Category> listCategory = cd.getAll();
        request.setAttribute("listManufacturer", listManufacturer);
        request.setAttribute("listCategory", listCategory);
        request.getRequestDispatcher("addProduct.jsp").forward(request, response);
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

        ProductDAO pd = new ProductDAO();
        int productID = -1;
        String productName = "";
        int modifiedBy = -1;
        String type = "";
        ProductImageDAO pid = new ProductImageDAO();
        Employee employee = (Employee) request.getSession().getAttribute("account");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            productName = request.getParameter("productName");
            String description = request.getParameter("description");
            int historicalCost = Integer.parseInt(request.getParameter("historicalCost"));
            int price = Integer.parseInt(request.getParameter("price"));
            int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));
            int warrantyPeriod = Integer.parseInt(request.getParameter("warrantyPeriod"));
            int categoryID = Integer.parseInt(request.getParameter("categoryID"));
            type = request.getParameter("type");
            int manufacturerID = Integer.parseInt(request.getParameter("manufacturerID"));

            String raw1 = request.getParameter("manufacturinngDate");
            Date manufacturinngDate = dateFormat.parse(raw1);

            String raw2 = request.getParameter("expiryDate");
            Date expiryDate = dateFormat.parse(raw2);

            String productCapacity = request.getParameter("productCapacity");
            String productStatus = request.getParameter("productStatus");

            int createdBy = employee.getId();
            modifiedBy = employee.getId();
            
            Product p = new Product(productName, description, historicalCost, price, stockQuantity, warrantyPeriod, categoryID, type, manufacturerID, manufacturinngDate, expiryDate, productCapacity, productStatus, createdBy, modifiedBy);
            pd.createProduct(p);
            productID = pd.getNewProductID();
            ProductImage pi1 = new ProductImage();
            //img bytes setup
            Part filePart1 = request.getPart("productImage");
            InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
            byte[] productImage = IOUtils.toByteArray(inputStream1);// Read the input stream into a byte array
            pi1.setProductImage(productImage);
            pi1.setProductID(productID);
            pi1.setImageType("thumbnail");
            pid.insertImage(pi1);
            if (ServletFileUpload.isMultipartContent(request)) {
                List<Part> fileParts = (List<Part>) request.getParts(); // Get all uploaded files
                for (Part part : fileParts) {
                    if (part.getName().equals("productSizedImage")) {
                        InputStream inputStream2 = part.getInputStream();// Get the input stream from the file part
                        byte[] productSizedImage = IOUtils.toByteArray(inputStream2);// Read the input stream into a byte array
                        ProductImage pi2 = new ProductImage();
                        pi2.setProductImage(productSizedImage);
                        pi2.setProductID(productID);
                        pi2.setImageType("description");
                        pid.insertImage(pi2);
                    }
                }
            }

        } catch (ServletException | IOException | NumberFormatException | ParseException e) {
            request.getRequestDispatcher("errorPage.jsp");
        }
        response.sendRedirect("stock");
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

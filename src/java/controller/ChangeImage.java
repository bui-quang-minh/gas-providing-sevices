/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.CustomerDAO;
import dal.EmployeeDAO;
import dal.ManufacturerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import model.Customer;
import model.Employee;
import model.Manufacturer;
import org.apache.commons.io.IOUtils;



/**
 *
 * @author Minh Bui
 */
@WebServlet(name="ChangeImage", urlPatterns={"/changeimage"})
public class ChangeImage extends HttpServlet {
   
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
            out.println("<title>Servlet ChangeImage</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeImage at " + request.getContextPath () + "</h1>");
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
        processRequest(request, response);
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
        int change = Integer.parseInt(request.getParameter("change"));
        if(change == 1)
        {
            int employeeID = Integer.parseInt(request.getParameter("employeeid"));
            Part filePart1 = request.getPart("image");
            InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
            byte[] productImage = IOUtils.toByteArray(inputStream1);// Read tm inputStream1 = fileParhe input stream into a byte array
            Employee e = new Employee();
            e.setId(employeeID);
            e.setImage(productImage);
            EmployeeDAO ed = new EmployeeDAO();
            ed.updateImage(e);
            response.sendRedirect("editemployee?employeeid="+employeeID);
        }
        if(change == 2)
        {
            int customerID = Integer.parseInt(request.getParameter("customerid"));
            Part filePart1 = request.getPart("image");
            InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
            byte[] productImage = IOUtils.toByteArray(inputStream1);// Read tm inputStream1 = fileParhe input stream into a byte array
            Customer c = new Customer();
            c.setId(customerID);
            c.setImage(productImage);

            CustomerDAO cd = new CustomerDAO();
            cd.updateImage(c);
            response.sendRedirect("editcustomer?customerid="+customerID);
        }
        if(change == 3)
        {
            int manufacturerID = Integer.parseInt(request.getParameter("manufacturerid"));
            Part filePart1 = request.getPart("image");
            InputStream inputStream1 = filePart1.getInputStream();// Get the input stream from the file part
            byte[] productImage = IOUtils.toByteArray(inputStream1);// Read tm inputStream1 = fileParhe input stream into a byte array
            Manufacturer m = new Manufacturer();
            m.setManufacturerID(manufacturerID);
            m.setManufacturerImage(productImage);

            ManufacturerDAO md = new ManufacturerDAO();
            md.updateImage(m);
            response.sendRedirect("editmanufacturer?manufacturerid="+manufacturerID);
        }
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

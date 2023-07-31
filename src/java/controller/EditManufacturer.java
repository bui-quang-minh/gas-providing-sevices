/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.EmployeeDAO;
import dal.ManufacturerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Employee;
import model.Manufacturer;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "EditManufacturer", urlPatterns = {"/editmanufacturer"})
public class EditManufacturer extends HttpServlet {

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
            out.println("<title>Servlet EditManufacturer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditManufacturer at " + request.getContextPath() + "</h1>");
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
        String role = (String) request.getSession().getAttribute("role");

        if (role == null || role.equals("Customer")) {
            response.sendRedirect(request.getContextPath()+"/");
        } else {
            int id = Integer.parseInt(request.getParameter("manufacturerid"));

            ManufacturerDAO md = new ManufacturerDAO();
            Manufacturer m = md.getMan(id);
            request.setAttribute("manufacturer", m);

            EmployeeDAO ed = new EmployeeDAO();
            List<Employee> employees = ed.getEmployess();
            request.setAttribute("employees", employees);

            request.getRequestDispatcher("ManufacturerDetail.jsp").forward(request, response);
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
        Employee e = (Employee) request.getSession().getAttribute("account");

        ManufacturerDAO md = new ManufacturerDAO();
        Manufacturer m = new Manufacturer();
        m.setDescription(request.getParameter("description"));
        m.setManufacturerAddress(request.getParameter("address"));
        m.setManufacturerEmail(request.getParameter("email"));
        m.setManufacturerID(Integer.parseInt(request.getParameter("id")));
        m.setManufacturerName(request.getParameter("name"));
        m.setManufacturerPhone(request.getParameter("phonenumber"));
        m.setModifiedBy(e.getId());

        md.updateManufacturer(m);

        Manufacturer m1 = md.getMan(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("manufacturer", m1);

        EmployeeDAO ed = new EmployeeDAO();
        List<Employee> employees = ed.getEmployess();
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("ManufacturerDetail.jsp").forward(request, response);

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

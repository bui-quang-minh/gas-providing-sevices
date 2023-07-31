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
@WebServlet(name = "AllCategory", urlPatterns = {"/allcategory"})
public class AllCategory extends HttpServlet {

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
            out.println("<title>Servlet AllCategory</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllCategory at " + request.getContextPath() + "</h1>");
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
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            CategoryDAO cd = new CategoryDAO();
            List<Category> categories = cd.getAll();
            request.setAttribute("categories", categories);

            EmployeeDAO ed = new EmployeeDAO();
            List<Employee> employees = ed.getEmployess();
            request.setAttribute("employees", employees);
            ArrayList<Integer> count = new ArrayList<>();
            ArrayList<Integer> count1 = new ArrayList<>();

            for (Category c : categories) {
                String a = "" + c.getCategoryID() + "";
                ProductDAO pd = new ProductDAO();
                List<Product> products = pd.getByCategory(a);
                if (products.isEmpty()) {
                    count.add(c.getCategoryID());
                } else {
                    count1.add(c.getCategoryID());
                }

            }
            request.setAttribute("count", count);
            request.setAttribute("count1", count1);
            request.getRequestDispatcher("allCategory.jsp").forward(request, response);
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

        CategoryDAO cd = new CategoryDAO();

        Category c = new Category();
        c.setCategoryName(request.getParameter("name"));
        c.setDescription(request.getParameter("description"));
        c.setCreatedBy(e.getId());
        c.setModifiedBy(e.getId());

        cd.insertCategory(c);

        doGet(request, response);

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

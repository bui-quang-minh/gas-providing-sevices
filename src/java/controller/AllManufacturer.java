/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ManufacturerDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Manufacturer;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "AllManufacturer", urlPatterns = {"/allmanufacturer"})
public class AllManufacturer extends HttpServlet {

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
            out.println("<title>Servlet AllManufacturer</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AllManufacturer at " + request.getContextPath() + "</h1>");
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
            ManufacturerDAO md = new ManufacturerDAO();
            List<Manufacturer> manufacturers = md.getAll();
            request.setAttribute("manufacturers", manufacturers);
            request.getRequestDispatcher("allManufacturer.jsp").forward(request, response);
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
        int limit = 10;
        int page = 1;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String searchQuery = request.getParameter("query");

        ManufacturerDAO dao = new ManufacturerDAO();

        ArrayList<Manufacturer> manufacturers = dao.getManufacturersBySearch(page, limit, searchQuery);
        int totalRecords = dao.getTotalRecords();
        String output = "<div>";
        output += "<table class=\"table table-striped\">";
        output += "<thead>";
        output += "<tr>";
        output += "<th>Index</th>";
        output += "<th>Manufacturer Name</th>";
        output += "<th>Address</th>";
        output += "<th>Phone Number</th>";
        output += "<th>Email</th>";
        output += "<th>Actions</th>";
        output += "</tr>";
        output += "</thead>";
        output += "<tbody class=\"table-border-bottom-0\">";

        int index = 1;
        if (!manufacturers.isEmpty()) {
            for (Manufacturer manufacturer : manufacturers) {
                output += "<tr>";
                output += "<td><i class=\"fab fa-angular fa-lg text-danger me-3\"></i> " + index + "</td>";
                output += "<td>" + manufacturer.getManufacturerName() + "</td>";
                output += "<td>" + manufacturer.getManufacturerAddress() + "</td>";
                output += "<td>" + manufacturer.getManufacturerPhone() + "</td>";
                output += "<td>" + manufacturer.getManufacturerEmail() + "</td>";
                output += "<td>";
                output += "<div class=\"dropdown\">";
                output += "<a href=\"editmanufacturer?manufacturerid=" + manufacturer.getManufacturerID() + "\"><i class=\"bx bx-edit-alt me-1\"></i></a>";
                output += "<a href=\"#\" onclick=\"deleteManufacturer(" + manufacturer.getManufacturerID() + ")\"><i class=\"bx bx-trash me-1\"></i></a>";
                output += "</div>";
                output += "</td>";
                output += "</tr>";

                index++;
            }
        } else {
            output += "<tr><td colspan=\"6\" align=\"center\">No Data Found</td></tr>";
        }
        output += "</tbody>";
        output += "</table>";
        output += "</div>";
        output += "<div class=\"row\">";
        output += "<div class=\"col-lg-12\">";
        output += "<div class=\"pagination\" style=\"margin-left: 2%\">";

        int totalPages = (int) Math.ceil(totalRecords * 1.0 / limit);
        String previous_link = "";
        String next_link = "";
        String page_link = "";

        if (totalPages > 4) {
            if (page < 5) {
                for (int count = 1; count <= 5; count++) {
                    page_link += "<li class=\"page-item\"><a class=\"page-link\" href=\"#\">" + count + "<span class=\"sr-only\">(current)</span></a></li>";
                }
                page_link += "<li class=\"page-item\">...</li>";
                page_link += "<li class=\"page-item\">" + totalPages + "</li>";
            } else {
                int endLimit = totalPages - 4;
                if (page > endLimit) {
                    page_link += "<li class=\"page-item\">1</li>";
                    page_link += "<li class=\"page-item\">...</li>";
                    for (int count = endLimit; count <= totalPages; count++) {
                        page_link += "<li class=\"page-item\">" + count + "</li>";
                    }
                } else {
                    page_link += "<li class=\"page-item\">1</li>";
                    page_link += "<li class=\"page-item\">...</li>";
                    for (int count = page - 1; count <= page + 1; count++) {
                        page_link += "<li class=\"page-item\">" + count + "</li>";
                    }
                    page_link += "<li class=\"page-item\">...</li>";
                    page_link += "<li class=\"page-item\">" + totalPages + "</li>";
                }
            }
        } else {
            for (int count = 1; count <= totalPages; count++) {
                page_link += "<li class=\"page-item\"><a class=\"page-link\" href=\"javascript:void(0)\" data-page_number=\"" + count + "\">" + count + "</a></li>";
            }
        }
        output += (page_link);

        output
                += "</ul></div>";
        output += "</div>";
        output += "</div>";
        output += "</div>";

        response.setContentType(
                "text/html");
        PrintWriter out = response.getWriter();

        out.println(output);
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

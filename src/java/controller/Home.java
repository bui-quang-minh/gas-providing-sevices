/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DiscountDAO;
import dal.NewsDAO;
import dal.ProductDAO;
import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import model.Discount;
import model.News;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "Home", urlPatterns = {"/"})
public class Home extends HttpServlet {

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
            out.println("<title>Servlet Home</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        ProductDAO dao = new ProductDAO();
        DiscountDAO ddao = new DiscountDAO();
        ProductImageDAO pid = new ProductImageDAO();
        NewsDAO ndao = new NewsDAO();
        session.setAttribute("Navigation", ndao.getAllNavigation());
        session.setAttribute("Facts", ndao.getNewbyNavigationID(1));
        session.setAttribute("Running_text", ndao.getNewbyNavigationID(2));
        session.setAttribute("menu", ndao.getNewbyNavigationID(3));
        session.setAttribute("Banner", ndao.getNewbyNavigationID(4));
        session.setAttribute("listnew", ndao.getNewbyNavigationID(5));
        session.setAttribute("address", ndao.getNewbyNavigationID(6));
        session.setAttribute("footerlist", ndao.getNewbyNavigationID(7));
        String folderPath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 10.0\\work\\Catalina\\localhost\\swp\\tmp";
        File theDir = new File(folderPath);
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
        // Create the folder
        boolean created = theDir.mkdirs();
        Product lastestproduct = dao.getAll().get(dao.getAll().size() - 1);
        session.setAttribute("lastestproduct", lastestproduct);
        List<ProductImage> productImageList = pid.getAllImage();
        List<Product> productList = dao.getAll();
        List<Product> productListNew = dao.getNew();
        List<Product> productListDiscount = new ArrayList<>();
        List<Product> productlist3BestDiscount = new ArrayList<>();
        List<Product> productlistcheap = dao.getCheap();
        List<Discount> productListbestdis = ddao.getDiscount();
        List<Discount> top3 = ddao.getTop3Discount();
        List<Discount> allDiscount = ddao.getAll();

        for (Discount d : productListbestdis) {
            for (Product p : productList) {
                if (d.getProductID() == p.getProductID()) {
                    productListDiscount.add(p);
                }
            }
        }
        for (Discount d : top3) {
            for (Product p : productList) {
                if (d.getProductID() == p.getProductID()) {
                    productlist3BestDiscount.add(p);
                }
            }
        }
        request.setAttribute("listnew", ndao.getNewbyNavigationID(5));
        request.setAttribute("productListCheap", productlistcheap);
        request.setAttribute("productImageList", productImageList);
        request.setAttribute("productList", productList);
        request.setAttribute("productListDiscount", productListDiscount);
        request.setAttribute("newProductList", productListNew);
        request.setAttribute("allDiscount", allDiscount);
        request.setAttribute("productlist3BestDiscount", productlist3BestDiscount);

        request.getRequestDispatcher("index.jsp").forward(request, response);
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.ProductImageDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Customer;
import model.Product;
import model.ProductImage;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "Cart", urlPatterns = {"/cart"})
public class Cart extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
       out.print("akakak");
                
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
//        processRequest(request, response);
        HttpSession session =request.getSession();
        ProductImageDAO pid = new  ProductImageDAO();
        List<ProductImage> productImageList = pid.getAllImage();
        Customer acc = (Customer)session.getAttribute("account");
        
        request.setAttribute("productImageList", productImageList);
        
        
        List<Product> list=(List<Product>) session.getAttribute("listCart");
        
        if(list==null){
        list=new ArrayList<>();
        }
        
        request.setAttribute("ID", acc);
        if (session.getAttribute("listCart") == null){
        session.setAttribute("listCart", list);     
        }
//        for(int i=0;i<list.size();i++){
//            for(int j=1;j<list.size();j++){
//                if(list.get(i).getProductID()==list.get(j).getProductID()){
//                    if(list.get(i).getQuantity())
//                }
//            }
//        }
        
        for (int i = 0; i < list.size (); i++) {
          Product p1 = list.get (i); // get the first product
          for (int j = i + 1; j < list.size (); j++) {
            Product p2 = list.get (j); // get the second product
            if (p1.getProductID ()==(p2.getProductID ())) { // compare the productIDs
              if (p1.getQuantity () > p2.getQuantity ()) { // compare the quantities
                list.remove (j); // remove the second product
                j--; // decrement j to account for the removal
              } else {
                list.remove (i); // remove the first product
                i--; // decrement i to account for the removal
                break; // break the inner loop
              }
            }
          }
        }
        
        request.getRequestDispatcher("cart.jsp").forward(request, response);
        
        
        
        
        
        
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
        ProductImageDAO pid = new  ProductImageDAO();
        List<ProductImage> productImageList = pid.getAllImage();
        request.setAttribute("productImageList", productImageList);
        HttpSession session = request.getSession();
//            Product product = new Product();
//            ProductDAO pd = new ProductDAO();
            List<Product> list = (List<Product>) session.getAttribute("listCart");
            String id = request.getParameter("deleted");
            if(id!=null){
            int An = Integer.parseInt(id);
            for (Product p : list) {
                if (p.getProductID()==An) {
                    list.remove(p);
                    break;
                }
            }
            }
            if(request.getParameter("productID")!=null&&request.getParameter("quantity")!=null){
            String productid =request.getParameter("productID");
            String quantity =request.getParameter("quantity");
            int productidint = Integer.parseInt(productid);
            int quantityint = Integer.parseInt(quantity);
            for (Product p : list) {
                if (p.getProductID()==productidint) {
                    p.setQuantity(quantityint);
                    break;
                }
            }
            }
            
            session.setAttribute("listCart", list);
            if((List<Product>) session.getAttribute("listCart")!=null&&request.getParameter("ID")!=null){
                List<Product> tobuy;
                tobuy = new ArrayList<>();
                String id_real= request.getParameter("ID");
                for(Product p: tobuy){
                if(p.getProductID()==Integer.parseInt(id_real)){
                tobuy.remove(p);
                }
                }
                tobuy.add(list.get(Integer.parseInt(id_real)));
                session.setAttribute("tobuy", tobuy);
                request.getRequestDispatcher("cart").forward(request, response);
            }
            
            
            
            
            request.getRequestDispatcher("cart").forward(request, response);
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
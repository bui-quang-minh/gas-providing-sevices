/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.CategoryDAO;
import dal.CustomerDAO;
import dal.EmployeeDAO;
import dal.ManufacturerDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;
import model.Category;
import model.Customer;
import model.Employee;
import model.Manufacturer;
import model.Product;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author Minh Bui
 */
@WebServlet(name = "StockExport", urlPatterns = {"/stockexport"})
public class StockExport extends HttpServlet {

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
            out.println("<title>Servlet StockExport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StockExport at " + request.getContextPath() + "</h1>");
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
        if (Integer.parseInt(request.getParameter("id")) == 5) {
            ProductDAO pd = new ProductDAO();
            EmployeeDAO ed = new EmployeeDAO();
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
            List<Employee> listEmployee = ed.getEmployess();
            List<Product> listProduct = pd.getAll();
            Workbook wb = new HSSFWorkbook();
            Workbook wt = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Stock");
            DataFormat dataFormat = wb.createDataFormat();
            CellStyle csPrice = wb.createCellStyle();
            csPrice.setDataFormat(dataFormat.getFormat("0.000"));
            Font headerFont = wb.createFont();
            headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
            CellStyle cs = wb.createCellStyle();
            cs.setAlignment(cs.ALIGN_CENTER);
            cs.setFont(headerFont);

            int rowCount = 0;
            Object[][] data = {
                {"Index", "ID", "Product Name", "Price", "Stock Quantity", "Product Status", "Expiry Date", "Created By", "Last Modified", "Created Date", "Modified Date"}
            };
            int i = 0;
            for (Product p : listProduct) {
                i++;
                Object[] newData = {
                    i,
                    p.getProductID(),
                    p.getProductName(),
                    p.getPrice(),
                    p.getStockQuantity(),
                    p.getProductStatus(),
                    DateFormat.format(p.getExpiryDate()),
                    p.getCreatedBy(),
                    p.getModifiedBy(),
                    DateFormat.format(p.getCreatedDate()),
                    DateFormat.format(p.getModifiedDate())
                };
                Object[][] updatedData = new Object[data.length + 1][];
                System.arraycopy(data, 0, updatedData, 0, data.length);
                updatedData[data.length] = newData;
// Use the updatedData array with the new row of data
                data = updatedData;
            }

            for (int j = 1; j < data.length; j++) {
                int employeeID = (int) data[j][7];
                for (Employee e : listEmployee) {
                    if (employeeID == e.getId()) {
                        data[j][7] = e.getFirstname() + " " + e.getLastname();
                    }
                }
            }
            for (int k = 1; k < data.length; k++) {
                int employeeID = (int) data[k][8];
                for (Employee e : listEmployee) {
                    if (employeeID == e.getId()) {
                        data[k][8] = e.getFirstname() + "" + e.getLastname();
                    }
                }
            }

            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                    if (rowCount == 1) {
                        cell.setCellStyle(cs);
                    }
                }
            }
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 1500);
            sheet.setColumnWidth(2, 8000);
            sheet.setColumnWidth(3, 2500);
            sheet.setColumnWidth(4, 4000);
            sheet.setColumnWidth(5, 4000);
            sheet.setColumnWidth(6, 4000);
            sheet.setColumnWidth(7, 4000);
            sheet.setColumnWidth(8, 4000);
            sheet.setColumnWidth(9, 4000);
            sheet.setColumnWidth(10, 4000);
            String filePath = "data.xls";
            try ( FileOutputStream outputStream = new FileOutputStream(filePath)) {
                wb.write(outputStream);
                System.out.println("Data exported successfully to: " + filePath);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=data.xls");

            // Write Excel file to response
            try ( FileInputStream inputStream = new FileInputStream(filePath)) {
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }

            // Delete the temporary file
            Files.delete(Paths.get(filePath));

        }
        if (Integer.parseInt(request.getParameter("id")) == 1) {

            EmployeeDAO ed = new EmployeeDAO();
            List<Employee> listEmployee = ed.getEmployess();
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Employees");
            DataFormat dataFormat = wb.createDataFormat();
            CellStyle csPrice = wb.createCellStyle();
            csPrice.setDataFormat(dataFormat.getFormat("0.000"));
            Font headerFont = wb.createFont();
            headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
            CellStyle cs = wb.createCellStyle();
            cs.setAlignment(cs.ALIGN_CENTER);
            cs.setFont(headerFont);

            int rowCount = 0;
            Object[][] data = {
                {"Index", "ID", "Username", "Password", "First Name", "Last Name", "Address", "Phone Number", "Email", "DOB", "Role", "Description", "Hired Date"}
            };
            int i = 0;
            for (Employee e : listEmployee) {
                i++;
                Object[] newData = {
                    i,
                    e.getId(),
                    e.getUsername(),
                    e.getPassword(),
                    e.getFirstname(),
                    e.getLastname(),
                    e.getAddress(),
                    e.getPhone(),
                    e.getEmail(),
                    DateFormat.format(e.getDob()),
                    e.getRole(),
                    e.getDesciption(),
                    DateFormat.format(e.getHiredDate())
                };
                Object[][] updatedData = new Object[data.length + 1][];
                System.arraycopy(data, 0, updatedData, 0, data.length);
                updatedData[data.length] = newData;
                // Use the updatedData array with the new row of data
                data = updatedData;
            }

            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                    if (rowCount == 1) {
                        cell.setCellStyle(cs);
                    }
                }
            }
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 1500);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 4000);
            sheet.setColumnWidth(5, 4000);
            sheet.setColumnWidth(6, 10000);
            sheet.setColumnWidth(7, 8000);
            sheet.setColumnWidth(8, 8000);
            sheet.setColumnWidth(9, 5000);
            sheet.setColumnWidth(10, 4000);
            sheet.setColumnWidth(11, 4000);
            sheet.setColumnWidth(12, 5000);
            String filePath = "employee_data.xls";
            try ( FileOutputStream outputStream = new FileOutputStream(filePath)) {
                wb.write(outputStream);
                System.out.println("Data exported successfully to: " + filePath);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=employee_data.xls");

            // Write Excel file to response
            try ( FileInputStream inputStream = new FileInputStream(filePath)) {
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }

            // Delete the temporary file
            Files.delete(Paths.get(filePath));
        }

        if (Integer.parseInt(request.getParameter("id")) == 2) {
            CustomerDAO cd = new CustomerDAO();
            List<Customer> listCustomer = cd.getCustomers();
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Customers");
            DataFormat dataFormat = wb.createDataFormat();
            CellStyle csPrice = wb.createCellStyle();
            csPrice.setDataFormat(dataFormat.getFormat("0.000"));
            Font headerFont = wb.createFont();
            headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
            CellStyle cs = wb.createCellStyle();
            cs.setAlignment(cs.ALIGN_CENTER);
            cs.setFont(headerFont);

            int rowCount = 0;
            Object[][] data = {
                {"Index", "ID", "Username", "Password", "First Name", "Last Name", "Address", "Phone Number", "Email", "DOB", "Status", "Description", "Created Date"}
            };
            int i = 0;
            for (Customer c : listCustomer) {
                i++;
                Object[] newData = {
                    i,
                    c.getId(),
                    c.getUsername(),
                    c.getPassword(),
                    c.getFirstname(),
                    c.getLastname(),
                    c.getAddress(),
                    c.getPhone(),
                    c.getEmail(),
                    DateFormat.format(c.getDob()),
                    c.getStatus(),
                    c.getDesciption(),
                    DateFormat.format(c.getCreatedDate())
                };
                Object[][] updatedData = new Object[data.length + 1][];
                System.arraycopy(data, 0, updatedData, 0, data.length);
                updatedData[data.length] = newData;
                // Use the updatedData array with the new row of data
                data = updatedData;
            }

            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                    if (rowCount == 1) {
                        cell.setCellStyle(cs);
                    }
                }
            }
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 1500);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 4000);
            sheet.setColumnWidth(4, 4000);
            sheet.setColumnWidth(5, 4000);
            sheet.setColumnWidth(6, 10000);
            sheet.setColumnWidth(7, 8000);
            sheet.setColumnWidth(8, 8000);
            sheet.setColumnWidth(9, 5000);
            sheet.setColumnWidth(10, 4000);
            sheet.setColumnWidth(11, 4000);
            sheet.setColumnWidth(12, 5000);
            String filePath = "customer_data.xls";
            try ( FileOutputStream outputStream = new FileOutputStream(filePath)) {
                wb.write(outputStream);
                System.out.println("Data exported successfully to: " + filePath);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=customer_data.xls");

            // Write Excel file to response
            try ( FileInputStream inputStream = new FileInputStream(filePath)) {
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }

            // Delete the temporary file
            Files.delete(Paths.get(filePath));
        }

        if (Integer.parseInt(request.getParameter("id")) == 3) {
            CategoryDAO cd = new CategoryDAO();
            EmployeeDAO ed = new EmployeeDAO();
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
            List<Employee> listEmployee = ed.getEmployess();
            List<Category> listCategory = cd.getAll();
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Category");
            DataFormat dataFormat = wb.createDataFormat();
            CellStyle csPrice = wb.createCellStyle();
            csPrice.setDataFormat(dataFormat.getFormat("0.000"));
            Font headerFont = wb.createFont();
            headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
            CellStyle cs = wb.createCellStyle();
            cs.setAlignment(cs.ALIGN_CENTER);
            cs.setFont(headerFont);

            int rowCount = 0;
            Object[][] data = {
                {"Index", "ID", "Category Name", "Description", "Created By", "Last Modified", "Created Date", "Modified Date"}
            };
            int i = 0;
            for (Category c : listCategory) {
                i++;
                Object[] newData = {
                    i,
                    c.getCategoryID(),
                    c.getCategoryName(),
                    c.getDescription(),
                    c.getCreatedBy(),
                    c.getModifiedBy(),
                    DateFormat.format(c.getCreatedDate()),
                    DateFormat.format(c.getModifiedDate())
                };
                Object[][] updatedData = new Object[data.length + 1][];
                System.arraycopy(data, 0, updatedData, 0, data.length);
                updatedData[data.length] = newData;
// Use the updatedData array with the new row of data
                data = updatedData;
            }

            for (int j = 1; j < data.length; j++) {
                int employeeID = (int) data[j][4];
                for (Employee e : listEmployee) {
                    if (employeeID == e.getId()) {
                        data[j][4] = e.getFirstname() + " " + e.getLastname();
                    }
                }
            }
            for (int k = 1; k < data.length; k++) {
                int employeeID = (int) data[k][5];
                for (Employee e : listEmployee) {
                    if (employeeID == e.getId()) {
                        data[k][5] = e.getFirstname() + " " + e.getLastname();
                    }
                }
            }

            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                    if (rowCount == 1) {
                        cell.setCellStyle(cs);
                    }
                }
            }
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 1500);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 8000);
            sheet.setColumnWidth(4, 4000);
            sheet.setColumnWidth(5, 4000);
            sheet.setColumnWidth(6, 5000);
            sheet.setColumnWidth(7, 5000);

            String filePath = "category_data.xls";
            try ( FileOutputStream outputStream = new FileOutputStream(filePath)) {
                wb.write(outputStream);
                System.out.println("Data exported successfully to: " + filePath);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=category_data.xls");

            // Write Excel file to response
            try ( FileInputStream inputStream = new FileInputStream(filePath)) {
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }

            // Delete the temporary file
            Files.delete(Paths.get(filePath));

        }
        
        if (Integer.parseInt(request.getParameter("id")) == 4) {
            ManufacturerDAO md = new ManufacturerDAO();
            EmployeeDAO ed = new EmployeeDAO();
            SimpleDateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy");
            List<Employee> listEmployee = ed.getEmployess();
            List<Manufacturer> listManufacturer = md.getAll();
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Manufacturer");
            DataFormat dataFormat = wb.createDataFormat();
            CellStyle csPrice = wb.createCellStyle();
            csPrice.setDataFormat(dataFormat.getFormat("0.000"));
            Font headerFont = wb.createFont();
            headerFont.setBoldweight(headerFont.BOLDWEIGHT_BOLD);
            CellStyle cs = wb.createCellStyle();
            cs.setAlignment(cs.ALIGN_CENTER);
            cs.setFont(headerFont);

            int rowCount = 0;
            Object[][] data = {
                {"Index", "ID", "Manufacturer Name", "Address","Phone Number","Email","Description", "Created By", "Last Modified", "Created Date", "Modified Date"}
            };
            int i = 0;
            for (Manufacturer m : listManufacturer) {
                i++;
                Object[] newData = {
                    i,
                    m.getManufacturerID(),
                    m.getManufacturerName(),
                    m.getManufacturerAddress(),
                    m.getManufacturerPhone(),
                    m.getManufacturerEmail(),
                    m.getDescription(),
                    m.getCreatedBy(),
                    m.getModifiedBy(),
                    DateFormat.format(m.getCreatedDate()),
                    DateFormat.format(m.getModifiedDate())
                };
                Object[][] updatedData = new Object[data.length + 1][];
                System.arraycopy(data, 0, updatedData, 0, data.length);
                updatedData[data.length] = newData;
// Use the updatedData array with the new row of data
                data = updatedData;
            }

            for (int j = 1; j < data.length; j++) {
                int employeeID = (int) data[j][7];
                for (Employee e : listEmployee) {
                    if (employeeID == e.getId()) {
                        data[j][7] = e.getFirstname() + " " + e.getLastname();
                    }
                }
            }
            for (int k = 1; k < data.length; k++) {
                int employeeID = (int) data[k][8];
                for (Employee e : listEmployee) {
                    if (employeeID == e.getId()) {
                        data[k][8] = e.getFirstname() + " " + e.getLastname();
                    }
                }
            }

            for (Object[] rowData : data) {
                Row row = sheet.createRow(rowCount++);
                int colNum = 0;
                for (Object field : rowData) {
                    Cell cell = row.createCell(colNum++);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                    if (rowCount == 1) {
                        cell.setCellStyle(cs);
                    }
                }
            }
            sheet.setColumnWidth(0, 1500);
            sheet.setColumnWidth(1, 1500);
            sheet.setColumnWidth(2, 5000);
            sheet.setColumnWidth(3, 8000);
            sheet.setColumnWidth(4, 5000);
            sheet.setColumnWidth(5, 6000);
            sheet.setColumnWidth(6, 8000);
            sheet.setColumnWidth(7, 5000);
            sheet.setColumnWidth(8, 5000);
            sheet.setColumnWidth(9, 5000);
            sheet.setColumnWidth(10, 5000);

            String filePath = "manufacturer_data.xls";
            try ( FileOutputStream outputStream = new FileOutputStream(filePath)) {
                wb.write(outputStream);
                System.out.println("Data exported successfully to: " + filePath);
            }

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=manufacturer_data.xls");

            // Write Excel file to response
            try ( FileInputStream inputStream = new FileInputStream(filePath)) {
                int bytesRead;
                byte[] buffer = new byte[4096];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    response.getOutputStream().write(buffer, 0, bytesRead);
                }
            }

            // Delete the temporary file
            Files.delete(Paths.get(filePath));

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
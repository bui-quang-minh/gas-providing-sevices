/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author admin
 */
public class EmployeeDAO extends DBContext {

    public Employee getEmployeeByID(int id) {
        try {
            String sql = "SELECT\n"
                    + "    e.employeeID,e.firstName,e.lastName,e.emoloyeeUsername,e.employeePassword,e.employeeAddress,e.employeePhone,e.employeeEmail,\n"
                    + "	e.description,e.employeeDob,e.employeeImage,e.employeeRole,e.hireDate,\n"
                    + "    COALESCE(COUNT(o.orderID), 0) AS total_orders,\n"
                    + "    COALESCE(SUM(COALESCE(o.totalPrice, 0)), 0) AS total_amount\n"
                    + "FROM\n"
                    + "    Employee e\n"
                    + "    left JOIN [Order] o ON e.employeeID = o.createdBy\n"
                    + "WHERE\n"
                    + "	(o.orderStatusID ='3' or o.orderStatusID is null) and e.employeeID = ?\n"
                    + "GROUP BY\n"
                    + "     e.employeeID,e.firstName,e.lastName,e.emoloyeeUsername,e.employeePassword,e.employeeAddress,e.employeePhone,e.employeeEmail,\n"
                    + "	e.description,e.employeeDob,e.employeeImage,e.employeeRole,e.hireDate\n"
                    + "ORDER BY total_amount desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employeeID"));
                e.setUsername(rs.getString("emoloyeeUsername"));
                e.setPassword(rs.getString("employeePassword"));
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setAddress(rs.getString("employeeAddress"));
                e.setPhone(rs.getString("employeePhone"));
                e.setEmail(rs.getString("employeeEmail"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                e.setDob(rs.getDate("employeeDob"));
                e.setRole(rs.getString("employeeRole"));
                e.setDesciption(rs.getString("description"));
                e.setHiredDate(rs.getDate("hireDate"));
                e.setTotalAmount(rs.getFloat("total_amount"));
                e.setTotalOrder(rs.getInt("total_orders"));
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Employee getEmployee(String username, String password) {
        try {
            String sql = "select * from Employee\n"
                    + "where emoloyeeUsername COLLATE SQL_Latin1_General_CP1_CS_AS LIKE ? and employeePassword COLLATE SQL_Latin1_General_CP1_CS_AS LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employeeID"));
                e.setUsername(rs.getString("emoloyeeUsername"));
                e.setPassword(rs.getString("employeePassword"));
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setAddress(rs.getString("employeeAddress"));
                e.setPhone(rs.getString("employeePhone"));
                e.setEmail(rs.getString("employeeEmail"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                e.setDob(rs.getDate("employeeDob"));
                e.setRole(rs.getString("employeeRole"));
                e.setDesciption(rs.getString("description"));
                e.setHiredDate(rs.getDate("hireDate"));
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Employee getEmployee(String email) {
        try {
            String sql = "select * from Employee\n"
                    + "where employeeEmail = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employeeID"));
                e.setUsername(rs.getString("emoloyeeUsername"));
                e.setPassword(rs.getString("employeePassword"));
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setAddress(rs.getString("employeeAddress"));
                e.setPhone(rs.getString("employeePhone"));
                e.setEmail(rs.getString("employeeEmail"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                e.setDob(rs.getDate("employeeDob"));
                e.setRole(rs.getString("employeeRole"));
                e.setDesciption(rs.getString("description"));
                e.setHiredDate(rs.getDate("hireDate"));
                return e;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void resetPassword(String email, String password) {
        String sql = "update Employee\n"
                + "set employeePassword = ?\n"
                + "where employeeEmail = ?";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, email);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getEmployeeUsername(String username) {
        try {
            String sql = "select emoloyeeUsername from Employee \n"
                    + "where emoloyeeUsername = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                return rs.getString("emoloyeeUsername");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Employee> getEmployess() {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String sql = "SELECT\n"
                    + "    e.employeeID,e.firstName,e.lastName,e.emoloyeeUsername,e.employeePassword,e.employeeAddress,e.employeePhone,e.employeeEmail,\n"
                    + "	e.description,e.employeeDob,e.employeeImage,e.employeeRole,e.hireDate,\n"
                    + "    COALESCE(COUNT(o.orderID), 0) AS total_orders,\n"
                    + "    COALESCE(SUM(COALESCE(o.totalPrice, 0)), 0) AS total_amount\n"
                    + "FROM\n"
                    + "    Employee e\n"
                    + "    left JOIN [Order] o ON e.employeeID = o.createdBy\n"
                    + "WHERE\n"
                    + "	(o.orderStatusID ='3' or o.orderStatusID is null)\n"
                    + "GROUP BY\n"
                    + "     e.employeeID,e.firstName,e.lastName,e.emoloyeeUsername,e.employeePassword,e.employeeAddress,e.employeePhone,e.employeeEmail,\n"
                    + "	e.description,e.employeeDob,e.employeeImage,e.employeeRole,e.hireDate\n"
                    + "ORDER BY total_amount desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employeeID"));
                e.setUsername(rs.getString("emoloyeeUsername"));
                e.setPassword(rs.getString("employeePassword"));
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setAddress(rs.getString("employeeAddress"));
                e.setPhone(rs.getString("employeePhone"));
                e.setEmail(rs.getString("employeeEmail"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                e.setDob(rs.getDate("employeeDob"));
                e.setRole(rs.getString("employeeRole"));
                e.setDesciption(rs.getString("description"));
                e.setHiredDate(rs.getDate("hireDate"));
                e.setTotalAmount(rs.getFloat("total_amount"));
                e.setTotalOrder(rs.getInt("total_orders"));
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public void insertEmployee(Employee e) {

        try {
            String sql = "INSERT INTO [Employee]\n"
                    + "           ([emoloyeeUsername]\n"
                    + "           ,[employeePassword]\n"
                    + "           ,[firstName]\n"
                    + "           ,[lastName]\n"
                    + "           ,[employeeAddress]\n"
                    + "           ,[employeePhone]\n"
                    + "           ,[employeeEmail]\n"
                    + "           ,[employeeImage]\n"
                    + "           ,[employeeDob]\n"
                    + "           ,[employeeRole]\n"
                    + "           ,[description])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm2 = connection.prepareStatement(sql);
            stm2.setString(1, e.getUsername());
            stm2.setString(2, e.getPassword());
            stm2.setString(3, e.getFirstname());
            stm2.setString(4, e.getLastname());
            stm2.setString(5, e.getAddress());
            stm2.setString(6, e.getPhone());
            stm2.setString(7, e.getEmail());
            stm2.setBytes(8, e.getImage());
            stm2.setDate(9, e.getDob());
            stm2.setString(10, e.getRole());
            stm2.setString(11, e.getDesciption());
            stm2.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateEmployee(Employee e) {
        String sql = "UPDATE [Employee]\n"
                + "   SET [emoloyeeUsername] = ?\n"
                + "      ,[employeePassword] = ?\n"
                + "      ,[firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[employeeAddress] = ?\n"
                + "      ,[employeePhone] = ?\n"
                + "      ,[employeeEmail] = ?\n"
                + "      ,[employeeDob] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[hireDate] = ?\n"
                + " WHERE [employeeID] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getUsername());
            stm.setString(2, e.getPassword());
            stm.setString(3, e.getFirstname());
            stm.setString(4, e.getLastname());
            stm.setString(5, e.getAddress());
            stm.setString(6, e.getPhone());
            stm.setString(7, e.getEmail());
            stm.setDate(8, e.getDob());
            stm.setString(9, e.getDesciption());
            stm.setDate(10, e.getHiredDate());
            stm.setInt(11, e.getId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteEmployee(int id) {
        String sql_delete_employee = "DELETE FROM employee\n"
                + "WHERE employeeID = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql_delete_employee);
            stm.setInt(1, id);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateImage(Employee e) {
        try {
            String sql_update = "update Employee\n"
                    + "set employeeImage = ?\n"
                    + "where employeeID = ?";
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setBytes(1, e.getImage());
            stm.setInt(2, e.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Employee> getEmployeesBySearch(int page, int limit, String searchQuery) {
        ArrayList<Employee> employees = new ArrayList<>();

        String query = "select * from\n"
                + "(select *, row_number() over (order by employeeid asc) as row_index from Employee \n";

        if (searchQuery != null && !searchQuery.equals("")) {
            query += " where firstname like ? or lastName like ? ";
        }

        query += " ) as em\n"
                + "where row_index >= (? - 1) * ? + 1 and row_index <= ? * ?";

        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(query);
            if (searchQuery != null && !searchQuery.equals("")) {
                statement.setString(1, "%" + searchQuery.replace(" ", "%") + "%");
                statement.setString(2, "%" + searchQuery.replace(" ", "%") + "%");
                statement.setInt(3, page);
                statement.setInt(4, limit);
                statement.setInt(5, page);
                statement.setInt(6, limit);
            } else {
                statement.setInt(1, page);
                statement.setInt(2, limit);
                statement.setInt(3, page);
                statement.setInt(4, limit);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employeeID"));
                e.setUsername(rs.getString("emoloyeeUsername"));
                e.setPassword(rs.getString("employeePassword"));
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setAddress(rs.getString("employeeAddress"));
                e.setPhone(rs.getString("employeePhone"));
                e.setEmail(rs.getString("employeeEmail"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                e.setDob(rs.getDate("employeeDob"));
                e.setRole(rs.getString("employeeRole"));
                e.setDesciption(rs.getString("description"));
                e.setHiredDate(rs.getDate("hireDate"));
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return employees;
    }

    public int getTotalRecords() {
        int totalRecords = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM Employee";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalRecords = resultSet.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRecords;
    }

    public void updateEmployeeProfile(Employee e) {
        String sql = "UPDATE [Employee]\n"
                + "  SET  [firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[employeeDob] = ?\n"
                + "      ,[employeeAddress] = ?\n"
                + "      ,[employeePhone] = ?\n"
                + " WHERE [employeeID] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getFirstname());
            stm.setString(2, e.getLastname());
            stm.setDate(3, e.getDob());
            stm.setString(4, e.getAddress());
            stm.setString(5, e.getPhone());
            stm.setInt(6, e.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void updateEmployeeEmail(Employee e) {
        String sql = "UPDATE [Employee]\n"
                + "  SET  [employeeEmail] = ?\n"
                + " WHERE [employeeID] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, e.getEmail());
            stm.setInt(2, e.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public void changePassword(String password, int id) {
        String sql = "UPDATE [Employee]\n"
                + "  SET  [employeePassword] = ?\n"
                + " WHERE [employeeID] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public ArrayList<Employee> getEmployeessByDates(Date from, Date to, int eid) {
        ArrayList<Employee> employees = new ArrayList<>();

        try {
            String sql = "SELECT\n"
                    + "    e.employeeID,e.firstName,e.lastName,e.emoloyeeUsername,e.employeePassword,e.employeeAddress,e.employeePhone,e.employeeEmail,\n"
                    + "	e.description,e.employeeDob,e.employeeImage,e.employeeRole,e.hireDate,\n"
                    + "    COALESCE(COUNT(o.orderID), 0) AS total_orders,\n"
                    + "    COALESCE(SUM(COALESCE(o.totalPrice, 0)), 0) AS total_amount\n"
                    + "FROM\n"
                    + "    Employee e\n"
                    + "    left JOIN [Order] o ON e.employeeID = o.createdBy\n"
                    + "WHERE\n"
                    + "	(o.orderStatusID ='3' or o.orderStatusID is null) and (o.orderDate between ? and ? )\n";
            if (eid > -1) {
                sql += "and e.employeeID =? \n";
            }
            sql += "GROUP BY\n"
                    + "     e.employeeID,e.firstName,e.lastName,e.emoloyeeUsername,e.employeePassword,e.employeeAddress,e.employeePhone,e.employeeEmail,\n"
                    + "	e.description,e.employeeDob,e.employeeImage,e.employeeRole,e.hireDate\n"
                    + "ORDER BY total_amount desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            if (eid > -1) {
                stm.setDate(1, from);
                stm.setDate(2, to);
                stm.setInt(3, eid);
            }
            stm.setDate(1, from);
            stm.setDate(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getInt("employeeID"));
                e.setUsername(rs.getString("emoloyeeUsername"));
                e.setPassword(rs.getString("employeePassword"));
                e.setFirstname(rs.getString("firstName"));
                e.setLastname(rs.getString("lastName"));
                e.setAddress(rs.getString("employeeAddress"));
                e.setPhone(rs.getString("employeePhone"));
                e.setEmail(rs.getString("employeeEmail"));
                byte[] imageEmployee = rs.getBytes("employeeImage");
                e.setImage(imageEmployee);
                if (imageEmployee != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageEmployee);
                    e.setEncodedImage(encodedImage);
                }
                e.setDob(rs.getDate("employeeDob"));
                e.setRole(rs.getString("employeeRole"));
                e.setDesciption(rs.getString("description"));
                e.setHiredDate(rs.getDate("hireDate"));
                e.setTotalAmount(rs.getFloat("total_amount"));
                e.setTotalOrder(rs.getInt("total_orders"));
                employees.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    public ArrayList<Order> getOrdersbyEmployee(int id, Date from, Date to) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "select o.orderID, o.orderDate, p.productID, p.productName, p.price,\n"
                + "od.quantity, o.totalPrice	\n"
                + "from Employee e\n"
                + "inner join [Order] o on o.createdBy = e.employeeID\n"
                + "inner join [OrderDetail] od on od.orderID = o.orderID\n"
                + "inner join Product p on p.productID = od.productID\n"
                + "where e.employeeID = ? and o.orderDate between ? and ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.setDate(2, from);
            stm.setDate(3, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderID"));
                o.setOrderDate(rs.getDate("orderDate"));

                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setPrice(rs.getInt("price"));

                o.setTotally(rs.getFloat("totalPrice"));

                OrderDetail od = new OrderDetail();
                od.setQuantity(rs.getInt("quantity"));
                od.setProducts(p);
                o.setOrderDetails(od);
                orders.add(o);

            }

        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}

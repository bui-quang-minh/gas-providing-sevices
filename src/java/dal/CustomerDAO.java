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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conversation;
import model.Customer;
import model.Order;
import model.OrderDetail;
import model.Product;

/**
 *
 * @author admin
 */
public class CustomerDAO extends DBContext {

    public Customer getCusByID(int id) {
        try {
            String sql = "SELECT\n"
                    + "    c.customerID, c.customerUsername, c.customerPassword, c.firstName, c.lastName, c.customerAddress, c.customerPhone,\n"
                    + "	   c.customerEmail, c.customerStatus, c.customerImage, c.customerDob, c.description, c.createdDate,\n"
                    + "    COALESCE(COUNT(o.orderID), 0) AS total_orders,\n"
                    + "    COALESCE(SUM(COALESCE(o.totalPrice, 0)), 0) AS total_amount\n"
                    + "FROM\n"
                    + "    Customer c\n"
                    + "    left JOIN [Order] o ON c.customerID = o.customerID\n"
                    + "WHERE\n"
                    + "	   (o.orderStatusID ='3' or o.orderStatusID is null) and c.customerID = ? \n"
                    + "GROUP BY\n"
                    + "    c.customerID, c.lastName, c.firstName, c.createdDate, c.customerAddress, c.customerDob,c.customerEmail, c.customerImage,\n"
                    + "	   c.customerPassword,c.customerPhone,c.customerStatus,c.customerUsername,c.description\n"
                    + "ORDER BY total_amount desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));
                c.setTotalOrder(rs.getInt("total_orders"));
                c.setTotalAmount(rs.getFloat("total_amount"));

                return c;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Customer getCustomer(String username, String password) {
        try {
            String sql = "select * from Customer c \n"
                    + "left join Conversations co on co.customerID = c.customerID \n"
                    + "where customerUsername COLLATE SQL_Latin1_General_CP1_CS_AS LIKE ? and customerPassword COLLATE SQL_Latin1_General_CP1_CS_AS LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));
                Conversation co = new Conversation();
                co.setId(rs.getInt("conversationID"));
                c.setConversation(co);

                return c;
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void updateCustomer(Customer cus) throws SQLException {
        String sql = "UPDATE Customer set firstName=?,lastName=?,customerDob=?,customerEmail=?, customerImage=? where customerID=?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, cus.getFirstname());
        stm.setString(2, cus.getLastname());
        stm.setDate(3, cus.getDob());
        stm.setString(4, cus.getEmail());
        stm.setBytes(5, cus.getImage());
        stm.setInt(6, cus.getId());
        stm.execute();
    }

    public void updateCustomerPassword(Customer cus) throws SQLException {
        String sql = "UPDATE Customer set customerPassword=? where customerID=?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, cus.getPassword());
        stm.setInt(2, cus.getId());
        stm.execute();
    }

    public void insertCustomer(Customer c) {

        try {
            String sql = "INSERT INTO [dbo].[Customer]\n"
                    + "           ([customerUsername]\n"
                    + "           ,[customerPassword]\n"
                    + "           ,[firstName]\n"
                    + "           ,[lastName]\n"
                    + "           ,[customerAddress]\n"
                    + "           ,[customerPhone]\n"
                    + "           ,[customerEmail]\n"
                    + "           ,[customerStatus]\n"
                    + "           ,[customerImage]\n"
                    + "           ,[customerDob]\n"
                    + "           ,[description])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stm2 = connection.prepareStatement(sql);
            stm2.setString(1, c.getUsername());
            stm2.setString(2, c.getPassword());
            stm2.setString(3, c.getFirstname());
            stm2.setString(4, c.getLastname());
            stm2.setString(5, c.getAddress());
            stm2.setString(6, c.getPhone());
            stm2.setString(7, c.getEmail());
            stm2.setString(8, "Active");
            stm2.setBytes(9, c.getImage());
            stm2.setDate(10, c.getDob());
            stm2.setString(11, c.getDesciption());
            int id = stm2.executeUpdate();

            if (id > 0) {
                String sql1 = "select customerID from Customer where customerUsername = ?";
                PreparedStatement stm = connection.prepareStatement(sql1);
                stm.setString(1, c.getUsername());
                ResultSet rs = stm.executeQuery();
                if (rs.next()) {
                    int cid = rs.getInt("customerID");
                    String sql2 = "INSERT INTO Conversations (customerID) VALUES (?)";
                    PreparedStatement stm1 = connection.prepareStatement(sql2);
                    stm1.setInt(1, cid);
                    stm1.executeUpdate();

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetPassword(String email, String password) {
        String sql = "update Customer\n"
                + "set customerPassword = ? \n"
                + "where customerEmail = ?";
        PreparedStatement stm;
        try {
            stm = connection.prepareStatement(sql);
            stm.setString(1, password);
            stm.setString(2, email);
            stm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getCustomerUsername(String username) {
        try {
            String sql = "select customerUsername from Customer \n"
                    + "where customerUsername = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {

                return rs.getString("customerUsername");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public Customer getCustomer(String email) {
        try {
            String sql = "select * from Customer c\n"
                    + "left join Conversations co on co.customerID = c.customerID \n"
                    + "where customerEmail= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));
                Conversation co = new Conversation();
                co.setId(rs.getInt("conversationID"));
                c.setConversation(co);
                return c;

            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public ArrayList<Customer> getCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    c.customerID, c.customerUsername, c.customerPassword, c.firstName, c.lastName, c.customerAddress, c.customerPhone,\n"
                    + "	c.customerEmail, c.customerStatus, c.customerImage, c.customerDob, c.description, c.createdDate,\n"
                    + "    COALESCE(COUNT(o.orderID), 0) AS total_orders,\n"
                    + "    COALESCE(SUM(COALESCE(o.totalPrice, 0)), 0) AS total_amount\n"
                    + "FROM\n"
                    + "    Customer c\n"
                    + "    left JOIN [Order] o ON c.customerID = o.customerID\n"
                    + "WHERE\n"
                    + "	(o.orderStatusID ='3' or o.orderStatusID is null)\n"
                    + "GROUP BY\n"
                    + "    c.customerID, c.lastName, c.firstName, c.createdDate, c.customerAddress, c.customerDob,c.customerEmail, c.customerImage,\n"
                    + "	c.customerPassword,c.customerPhone,c.customerStatus,c.customerUsername,c.description\n"
                    + "ORDER BY total_amount desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));
                c.setTotalOrder(rs.getInt("total_orders"));
                c.setTotalAmount(rs.getFloat("total_amount"));
                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    public void deleteCustomer(int id) {
        String sql = "DELETE FROM Customer\n"
                + "WHERE customerID = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Customer getCustomer(int id) {
        try {
            String sql = "select * from Customer\n"
                    + "where customerID= ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));
                return c;

            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public void updateDetail(Customer c) {
        String sql = "UPDATE [Customer]\n"
                + "   SET [customerUsername] = ?\n"
                + "      ,[customerPassword] = ?\n"
                + "      ,[firstName] = ?\n"
                + "      ,[lastName] = ?\n"
                + "      ,[customerAddress] = ?\n"
                + "      ,[customerPhone] = ?\n"
                + "      ,[customerEmail] = ?\n"
                + "      ,[customerDob] = ?\n"
                + "      ,[customerStatus] = ?\n"
                + " WHERE [customerID] = ?";

        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getUsername());
            stm.setString(2, c.getPassword());
            stm.setString(3, c.getFirstname());
            stm.setString(4, c.getLastname());
            stm.setString(5, c.getAddress());
            stm.setString(6, c.getPhone());
            stm.setString(7, c.getEmail());
            stm.setDate(8, c.getDob());
            stm.setString(9, c.getStatus());
            stm.setInt(10, c.getId());

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateImage(Customer c) {
        try {
            String sql_update = "update Customer\n"
                    + "set customerImage = ?\n"
                    + "where customerID = ?";
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setBytes(1, c.getImage());
            stm.setInt(2, c.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Customer> getCustomersBySearch(int page, int limit, String searchQuery) {
        ArrayList<Customer> customers = new ArrayList<>();

        String query = "select * from\n"
                + "(select *, row_number() over (order by customerID asc) as row_index from Customer \n";

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
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));

                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return customers;
    }

    public int getTotalRecords() {
        int totalRecords = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM Customer";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalRecords = resultSet.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRecords;
    }

    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Customer]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setEmail(rs.getString("customerEmail"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int getNumberOfCustomer() {
        int count = 0;
        String sql = "Select Count(customerID) As num\n"
                + "From Customer";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                count = rs.getInt("num");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return count;
    }

    public ArrayList<Customer> getCustomersByDates(Date from, Date to) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            String sql = "SELECT\n"
                    + "    c.customerID, c.customerUsername, c.customerPassword, c.firstName, c.lastName, c.customerAddress, c.customerPhone,\n"
                    + "	c.customerEmail, c.customerStatus, c.customerImage, c.customerDob, c.description, c.createdDate,\n"
                    + "    COALESCE(COUNT(o.orderID), 0) AS total_orders,\n"
                    + "    COALESCE(SUM(COALESCE(o.totalPrice, 0)), 0) AS total_amount\n"
                    + "FROM\n"
                    + "    Customer c\n"
                    + "    left JOIN [Order] o ON c.customerID = o.customerID\n"
                    + "WHERE\n"
                    + "	(o.orderStatusID ='3' or o.orderStatusID is null) and (o.orderDate between ? and ? )\n"
                    + "GROUP BY\n"
                    + "    c.customerID, c.lastName, c.firstName, c.createdDate, c.customerAddress, c.customerDob,c.customerEmail, c.customerImage,\n"
                    + "	c.customerPassword,c.customerPhone,c.customerStatus,c.customerUsername,c.description\n"
                    + "ORDER BY total_amount desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setDate(1, from);
            stm.setDate(2, to);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customerID"));
                c.setUsername(rs.getString("customerUsername"));
                c.setPassword(rs.getString("customerPassword"));
                c.setFirstname(rs.getString("firstName"));
                c.setLastname(rs.getString("lastName"));
                c.setAddress(rs.getString("customerAddress"));
                c.setPhone(rs.getString("customerPhone"));
                c.setEmail(rs.getString("customerEmail"));
                c.setStatus(rs.getString("customerStatus"));
                byte[] imageCustomer = rs.getBytes("customerImage");
                c.setImage(imageCustomer);
                if (imageCustomer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageCustomer);
                    c.setEncodedImage(encodedImage);
                }
                c.setDob(rs.getDate("customerDob"));
                c.setDesciption(rs.getString("description"));
                c.setCreatedDate(rs.getDate("createdDate"));
                c.setTotalOrder(rs.getInt("total_orders"));
                c.setTotalAmount(rs.getFloat("total_amount"));
                customers.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;

    }

    public Date getMinDate() {
        try {
            String sql = "select min(createdDate) as createdDate from Customer";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getDate("createdDate");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Order> getOrdersbyCustomer(int id, Date from, Date to) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "select o.orderID, o.orderDate, p.productID, p.productName, p.price,\n"
                + "	   od.quantity, o.totalPrice		\n"
                + "from Customer c\n"
                + "inner join [Order] o on o.customerID = c.customerID\n"
                + "inner join [OrderDetail] od on od.orderID = o.orderID\n"
                + "inner join Product p on p.productID = od.productID\n"
                + "where c.customerID = ? and o.orderDate between ? and ?";
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
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

}

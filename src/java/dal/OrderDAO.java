/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Order;

/**
 *
 * @author minh
 */
public class OrderDAO extends DBContext {

    public int getTotalOrder() {
        String sql = "Select count(*) from [Order]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Order> pagingOrder(int index) {
        List<Order> list = new ArrayList<>();
        String sql = "Select * from [Order]\n"
                + "ORDER by orderStatusID, orderDate desc\n"
                + "offset ? rows fetch next 8 rows only;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (index - 1) * 8);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderID"));
                o.setCustomerID(rs.getInt("customerID"));
                o.setOrderDate(rs.getDate("orderDate"));
                o.setShipName(rs.getString("shipName"));
                o.setCreatedBy(rs.getInt("createdBy"));
                o.setShipAddress(rs.getString("shipAddress"));
                o.setOrderStatus(rs.getInt("orderStatusID"));
                o.setTotally(rs.getFloat("totalPrice"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Order> pagingOrderCus(int index, int id) {
        List<Order> list = new ArrayList<>();
        String sql = "Select * from [Order] where customerID=? ORDER by orderID  offset ? rows fetch next 3 rows only ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.setInt(2, (index - 1) * 3);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderID"));
                o.setCustomerID(rs.getInt("customerID"));
                o.setOrderDate(rs.getDate("orderDate"));
                o.setShipName(rs.getString("shipName"));
                o.setCreatedBy(rs.getInt("createdBy"));
                o.setShipAddress(rs.getString("shipAddress"));
                o.setOrderStatus(rs.getInt("orderStatusID"));
                o.setTotally(rs.getFloat("totalPrice"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * from [Order]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderID"));
                o.setCustomerID(rs.getInt("customerID"));
                o.setOrderDate(rs.getDate("orderDate"));
                o.setShipName(rs.getString("shipName"));
                o.setCreatedBy(rs.getInt("createdBy"));
                o.setShipAddress(rs.getString("shipAddress"));
                o.setOrderStatus(rs.getInt("orderStatusID"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateStatus(int statusID, int OrderID, int EmpID) throws SQLException {
        String sql = "UPDATE [Order] set orderStatusID=?,createdBy=?,deliveredDate=? where orderID=?";
        PreparedStatement stm = connection.prepareStatement(sql);
        LocalDate date = LocalDate.now();
        Date formatDate = Date.valueOf(date);
        stm.setInt(1, statusID);
        stm.setInt(2, EmpID);
        stm.setDate(3, formatDate);
        stm.setInt(4, OrderID);
        stm.execute();
    }

    public List<Order> getAllByCus(int cusID) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * from [Order] where customerID=? Order by OrderID Desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cusID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setOrderID(rs.getInt("orderID"));
                o.setCustomerID(rs.getInt("customerID"));
                o.setOrderDate(rs.getDate("orderDate"));
                o.setDeliveredDate(rs.getDate("deliveredDate"));
                o.setShipName(rs.getString("shipName"));
                o.setCreatedBy(rs.getInt("createdBy"));
                o.setShipAddress(rs.getString("shipAddress"));
                o.setOrderStatus(rs.getInt("orderStatusID"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void confirmOrder(Order o) {

        String sql = "INSERT INTO [dbo].[Order] VALUES(?,?,null,?,?,?,?,null)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, o.getCustomerID());
            st.setDate(2, o.getOrderDate());
            st.setString(3, o.getShipName());
            st.setString(4, o.getShipAddress());
            st.setInt(5, o.getOrderStatus());
            st.setFloat(6, o.getTotally());

            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public int lastOrderID() {

        int last = 0;
        String sql = "SELECT TOP (1) orderID\n"
                + "FROM [Order]\n"
                + "ORDER BY [Order].orderID DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                last = rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return last;

    }

    public List<Object[]> listByMonth(int year) {
        List<Object[]> result = new ArrayList<>();

        String sql = "SELECT COALESCE(SUM(od.quantity), 0) AS quantity, m.[month], m.[year]\n"
                + "FROM (\n"
                + "  SELECT 1 AS [month], " + year + " AS [year]\n"
                + "  UNION ALL SELECT 2, " + year + "\n"
                + "  UNION ALL SELECT 3, " + year + "\n"
                + "  UNION ALL SELECT 4, " + year + "\n"
                + "  UNION ALL SELECT 5, " + year + "\n"
                + "  UNION ALL SELECT 6, " + year + "\n"
                + "  UNION ALL SELECT 7, " + year + "\n"
                + "  UNION ALL SELECT 8, " + year + "\n"
                + "  UNION ALL SELECT 9, " + year + "\n"
                + "  UNION ALL SELECT 10, " + year + "\n"
                + "  UNION ALL SELECT 11, " + year + "\n"
                + "  UNION ALL SELECT 12, " + year + "\n"
                + ") m\n"
                + "LEFT JOIN [Order] o ON m.[year] = YEAR(o.orderDate) AND m.[month] = MONTH(o.orderDate)\n"
                + "LEFT JOIN OrderDetail od ON o.orderID = od.orderID\n"
                + "GROUP BY m.[month], m.[year]\n"
                + "ORDER BY m.[year], m.[month]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[3]; // Assuming there are 3 values to retrieve

                row[0] = rs.getInt("month");
                row[1] = rs.getInt("year");
                row[2] = rs.getInt("quantity");

                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public void cancelOrder(int OrderID) throws SQLException {
        String sql = "UPDATE [Order] set orderStatusID=? where orderID=?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, 4);
        stm.setInt(2, OrderID);
        stm.execute();
    }
}

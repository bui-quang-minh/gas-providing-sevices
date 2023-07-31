/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author minh
 */
public class OrderDetailDAO extends DBContext {

    public List<OrderDetail> getAll() {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT * from OrderDetail";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderDetail o = new OrderDetail();
                o.setOrderDetailID(rs.getInt("orderDetailID"));
                o.setOrderID(rs.getInt("orderID"));
                o.setProductID(rs.getInt("productID"));
                o.setQuantity(rs.getInt("quantity"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void add(int orderID, int productID, int quantity) {
        String sql = "INSERT INTO [dbo].[OrderDetail]\n"
                + "           ([orderID]\n"
                + "           ,[productID]\n"
                + "           ,[quantity])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            st.setInt(2, productID);
            st.setInt(3, quantity);
            st.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public int getLast() throws SQLException{
        String sql = "Select top 1 OrderID from [Order] ORDER by orderID Desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("OrderID");
            }
        return -1;
    }
    public int totalSales() {
        int total = 0;
        String sql = "Select sum(quantity) as quantity\n"
                + "from [OrderDetail]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                total = rs.getInt("quantity");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return total;
    }
}
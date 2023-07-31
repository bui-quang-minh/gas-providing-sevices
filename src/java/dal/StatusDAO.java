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
import model.Order;
import model.OrderStatus;

/**
 *
 * @author minh
 */
public class StatusDAO extends DBContext{
    public List<OrderStatus> getAll() {
        List<OrderStatus> list = new ArrayList<>();
        String sql = "SELECT * from [OrderStatus]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderStatus o = new OrderStatus();
                o.setOrderStatusId(rs.getInt("orderStatusID"));
                o.setOrderStatusName(rs.getString("orderStatusName"));
                list.add(o);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public OrderStatus getStatus(int orderID) {
        String sql = "SELECT * from [OrderStatus] where orderStatusID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                OrderStatus o = new OrderStatus();
                o.setOrderStatusId(rs.getInt("orderStatusID"));
                o.setOrderStatusName(rs.getString("orderStatusName"));
                return o;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
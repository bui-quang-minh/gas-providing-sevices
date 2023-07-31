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
import java.util.List;
import model.Warranty;

/**
 *
 * @author An
 */
public class WarrantyDAO extends DBContext {

    public static void main(String[] args) {
        WarrantyDAO wdao = new WarrantyDAO();
        System.out.println(wdao.getWarrantybyCustomerID(3).get(1).getWarrantyID());
    }

    public void addwarranty(Warranty wa) {
        String sql = "INSERT INTO [dbo].[Warranty]\n"
                + "           ([productID]\n"
                + "           ,[customerID]\n"
                + "           ,[purchasedDate]\n"
                + "           ,[expirationDate]\n"
                + "           ,[status])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, wa.getProductID());
            st.setInt(2, wa.getCustomerID());
            st.setDate(3, (Date) wa.getPurchasedDate());
            st.setDate(4, (Date) wa.getExpirationDate());
            st.setFloat(5, 1);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Warranty> getAll() {
        List<Warranty> list = new ArrayList<>();
        String sql = "SELECT *"
                + "FROM [SQL_SWP_5].[dbo].[Warranty]\n"
                + "ORDER BY [expirationDate]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Warranty wa = new Warranty();
                wa.setWarrantyID(rs.getInt("warrantyID"));
                wa.setProductID(rs.getInt("productID"));
                wa.setCustomerID(rs.getInt("customerID"));
                wa.setPurchasedDate(rs.getDate("purchasedDate"));
                wa.setExpirationDate(rs.getDate("expirationDate"));
                wa.setStatus(rs.getInt("status"));
                wa.setModifiedDate(rs.getDate("modifiedDate"));
                wa.setModifiedBy(rs.getInt("modifiedBy"));
                wa.setWarrantycondition(rs.getString("Condition"));
                wa.setPrice(rs.getInt("Price"));
                list.add(wa);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void changestatus(int warrantyID, int statusold) {

        String sql = "UPDATE [dbo].[Warranty]\n"
                + "   SET [status] = ?\n"
                + " WHERE [warrantyID]=" + warrantyID;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusold);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void updateWarrantyConditionAndPrice(int warrantyID, String condition, int price) {
        String sql = "UPDATE [dbo].[Warranty] SET [Condition] = ?, [Price] = ? WHERE [warrantyID] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, condition);
            st.setInt(2, price);
            st.setInt(3, warrantyID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public List<Warranty> getWarrantybyCustomerID(int CustomerID) {
        List<Warranty> list = new ArrayList<>();
        String sql = "SELECT *"
                + "FROM [SQL_SWP_5].[dbo].[Warranty]\n"
                + "Where customerID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, CustomerID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Warranty wa = new Warranty();
                wa.setWarrantyID(rs.getInt("warrantyID"));
                wa.setProductID(rs.getInt("productID"));
                wa.setCustomerID(rs.getInt("customerID"));
                wa.setPurchasedDate(rs.getDate("purchasedDate"));
                wa.setExpirationDate(rs.getDate("expirationDate"));
                wa.setStatus(rs.getInt("status"));
                wa.setModifiedDate(rs.getDate("modifiedDate"));
                wa.setModifiedBy(rs.getInt("modifiedBy"));
                wa.setWarrantycondition(rs.getString("Condition"));
                wa.setPrice(rs.getInt("Price"));
                list.add(wa);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
}

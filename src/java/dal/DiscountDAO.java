/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import model.Discount;

/**
 *
 * @author Minh Bui
 */
public class DiscountDAO extends DBContext {

    public List<Discount> getAllAvalibleDiscount() {
        List<Discount> listDiscount = new ArrayList<>();
        String sql = "SELECT [discountID]\n"
                + "      ,[discountName]\n"
                + "      ,[discountValue]\n"
                + "      ,[productID]\n"
                + "      ,[startDate]\n"
                + "      ,[endDate]\n"
                + "      ,[createdDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[modifiedBy]\n"
                + "FROM [dbo].[Discount]\n"
                + "WHERE GETDATE() >= [startDate] AND GETDATE() <= [endDate]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount d = new Discount();
                d.setDiscountID(rs.getInt("discountID"));
                d.setDiscountName(rs.getString("discountName"));
                d.setDiscountValue(rs.getInt("discountValue"));
                d.setProductID(rs.getInt("productID"));
                d.setStartDate(rs.getDate("startDate"));
                d.setEndDate(rs.getDate("endDate"));
                d.setCreatedDate(rs.getDate("createdDate"));
                d.setModifiedDate(rs.getDate("modifiedDate"));
                d.setCreatedBy(rs.getInt("createdBy"));
                d.setModifiedBy(rs.getInt("modifiedBy"));
                listDiscount.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listDiscount;
    }

    public List<Discount> getAll() {
        List<Discount> listDiscount = new ArrayList<>();
        String sql = "SELECT [discountID]\n"
                + "      ,[discountName]\n"
                + "      ,[discountValue]\n"
                + "      ,[productID]\n"
                + "      ,[startDate]\n"
                + "      ,[endDate]\n"
                + "      ,[createdDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[modifiedBy]\n"
                + "  FROM [dbo].[Discount]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount d = new Discount();
                d.setDiscountID(rs.getInt("discountID"));
                d.setDiscountName(rs.getString("discountName"));
                d.setDiscountValue(rs.getInt("discountValue"));
                d.setProductID(rs.getInt("productID"));
                d.setStartDate(rs.getDate("startDate"));
                d.setEndDate(rs.getDate("endDate"));
                d.setCreatedDate(rs.getDate("createdDate"));
                d.setModifiedDate(rs.getDate("modifiedDate"));
                d.setCreatedBy(rs.getInt("createdBy"));
                d.setModifiedBy(rs.getInt("modifiedBy"));
                listDiscount.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listDiscount;
    }

    public void changeDiscount(Discount d) {
        String sql = "UPDATE [dbo].[Discount]\n"
                + "   SET [discountName] = ?\n"
                + "      ,[discountValue] = ?\n"
                + "      ,[productID] = ?\n"
                + "      ,[startDate] = ?\n"
                + "      ,[endDate] = ?\n"
                + "      ,[modifiedDate] = getdate()\n"
                + "      ,[modifiedBy] = ?\n"
                + " WHERE discountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, d.getDiscountName());
            st.setInt(2, d.getDiscountValue());
            st.setInt(3, d.getProductID());
            st.setDate(4, new java.sql.Date(d.getStartDate().getTime()));
            st.setDate(5, new java.sql.Date(d.getEndDate().getTime()));
            st.setInt(6, d.getModifiedBy());
            st.setInt(7, d.getDiscountID());
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void deleteDiscount(int discountID) {
        String sql = "DELETE FROM [dbo].[Discount]\n"
                + "      WHERE discountID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, discountID);
            st.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void createDiscount(Discount d) {
        String sql = "INSERT INTO [dbo].[Discount]\n"
                + "           ([discountName]\n"
                + "           ,[discountValue]\n"
                + "           ,[productID]\n"
                + "           ,[startDate]\n"
                + "           ,[endDate]\n"
                + "           ,[createdBy]\n"
                + "           ,[modifiedBy])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, d.getDiscountName());
            st.setInt(2, d.getDiscountValue());
            st.setInt(3, d.getProductID());
            st.setDate(4, new java.sql.Date(d.getStartDate().getTime()));
            st.setDate(5, new java.sql.Date(d.getEndDate().getTime()));
            st.setInt(6, d.getCreatedBy());
            st.setInt(7, d.getModifiedBy());
            st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<Discount> getDiscount() {
        List<Discount> list = new ArrayList<>();
        String sql = "SELECT *"
                + "  FROM [SQL_SWP_5].[dbo].[Discount]\n"
                + "  order by [Discount].discountValue desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount d = new Discount();
                d.setDiscountID(rs.getInt("discountID"));
                d.setDiscountName(rs.getString("discountName"));
                d.setDiscountValue(rs.getInt("discountValue"));
                d.setProductID(rs.getInt("productID"));
                d.setStartDate(rs.getDate("startDate"));
                d.setEndDate(rs.getDate("endDate"));
                d.setCreatedBy(rs.getInt("createdBy"));
                d.setModifiedDate(rs.getDate("modifiedDate"));
                d.setModifiedBy(rs.getInt("modifiedBy"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Discount> getTop3Discount() {
        List<Discount> list = new ArrayList<>();
        String sql = "SELECT D.[discountID], D.[discountName], D.[discountValue], "
                + "D.[productID], D.[startDate], D.[endDate], D.[createdDate], "
                + "D.[createdBy], D.[modifiedDate], D.[modifiedBy]\n"
                + "FROM [SQL_SWP_5].[dbo].[Discount] AS D\n"
                + "INNER JOIN [SQL_SWP_5].[dbo].[Product] AS P\n"
                + "ON D.[productID] = P.[productID]\n"
                + "WHERE GETDATE() BETWEEN D.[startDate] AND D.[endDate]\n"
                + "ORDER BY P.[price]/100 * (100-D.[discountValue])";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Discount d = new Discount();
                d.setDiscountID(rs.getInt("discountID"));
                d.setDiscountName(rs.getString("discountName"));
                d.setDiscountValue(rs.getInt("discountValue"));
                d.setProductID(rs.getInt("productID"));
                d.setStartDate(rs.getDate("startDate"));
                d.setEndDate(rs.getDate("endDate"));
                d.setCreatedBy(rs.getInt("createdBy"));
                d.setModifiedDate(rs.getDate("modifiedDate"));
                d.setModifiedBy(rs.getInt("modifiedBy"));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}

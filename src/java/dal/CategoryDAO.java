
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Employee;

/**
 *
 * @author Minh Bui
 */
public class CategoryDAO extends DBContext {

    /*
    CREATED BY: Bui Quang Minh 
    DATE: 14/5/2023
    STATUS: Done
    DESCRIPTION: Get all properties of product
     */
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT [categoryID]\n"
                + "      ,[categoryName]\n"
                + "      ,[createdBy]\n"
                + "      ,[createdDate]\n"
                + "      ,[description]\n"
                + "      ,[modifiedBy]\n"
                + "      ,[modifiedDate]\n"
                + "  FROM [dbo].[Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setCategoryID(rs.getInt("categoryID"));
                c.setCategoryName(rs.getString("categoryName"));
                c.setDescription(rs.getString("description"));
                c.setCreatedBy(rs.getInt("createdBy"));
                c.setModifiedBy(rs.getInt("modifiedBy"));
                c.setCreatedDate(rs.getDate("createdDate"));
                c.setModifiedDate(rs.getDate("modifiedDate"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void insertCategory(Category c) {
        try {
            String sql = "INSERT INTO [dbo].[Category]\n"
                    + "           ([categoryName]\n"
                    + "           ,[createdBy]\n"
                    + "           ,[description]\n"
                    + "           ,[modifiedBy])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCategoryName());
            stm.setInt(2, c.getCreatedBy());
            stm.setString(3, c.getDescription());
            stm.setInt(4, c.getModifiedBy());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteCategory(int id) {
        String sql_update = "update Product\n"
                + "set categoryID = NULL\n"
                + "where categoryID = ?";
        String sql_delete = "DELETE FROM [Category]\n"
                + "      WHERE categoryID = ? ";

        try {
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setInt(1, id);
            int i = stm.executeUpdate();
            if (i > 0) {
                PreparedStatement stm1 = connection.prepareStatement(sql_delete);
                stm1.setInt(1, id);
                stm1.executeUpdate();
            } else {
                PreparedStatement stm1 = connection.prepareStatement(sql_delete);
                stm1.setInt(1, id);
                stm1.executeUpdate();
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateCategory(Category c) {
        try {
            String sql = "UPDATE [Category]\n"
                    + "   SET [categoryName] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[modifiedBy] = ?\n"
                    + "      ,[modifiedDate] = getdate()"
                    + " WHERE categoryID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, c.getCategoryName());
            stm.setString(2, c.getDescription());
            stm.setInt(3, c.getModifiedBy());
            stm.setInt(4, c.getCategoryID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
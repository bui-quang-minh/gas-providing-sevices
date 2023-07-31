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
import model.Manufacturer;

/**
 *
 * @author Minh Bui
 */
public class ManufacturerDAO extends DBContext {

    /*
    CREATED BY: Bui Quang Minh 
    DATE: 14/5/2023
    STATUS: Done
    DESCRIPTION: Get all properties of manufacturer
     */
    public List<Manufacturer> getAll() {
        List<Manufacturer> list = new ArrayList<>();
        String sql = "SELECT [manufacturerID]\n"
                + "      ,[manufacturerName]\n"
                + "      ,[manufacturerAddress]\n"
                + "      ,[manufacturerPhone]\n"
                + "      ,[manufacturerEmail]\n"
                + "      ,[manufacturerImage]\n"
                + "      ,[description]\n"
                + "      ,[createdDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[modifiedBy]\n"
                + "  FROM [dbo].[Manufacturer]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Manufacturer m = new Manufacturer();
                m.setManufacturerID(rs.getInt("manufacturerID"));
                m.setManufacturerName(rs.getString("manufacturerName"));
                m.setManufacturerAddress(rs.getString("manufacturerAddress"));
                m.setManufacturerPhone(rs.getString("manufacturerPhone"));
                m.setManufacturerEmail(rs.getString("manufacturerEmail"));

                byte[] imageManufacturer = rs.getBytes("manufacturerImage");
                m.setManufacturerImage(imageManufacturer);
                if (imageManufacturer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageManufacturer);
                    m.setEncodedManufacturerImage(encodedImage);
                } else {
                    // handle the case where image is null
                }
                m.setDescription(rs.getString("description"));
                m.setCreatedDate(rs.getDate("createdDate"));
                m.setCreatedBy(rs.getInt("createdBy"));
                m.setModifiedDate(rs.getDate("modifiedDate"));
                m.setModifiedBy(rs.getInt("modifiedBy"));

                list.add(m);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Manufacturer getMan(int id) {
        String sql = "SELECT * FROM [dbo].[Manufacturer] where manufacturerID=" + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Manufacturer m = new Manufacturer();
                m.setManufacturerID(rs.getInt("manufacturerID"));
                m.setManufacturerName(rs.getString("manufacturerName"));
                m.setManufacturerAddress(rs.getString("manufacturerAddress"));
                m.setManufacturerPhone(rs.getString("manufacturerPhone"));
                m.setManufacturerEmail(rs.getString("manufacturerEmail"));

                byte[] imageManufacturer = rs.getBytes("manufacturerImage");
                m.setManufacturerImage(imageManufacturer);
                if (imageManufacturer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageManufacturer);
                    m.setEncodedManufacturerImage(encodedImage);
                } else {
                    // handle the case where image is null
                }
                m.setDescription(rs.getString("description"));
                m.setCreatedDate(rs.getDate("createdDate"));
                m.setCreatedBy(rs.getInt("createdBy"));
                m.setModifiedDate(rs.getDate("modifiedDate"));
                m.setModifiedBy(rs.getInt("modifiedBy"));

                return m;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void deleteManufacturer(int id) {
        String sql_update = "update Product\n"
                + "set manufacturerID = NULL\n"
                + "where manufacturerID = ?";
        String sql_delete = "DELETE FROM [Manufacturer]\n"
                + "      WHERE manufacturerID = ? ";

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
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertManufacturer(Manufacturer m) {
        try {
            String sql = "INSERT INTO [Manufacturer]\n"
                    + "           ([manufacturerName]\n"
                    + "           ,[manufacturerAddress]\n"
                    + "           ,[manufacturerPhone]\n"
                    + "           ,[manufacturerEmail]\n"
                    + "           ,[manufacturerImage]\n"
                    + "           ,[description]\n"
                    + "           ,[createdBy]\n"
                    + "           ,[modifiedBy])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, m.getManufacturerName());
            stm.setString(2, m.getManufacturerAddress());
            stm.setString(3, m.getManufacturerPhone());
            stm.setString(4, m.getManufacturerEmail());
            stm.setBytes(5, m.getManufacturerImage());
            stm.setString(6, m.getDescription());
            stm.setInt(7, m.getCreatedBy());
            stm.setInt(8, m.getModifiedBy());
            stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateManufacturer(Manufacturer m) {
        try {
            String sql = "UPDATE [Manufacturer]\n"
                    + "   SET [manufacturerName] = ?\n"
                    + "      ,[manufacturerAddress] = ?\n"
                    + "      ,[manufacturerPhone] = ?\n"
                    + "      ,[manufacturerEmail] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[modifiedDate] = getdate()\n"
                    + "      ,[modifiedBy] = ?\n"
                    + " WHERE [manufacturerID] = ?";
            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, m.getManufacturerName());
            stm.setString(2, m.getManufacturerAddress());
            stm.setString(3, m.getManufacturerPhone());
            stm.setString(4, m.getManufacturerEmail());
            stm.setString(5, m.getDescription());
            stm.setInt(6, m.getModifiedBy());
            stm.setInt(7, m.getManufacturerID());
            stm.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateImage(Manufacturer m) {
        try {
            String sql_update = "update Manufacturer\n"
                    + "set manufacturerImage = ?\n"
                    + "where manufacturerID = ?";
            PreparedStatement stm = connection.prepareStatement(sql_update);
            stm.setBytes(1, m.getManufacturerImage());
            stm.setInt(2, m.getManufacturerID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Manufacturer> getManufacturersBySearch(int page, int limit, String searchQuery) {
        ArrayList<Manufacturer> manufacturers = new ArrayList<>();

        String query = "select * from\n"
                + "(select *, row_number() over (order by manufacturerID asc) as row_index from Manufacturer \n";

        if (searchQuery != null && !searchQuery.equals("")) {
            query += " where manufacturerName like ? or manufacturerPhone like ? ";
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
                Manufacturer m = new Manufacturer();
                m.setManufacturerID(rs.getInt("manufacturerID"));
                m.setManufacturerName(rs.getString("manufacturerName"));
                m.setManufacturerAddress(rs.getString("manufacturerAddress"));
                m.setManufacturerPhone(rs.getString("manufacturerPhone"));
                m.setManufacturerEmail(rs.getString("manufacturerEmail"));

                byte[] imageManufacturer = rs.getBytes("manufacturerImage");
                m.setManufacturerImage(imageManufacturer);
                if (imageManufacturer != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageManufacturer);
                    m.setEncodedManufacturerImage(encodedImage);
                } else {
                    // handle the case where image is null
                }
                m.setDescription(rs.getString("description"));
                m.setCreatedDate(rs.getDate("createdDate"));
                m.setCreatedBy(rs.getInt("createdBy"));
                m.setModifiedDate(rs.getDate("modifiedDate"));
                m.setModifiedBy(rs.getInt("modifiedBy"));
                manufacturers.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return manufacturers;
    }

    public int getTotalRecords() {
        int totalRecords = 0;
        try {
            String query = "SELECT COUNT(*) AS total FROM Manufacturer";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                totalRecords = resultSet.getInt("total");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ManufacturerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalRecords;
    }
    
}
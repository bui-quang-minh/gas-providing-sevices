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

import model.ProductImage;

/**
 *
 * @author Minh Bui Test branch
 */
public class ProductImageDAO extends DBContext {

    public List<ProductImage> getAllImage() {
        List<ProductImage> list = new ArrayList<>();
        String sql = "Select * from ProductImage";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductImage pi = new ProductImage();
                pi.setProductImageID(rs.getInt("productImageID"));
                pi.setProductID(rs.getInt("productID"));
                byte[] imageProduct = rs.getBytes("productImage");
                pi.setProductImage(imageProduct);
                if (imageProduct != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageProduct);
                    pi.setEncodedProductImage(encodedImage);
                } else {
                    // handle the case where image is null
                }
                pi.setImageType(rs.getString("imageType"));
                list.add(pi);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ProductImage> getImageByID(String productID) {
        List<ProductImage> list = new ArrayList<>();
        String sql = "Select * from ProductImage where productID = " + productID + "";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductImage pi = new ProductImage();
                pi.setProductImageID(rs.getInt("productImageID"));
                pi.setProductID(rs.getInt("productID"));
                byte[] imageProduct = rs.getBytes("productImage");
                pi.setProductImage(imageProduct);
                if (imageProduct != null) {
                    String encodedImage = Base64.getEncoder().encodeToString(imageProduct);
                    pi.setEncodedProductImage(encodedImage);
                } else {
                    // handle the case where image is null
                }
                pi.setImageType(rs.getString("imageType"));
                list.add(pi);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String insertImage(ProductImage pi) {

        String sql = "INSERT INTO [dbo].[ProductImage]\n"
                + "           ([productID]\n"
                + "           ,[productImage]\n"
                + "           ,[imageType])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pi.getProductID());
            st.setBytes(2, pi.getProductImage());
            st.setString(3, pi.getImageType());
            st.executeUpdate();
        } catch (SQLException e) {
            return "" + e;
        }
        return "SUCCESS";
    }
    
    public void deleteImage(ProductImage pi){
        String sql1 = "Delete from ProductImage \n"
                + "Where productID = "+pi.getProductID()+" and imageType = '"+pi.getImageType()+"'";
        try{
            PreparedStatement st = connection.prepareStatement(sql1);
            st.executeUpdate();
        }catch(SQLException e){
        }
    }
    
    public void updateImage(ProductImage pi) {
        String sql = "INSERT INTO [dbo].[ProductImage]\n"
                + "           ([productID]\n"
                + "           ,[productImage]\n"
                + "           ,[imageType])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, pi.getProductID());
            st.setBytes(2, pi.getProductImage());
            st.setString(3, pi.getImageType());
            st.executeUpdate();
        } catch (SQLException e) {
            
        }
    }
}

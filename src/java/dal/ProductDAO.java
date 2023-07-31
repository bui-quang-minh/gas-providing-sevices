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
import model.Comment;
import model.Product;

/**
 *
 * @author Minh Bui
 */
public class ProductDAO extends DBContext {

    public List<Product> getByRange(int range) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Product] where price <= " + range;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getCheap() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT *\n"
                + "FROM [dbo].[Product] order by price asc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public float getAve(int id) {
        float rate = 0;
        String sql = "SELECT AVG(Cast(rate as Float)) as rate FROM Comments where productID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                rate = rs.getFloat("rate");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rate;
    }

    public List<Comment> getAllComment(int ProductID) {
        List<Comment> list = new ArrayList<>();
        String sql = "Select * from Comments where productID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, ProductID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Comment p = new Comment();
                p.setCommentID(rs.getInt("commentID"));
                p.setProductID(rs.getInt("productID"));
                p.setCustomerID(rs.getInt("customerID"));
                p.setRate(rs.getInt("rate"));
                p.setContent(rs.getString("comment"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Product> getMaxQuan() {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Product where stockQuantity=(select Max(stockQuantity) FROM Product)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));

                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getNew() {
        List<Product> list = new ArrayList<>();
        String sql = "Select * from Product where modifiedDate=(select Max(modifiedDate) FROM Product)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    /*
    CREATED BY: Bui Quang Minh 
    DATE: 14/5/2023
    STATUS: Done
    DESCRIPTION: Get all properties of product
     */
    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [productID]\n"
                + "      ,[productName]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[warrantyPeriod]\n"
                + "      ,[categoryID]\n"
                + "      ,[type]\n"
                + "      ,[manufacturerID]\n"
                + "      ,[manufacturinngDate]\n"
                + "      ,[expiryDate]\n"
                + "      ,[productCapacity]\n"
                + "      ,[productStatus]\n"
                + "      ,[createdDate]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedBy]\n"
                + "  FROM [dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean createFullProduct(Product p) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([productName]\n"
                + "           ,[description]\n"
                + "           ,[price]\n"
                + "           ,[stockQuantity]\n"
                + "           ,[warrantyPeriod]\n"
                + "           ,[categoryID]\n"
                + "           ,[type]\n"
                + "           ,[manufacturerID]\n"
                + "           ,[manufacturinngDate]\n"
                + "           ,[expiryDate]\n"
                + "           ,[productCapacity]\n"
                + "           ,[productStatus]\n"
                + "           ,[createdDate]\n"
                + "           ,[modifiedDate]\n"
                + "           ,[createdBy]\n"
                + "           ,[modifiedBy]\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getPrice());
            st.setInt(4, p.getStockQuantity());
            st.setInt(5, p.getWarrantyPeriod());
            st.setInt(6, p.getCategoryID());
            st.setString(7, p.getType());
            st.setInt(8, p.getManufacturerID());
            st.setDate(9, (Date) p.getManufacturinngDate());
            st.setDate(10, (Date) p.getExpiryDate());
            st.setString(11, p.getProductCapacity());
            st.setString(12, p.getProductStatus());
            st.setDate(13, (Date) p.getCreatedDate());
            st.setDate(14, (Date) p.getModifiedDate());
            st.setInt(15, p.getCreatedBy());
            st.setInt(16, p.getModifiedBy());
            st.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    /*
    CREATED BY: Bui Quang Minh 
    DATE: 24/5/2023
    STATUS: Done
    DESCRIPTION: Create a new product    
     */
    public void createProduct(Product p) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([productName]\n"
                + "           ,[description]\n"
                + "           ,[historicalCost]\n"
                + "           ,[price]\n"
                + "           ,[stockQuantity]\n"
                + "           ,[warrantyPeriod]\n"
                + "           ,[categoryID]\n"
                + "           ,[type]\n"
                + "           ,[manufacturerID]\n"
                + "           ,[manufacturinngDate]\n"
                + "           ,[expiryDate]\n"
                + "           ,[productCapacity]\n"
                + "           ,[productStatus]\n"
                + "           ,[createdBy]\n"
                + "           ,[modifiedBy])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getHistoricalCost());
            st.setInt(4, p.getPrice());
            st.setInt(5, p.getStockQuantity());
            st.setInt(6, p.getWarrantyPeriod());
            st.setInt(7, p.getCategoryID());
            st.setString(8, p.getType());
            st.setInt(9, p.getManufacturerID());
            st.setDate(10, new java.sql.Date(p.getManufacturinngDate().getTime()));
            st.setDate(11, new java.sql.Date(p.getExpiryDate().getTime()));
            st.setString(12, p.getProductCapacity());
            st.setString(13, p.getProductStatus());
            st.setInt(14, p.getCreatedBy());
            st.setInt(15, p.getModifiedBy());
            st.executeUpdate();
        } catch (SQLException e) {
            
        }
    }

    public int getNewProductID() {
        int id = -1;
        String sql = "Select top 1 productID from Product \n"
                + "order by productID desc\n"
                + "";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                id = rs.getInt("productID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return id;
    }

    public List<Product> getByCategory(String categoryID) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [productID]\n"
                + "      ,[productName]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[warrantyPeriod]\n"
                + "      ,[categoryID]\n"
                + "      ,[type]\n"
                + "      ,[manufacturerID]\n"
                + "      ,[manufacturinngDate]\n"
                + "      ,[expiryDate]\n"
                + "      ,[productCapacity]\n"
                + "      ,[productStatus]\n"
                + "      ,[createdDate]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedBy]\n"
                + "  FROM [dbo].[Product] where categoryID = " + categoryID + "";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public void AddComment(Comment comment) {
        String sql = "INSERT INTO Comments VALUES (?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, comment.getCustomerID());
            st.setInt(2, comment.getProductID());
            st.setInt(3, comment.getRate());
            st.setString(4, comment.getContent());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Product getProductByID(String productID) {
        Product p = new Product();
        String sql = "SELECT [productID]\n"
                + "      ,[productName]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[warrantyPeriod]\n"
                + "      ,[categoryID]\n"
                + "      ,[type]\n"
                + "      ,[manufacturerID]\n"
                + "      ,[manufacturinngDate]\n"
                + "      ,[expiryDate]\n"
                + "      ,[productCapacity]\n"
                + "      ,[productStatus]\n"
                + "      ,[createdDate]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedBy]\n"
                + "      ,[historicalCost]\n"
                + "  FROM [dbo].[Product] where productID = " + productID + "";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setThumbnail();
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setHistoricalCost(rs.getInt("historicalCost"));
                p.setRate(rs.getInt("productID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }

    public void updateProduct(Product p) {

        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [productName] = ? \n"
                + "      ,[description] = ? \n"
                + "      ,[price] = ? \n"
                + "      ,[stockQuantity] = ? \n"
                + "      ,[warrantyPeriod] = ? \n"
                + "      ,[categoryID] = ? \n"
                + "      ,[type] = ? \n"
                + "      ,[manufacturerID] = ? \n"
                + "      ,[manufacturinngDate] = ? \n"
                + "      ,[expiryDate] = ? \n"
                + "      ,[productCapacity] = ? \n"
                + "      ,[productStatus] = ? \n"
                + "      ,[modifiedDate] = getdate() \n"
                + "      ,[modifiedBy] = ? \n"
                + "      ,[historicalCost] = ? \n"
                + " WHERE productID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, p.getProductName());
            st.setString(2, p.getDescription());
            st.setInt(3, p.getPrice());
            st.setInt(4, p.getStockQuantity());
            st.setInt(5, p.getWarrantyPeriod());
            st.setInt(6, p.getCategoryID());
            st.setString(7, p.getType());
            st.setInt(8, p.getManufacturerID());
            st.setDate(9, new java.sql.Date(p.getManufacturinngDate().getTime()));
            st.setDate(10, new java.sql.Date(p.getExpiryDate().getTime()));
            st.setString(11, p.getProductCapacity());
            st.setString(12, p.getProductStatus());
            st.setInt(13, p.getModifiedBy());
            st.setInt(14, p.getHistoricalCost());
            st.setInt(15, p.getProductID());
            st.executeUpdate();
        } catch (SQLException e) {

        }

    }

    public List<Product> getByName(String obj) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT [productID]\n"
                + "      ,[productName]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[warrantyPeriod]\n"
                + "      ,[categoryID]\n"
                + "      ,[type]\n"
                + "      ,[manufacturerID]\n"
                + "      ,[manufacturinngDate]\n"
                + "      ,[expiryDate]\n"
                + "      ,[productCapacity]\n"
                + "      ,[productStatus]\n"
                + "      ,[createdDate]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedBy]\n"
                + "  FROM [dbo].[Product] Where productName like N'%" + obj + "%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setThumbnail();
                p.setRate(rs.getInt("productID"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getProductbyPrice() {
        Product p = new Product();
        String sql = "SELECT top 1 [productID]\n"
                + "      ,[productName]\n"
                + "      ,[description]\n"
                + "      ,[price]\n"
                + "      ,[stockQuantity]\n"
                + "      ,[warrantyPeriod]\n"
                + "      ,[categoryID]\n"
                + "      ,[type]\n"
                + "      ,[manufacturerID]\n"
                + "      ,[manufacturinngDate]\n"
                + "      ,[expiryDate]\n"
                + "      ,[productCapacity]\n"
                + "      ,[productStatus]\n"
                + "      ,[createdDate]\n"
                + "      ,[modifiedDate]\n"
                + "      ,[createdBy]\n"
                + "      ,[modifiedBy]\n"
                + "  FROM [dbo].[Product] order by price asc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getInt("price"));
                p.setStockQuantity(rs.getInt("stockQuantity"));
                p.setWarrantyPeriod(rs.getInt("warrantyPeriod"));
                p.setCategoryID(rs.getInt("categoryID"));
                p.setType(rs.getString("type"));
                p.setManufacturerID(rs.getInt("manufacturerID"));
                p.setManufacturinngDate(rs.getDate("manufacturinngDate"));
                p.setExpiryDate(rs.getDate("expiryDate"));
                p.setProductCapacity(rs.getString("productCapacity"));
                p.setProductStatus(rs.getString("productStatus"));
                p.setThumbnail();
                p.setCreatedDate(rs.getDate("createdDate"));
                p.setModifiedDate(rs.getDate("modifiedDate"));
                p.setCreatedBy(rs.getInt("createdBy"));
                p.setModifiedBy(rs.getInt("modifiedBy"));
                p.setRate(rs.getInt("productID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return p;
    }

    public List<Product> getStockProductByPage(List<Product> list, int start, int end) {
        List<Product> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public int updateStock(Product product, int quantity) {
        int newStock = product.getStockQuantity() - quantity;
        String sql = "UPDATE Product set stockQuantity = ? where productID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, newStock);
            st.setInt(2, product.getProductID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return newStock;
    }

    public List<Product> listTopProduct() {
        List<Product> lp = new ArrayList<>();
        String sql = "Select od.productID, productName, sum(quantity) as quantity\n"
                + "from [OrderDetail] od, Product p\n"
                + "Where od.productID = p.productID\n"
                + "Group by od.productID, productName\n"
                + "order by quantity desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("productID"));
                p.setProductName(rs.getString("productName"));
                p.setQuantity(rs.getInt("quantity"));
                lp.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return lp;
    }

    public Object[] getAllRevenue() {
        Object[] result = new Object[5]; // Assuming there are 5 values to retrieve

        String sql = "SELECT SUM(total_cost) AS buyIn, SUM(sellOut) AS expectedSellOutPrice, (SELECT SUM(totalPrice) AS actualSellOutPrice\n"
                + "FROM [order]) AS actualSellOutPrice\n"
                + "FROM (\n"
                + "  SELECT od.productID, SUM(quantity) AS totalQuantity, (SUM(quantity) * historicalCost) AS total_cost, (SUM(quantity) * price) AS sellOut\n"
                + "  FROM OrderDetail od, Product p\n"
                + "  WHERE od.productID = p.productID\n"
                + "  GROUP BY od.productID, historicalCost, price\n"
                + ") AS subquery;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                result[0] = rs.getFloat("buyIn");
                result[1] = rs.getFloat("expectedSellOutPrice");
                result[2] = rs.getFloat("actualSellOutPrice");
                result[3] = rs.getFloat("expectedSellOutPrice") - rs.getFloat("buyIn");
                result[4] = rs.getFloat("actualSellOutPrice") - rs.getFloat("buyIn");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }

    public List<Object[]> listByYear() {
        List<Object[]> result = new ArrayList<>();

        String sql = "Select a.buyIn , a.expectedSellOutPrice, b.actualSellOutPrice, a.orderYear\n"
                + "From\n"
                + "(SELECT SUM(total_cost) AS buyIn,\n"
                + "       SUM(sellOut) AS expectedSellOutPrice,\n"
                + "orderYear\n"
                + "FROM (\n"
                + "  SELECT \n"
                + "od.productID,\n"
                + "         SUM(quantity) AS totalQuantity,\n"
                + "         (SUM(quantity) * historicalCost) AS total_cost,\n"
                + "         (SUM(quantity) * price) AS sellOut,\n"
                + "         YEAR(orderDate) AS orderYear\n"
                + "  FROM OrderDetail od\n"
                + "  JOIN Product p ON od.productID = p.productID\n"
                + "  JOIN [Order] o ON o.orderID = od.orderID\n"
                + "  GROUP BY od.productID, historicalCost, price, YEAR(orderDate)\n"
                + ") AS subquery\n"
                + "GROUP BY orderYear) as a\n"
                + ",\n"
                + "(SELECT SUM(totalPrice) AS actualSellOutPrice, Year(orderDate) as orderYear FROM [order] Group by Year(orderDate)) as b\n"
                + "where a.orderYear=b.orderYear";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Object[] row = new Object[6]; // Assuming there are 5 values to retrieve
                row[0] = rs.getFloat("buyIn");
                row[1] = rs.getFloat("expectedSellOutPrice");
                row[2] = rs.getFloat("actualSellOutPrice");
                row[3] = rs.getFloat("expectedSellOutPrice") - rs.getFloat("buyIn");
                row[4] = rs.getFloat("actualSellOutPrice") - rs.getFloat("buyIn");
                row[5] = rs.getInt("orderYear");
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    public void deleteProduct(int id){
        String sql = "delete from Product where productID = " + id;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}

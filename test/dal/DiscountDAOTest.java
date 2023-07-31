/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Discount;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Minh Bui
 */
public class DiscountDAOTest extends DBContext {

    private DiscountDAO dd;
    private ProductDAO pd;

    public DiscountDAOTest() {
        dd = new DiscountDAO();
        pd = new ProductDAO();
    }

    public static boolean isDateBetween(Date targetDate, Date startDate, Date endDate) {
        return (targetDate.equals(startDate) || targetDate.equals(endDate)
                || (targetDate.after(startDate) && targetDate.before(endDate)));
    }

    @Test
    public void testGetAllAvalibleDiscount() {
        List<Discount> ld = dd.getAllAvalibleDiscount();
        for (Discount d : ld) {
            assertTrue(isDateBetween(new Date(), d.getStartDate(), d.getEndDate()));
        }
    }

    @Test
    public void testGetAll() {
        List<Discount> ld = dd.getAll();
        assertNotNull(ld);
    }

    @Test
    public void testChangeDiscount() {
        Discount d = new Discount();
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
                + "  FROM [dbo].[Discount]"
                + "  WHERE discountID = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        String origin = d.getDiscountName();
        d.setDiscountName("Test@#$%^&*&");
        dd.changeDiscount(d);
        sql = "SELECT * FROM Discount where discountName = 'Test@#$%^&*&'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                assertTrue(rs.getString("discountName").equals("Test@#$%^&*&"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        d.setDiscountName(origin);
        dd.changeDiscount(d);
    }

    @Test
    public void testDeleteDiscount() {
        Discount d = new Discount();
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
                + "  FROM [dbo].[Discount]"
                + "  WHERE discountName = 'TestObject*#&*'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
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
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        dd.deleteDiscount(d.getDiscountID());
        List<Discount> ld = dd.getAll();
        for (Discount tmp : ld){
            assertTrue(tmp.getDiscountID()!=d.getDiscountID());
        }
        dd.createDiscount(d);
    }

    @Test
    public void testCreateDiscount() {
        Discount d = new Discount();
        d.setDiscountName("Test@#$%^&*&");
        d.setDiscountValue(10);
        d.setProductID(3);
        d.setStartDate(new Date());
        d.setEndDate(new Date());
        d.setCreatedBy(1);
        d.setModifiedBy(1);
        dd.createDiscount(d);
        //-------------------------- Check for name ------------------------------
        String sql = "SELECT * FROM Discount where discountName = 'Test@#$%^&*&'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                assertTrue(rs.getString("discountName").equals("Test@#$%^&*&"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        //-------------------------------------------------------------------------
        sql = "delete from Discount where discountName = 'Test@#$%^&*&'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }

    @Test
    public void testGetDiscount() {
        List<Discount> ld = dd.getDiscount();
        assertNotNull(ld);
        int lastValue = ld.get(0).getDiscountValue();
        for (Discount d : ld) {
            assertTrue(d.getDiscountValue() <= lastValue);
            lastValue = d.getDiscountValue();
        }
    }

    @Test
    public void testGetTop3Discount() {
        List<Discount> ld = dd.getTop3Discount();
        assertNotNull(ld);
        float lastValue = pd.getProductByID(Integer.toString(ld.get(0).getProductID())).getPrice() / 100 * (100 - ld.get(0).getDiscountValue());
        for (Discount d : ld) {
            float newValue = pd.getProductByID(Integer.toString(d.getProductID())).getPrice() / 100 * (100 - d.getDiscountValue());
            assertTrue(newValue >= lastValue);
            lastValue = newValue;
        }
    }

}

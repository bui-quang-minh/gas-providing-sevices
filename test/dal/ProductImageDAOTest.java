/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import model.ProductImage;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Minh Bui
 */
public class ProductImageDAOTest {

    private ProductImageDAO pid;
    private ProductDAO pd;

    public ProductImageDAOTest() {
        pid = new ProductImageDAO();
        pd = new ProductDAO();
    }

    @Test
    public void testGetAllImage() {
        List<ProductImage> lpi = pid.getAllImage();
        assertNotNull(lpi);
    }

    @Test
    public void testGetImageByID() {
        List<ProductImage> lpi = pid.getImageByID("1");
        assertNotNull(lpi);
        for (ProductImage pi : lpi) {
            assertEquals(pi.getProductID(), 1);
        }
    }

    @Test
    public void testInsertImage() {
        List<ProductImage> lpi = pid.getImageByID("2");
        for (ProductImage pi : lpi) {
            if (pi.getImageType().equals("thumbnail")) {
                byte[] image = pi.getProductImage();
                ProductImage insertObj = new ProductImage();
                insertObj.setProductID(2);
                insertObj.setProductImage(image);
                insertObj.setImageType("thumbnail");
                pid.deleteImage(insertObj);
                String res = pid.insertImage(insertObj);
                assertTrue(res.equals("SUCCESS"));
            }
        }
    }

    @Test
    public void testDeleteImage() {
        List<ProductImage> lpi = pid.getImageByID("2");
        for (ProductImage pi : lpi) {
            if (pi.getImageType().equals("thumbnail")) {
                byte[] image = pi.getProductImage();
                ProductImage insertObj = new ProductImage();
                insertObj.setProductID(2);
                insertObj.setProductImage(image);
                insertObj.setImageType("thumbnail");
                pid.deleteImage(insertObj);
                String res = pid.insertImage(insertObj);
                assertTrue(res.equals("SUCCESS"));
            }
        }
    }

    @Test
    public void testUpdateImage() {
    }

}

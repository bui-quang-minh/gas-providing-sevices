/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import model.Comment;
import model.Product;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Minh Bui
 */
public class ProductDAOTest {
    private ProductDAO pd;

    public ProductDAOTest() {
        pd = new ProductDAO();
    }

    @Test
    public void testGetAll() {
        List<Product> lp = pd.getAll();
        assertNotNull(lp);
    }

    @Test
    public void testGetByRangeUTCID01() {
        List<Product> lp1 = pd.getByRange(200000);
        assertTrue(lp1.size()>0?true:false);
    }
    
    public void testGetByRangeUTCID03 () {
        List<Product> lp2 = pd.getByRange(0);
        assertTrue(lp2.size()>0?false:true);
    }
    public void testGetByRangeUTCID05 () {
        List<Product> lp3 = pd.getByRange(-1);
        assertTrue(lp3.size()>0?false:true);
    }


    @Test
    public void testGetCheap() {
        List<Product> lp = pd.getCheap();
        assertNotNull(lp);
        int lastPrice = lp.get(0).getPrice();
        for (Product p : lp) {
            assertTrue(p.getPrice() >= lastPrice);
            lastPrice = p.getPrice();
        }
    }

    @Test
    public void testGetAve() {
        float i = pd.getAve(1);
        assertTrue(i >= 0 && i <= 5);
    }

    @Test
    public void testGetAllComment() {
        List<Comment> cmt = pd.getAllComment(1);
        assertNotNull(cmt);
    }

    @Test
    public void testGetMaxQuan() {
        List<Product> lp = pd.getMaxQuan();
        assertNotNull(lp);
        Product product = new Product();
        for (Product p : lp) {
            product = p;
        }
        List<Product> listAll = pd.getAll();
        for (Product p : listAll) {
            assertTrue(p.getQuantity() <= product.getQuantity());
        }
    }

    @Test
    public void testGetNew() {
        List<Product> lp = pd.getNew();
        assertNotNull(lp);
        Product product = new Product();
        for (Product p : lp) {
            product = p;
        }
        List<Product> listAll = pd.getAll();
        for (Product p : listAll) {
            assertTrue(!p.getModifiedDate().after(product.getModifiedDate()));
        }
    }

    @Test
    public void testGetNewProductID() {
        int i = pd.getNewProductID();
        List<Product> listAll = pd.getAll();
        for (Product p : listAll) {
            assertTrue(p.getProductID() <= i);
        }
    }

    @Test
    public void testGetByCategory() {
        List<Product> listAll = pd.getByCategory("1");
        for (Product p : listAll) {
            assertTrue(p.getCategoryID() == 1);
        }
    }

    @Test
    public void testGetProductByIDUTCID01() {
        Product p = pd.getProductByID("1");
        assertTrue(p.getProductID() == 1);
    }

    @Test
    public void testGetProductByIDUTCID03() {
        Product p = pd.getProductByID("0");
        assertNotNull(p);
    }
    @Test
    public void testGetByName() {
        List<Product> listName = pd.getByName("Alpha");
        for (Product p : listName) {
            assertTrue(p.getProductName().contains("Alpha"));
        }
    }

    @Test
    public void testGetProductbyPriceUTCID01() {
        Product pMax = pd.getProductbyPrice();
        List<Product> listAll = pd.getAll();
        for (Product p : listAll) {
            assertTrue(p.getPrice() >= pMax.getPrice());
        }
    }
    @Test
    public void testGetProductbyPriceUTCID03() {
        Product pMax = pd.getProductbyPrice();
        List<Product> listAll = pd.getAll();
        for (Product p : listAll) {
            assertTrue(p.getPrice() >= pMax.getPrice());
        }
    }

    @Test
    public void testGetStockProductByPage() {
        List<Product> list = pd.getAll();
        List<Product> lp = pd.getStockProductByPage(list, 0, 10);
        assertNotNull(lp);
        assertEquals(lp.size(), 10);
    }

    @Test
    public void testListTopProduct() {
        List<Product> lp = pd.listTopProduct();
        int lastQuantity = lp.get(0).getQuantity();
        for (Product p : lp) {
            assertTrue(p.getQuantity()<= lastQuantity);
            lastQuantity = p.getQuantity();
        }
    }

    @Test
    public void testGetAllRevenue() {
        Object[] result = pd.getAllRevenue();
        assertNotNull(result);
    }

    @Test
    public void testListByYear() {
        List<Object[]> result = pd.listByYear();
        assertNotNull(result);
    }

    @Test
    public void testCreateFullProduct() {
        //discarded function
    }

    @Test
    public void testCreateProductUTCID01() {
        Product cp = new Product();
        cp.setProductName("Test^%&*@@####iuwqdhqwifo**&");
        cp.setDescription("Test");
        cp.setHistoricalCost(1);
        cp.setPrice(1);
        cp.setStockQuantity(1);
        cp.setWarrantyPeriod(1);
        cp.setCategoryID(1);
        cp.setManufacturerID(1);
        cp.setManufacturinngDate(new java.sql.Date((new Date()).getTime()));
        cp.setExpiryDate(new java.sql.Date((new Date()).getTime()));
        cp.setProductStatus("Good");
        cp.setCreatedBy(1);
        cp.setModifiedBy(1);
        pd.createProduct(cp);
        List<Product> listName = pd.getByName("Test^%&*@@####iuwqdhqwifo**&");
        for (Product p : listName) {
            assertTrue(p.getProductName().contains("Test^%&*@@####iuwqdhqwifo**&"));
            pd.deleteProduct(p.getProductID());
        }  
    }

    @Test(expected=NullPointerException.class)
    public void testCreateProductUTCID03() {
        Product cp = new Product();
        cp.setProductName("Test^%&*@@####iuwqdhqwifo**&");
        pd.createProduct(cp);
        List<Product> listName = pd.getByName("Test^%&*@@####iuwqdhqwifo**&");
        for (Product p : listName) {
            assertTrue(p.getProductName().contains("Test^%&*@@####iuwqdhqwifo**&"));
            pd.deleteProduct(p.getProductID());
        }  
    }
    
    @Test
    public void testAddComment() {
        //
    }

    @Test
    public void testUpdateProduct() {
        Product origin = pd.getProductByID("1");
        int originRes = origin.getStockQuantity();
        origin.setStockQuantity(199999999);
        pd.updateProduct(origin);
        assertTrue(origin.getStockQuantity()==199999999);
        origin.setStockQuantity(originRes);
        pd.updateProduct(origin);
        
    }

    @Test
    public void testUpdateStock() {
        Product origin = pd.getProductByID("1");
        int originRes = origin.getStockQuantity();
        pd.updateStock(origin, 1);
        origin= pd.getProductByID("1");
        assertTrue(origin.getStockQuantity()==(originRes-1));
        origin.setStockQuantity(originRes);
        pd.updateProduct(origin);
    }
}

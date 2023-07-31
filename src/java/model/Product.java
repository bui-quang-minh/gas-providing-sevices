
    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import dal.ProductDAO;
import dal.ProductImageDAO;
import java.util.Date;

/**
 *
 * @author Minh Bui
 * 14/5/2023
 */
public class Product {
    private int productID;
    private String productName;
    private String description;
    private int historicalCost;
    private int price;
    private int stockQuantity;
    private int warrantyPeriod;
    private int categoryID;
    private String type;
    private int manufacturerID;
    private Date manufacturinngDate;
    private Date expiryDate;
    private String productCapacity;
    private String productStatus;
    private Date createdDate;
    private Date modifiedDate;
    private int createdBy;
    private int modifiedBy;
    private ProductImage productImage;
    private float rate;
    private String thumbnail;
    int quantity;
    public String getThumbnail() {
        return thumbnail;
    }

    public Product(int productID, String productName, int price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public void setThumbnail() {
        ProductImageDAO dao = new ProductImageDAO();
        String Image = "";
        for(ProductImage i: dao.getImageByID(Integer.toString(productID))){
            if(i.getImageType().compareTo("thumbnail")==0){
                Image=i.getEncodedProductImage();
            }
        }
        this.thumbnail = Image;
    }
    
    public Product(int productID, String productName, String description, int price, int stockQuantity, int warrantyPeriod, int categoryID, String type, int manufacturerID, Date manufacturinngDate, Date expiryDate, String productCapacity, String productStatus, Date createdDate, Date modifiedDate, int createdBy, int modifiedBy, ProductImage productImage) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.warrantyPeriod = warrantyPeriod;
        this.categoryID = categoryID;
        this.type = type;
        this.manufacturerID = manufacturerID;
        this.manufacturinngDate = manufacturinngDate;
        this.expiryDate = expiryDate;
        this.productCapacity = productCapacity;
        this.productStatus = productStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.productImage = productImage;
    }

    public Product(int productID, String productName, String description, int price, int stockQuantity, int warrantyPeriod, int categoryID, String type, int manufacturerID, Date manufacturinngDate, Date expiryDate, String productCapacity, String productStatus, int modifiedBy) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.warrantyPeriod = warrantyPeriod;
        this.categoryID = categoryID;
        this.type = type;
        this.manufacturerID = manufacturerID;
        this.manufacturinngDate = manufacturinngDate;
        this.expiryDate = expiryDate;
        this.productCapacity = productCapacity;
        this.productStatus = productStatus;
        this.modifiedBy = modifiedBy;
    }

    public Product(String productName, String description, int price, int stockQuantity, int warrantyPeriod, int categoryID, String type, int manufacturerID, Date manufacturinngDate, Date expiryDate, String productCapacity, String productStatus, int createdBy, int modifiedBy) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.warrantyPeriod = warrantyPeriod;
        this.categoryID = categoryID;
        this.type = type;
        this.manufacturerID = manufacturerID;
        this.manufacturinngDate = manufacturinngDate;
        this.expiryDate = expiryDate;
        this.productCapacity = productCapacity;
        this.productStatus = productStatus;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Product(String productName, String description, int historicalCost, int price, int stockQuantity, int warrantyPeriod, int categoryID, String type, int manufacturerID, Date manufacturinngDate, Date expiryDate, String productCapacity, String productStatus, int createdBy, int modifiedBy) {
        this.productName = productName;
        this.description = description;
        this.historicalCost = historicalCost;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.warrantyPeriod = warrantyPeriod;
        this.categoryID = categoryID;
        this.type = type;
        this.manufacturerID = manufacturerID;
        this.manufacturinngDate = manufacturinngDate;
        this.expiryDate = expiryDate;
        this.productCapacity = productCapacity;
        this.productStatus = productStatus;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }
    
    

    public float getRate() {
        return rate;
    }

    public void setRate(int value) {
        ProductDAO dao = new ProductDAO();
        this.rate = dao.getAve(value);
    }

    public Product() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getHistoricalCost() {
        return historicalCost;
    }

    public void setHistoricalCost(int historicalCost) {
        this.historicalCost = historicalCost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public Date getManufacturinngDate() {
        return manufacturinngDate;
    }

    public void setManufacturinngDate(Date manufacturinngDate) {
        this.manufacturinngDate = manufacturinngDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getProductCapacity() {
        return productCapacity;
    }

    public void setProductCapacity(String productCapacity) {
        this.productCapacity = productCapacity;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public ProductImage getProductImage() {
        return productImage;
    }

    public void setProductImage(ProductImage productImage) {
        this.productImage = productImage;
    }
    
    
    

    
    
}
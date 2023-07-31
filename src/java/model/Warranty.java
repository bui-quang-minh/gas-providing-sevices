/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;
public class Warranty {
    private int warrantyID;
    private int productID;
    private int customerID;
    private Date purchasedDate;
    private Date expirationDate;
    private int status;
    private Date modifiedDate;
    private int modifiedBy;

    public String getWarrantycondition() {
        return warrantycondition;
    }

    public void setWarrantycondition(String warrantycondition) {
        this.warrantycondition = warrantycondition;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Warranty(int warrantyID, int productID, int customerID, Date purchasedDate, Date expirationDate, int status, Date modifiedDate, int modifiedBy, String warrantycondition, int price) {
        this.warrantyID = warrantyID;
        this.productID = productID;
        this.customerID = customerID;
        this.purchasedDate = purchasedDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
        this.warrantycondition = warrantycondition;
        this.price = price;
    }
    private String warrantycondition;
    private int price;
    public Warranty(int warrantyID, int productID, int customerID, Date purchasedDate, Date expirationDate, int status, Date modifiedDate, int modifiedBy) {
        this.warrantyID = warrantyID;
        this.productID = productID;
        this.customerID = customerID;
        this.purchasedDate = purchasedDate;
        this.expirationDate = expirationDate;
        this.status = status;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }
    
    public Warranty(int warrantyID, int productID, int customerID, Date purchasedDate, Date expirationDate) {
        this.warrantyID = warrantyID;
        this.productID = productID;
        this.customerID = customerID;
        this.purchasedDate = purchasedDate;
        this.expirationDate = expirationDate;
    }

    public Warranty() {
    }

    public int getWarrantyID() {
        return warrantyID;
    }

    public void setWarrantyID(int warrantyID) {
        this.warrantyID = warrantyID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(Date purchasedDate) {
        this.purchasedDate = purchasedDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
        public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}

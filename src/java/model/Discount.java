/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author Minh Bui
 */
public class Discount {
    int discountID;
    String discountName;
    int discountValue;
    int productID;
    Date startDate;
    Date endDate;
    Date createdDate;
    Date modifiedDate;
    int createdBy;
    int modifiedBy;

    public Discount() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public Discount(int discountID, String discountName, int discountValue, int productID, Date startDate, Date endDate) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.discountValue = discountValue;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Discount(int discountID, String discountName, int discountValue, Date startDate, Date endDate) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.discountValue = discountValue;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Discount(int discountID, String discountName, int discountValue, int productID, Date startDate, Date endDate, Date createdDate, Date modifiedDate, int createdBy, int modifiedBy) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.discountValue = discountValue;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
    }

    public Discount(int discountID, String discountName, int discountValue, int productID, Date startDate, Date endDate, int modifiedBy) {
        this.discountID = discountID;
        this.discountName = discountName;
        this.discountValue = discountValue;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.modifiedBy = modifiedBy;
    }

    public Discount(String discountName, int discountValue, int productID, Date startDate, Date endDate, int createdBy, int modifiedBy) {
        this.discountName = discountName;
        this.discountValue = discountValue;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
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

    
    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public String getDiscountName() {
        return discountName;
    }

    public void setDiscountName(String discountName) {
        this.discountName = discountName;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    
}

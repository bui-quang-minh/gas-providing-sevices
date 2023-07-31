/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;

/**
 *
 * @author Minh Bui
 */
public class Category {
    int categoryID;
    String categoryName;
    int createdBy;
    Date createdDate;
    int modifiedBy;
    Date modifiedDate;
    String description;

    public Category(int categoryID, String categoryName, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate, String description) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
        this.description = description;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    
    public Category() {
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public static String toSHA1 (String password)
    {
        String salt = "asjrlkmcoewjtjle;oxqskjhdafevoprlsvmx@123";
        String result = null;
        
        password = password + salt;
        try {
            byte[] dataBytes = password.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = org.apache.commons.codec.binary.Base64.encodeBase64String(md.digest(dataBytes));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
             e.printStackTrace();
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(toSHA1("admin"));
    }
}
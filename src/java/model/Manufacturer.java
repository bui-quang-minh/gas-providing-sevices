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
public class Manufacturer {
    int manufacturerID;
    String manufacturerName;
    String manufacturerAddress;
    String manufacturerPhone;
    String manufacturerEmail;
    byte[] manufacturerImage;
    String encodedManufacturerImage;
    String description;
    Date createdDate;
    int createdBy;
    Date modifiedDate;
    int modifiedBy;

    public Manufacturer() {
    }

    public Manufacturer(int manufacturerID, String manufacturerName, String manufacturerAddress, String manufacturerPhone, String manufacturerEmail, byte[] manufacturerImage, String encodedManufacturerImage, String description, Date createdDate, int createdBy, Date modifiedDate, int modifiedBy) {
        this.manufacturerID = manufacturerID;
        this.manufacturerName = manufacturerName;
        this.manufacturerAddress = manufacturerAddress;
        this.manufacturerPhone = manufacturerPhone;
        this.manufacturerEmail = manufacturerEmail;
        this.manufacturerImage = manufacturerImage;
        this.encodedManufacturerImage = encodedManufacturerImage;
        this.description = description;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }

    public int getManufacturerID() {
        return manufacturerID;
    }

    public void setManufacturerID(int manufacturerID) {
        this.manufacturerID = manufacturerID;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getManufacturerAddress() {
        return manufacturerAddress;
    }

    public void setManufacturerAddress(String manufacturerAddress) {
        this.manufacturerAddress = manufacturerAddress;
    }

    public String getManufacturerPhone() {
        return manufacturerPhone;
    }

    public void setManufacturerPhone(String manufacturerPhone) {
        this.manufacturerPhone = manufacturerPhone;
    }

    public String getManufacturerEmail() {
        return manufacturerEmail;
    }

    public void setManufacturerEmail(String manufacturerEmail) {
        this.manufacturerEmail = manufacturerEmail;
    }

    public byte[] getManufacturerImage() {
        return manufacturerImage;
    }

    public void setManufacturerImage(byte[] manufacturerImage) {
        this.manufacturerImage = manufacturerImage;
    }

    public String getEncodedManufacturerImage() {
        return encodedManufacturerImage;
    }

    public void setEncodedManufacturerImage(String encodedManufacturerImage) {
        this.encodedManufacturerImage = encodedManufacturerImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
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

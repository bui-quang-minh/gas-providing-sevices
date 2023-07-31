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
public class News {
    private int newID;
    private String newsTitle;
    private String newsHeading;
    private byte[] newsImage;
    private String encodedImage;
    private String newsContent;
    private Date createdDate;
    private Date modifiedDate;
    private int createdBy;
    private int modifiedBy;
    private String newsStatus;
    private Date approvedDate;
    private int navigationID;
    private int parentID;

    public News() {
    }

    public News(int newID, String newsTitle, String newsHeading, byte[] newsImage, String encodedImage, String newsContent, Date createdDate, Date modifiedDate, int createdBy, int modifiedBy, String newsStatus, Date approvedDate, int navigationID, int parentID) {
        this.newID = newID;
        this.newsTitle = newsTitle;
        this.newsHeading = newsHeading;
        this.newsImage = newsImage;
        this.encodedImage = encodedImage;
        this.newsContent = newsContent;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.newsStatus = newsStatus;
        this.approvedDate = approvedDate;
        this.navigationID = navigationID;
        this.parentID = parentID;
    }

    public News(int newID, String newsTitle, String newsHeading, byte[] newsImage, String encodedImage, String newsContent, Date createdDate, Date modifiedDate, int createdBy, int modifiedBy, String newsStatus, Date approvedDate) {
        this.newID = newID;
        this.newsTitle = newsTitle;
        this.newsHeading = newsHeading;
        this.newsImage = newsImage;
        this.encodedImage = encodedImage;
        this.newsContent = newsContent;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.createdBy = createdBy;
        this.modifiedBy = modifiedBy;
        this.newsStatus = newsStatus;
        this.approvedDate = approvedDate;
    }

    public int getNewID() {
        return newID;
    }

    public void setNewID(int newID) {
        this.newID = newID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsHeading() {
        return newsHeading;
    }

    public void setNewsHeading(String newsHeading) {
        this.newsHeading = newsHeading;
    }

    public byte[] getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(byte[] newsImage) {
        this.newsImage = newsImage;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
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

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Date approvedDate) {
        this.approvedDate = approvedDate;
    }

    public int getNavigationID() {
        return navigationID;
    }

    public void setNavigationID(int navigationID) {
        this.navigationID = navigationID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }
    

}

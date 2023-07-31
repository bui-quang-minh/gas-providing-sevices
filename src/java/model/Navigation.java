/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author An
 */
public class Navigation {
    public  int navigationID;
    public  String navigationName;
    public  String navigationDescription;
    public  int createdBy;
    public  Date createdDate;
    public  int modifiedBy;
    public  Date modifiedDate;
    public int getNavigationID() {
        return navigationID;
    }

    public void setNavigationID(int navigationID) {
        this.navigationID = navigationID;
    }

    public String getNavigationName() {
        return navigationName;
    }

    public void setNavigationName(String navigationName) {
        this.navigationName = navigationName;
    }

    public String getNavigationDescription() {
        return navigationDescription;
    }

    public void setNavigationDescription(String navigationDescription) {
        this.navigationDescription = navigationDescription;
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

    public Navigation(int navigationID, String navigationName, String navigationDescription, int createdBy, Date createdDate, int modifiedBy, Date modifiedDate) {
        this.navigationID = navigationID;
        this.navigationName = navigationName;
        this.navigationDescription = navigationDescription;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.modifiedBy = modifiedBy;
        this.modifiedDate = modifiedDate;
    }

}
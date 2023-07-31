/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Minh Bui
 */
public class Order {
    private int orderID;
    private int customerID;
    private Date orderDate;
    private String shipName;
    private String shipAddress;
    private Date deliveredDate;
    private int createdBy;
    private int orderStatus;
    private float totally;
    private OrderDetail orderDetails;

    public OrderDetail getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetail orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    
    public Order(int customerID, Date orderDate, String shipName, String shipAddress, Date deliveredDate, int createdBy, int orderStatus,float totally) {
        this.customerID = customerID;
        this.orderDate = orderDate;
        this.shipName = shipName;
        this.shipAddress = shipAddress;
        this.deliveredDate = deliveredDate;
        this.createdBy = createdBy;
        this.orderStatus = orderStatus;
        this.totally = totally;
    }

    public float getTotally() {
        return totally;
    }

    public void setTotally(float totally) {
        this.totally = totally;
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(Date deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author minh
 */
public class OrderStatus {
    int OrderStatusId;
    String OrderStatusName;

    public OrderStatus() {
    }

    public OrderStatus(int OrderStatusId, String OrderStatusName) {
        this.OrderStatusId = OrderStatusId;
        this.OrderStatusName = OrderStatusName;
    }

    public int getOrderStatusId() {
        return OrderStatusId;
    }

    public void setOrderStatusId(int OrderStatusId) {
        this.OrderStatusId = OrderStatusId;
    }

    public String getOrderStatusName() {
        return OrderStatusName;
    }

    public void setOrderStatusName(String OrderStatusName) {
        this.OrderStatusName = OrderStatusName;
    }
    
}
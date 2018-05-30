/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sairam
 */
public class OrderAct {
    private int orderid;
    private String customer;
    private String seller;
    private int item;
    private String status;

    public OrderAct() {
    }

    public OrderAct(int orderid, String customer, String seller, int item, String status) {
        this.orderid = orderid;
        this.customer = customer;
        this.seller = seller;
        this.item = item;
        this.status = status;
    }
    

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
}

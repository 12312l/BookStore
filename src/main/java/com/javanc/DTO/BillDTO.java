package com.javanc.DTO;

import java.awt.geom.Arc2D;

public class BillDTO {
    private Integer billID;
    private Integer userID;
    private Integer quantity;
    private Double total;
    private String methodPay;
    private String doe;

    public BillDTO(Integer billID, Integer userID, Integer quantity, Double total, String methodPay, String doe) {
        this.billID = billID;
        this.userID = userID;
        this.quantity = quantity;
        this.total = total;
        this.methodPay = methodPay;
        this.doe = doe;
    }

    public Integer getBillID() {
        return billID;
    }

    public void setBillID(Integer billID) {
        this.billID = billID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDoe() {
        return doe;
    }

    public void setDoe(String doe) {
        this.doe = doe;
    }
}

package com.example.inventory.dataObject;

import com.example.inventory.utils.paymentType;

import java.util.Date;

public class billObject {
    private Date date;
    private Integer employeeNo;

    public Date getDate() {
        return date;
    }

    public Integer getEmployeeNo() {
        return employeeNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public Number getBuyerId() {
        return buyerId;
    }

    public com.example.inventory.utils.paymentType getPaymentType() {
        return paymentType;
    }

    public billObject(Integer employeeNo, Number buyerId, com.example.inventory.utils.paymentType paymentType) {
        this.employeeNo = employeeNo;
        this.buyerId = buyerId;
        this.paymentType = paymentType;
    }

    private String billNo;
    private Number buyerId;
    private paymentType paymentType;
}

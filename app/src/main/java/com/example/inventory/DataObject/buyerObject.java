package com.example.inventory.DataObject;

import androidx.annotation.NonNull;

public class buyerObject {
    private String name;
    private String phoneNumber;
    private String buyerDescription;
    private String buyerId;

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public void setBuyerDescription(String buyerDescription) {
        this.buyerDescription = buyerDescription;
    }

    private String buyerEmail;

    public buyerObject() {
    }

    public buyerObject(String name, String phoneNumber, String buyerDescription, String buyerEmail) {
        this.name = name;
        this.buyerEmail = buyerEmail;
        this.phoneNumber = phoneNumber;
        this.buyerDescription = buyerDescription;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBuyerDescription() {
        return buyerDescription;
    }


    public String getBuyerId() {
        return buyerId;
    }

    public boolean isSameAs(buyerObject value) {

        if(value.getName().equals(name) && value.getPhoneNumber().equals(phoneNumber) && value.getBuyerEmail().equals(buyerEmail))
        {
            return true;
        }

        return false;
    }
}

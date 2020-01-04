package com.example.inventory.DataObject;

public class buyerObject {
    private String name;
    private String phoneNumber;
    private String buyerDescription;
    private String buyerId;
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

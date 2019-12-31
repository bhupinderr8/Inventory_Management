package com.example.inventory.dataObject;

public class buyerObject {
    private String name;
    private String phoneNumber;
    private String buyerDescription;
    private String buyerId;
    private String buyerEmail;

    public buyerObject(String name, String phoneNumber, String buyerDescription, String buyerEmail) {
        this.name = name;
        this.buyerEmail = buyerEmail;
        this.phoneNumber = phoneNumber;
        this.buyerDescription = buyerDescription;
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

}

package com.example.inventory.dataObject;

public class buyerObject {
    private String name;
    private String phoneNumber;
    private String buyerDescription;
    private String adress;
    private String buyerId;
    private String accountNumber;

    public buyerObject(String name, String phoneNumber, String buyerDescription, String adress, String accountNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.buyerDescription = buyerDescription;
        this.adress = adress;
        this.accountNumber = accountNumber;
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

    public String getAdress() {
        return adress;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

package com.example.inventory.dataObject;

public class sellerObject {
    private String name;
    private String sellerId;
    private String email;
    private String accountNumber;
    private String address;

    public sellerObject(String name, String email, String accountNumber, String address) {
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getEmail() {
        return email;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAddress() {
        return address;
    }
}

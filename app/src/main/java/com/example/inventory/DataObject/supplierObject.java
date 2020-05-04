package com.example.inventory.DataObject;

import androidx.annotation.NonNull;

public class supplierObject {
    private String name;
    private String phoneNumber;
    private String supplierDescription;
    private String supplierId;
    private String supplierEmail;

    public supplierObject() {
    }

    public supplierObject(String name, String phoneNumber, String supplierDescription, String supplierEmail) {
        this.name = name;
        this.supplierEmail = supplierEmail;
        this.phoneNumber = phoneNumber;
        this.supplierDescription = supplierDescription;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }

    public String getSupplierEmail() {
        return supplierEmail;
    }

    public void setSupplierEmail(String supplierEmail) {
        this.supplierEmail = supplierEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSupplierDescription() {
        return supplierDescription;
    }

    public void setSupplierDescription(String supplierDescription) {
        this.supplierDescription = supplierDescription;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public boolean isSameAs(supplierObject value) {

        return value.getName().equals(name) && value.getPhoneNumber().equals(phoneNumber) && value.getSupplierEmail().equals(supplierEmail);
    }
}

package com.example.inventory.AddSupplier;

public interface AddSupplierView {
    boolean checkValuesSet();

    String getSupplierEmail();

    String getSupplierPhone();

    String getSupplierDescription();

    String getSupplierName();

    void finishActivity();

    void show(String s);
}

package com.example.inventory.AddBuyer;

public interface AddBuyerView {
    boolean checkValuesSet();

    String getBuyerEmail();

    String getBuyerPhone();

    String getBuyerDescription();

    String getBuyerName();

    void finishActivity();

    void show(String s);
}

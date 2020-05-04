package com.example.inventory.Order;

import com.example.inventory.DataObject.itemObject;

import java.util.HashMap;

public interface SelectView {
    void setAdapter();
    void addItemToAdapter(itemObject item);
    void OnAdapterChange();
    void removeValueFromAdapter(String itemId);
    void show(String val);
    void scrollToEnd();
    void clearAllItems();

    boolean itemInAdapter(String itemNumber);

    HashMap<String, Integer> getValues();

    void finishActivity();

    void showPopUpWindow();

}

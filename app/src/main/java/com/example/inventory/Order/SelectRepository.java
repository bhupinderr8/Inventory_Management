package com.example.inventory.Order;

import com.example.inventory.DataObject.itemObject;

import java.util.HashMap;

public interface SelectRepository {
    void queryItemName(String text);

    void retrieveSelectItems();

    void updateItems(HashMap<String, Integer> mList);
}

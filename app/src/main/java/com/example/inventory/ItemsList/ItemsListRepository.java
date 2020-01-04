package com.example.inventory.ItemsList;

import com.example.inventory.DataObject.itemObject;

public interface ItemsListRepository {
    void queryItemName(String text);

    void insertItem(itemObject item);

    void retrieveItems();
}

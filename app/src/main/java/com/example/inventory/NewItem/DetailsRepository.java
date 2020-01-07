package com.example.inventory.NewItem;

import com.example.inventory.DataObject.itemObject;

public interface DetailsRepository {
    void insertItem(itemObject itemObj);

    void updateItem(String currentItemId, Integer quantity);

    void deleteItem(String itemId);

    void deleteAllItems();

    void updateValues(String currentItemId);
}

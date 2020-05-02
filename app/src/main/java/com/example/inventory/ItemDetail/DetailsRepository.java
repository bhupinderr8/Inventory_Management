package com.example.inventory.ItemDetail;

import com.example.inventory.DataObject.itemObject;

public interface DetailsRepository {
    void insertItem(itemObject itemObj);

    void updateItem(String currentItemId, Integer quantity);

    void deleteItem(String itemId);

    void deleteAllItems();

    void updateValues(String currentItemId);
}

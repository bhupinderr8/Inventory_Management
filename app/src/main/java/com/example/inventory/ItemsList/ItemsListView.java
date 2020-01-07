package com.example.inventory.ItemsList;

import com.example.inventory.DataObject.itemObject;

public interface ItemsListView {
    void setTitleToUserName(String userName);
    void setAdapter();
    void addItemToAdapter(itemObject item);
    void OnAdapterChange();
    void removeValueFromAdapter(String itemId);
    void launchLoginActivity();
    void show(String val);
    void scrollToEnd();

    void clearAllItems();

    boolean itemInAdapter(String itemNumber);
}

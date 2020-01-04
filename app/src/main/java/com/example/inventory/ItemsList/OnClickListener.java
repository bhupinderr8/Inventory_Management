package com.example.inventory.ItemsList;

import com.example.inventory.DataObject.itemObject;

public interface OnClickListener {
    void onItemClick(itemObject item);
    void onLongItemClick(itemObject item);
}

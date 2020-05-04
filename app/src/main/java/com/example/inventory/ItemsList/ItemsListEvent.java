package com.example.inventory.ItemsList;

import com.example.inventory.DataObject.itemObject;

public class ItemsListEvent {
    public static int onChildAdded = 0;
    public static int onChildUpdated = 1;
    public static int onChildRemoved = 2;
    private itemObject item;
    private int eventType;

    public ItemsListEvent(itemObject item, int eventType) {
        this.item = item;
        this.eventType = eventType;
    }

    public itemObject getItem() {
        return item;
    }

    public int getEventType() {
        return eventType;
    }
}

package com.example.inventory.data;

import android.database.Cursor;

import com.example.inventory.dataObject.itemObject;
import com.example.inventory.dataObject.userObject;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FireBaseHelper {
    private DatabaseReference mDatabase;

    public FireBaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void insertItem(itemObject item)
    {
        mDatabase.child("itemObject").child(item.getItemNumber()).setValue(item);
    }

    public void updateItem(String itemId, Integer qty)
    {

    }


    public itemObject getItem(String itemId) {
        return null;
    }

    public int deleteAllItems() {
        return 0;
    }

    public int deleteItem(String itemId) {
        return 0;
    }

    public void sellOneItem(String id, int quantity) {

    }

    public userObject readUser(String username) {
        return null;
    }

    public Query getItemRef() {
        return mDatabase.child("itemObject");
    }
}

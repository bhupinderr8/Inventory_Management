package com.example.inventory.utils;

import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.inventory.Activities.ConfirmAdapter;
import com.example.inventory.Activities.DetailsActivity;
import com.example.inventory.Activities.LoginActivity;
import com.example.inventory.dataObject.buyerObject;
import com.example.inventory.dataObject.itemObject;
import com.example.inventory.dataObject.userObject;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FireBaseHelper {
    private DatabaseReference mDatabase;
    private static String ITEM_TABLE = "itemObject";
    private static String USER_TABLE = "userObject";
    private static String BUYER_TABLE = "buyerObject";

    public FireBaseHelper() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void insertItem(itemObject item)
    {
        item.setItemNumber(mDatabase.child(ITEM_TABLE).push().getKey());
        mDatabase.child(ITEM_TABLE).child(item.getItemNumber()).setValue(item);
    }

    public void updateItem(String itemId, Integer qty)
    {
        mDatabase.child(ITEM_TABLE + "/"+itemId).child("qty").setValue(qty);
    }

    public int deleteAllItems() {
        mDatabase.child(ITEM_TABLE).setValue(null);
        return 0;
    }

    public int deleteItem(String itemId) {
        mDatabase.child(ITEM_TABLE).child(itemId).setValue(null);
        return 0;
    }

    public void checkUser(final LoginActivity activity)
    {
        mDatabase.child(USER_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    userObject obj = child.getValue(userObject.class);
                    if(obj.getName().equals(activity.getUserName()))
                    {
                        if(!obj.getPassword().equals(activity.getPassword()))
                        {
                            Toast.makeText(activity.getApplicationContext(), "PassWord Incorrect", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            activity.doLogin();
                            return;
                        }
                    }
                }
                Toast.makeText(activity.getApplicationContext(), "Cannot Find User", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public Query getItemRef() {
        return mDatabase.child(ITEM_TABLE);
    }

    public void insertBuyer(final buyerObject obj) {

        mDatabase.child(BUYER_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    if(obj.isSameAs(Objects.requireNonNull(child.getValue(buyerObject.class))))
                    {
                       return;
                    }
                }
                String buyerId = mDatabase.child(BUYER_TABLE).push().getKey();
                mDatabase.child(BUYER_TABLE).child(obj.getBuyerId()).setValue(obj);
                mDatabase.child(BUYER_TABLE).child(buyerId).child("buyerId").setValue(buyerId);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addItems(final HashMap<String, Integer> list, final ArrayList<itemObject> objectArrayList, final ConfirmAdapter adapter)
    {
        mDatabase.child(ITEM_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    if(list.containsKey(child.getKey()))
                    {
                        objectArrayList.add(child.getValue(itemObject.class));
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void addUser(userObject admin) {
        String tmp = mDatabase.child(USER_TABLE).push().getKey();
        assert tmp != null;
        mDatabase.child(USER_TABLE).child(tmp).setValue(admin);
    }

    public void updateValues(final DetailsActivity detailsActivity, final String itemId) {
        mDatabase.child(ITEM_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    if(child.getKey().equals(itemId))
                    {
                        detailsActivity.updateValues(Objects.requireNonNull(child.getValue(itemObject.class)));
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

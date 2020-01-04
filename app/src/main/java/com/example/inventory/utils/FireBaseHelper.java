package com.example.inventory.utils;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.inventory.AddBuyer.AddBuyerRepository;
import com.example.inventory.ItemsList.ItemsListEvent;
import com.example.inventory.ItemsList.ItemsListRepository;
import com.example.inventory.Order.ConfirmAdapter;
import com.example.inventory.NewItem.DetailsActivity;
import com.example.inventory.Login.LoginEvent;
import com.example.inventory.Login.LoginRepository;
import com.example.inventory.Register.RegisterEvent;
import com.example.inventory.Register.RegisterRepository;
import com.example.inventory.DataObject.buyerObject;
import com.example.inventory.DataObject.itemObject;
import com.example.inventory.DataObject.userObject;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FireBaseHelper implements RegisterRepository, LoginRepository, ItemsListRepository, AddBuyerRepository {
    private final static String LOG_TAG = FireBaseHelper.class.getCanonicalName();
    private DatabaseReference mDatabase;
    private static String ITEM_TABLE = "itemObject";
    private static String USER_TABLE = "userObject";
    private static String BUYER_TABLE = "buyerObject";
    private static FirebaseDatabase database;

    public FireBaseHelper() {
        if(database==null)
        {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        mDatabase = database.getReference();
        mDatabase.keepSynced(true);
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

    private void checkUser(final String username, final String password)
    {
        mDatabase.child(USER_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren())
                {
                    userObject obj = child.getValue(userObject.class);
                    if(obj.getName().equals(username))
                    {
                        if(obj.getPassword().equals(password))
                        {
                            EventBus.getDefault().post(new LoginEvent(LoginEvent.onSignInSuccess, null, username));
                        }
                        else
                        {
                            EventBus.getDefault().post(new LoginEvent(LoginEvent.onSignInError, "Wrong Password", null));
                            return;
                        }
                    }
                }
                EventBus.getDefault().post(new LoginEvent(LoginEvent.onSignInSuccess, "Cannot Find User", null));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                EventBus.getDefault().post(new LoginEvent(LoginEvent.onSignInSuccess, databaseError.toString(), null));
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
        Query query = mDatabase.child(ITEM_TABLE).limitToFirst(7);
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
        mDatabase.child(USER_TABLE).child(tmp).setValue(admin, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                EventBus.getDefault().post(new RegisterEvent(RegisterEvent.onSignUpSuccess));
            }
        });
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

    @Override
    public void initSignIn(String username, String password) {
        checkUser(username, password);
    }

    @Override
    public void retrieveItems() {
        mDatabase.child(ITEM_TABLE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                itemObject item = dataSnapshot.getValue(itemObject.class);
                EventBus.getDefault().post(new ItemsListEvent(item, ItemsListEvent.onChildAdded));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                itemObject item = dataSnapshot.getValue(itemObject.class);
                EventBus.getDefault().post(new ItemsListEvent(item, ItemsListEvent.onChildUpdated));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                itemObject item = dataSnapshot.getValue(itemObject.class);
                EventBus.getDefault().post(new ItemsListEvent(item, ItemsListEvent.onChildRemoved));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void queryItemName(String text) {
        Query query = mDatabase.child(ITEM_TABLE).orderByChild("itemName").equalTo(text);
        Log.d(LOG_TAG, query.toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Log.d(LOG_TAG, "children");
                    for(DataSnapshot child : dataSnapshot.getChildren())
                    {
                        Log.d(LOG_TAG, "Child Added");
                        itemObject item = child.getValue(itemObject.class);
                        EventBus.getDefault().post(new ItemsListEvent(item, ItemsListEvent.onChildAdded));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

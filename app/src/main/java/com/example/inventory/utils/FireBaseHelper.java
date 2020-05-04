package com.example.inventory.utils;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.inventory.AddSupplier.AddSupplierRepository;
import com.example.inventory.DataObject.supplierObject;
import com.example.inventory.DataObject.itemObject;
import com.example.inventory.DataObject.userObject;
import com.example.inventory.ItemDetail.DetailsRepository;
import com.example.inventory.ItemDetail.DetailsViewImpl;
import com.example.inventory.ItemsList.ItemsListEvent;
import com.example.inventory.ItemsList.ItemsListRepository;
import com.example.inventory.Login.LoginEvent;
import com.example.inventory.Login.LoginRepository;
import com.example.inventory.Order.SelectEvent;
import com.example.inventory.Order.SelectRepository;
import com.example.inventory.Register.RegisterEvent;
import com.example.inventory.Register.RegisterRepository;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Objects;

public class FireBaseHelper implements RegisterRepository, LoginRepository, ItemsListRepository, AddSupplierRepository, DetailsRepository, SelectRepository {
    private final static String LOG_TAG = FireBaseHelper.class.getCanonicalName();
    private static String ITEM_TABLE = "itemObject";
    private static String USER_TABLE = "userObject";
    private static String SUPPLIER_TABLE = "supplierObject";
    private static FirebaseDatabase database;
    private DatabaseReference mDatabase;

    public FireBaseHelper() {
        if (database == null) {
            database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
        }
        mDatabase = database.getReference();
        mDatabase.keepSynced(true);
    }

    public void insertItem(itemObject item) {
        item.setItemNumber(mDatabase.child(ITEM_TABLE).push().getKey());
        mDatabase.child(ITEM_TABLE).child(item.getItemNumber()).setValue(item);
    }

    public void updateItem(String itemId, Integer qty) {
        mDatabase.child(ITEM_TABLE + "/" + itemId).child("qty").setValue(qty);
    }

    public void deleteAllItems() {
        mDatabase.child(ITEM_TABLE).setValue(null);
    }

    @Override
    public void updateValues(final String currentItemId) {
        mDatabase.child(ITEM_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals(currentItemId)) {
                        EventBus.getDefault().post(child.getValue(itemObject.class));
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
    public void getSupplierUpdates() {
        mDatabase.child(SUPPLIER_TABLE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                supplierObject item = dataSnapshot.getValue(supplierObject.class);
                Log.e(LOG_TAG, "New child added" + item.toString());
                EventBus.getDefault().post(item);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                supplierObject item = dataSnapshot.getValue(supplierObject.class);
                EventBus.getDefault().post(item);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                supplierObject item = dataSnapshot.getValue(supplierObject.class);
                EventBus.getDefault().post(item);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void deleteItem(String itemId) {
        mDatabase.child(ITEM_TABLE).child(itemId).setValue(null);
    }

    private void checkUser(final String username, final String password) {
        mDatabase.child(USER_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    userObject obj = child.getValue(userObject.class);
                    if (obj.getName().equals(username)) {
                        if (obj.getPassword().equals(password)) {
                            EventBus.getDefault().post(new LoginEvent(LoginEvent.onSignInSuccess, null, username));
                        } else {
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

    public void insertSupplier(final supplierObject obj) {

        mDatabase.child(SUPPLIER_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (obj.isSameAs(Objects.requireNonNull(child.getValue(supplierObject.class)))) {
                        return;
                    }
                }
                String supplierId = mDatabase.child(SUPPLIER_TABLE).push().getKey();
                obj.setSupplierId(supplierId);
                mDatabase.child(SUPPLIER_TABLE).child(obj.getSupplierId()).setValue(obj);
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

    public void updateValues(final DetailsViewImpl detailsViewImpl, final String itemId) {
        mDatabase.child(ITEM_TABLE).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (child.getKey().equals(itemId)) {
                        detailsViewImpl.updateValues(Objects.requireNonNull(child.getValue(itemObject.class)));
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
                Log.e(LOG_TAG, "New child added" + item.toString());
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
    public void updateItems(HashMap<String, Integer> mList) {
        for (HashMap.Entry<String, Integer> entry : mList.entrySet()) {
            updateItem(entry.getKey(), entry.getValue());
        }

    }

    @Override
    public void queryItemName(final String text) {
        Query query = mDatabase.child(ITEM_TABLE);
        Log.d(LOG_TAG, query.toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Log.d(LOG_TAG, "children");
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Log.d(LOG_TAG, "Child Added");
                        itemObject item = child.getValue(itemObject.class);
                        if (item.getItemName().toLowerCase().contains(text.toLowerCase())) {
                            EventBus.getDefault().post(new ItemsListEvent(item, ItemsListEvent.onChildAdded));
                            EventBus.getDefault().post(new SelectEvent(item, SelectEvent.onChildAdded));
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void retrieveSelectItems() {
        mDatabase.child(ITEM_TABLE).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                itemObject item = dataSnapshot.getValue(itemObject.class);
                Log.e(LOG_TAG, "New child added" + item.toString());
                EventBus.getDefault().post(new SelectEvent(item, SelectEvent.onChildAdded));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                itemObject item = dataSnapshot.getValue(itemObject.class);
                EventBus.getDefault().post(new SelectEvent(item, SelectEvent.onChildUpdated));
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                itemObject item = dataSnapshot.getValue(itemObject.class);
                EventBus.getDefault().post(new SelectEvent(item, SelectEvent.onChildRemoved));
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

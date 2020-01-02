package com.example.inventory.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.Session;
import com.example.inventory.dataObject.itemObject;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private final static String LOG_TAG = MainActivity.class.getCanonicalName();
    private FireBaseHelper dbHelper;
    private Session session;
    private RecyclerView recyclerView;
    ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session = new Session(this);
        this.setTitle(session.getUserName());

        dbHelper = new FireBaseHelper();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<itemObject> options =
                new FirebaseRecyclerOptions.Builder<itemObject>()
                        .setQuery(dbHelper.getItemRef(), itemObject.class)
                        .build();
        adapter = new ItemAdapter(options, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                addDummyData();
                break;
            case R.id.logout:
                launchLoginActivity();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void launchLoginActivity()
    {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        session.doLogout();
        startActivity(intent);
        finish();
    }

    private void addDummyData() {

        itemObject gummibears = new itemObject(
                "Bottle",
                "android.resource://com.example.inventory/drawable/bottle",
                "Bottle Good.",
                97,
                97
        );
        dbHelper.insertItem(gummibears);
    }

    public void launchDetails(int pos) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("ItemId", adapter.getItem(pos).getItemNumber());
        startActivity(intent);
    }
}


package com.example.inventory.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.dataObject.itemObject;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.Session;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectActivity extends AppCompatActivity {

    SearchView searchView;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    SelectAdapter adapter;
    private FireBaseHelper dbHelper;
    private Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        searchView = findViewById(R.id.select_search_view);
        recyclerView = findViewById(R.id.select_recycler_view);

        dbHelper = new FireBaseHelper();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<itemObject> options =
                new FirebaseRecyclerOptions.Builder<itemObject>()
                        .setQuery(dbHelper.getItemRef(), itemObject.class)
                        .build();
        adapter = new SelectAdapter(options);
        recyclerView.setAdapter(adapter);
        session = new Session(this);

        

        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener() {
            @Override
            public boolean onSuggestionSelect(int position) {
                return false;
            }

            @Override
            public boolean onSuggestionClick(int position) {
                return false;
            }
        });
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.select_floating_action_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SelectActivity.this, ConfirmActivity.class);
                intent.putExtra("list", adapter.mList);
                startActivityForResult(intent, 0);
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0)
        {
            finish();
        }
    }
}

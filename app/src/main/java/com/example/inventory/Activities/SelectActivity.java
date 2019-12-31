package com.example.inventory.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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
    RecyclerView recyclerView;
    SelectAdapter adapter;
    private FireBaseHelper dbHelper;
    HashMap<String, itemObject> map;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        searchView = findViewById(R.id.select_search_view);
        fab = findViewById(R.id.select_floating_action_button);
        recyclerView = findViewById(R.id.select_recycler_view);

        dbHelper = new FireBaseHelper();
        recyclerView = findViewById(R.id.select_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<itemObject> options =
                new FirebaseRecyclerOptions.Builder<itemObject>()
                        .setQuery(dbHelper.getItemRef(), itemObject.class)
                        .build();

        adapter = new SelectAdapter(options, map);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> list = new ArrayList<>();
                for(Map.Entry<String, itemObject> element : map.entrySet())
                {
                    session.saveHashMap(element.getKey(), element.getValue());
                    list.add(element.getKey());
                }
                Intent intent = new Intent(SelectActivity.this, ConfirmActivity.class);
                intent.putStringArrayListExtra("list", list);
                startActivityForResult(intent, 0);
            }
        });

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

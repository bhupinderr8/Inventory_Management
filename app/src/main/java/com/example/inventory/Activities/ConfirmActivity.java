package com.example.inventory.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.inventory.R;
import com.example.inventory.dataObject.itemObject;
import com.example.inventory.utils.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfirmActivity extends AppCompatActivity {

    ListView listview;
    FloatingActionButton fab;
    SearchView searchView;
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        listview = findViewById(R.id.confirm_list_view);
        fab = findViewById(R.id.confirm_action_button);
        searchView = findViewById(R.id.confirm_search_view);

        final ArrayList<String> list = getIntent().getStringArrayListExtra("list");

        assert list != null;
        ArrayList<itemObject> objectArrayList = new ArrayList<>();
        for(String s : list)
        {
            objectArrayList.add(session.getHashMap(s).get(s));
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmActivity.this, BuyerDetailsActivity.class);
                intent.putStringArrayListExtra("list", list);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            Intent intent=new Intent();
            setResult(0,intent);
            finish();
        }
    }
}

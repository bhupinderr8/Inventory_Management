package com.example.inventory.Order;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.inventory.AddBuyer.AddBuyerViewImpl;
import com.example.inventory.R;
import com.example.inventory.DataObject.itemObject;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.SessionImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

public class ConfirmViewImpl extends AppCompatActivity implements ConfirmView {
    private static final String TAG = "Check";
    ListView listview;
    FloatingActionButton fab;
    SearchView searchView;
    SessionImpl session;
    FireBaseHelper dbhelper;
    ConfirmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        dbhelper = new FireBaseHelper();
        listview = findViewById(R.id.confirm_list_view);

        fab = findViewById(R.id.confirm_action_button);
        searchView = findViewById(R.id.confirm_search_view);
        session = new SessionImpl(this);
        final HashMap<String, Integer> list = (HashMap<String, Integer>) getIntent().getSerializableExtra("list");

        assert list != null;
        ArrayList<itemObject> objectArrayList = new ArrayList<>();

        adapter = new ConfirmAdapter(this, R.layout.confirm_list_item, objectArrayList);
        dbhelper.addItems(list, objectArrayList, adapter);
        listview.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmViewImpl.this, AddBuyerViewImpl.class);
                intent.putExtra("list", list);
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

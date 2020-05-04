package com.example.inventory.Order;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.DataObject.itemObject;
import com.example.inventory.R;
import com.example.inventory.utils.SessionImpl;

import java.util.ArrayList;
import java.util.HashMap;

public class SelectViewImpl extends AppCompatActivity implements SelectView, SelectAdapterView {

    SearchView searchView;
    private RecyclerView recyclerView;
    SelectAdapter adapter;
    private ArrayList<itemObject> list;
    private SelectPresenter presenter;
    ConstraintLayout anchor;
    static final String[] ITEMS={"lorem", "ipsum", "dolor",
            "sit", "amet", "consectetuer", "adipiscing", "elit", "morbi",
            "vel", "ligula", "vitae", "arcu", "aliquet", "mollis", "etiam",
            "vel", "erat", "placerat", "ante", "porttitor", "sodales",
            "pellentesque", "augue", "purus"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.select_recycler_view);
        presenter = new SelectPresenterImpl(this, new SessionImpl(this));
        anchor = findViewById(R.id.activity_select_root_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_select_items, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_item_list_search);
        searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.onQueryTextChange(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.onQueryTextChange(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_confirm:
                // add dummy data for testing
                presenter.OnConfirmItemClicked();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SelectAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void addItemToAdapter(itemObject item) {
        list.add(item);
    }

    @Override
    public void OnAdapterChange() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void removeValueFromAdapter(String itemId) {
        int i=0;
        while(i<list.size())
        {
            if(list.get(i).getItemNumber().equals(itemId))
            {
                list.remove(i);
                return;
            }
            i++;
        }
    }

    @Override
    public void show(String val) {
        Toast.makeText(this, val, Toast.LENGTH_LONG).show();
    }

    @Override
    public void scrollToEnd() {
        recyclerView.smoothScrollToPosition(list.size()-1);
    }

    @Override
    public void clearAllItems() {
        adapter.data.clear();
    }

    @Override
    public boolean itemInAdapter(String itemId) {
        int i=0;
        while(i<list.size())
        {
            if(list.get(i).getItemNumber().equals(itemId))
            {
                return true;
            }
            i++;
        }
        return false;
    }

    @Override
    public HashMap<String, Integer> getValues() {
        return adapter.mList;
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void showPopUpWindow() {

        final ListPopupWindow popup = new ListPopupWindow(this);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ITEMS);
        popup.setAdapter(adapter1);
        popup.setAnchorView(anchor);
        popup.setHeight(ListPopupWindow.MATCH_PARENT);
        popup.setWidth(ListPopupWindow.MATCH_PARENT);
        popup.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        popup.dismiss();
                    }
                });

        popup.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                presenter.OnPopUpDismissed();
            }
        });

        popup.show();
    }

    @Override
    public void informDataChanged() {
        presenter.informDataChanged();
    }
}

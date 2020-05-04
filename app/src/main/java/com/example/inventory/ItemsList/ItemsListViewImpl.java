package com.example.inventory.ItemsList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.inventory.ItemDetail.DetailsViewImpl;
import com.example.inventory.Login.LoginViewImpl;
import com.example.inventory.Order.SelectViewImpl;
import com.example.inventory.R;
import com.example.inventory.utils.SessionImpl;
import com.example.inventory.DataObject.itemObject;

import java.util.ArrayList;

public class ItemsListViewImpl extends AppCompatActivity implements OnClickListener, ItemsListView {

    private final static String LOG_TAG = ItemsListViewImpl.class.getCanonicalName();
    private RecyclerView recyclerView;
    ItemAdapter adapter;
    private ItemsListPresenter presenter;
    private ArrayList<itemObject> list;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        list = new ArrayList<>();

        presenter = new ItemsListPresenterImpl(this, new SessionImpl(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.activity_list_search);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_dummy_data:
                // add dummy data for testing
                presenter.addDummyData();
                break;
            case R.id.logout:
                presenter.OnLogoutButton();
                break;
            case R.id.action_new_order:
                presenter.OnNewOrderButton();
                break;
            case R.id.action_add_items:
                presenter.OnAddItem();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void launchLoginActivity()
    {
        Intent intent = new Intent(ItemsListViewImpl.this, LoginViewImpl.class);
        startActivity(intent);
        finish();
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
    public void launchNewOrderActivity() {
        Intent intent = new Intent(ItemsListViewImpl.this, SelectViewImpl.class);
        startActivity(intent);
    }

    @Override
    public void launchDetailsActivity() {
        Intent intent = new Intent(ItemsListViewImpl.this, DetailsViewImpl.class);
        intent.putExtra("ItemId", "");
        startActivity(intent);
    }

    @Override
    public void onItemClick(itemObject item) {
        Intent intent = new Intent(this, DetailsViewImpl.class);
        intent.putExtra("ItemId", item.getItemNumber());
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(itemObject item) {
        Intent intent = new Intent(this, DetailsViewImpl.class);
        intent.putExtra("ItemId", item.getItemNumber());
        startActivity(intent);
    }

    @Override
    public void setTitleToUserName(String userName) {
        setTitle(userName);
    }

    @Override
    public void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ItemAdapter(list,this);
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
}


package com.example.inventory.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory.Login.LoginViewImpl;
import com.example.inventory.NewItem.DetailsActivity;
import com.example.inventory.Order.SelectActivity;
import com.example.inventory.ItemsList.ItemsListViewImpl;
import com.example.inventory.R;
import com.example.inventory.utils.Session;
import com.example.inventory.utils.SessionImpl;

public class HomeActivity extends AppCompatActivity implements HomeView{

    private Button newItemButton;
    private Button orderButton;
    private Button viewItemsButton;
    private TextView userNameTextView;
    private HomePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        newItemButton = findViewById(R.id.add_new_item_button);
        orderButton = findViewById(R.id.order_button);
        viewItemsButton = findViewById(R.id.view_items_button);
        userNameTextView = findViewById(R.id.user_name_text_view);
        presenter = new HomePresenterImpl(this, new SessionImpl(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_menu_logout) {
            presenter.doLogout();
        }
        return super.onOptionsItemSelected(item);
    }


    public void onItemButtonPressed(View view)
    {
        presenter.itemButtonPressed();
    }

    public void onOrderButtonPressed(View view)
    {
        presenter.orderButtonPressed();
    }

    public void onViewItemsButtonPressed(View view)
    {
        presenter.viewButtonPressed();
    }

    @Override
    public void showName(String name) {
        userNameTextView.setText(name);
    }

    @Override
    public void OnLogout() {
        startActivity(new Intent(HomeActivity.this, LoginViewImpl.class));
        finish();
    }

    @Override
    public void show(String val) {
        Toast.makeText(this, val, Toast.LENGTH_LONG).show();
    }

    @Override
    public void launchActivity(Class classItem) {
        startActivity(new Intent(HomeActivity.this, classItem).putExtra("itemId", ""));
    }
}

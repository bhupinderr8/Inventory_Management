package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.inventory.data.Session;

public class HomeActivity extends AppCompatActivity {

    private Button newItemButton;
    private Button orderButton;
    private Button viewItemsButton;
    private TextView userNameTextView;
    private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        newItemButton = findViewById(R.id.add_new_item_button);
        orderButton = findViewById(R.id.order_button);
        viewItemsButton = findViewById(R.id.view_items_button);
        userNameTextView = findViewById(R.id.user_name_text_view);
        session = new Session(this);
        userNameTextView.setText(session.getUserName());
    }

    public void onItemButtonPressed(View view)
    {
        Intent intent = new Intent(HomeActivity.this, DetailsActivity.class);
        startActivity(intent);
    }

    public void onOrderButtonPressed(View view)
    {
        Intent intent = new Intent(HomeActivity.this, OrderActivity.class);
        startActivity(intent);
    }

    public void onViewItemsButtonPressed(View view)
    {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}

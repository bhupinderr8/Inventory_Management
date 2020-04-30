package com.example.inventory.AddBuyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.inventory.R;
import com.example.inventory.DataObject.buyerObject;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.SessionImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.Map;

public class AddBuyerViewImpl extends AppCompatActivity implements AddBuyerView{
    EditText buyerNameEditText, buyerEmailEditText, buyerPhoneNumberEditText, buyerDescriptionEditText;
    AddBuyerPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_details);
        buyerNameEditText = findViewById(R.id.buyer_name);
        buyerEmailEditText= findViewById(R.id.buyer_email_address);
        buyerPhoneNumberEditText = findViewById(R.id.buyer_phone_number);
        buyerDescriptionEditText = findViewById(R.id.buyer_details);

        presenter = new AddBuyerPresenterImpl(this, new SessionImpl(this));


    }
}

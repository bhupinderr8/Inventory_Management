package com.example.inventory.AddBuyer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

        presenter = new AddBuyerPresenterImpl(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_buyer_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_save_buyer:
                presenter.OnAddBuyerButton();
                break;
        }
        return true;
    }

    @Override
    public boolean checkValuesSet() {
        boolean isAllOk = true;
        if (!checkIfValueSet(buyerNameEditText, "Name")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(buyerEmailEditText, "Email")) {
            isAllOk = false;
        }
        if (!checkIfValueSet(buyerPhoneNumberEditText, "Phone")) {
            isAllOk = false;
        }
        return isAllOk;
    }

    @Override
    public String getBuyerEmail() {
        return buyerEmailEditText.getText().toString();
    }

    @Override
    public String getBuyerPhone() {
        return buyerNameEditText.getText().toString();
    }

    @Override
    public String getBuyerDescription() {
        return buyerDescriptionEditText.getText().toString();
    }

    @Override
    public String getBuyerName() {
        return buyerNameEditText.getText().toString();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void show(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private boolean checkIfValueSet(EditText text, String description) {
        if (TextUtils.isEmpty(text.getText())) {
            text.setError("Missing product " + description);
            return false;
        } else {
            text.setError(null);
            return true;
        }
    }
}

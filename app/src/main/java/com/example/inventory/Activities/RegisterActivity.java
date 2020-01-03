package com.example.inventory.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.inventory.R;
import com.example.inventory.dataObject.userObject;
import com.example.inventory.utils.FireBaseHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {

    EditText userNameEditText, passwordEditText, addressEditText, accountNumberEditText, dobEditText, phoneEditText;
    FireBaseHelper dbhelper;
    DateFormat formatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        userNameEditText = findViewById(R.id.activity_register_user_name);
        passwordEditText = findViewById(R.id.activity_register_password);
        addressEditText = findViewById(R.id.activity_register_address);
        accountNumberEditText = findViewById(R.id.activity_register_account);
        dobEditText = findViewById(R.id.activity_register_dob);
        phoneEditText = findViewById(R.id.activity_register_phone);
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        dbhelper = new FireBaseHelper();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_item_register)
        {
            dbhelper.addUser(getUserObject());
        }
        return true;
    }

    private userObject getUserObject() {
        userObject obj = new userObject();
        obj.setAccountNumber(getAccountNumber());
        obj.setAddress(getAddress());
        obj.setName(getName());
        obj.setPassword(getPassword());
        obj.setPhoneNumber(getPhone());
        obj.setBirthDate(getDOB());

        return obj;
    }

    private Date getDOB() {
        try {
            return formatter.parse(dobEditText.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getPhone() {
        return phoneEditText.getText().toString();
    }

    private String getPassword() {
        return passwordEditText.getText().toString();
    }

    private String getName() {
        return userNameEditText.getText().toString();
    }

    private String getAddress() {
        return addressEditText.getText().toString();
    }

    private String getAccountNumber() {
        return accountNumberEditText.getText().toString();
    }
}

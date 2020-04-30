package com.example.inventory.Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.inventory.ItemsList.ItemsListViewImpl;
import com.example.inventory.R;
import com.example.inventory.DataObject.userObject;
import com.example.inventory.utils.SessionImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterViewImpl extends AppCompatActivity implements RegisterView{

    EditText userNameEditText, passwordEditText, addressEditText, accountNumberEditText, dobEditText, phoneEditText;
    DateFormat formatter;
    SessionImpl session;
    RegisterPresenter presenter;
    ProgressBar progressBar;

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
        progressBar = findViewById(R.id.activity_register_progress_bar);
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        presenter = new RegisterPresenterImpl(this);
        session = new SessionImpl(this);

        hideProgess();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStat();
    }

    @Override
    protected void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_item_register)
        {
            handleSignUp();
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

    @Override
    public void disableInputs() {
        userNameEditText.setFocusable(false);
        passwordEditText.setFocusable(false);
        dobEditText.setFocusable(false);
        addressEditText.setFocusable(false);
        phoneEditText.setFocusable(false);
        accountNumberEditText.setFocusable(false);
    }

    @Override
    public void enableInputs() {
        userNameEditText.setFocusable(true);
        passwordEditText.setFocusable(true);
        dobEditText.setFocusable(true);
        addressEditText.setFocusable(true);
        phoneEditText.setFocusable(true);
        accountNumberEditText.setFocusable(true);
    }

    @Override
    public void showProgess() {
        userNameEditText.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.GONE);
        dobEditText.setVisibility(View.GONE);
        addressEditText.setVisibility(View.GONE);
        phoneEditText.setVisibility(View.GONE);
        accountNumberEditText.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgess() {
        userNameEditText.setVisibility(View.VISIBLE);
        passwordEditText.setVisibility(View.VISIBLE);
        dobEditText.setVisibility(View.VISIBLE);
        addressEditText.setVisibility(View.VISIBLE);
        phoneEditText.setVisibility(View.VISIBLE);
        accountNumberEditText.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void handleSignUp() {
        presenter.addUser(getUserObject());
        showProgess();
    }

    @Override
    public void onSignupSuccess() {
        session.doLogin(getName());
        startActivity(new Intent(RegisterViewImpl.this, ItemsListViewImpl.class));
        finish();
    }

    @Override
    public void onSignUpError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT);
    }
}

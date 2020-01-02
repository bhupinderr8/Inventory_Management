package com.example.inventory.Activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.utils.FireBaseHelper;
import com.example.inventory.utils.Session;
import com.example.inventory.dataObject.userObject;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private FireBaseHelper dbHelper;
    private Button loginButton;
    private Session session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new FireBaseHelper();
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        session = new Session(this);

        if(session.isLogin())
        {
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            clearText();
            startActivity(intent);
        }

        userObject admin = new userObject("admin123", "admin123");

        dbHelper.addUser(admin);

    }

    private boolean isValidCredentials()
    {

        if(usernameEditText.getText().toString().length()<5)
        {
            Toast.makeText(getApplicationContext(), "USERNAME SHORT", Toast.LENGTH_LONG).show();
            return true;

        }
        else if(passwordEditText.getText().toString().length()<5)
        {
            Toast.makeText(getApplicationContext(), "PASSWORD SHORT", Toast.LENGTH_LONG).show();
            return true;

        }
        return false;
    }

    public void loginUser(View view)
    {
        String username = usernameEditText.getText().toString();
        if(isValidCredentials())
        {
            return;

        }
        else
        {
            dbHelper.checkUser(this);
        }

    }

    public void doLogin()
    {
        Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        clearText();
        session.doLogin(getUserName());
        startActivity(intent);
    }

    public String getUserName() {
        return usernameEditText.getText().toString();
    }

    public void setUserName(String usernameEditText) {
        this.usernameEditText.setText(usernameEditText);
    }

    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    public void setPassword(String passwordEditText) {
        this.passwordEditText.setText(passwordEditText);
    }

    private void clearText()
    {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}

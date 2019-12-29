package com.example.inventory;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.inventory.data.FireBaseHelper;
import com.example.inventory.data.Session;
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
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            clearText();
            startActivity(intent);
        }

    }

    public boolean validUser(String username, String password)
    {
        if(username.equals("admin123") && password.equals("admin123"))
        {
            return true;
        }

        userObject user = dbHelper.readUser(username);

        return user.getName() != null;
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

    public void loginUser(android.view.View view)
    {
        String username = usernameEditText.getText().toString();
        if(isValidCredentials())
        {
            return;

        }
        else if(!validUser(username, passwordEditText.getText().toString()))
        {
            Toast.makeText(getApplicationContext(), "INVALID USERNAME OR PASSWORD", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "WELCOME", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            clearText();
            startActivity(intent);
            session.doLogin(username);
            finish();
        }

    }

    private boolean validUserName()
    {
        String username = usernameEditText.getText().toString();
        if(username.equals("admin123"))
            return true;

        return dbHelper.readUser(username).getName()!=null;
    }

    private void clearText()
    {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }
}

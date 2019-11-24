package com.example.inventory;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.inventory.data.InventoryDbHelper;
import com.example.inventory.data.Session;
import com.example.inventory.data.StockContract;
import com.example.inventory.data.UserItem;

public class LoginActivity extends AppCompatActivity {
    private Button registerButton;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private InventoryDbHelper dbHelper;
    private Button loginButton;
    private Session session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelper = new InventoryDbHelper(this);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        registerButton = findViewById(R.id.register);
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

        Cursor cursor = dbHelper.readUser(username);

        while(cursor.moveToNext())
        {
            String temp = cursor.getString(cursor.getColumnIndex(StockContract.StockEntry.USER_PASSWORD));
            if(temp.equals(password))
            {
                boolean checkAdmin=false;
                if(cursor.getColumnIndex(StockContract.StockEntry.USER_ISADMIN)==1)
                    checkAdmin=true;
                return true;
            }
        }

        return false;
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
        Cursor cursor = dbHelper.readUser(username);

        return cursor.moveToNext();
    }

    private void clearText()
    {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }

    public void registerUser(android.view.View view)
    {
        if(isValidCredentials())
            return;
        if(validUserName())
        {
            Toast.makeText(getApplicationContext(), "USERNAME EXISTS", Toast.LENGTH_LONG).show();
            return;
        }

        UserItem user = new UserItem(usernameEditText.getText().toString(), passwordEditText.getText().toString(), false);
        dbHelper.insertUser(user);
        Toast.makeText(getApplicationContext(), "REGISTERED", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        clearText();
        session.doLogin(usernameEditText.getText().toString());
        startActivity(intent);
        finish();

    }
}

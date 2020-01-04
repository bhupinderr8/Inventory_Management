package com.example.inventory.Login;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.inventory.Home.HomeActivity;
import com.example.inventory.R;
import com.example.inventory.Register.RegisterViewImpl;
import com.example.inventory.utils.Session;
import com.example.inventory.utils.SessionImpl;

public class LoginViewImpl extends AppCompatActivity implements LoginView{
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Session session;
    private LoginPresenter presenter;
    private ProgressBar progressBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        progressBar = findViewById(R.id.activity_login_progressBar);
        presenter = new LoginPresenterImpl(this, new SessionImpl(this));
        presenter.initSignIn();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onCreate();
    }

    @Override
    protected void onStop() {
        presenter.onDestroy();
        super.onStop();
    }

    public void registerUser(View view)
    {
        startActivity(new Intent(LoginViewImpl.this, RegisterViewImpl.class));
    }

    public void loginUser(View view)
    {
        presenter.initSignIn(getUserName(), getPassword());
    }

    public String getUserName() {
        return usernameEditText.getText().toString();
    }

    public String getPassword() {
        return passwordEditText.getText().toString();
    }

    @Override
    public void onSignInSucess() {
        show("Welcome");
        Intent intent = new Intent(LoginViewImpl.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void clearText()
    {
        usernameEditText.getText().clear();
        passwordEditText.getText().clear();
    }

    @Override
    public void onSignInError(String error) {
        show(error);
    }

    @Override
    public void show(String val)
    {
        Toast.makeText(this, val, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }
}

package com.example.inventory.Login;

public interface LoginPresenter {
    void initSignIn(String username, String password);
    void initSignIn();
    void onCreate();
    void onDestroy();
}

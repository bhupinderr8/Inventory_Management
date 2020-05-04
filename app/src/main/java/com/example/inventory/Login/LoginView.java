package com.example.inventory.Login;

public interface LoginView {
    void onSignInSucess();

    void clearText();

    void onSignInError(String error);

    void showProgress();

    void hideProgress();

    void show(String val);
}

package com.example.inventory.Register;

public interface RegisterView {
    void disableInputs();
    void enableInputs();
    void showProgess();
    void hideProgess();
    void handleSignUp();
    void onSignupSuccess();
    void onSignUpError(String error);
}

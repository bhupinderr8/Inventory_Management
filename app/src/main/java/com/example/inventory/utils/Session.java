package com.example.inventory.utils;

public interface Session {
    boolean isLogin();

    void doLogin(String user);

    String getUserName();

    void doLogout();
}

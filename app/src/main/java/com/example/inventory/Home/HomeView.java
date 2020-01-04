package com.example.inventory.Home;

public interface HomeView {
    void showName(String name);
    void OnLogout();
    void show(String val);

    void launchActivity(Class classItem);
}
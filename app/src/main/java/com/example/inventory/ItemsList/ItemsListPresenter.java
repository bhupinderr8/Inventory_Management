package com.example.inventory.ItemsList;

public interface ItemsListPresenter {
    void addDummyData();
    void OnLogoutButton();

    void onStart();
    void onStop();

    void onQueryTextChange(String text);

    void OnNewOrderButton();

    void OnAddItem();
}

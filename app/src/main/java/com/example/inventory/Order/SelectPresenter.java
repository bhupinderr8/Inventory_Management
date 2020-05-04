package com.example.inventory.Order;

public interface SelectPresenter {
    void onQueryTextChange(String query);

    void onStart();

    void onStop();

    void OnConfirmItemClicked();

    void OnPopUpDismissed();

    void informDataChanged();
}

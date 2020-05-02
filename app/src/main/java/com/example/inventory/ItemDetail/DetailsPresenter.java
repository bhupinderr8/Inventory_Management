package com.example.inventory.ItemDetail;

public interface DetailsPresenter {
    void onSubtractOneToQuantity();

    void onSumOneToQuantity();

    void onImageSelectButton();

    void onActionSave();

    void deleteAllRowsFromTable();

    void deleteOneItemFromTable(String itemId);

    void onStart();

    void onStop();
}

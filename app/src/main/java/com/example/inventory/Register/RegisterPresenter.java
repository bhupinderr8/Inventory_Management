package com.example.inventory.Register;

import com.example.inventory.DataObject.userObject;

public interface RegisterPresenter {
    void addUser(userObject userObject);

    void onStat();

    void onStop();

}

package com.example.inventory.ui.login;

/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;
    private int isAdmin;
    //... other data fields that may be accessible to the UI

    public int getIsAdmin() {
        return isAdmin;
    }

    LoggedInUserView(String displayName, int isAdmin) {
        this.displayName = displayName;
        this.isAdmin = isAdmin;
    }

    String getDisplayName() {
        return displayName;
    }
}

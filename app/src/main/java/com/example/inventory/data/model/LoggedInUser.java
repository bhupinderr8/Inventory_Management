package com.example.inventory.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private String userId;
    private String displayName;
    private int isAdmin;

    public int getAdmin() {
        return isAdmin;
    }

    public LoggedInUser(String userId, String displayName) {
        this.userId = userId;
        this.displayName = displayName;
        this.isAdmin=0;
    }

    public LoggedInUser(String userId, String displayName, int isAdmin) {
        this.userId = userId;
        this.displayName = displayName;
        this.isAdmin=isAdmin;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }
}

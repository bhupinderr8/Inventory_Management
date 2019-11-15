package com.example.inventory.data;

public class UserItem {
    private final String userName;
    private final String password;
    private final Boolean isAdmin;

    UserItem(String userName, String password, Boolean isAdmin)
    {
        this.isAdmin = isAdmin;
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
}

package com.example.inventory.data;

import android.database.Cursor;

import com.example.inventory.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private InventoryDbHelper dbHelper;

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication

            if(username.equals("admin123") && password.equals("admin123"))
            {
                LoggedInUser u = new LoggedInUser(username, username, 1);
                return new Result.Success<>(u);
            }

            Cursor cursor = dbHelper.readUser(username);

            while(cursor.moveToNext())
            {
                String temp = cursor.getString(cursor.getColumnIndex(StockContract.StockEntry.USER_PASSWORD));
                if(temp.equals(password))
                {
                    LoggedInUser u = new LoggedInUser(username, username, cursor.getColumnIndex(StockContract.StockEntry.USER_ISADMIN));
                    return new Result.Success<>(u);
                }
            }

            return new Result.Error(new IOException("Error logging in", new Throwable("Not Found")));
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication

    }
}

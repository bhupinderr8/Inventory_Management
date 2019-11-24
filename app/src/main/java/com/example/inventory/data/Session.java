package com.example.inventory.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {
    SharedPreferences pref;

    SharedPreferences.Editor editor;

    Context _context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "AndroidHivePref";

    private static final String IS_LOGIN = "IsLoggedIn";

    public static final String KEY_NAME = "name";

    public Session(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void doLogin(String name){
        editor.putBoolean(IS_LOGIN, true);

        editor.putString(KEY_NAME, name);

        editor.commit();
    }

    public String getUserName()
    {
        return pref.getString(KEY_NAME, null);
    }

    public void doLogout(){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, false);

        // Storing name in pref
        editor.putString(KEY_NAME, null);

        // commit changes
        editor.commit();
    }

    public boolean isLogin()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }
}

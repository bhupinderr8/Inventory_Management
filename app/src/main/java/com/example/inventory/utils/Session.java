package com.example.inventory.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.example.inventory.dataObject.itemObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

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

    public void saveHashMap(String key , Object obj) {
        SharedPreferences.Editor editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        editor.putString(key,json);
        editor.apply();     // This line is IMPORTANT !!!
    }


    public itemObject getHashMap(String key) {
        Gson gson = new Gson();
        String json = pref.getString(key,"");
        java.lang.reflect.Type type = new TypeToken<itemObject>(){}.getType();
        return gson.fromJson(json, type);
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

    public void addItem(itemObject item)
    {

    }

    public boolean isLogin()
    {
        return pref.getBoolean(IS_LOGIN, false);
    }
}

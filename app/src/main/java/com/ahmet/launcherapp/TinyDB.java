package com.ahmet.launcherapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Ahmet TOPAK
 * @version 1.0
 * @since 5/13/2024
 */


public class TinyDB {
    private static final String DEFAULT_PREFERENCE_NAME = "TinyDB";
    private SharedPreferences preferences;

    public TinyDB(Context context) {
        this(context, DEFAULT_PREFERENCE_NAME);
    }

    public TinyDB(Context context, String dbName) {
        preferences = context.getSharedPreferences(dbName, Context.MODE_PRIVATE);
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }
    public String getString(String key , String defaultValue) {
        return preferences.getString(key, defaultValue);
    }
}
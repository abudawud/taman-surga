package com.envoss.tamansurga.helpers;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreference {
    private String KEY_ID = "id";
    private String KEY_USERNAME = "username";
    private String KEY_EMAIL = "email";
    private String KEY_FIRST_NAME = "first_name";
    private String KEY_LAST_NAME = "last_name";
    private String KEY_ROLE = "role";
    private String KEY_TOKEN = "token";

    private SharedPreferences preferences;

    public UserPreference(Context context) {
        String PREFS_NAME = "UserPref";
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setUsername(String name) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USERNAME, name);
        editor.apply();
    }

    public String getUsername() {
        return preferences.getString(KEY_USERNAME, null);
    }

    public void setEmail(String email) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public String getEmail() {
        return preferences.getString(KEY_EMAIL, null);
    }

    public void setId(Integer id) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_ID, id);
        editor.apply();
    }

    public Integer getId() {
        return preferences.getInt(KEY_ID, 0);
    }

    public void setFirstname(String firstname) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_FIRST_NAME, firstname);
        editor.apply();
    }

    public String getFristname() {
        return preferences.getString(KEY_FIRST_NAME, null);
    }

    public void setLastname(String lastname) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LAST_NAME, lastname);
        editor.apply();
    }

    public String getLastname() {
        return preferences.getString(KEY_LAST_NAME, null);
    }

    public void setRole(int role){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(KEY_ROLE, role);
        editor.apply();
    }

    public int getRole(){
        return preferences.getInt(KEY_ROLE, 0);
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

    public String getToken(){
        return preferences.getString(KEY_TOKEN, null);
    }
}

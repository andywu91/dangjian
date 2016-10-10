package com.example.andy.dangjian.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/10/5.
 */

public enum Utils {

    INSTANCE;

    private static final String SHAREDPREFERENCE_USER_NAME = "user";

    private static final String USER_PHONE_NUMBER = "user_phone_number";

    private static final String USER_PID_NUMBER = "user_pid_number";

    public void saveUserPhoneNumber(Context context, String userPhoneNumber) {

        setUserPhoneNumberInSharedPreferences(getSharedPreferencesForUser(context), userPhoneNumber);
    }

    public String getUserPhoneNumber(Context context) {

        return getUserPhoneNumberInSharedPreferences(getSharedPreferencesForUser(context));
    }

    public void saveUserPidNumber(Context context, String userPidNumber) {
        setUserPidNumberInSharedPreferences(getSharedPreferencesForUser(context), userPidNumber);
    }

    public String getUserPidNumber(Context context) {
        return getUserPidumberInSharedPreferences(getSharedPreferencesForUser(context));
    }

    public void clearUserPhoneNumber(Context context) {
        clearUserPhoneNumberInSharedPreferences(getSharedPreferencesForUser(context));
    }

    public void clearUserPidNumber(Context context) {
        clearUserPidNumberInSharedPreferences(getSharedPreferencesForUser(context));
    }

    private SharedPreferences getSharedPreferencesForUser(Context context) {

        return context.getSharedPreferences(SHAREDPREFERENCE_USER_NAME, Context.MODE_PRIVATE);

    }

    private void setUserPidNumberInSharedPreferences(SharedPreferences sharedPreference, String userPidNumber) {

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(USER_PID_NUMBER, userPidNumber);
        editor.apply();
    }

    private void setUserPhoneNumberInSharedPreferences(SharedPreferences sharedPreference, String userPhoneNumber) {

        SharedPreferences.Editor editor = sharedPreference.edit();
        editor.putString(USER_PHONE_NUMBER, userPhoneNumber);
        editor.apply();
    }

    private String getUserPidumberInSharedPreferences(SharedPreferences sharedPreferences) {

        return sharedPreferences.getString(USER_PID_NUMBER, "");

    }

    private String getUserPhoneNumberInSharedPreferences(SharedPreferences sharedPreferences) {

        return sharedPreferences.getString(USER_PHONE_NUMBER, "");

    }

    private void clearUserPhoneNumberInSharedPreferences(SharedPreferences sharedPreferences) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PHONE_NUMBER,"");
        editor.apply();
    }

    private void clearUserPidNumberInSharedPreferences(SharedPreferences sharedPreferences) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_PID_NUMBER,"");
        editor.apply();
    }

    public void hideSoftInput(Context context, EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

}

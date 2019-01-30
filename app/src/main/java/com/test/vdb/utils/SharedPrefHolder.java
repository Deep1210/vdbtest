package com.test.vdb.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Kashif on 16-Apr-18.
 */
public class SharedPrefHolder {
    private final static String PREF_FILE = "PREF";

    private static SharedPrefHolder sharedPrefHolder;
    private SharedPreferences pref;
    SharedPreferences.Editor editor;

    public SharedPrefHolder(Context context) {
        pref = context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public static SharedPrefHolder getInstance(Context context) {
        if (sharedPrefHolder == null) {
            sharedPrefHolder = new SharedPrefHolder(context);
        }
        return sharedPrefHolder;
    }

    public void clearData(){
        editor.clear().commit();
    }

    public void setAuthToken(boolean token){
        editor.putBoolean(AppConstants.AUTH_START,token);
        editor.commit();
    }

    public boolean getAuthToken(){
        return pref.getBoolean(AppConstants.AUTH_START,false);
    }


}

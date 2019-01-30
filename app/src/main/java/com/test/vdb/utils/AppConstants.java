package com.test.vdb.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Kashif on 26-Jul-18.
 */
public class AppConstants {

    public static String BASE_URL = "https://api.github.com/users/JakeWharton/";  ////// STAGING

    public static final String GET_LIST = "repos?";
    public static final String CONTENT_TYPE_KEY = "Content-Type";
    public static final String CONTENT_TYPE_VALUE = "application/json";
    public static final String AUTH_START = "auth_start";


    public static  boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}

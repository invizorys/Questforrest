package com.mediahack.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.mediahack.MediaHackApp;

/**
 * Created by Roma on 08.10.2016.
 */

public class SharedPrefHelper {
    private static final String SETTINGS_FILE = "com.quest.for.rest.settings";
    private static final String TOKEN = "token";

    private static SharedPreferences getPreferences() {
        return MediaHackApp.instance().getSharedPreferences(SETTINGS_FILE, Context.MODE_PRIVATE);
    }

    public static void saveToken(String token) {
        getPreferences().edit().putString(TOKEN, token).apply();
    }

    public static String getToken() {
        return getPreferences().getString(TOKEN, "");
    }
}

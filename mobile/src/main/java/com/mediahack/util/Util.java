package com.mediahack.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.IOException;

/**
 * Created by Roma on 08.10.2016.
 */

public class Util {

    public static AlertDialog.Builder getStandardDialog(Context context, String message) {
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setCancelable(true);
        ad.setMessage(message);
        ad.setPositiveButton(context.getString(android.R.string.ok), null);
        return ad;
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static boolean isInternetAvailable() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

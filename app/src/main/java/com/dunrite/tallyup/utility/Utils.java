package com.dunrite.tallyup.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Random;

/**
 * Utility class for various common methods
 */

public class Utils {

    /**
     * Empty Constructor
     */
    private Utils() {}

    /*****************************************************************************
     * Getters
     *****************************************************************************/

    /**
     * Returns whether or not this is the first time the app has been opened
     *
     * @param a current activity
     * @return if first launch or not
     */
    public static boolean isFirstLaunch(Activity a) {
        SharedPreferences sharedPref = a.getSharedPreferences("FIRST", Context.MODE_PRIVATE);
        return sharedPref.getBoolean("first", true);
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /*****************************************************************************
     * Setters
     *****************************************************************************/

    /**
     * App has been launched, set first to false
     */
    public static void appHasLaunched(Activity a) {
        SharedPreferences sharedPref = a.getSharedPreferences("FIRST", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("first", false);
        editor.apply();
    }


    /*****************************************************************************
     * Misc
     *****************************************************************************/


    public static String generateSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static String replaceLast(String string, String toReplace, String replacement) {
        int pos = string.lastIndexOf(toReplace);
        if (pos > -1) {
            return string.substring(0, pos)
                    + replacement
                    + string.substring(pos + toReplace.length(), string.length());
        } else {
            return string;
        }
    }
}

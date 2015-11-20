package com.reeuse.component;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * MyApplication.java
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


    }

    /**
     * Check Internet connection
     *
     * @param context
     *            of the activity
     * @return true if Internet available otherwise false.
     */

    public static boolean isNetConnected(Context context) {
        ConnectivityManager conMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = conMgr.getAllNetworkInfo();
        // Checking for all possible Internet providers
        if (info != null)
            for (int i = 0; i < info.length; i++)
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    return true; // internet connection available
                }
        return false;//No internet connection.
    }

}

package com.example.khizar.magicrecipe;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class InternetStatus {
    private static InternetStatus instance = new InternetStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;

    public static InternetStatus getInstance(Context ctx) {
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline() {
        try {

            connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            Log.v("Buzz", "Network details: " + networkInfo);
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected() && networkInfo.isConnectedOrConnecting();
            return connected;

        } catch (Exception e) {
            Log.v("Buzz","Connectivity Exception"+ e.toString());
        }
        return connected;
    }

}

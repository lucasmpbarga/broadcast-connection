package com.example.computer.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static com.example.computer.myapplication.MainActivity.dialog;


public class NetworkChangeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {
                dialog(true);
                Log.e("Barga", "Online Connect Intenet");
            } else {
                dialog(false);
                Log.e("Barga", "Connect Fail");
            }
        } catch (NullPointerException nullPointerException) {
            nullPointerException.printStackTrace();
        }
    }

    private boolean isOnline(Context context){
        try{
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo != null && networkInfo.isConnected());
        }catch (NullPointerException nullPointerException){
            nullPointerException.printStackTrace();
            return false;
        }
    }
}

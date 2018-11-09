package com.example.computer.myapplication;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.computer.receivers.NetworkChangeReceiver;


public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    static TextView textViewCheckConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        textViewCheckConnection = (TextView) findViewById(R.id.textViewCheckConnection);
        broadcastReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForAndroid();
    }

    public static void dialog(boolean value) {
        if (value) {
            textViewCheckConnection.setText("1 - Connected");
            textViewCheckConnection.setBackgroundColor(Color.GREEN);
            textViewCheckConnection.setTextColor(Color.WHITE);

            Handler handler = new Handler();
            Runnable delayrunnable = new Runnable() {
                @Override
                public void run() {
                    textViewCheckConnection.setVisibility(View.GONE);
                }
            };
            handler.postDelayed(delayrunnable, 3000);

        } else {
            textViewCheckConnection.setVisibility(View.VISIBLE);
            textViewCheckConnection.setText("2- Not Connected");
            textViewCheckConnection.setBackgroundColor(Color.RED);
            textViewCheckConnection.setTextColor(Color.WHITE);
        }
    }

    private void registerNetworkBroadcastForAndroid() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(broadcastReceiver);
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}
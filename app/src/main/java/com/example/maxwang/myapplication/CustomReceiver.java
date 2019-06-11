package com.example.maxwang.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Received BroadCast from CustomReceiver", Toast.LENGTH_SHORT).show();
        Log.i("broadcast", "custom");
    }
}

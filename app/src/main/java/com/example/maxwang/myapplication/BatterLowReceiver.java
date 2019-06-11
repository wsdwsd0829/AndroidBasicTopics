package com.example.maxwang.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BatterLowReceiver extends BroadcastReceiver {

    // ??? why this is called does not match the number of register???
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Toast.makeText(context, "Battery Low", Toast.LENGTH_SHORT).show();
        Log.i("broadcast", "battery low");
    }
}

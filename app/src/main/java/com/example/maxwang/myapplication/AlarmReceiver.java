package com.example.maxwang.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "in Alarm Receiver", Toast.LENGTH_SHORT).show();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
            notificationManager.createNotificationChannel(notificationChannel);
            builder = new NotificationCompat.Builder(context.getApplicationContext(), notificationChannel.getId());
        } else {
            builder = new NotificationCompat.Builder(context.getApplicationContext());
        }

        Intent it = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 1,it, 0);

        builder = builder
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .setContentTitle("Alarm")
                .setContentText("msg")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pi);
        notificationManager.notify(1, builder.build());
    }
}

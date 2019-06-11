package com.example.maxwang.myapplication;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import static android.widget.CompoundButton.OnCheckedChangeListener;
import static android.widget.CompoundButton.OnClickListener;

public class MainActivity extends AppCompatActivity implements OnCheckedChangeListener{
    RadioButton rb1;
    RadioButton rb2;
    CheckBox cb1;
    CheckBox cb2;
    ToggleButton tb1;
    Button toTabLayoutBtn;
    Button toRecyclerViewBtn;
    Button toStyleButton;

    Button uitestButton;
    EditText nameEditText;

    Button asyncTaskButton;

    Button toInternetButton;

    Button systemBroadCastButton;
    Button customBroadCastButton;

    Button startServiceBtn;
    Button stopServiceBtn;

    Button notificationBtn;

    ToggleButton jobSchedulerToggleBtn;

    EditText sharedPrefEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        cb1 = findViewById(R.id.checkBox);
        cb2 = findViewById(R.id.checkBox2);
        tb1 = findViewById(R.id.toggleButton);
        toTabLayoutBtn = findViewById(R.id.tab);
        toRecyclerViewBtn = findViewById(R.id.recycler_view);
        toStyleButton = findViewById(R.id.style);

        nameEditText = findViewById(R.id.student_name);
        uitestButton = findViewById(R.id.uitest);

        asyncTaskButton = findViewById(R.id.async_task);

        toInternetButton = findViewById(R.id.internet);

        systemBroadCastButton = findViewById(R.id.sys_broadcast);
        customBroadCastButton = findViewById(R.id.custom_broadcast);

        startServiceBtn = findViewById(R.id.start_service);
        stopServiceBtn = findViewById(R.id.stop_service);

        notificationBtn = findViewById(R.id.notification);

        jobSchedulerToggleBtn = findViewById(R.id.job_scheduler);
        jobSchedulerToggleBtn.setOnCheckedChangeListener(this);

        sharedPrefEditText = findViewById(R.id.sharedPrefText);

        rb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "rb1 checked" + rb1.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        rb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "rb2 checked" + rb2.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "cb1 checked" + cb1.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "cb1 unchecked" + cb1.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        cb2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(MainActivity.this, "cb2 checked" + cb2.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "cb2 unchecked" + cb2.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        tb1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tb1.isChecked()) {
                    Toast.makeText(MainActivity.this, "Toggle On", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Toggle Off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        toTabLayoutBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(v.getContext(), TabActivity.class);
                startActivity(t);
            }
        });


        toRecyclerViewBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(v.getContext(), Recycler.class);
                startActivity(t);
            }
        });

        toStyleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(v.getContext(), StyleActivity.class);
                startActivity(t);
            }
        });

        uitestButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), UITestActivity.class);
                // NEED ToString() !!!!.
                it.putExtra("nameKey", nameEditText.getText().toString());
                startActivity(it);
            }
        });

        asyncTaskButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAsyncTask mat = new MyAsyncTask(v.getContext());
                mat.execute();
            }
        });

        toInternetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(v.getContext(), NetworkConnection.class);
                startActivity(it);
            }
        });

        systemBroadCastButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // not working without this. // at least on emulator.
                registerReceiver(new BatterLowReceiver(), new IntentFilter(Intent.ACTION_BATTERY_LOW));
            }
        });

        customBroadCastButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter mIntentFilter=new IntentFilter("android.intent.action.MY_CUSTOM_ACTION");
                registerReceiver(new CustomReceiver(), mIntentFilter);

                Intent it = new Intent();
                it.setAction("android.intent.action.MY_CUSTOM_ACTION");
                sendBroadcast(it);
            }
        });

        startServiceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent(v.getContext(), MyService.class);
                startService(service);
            }
        });

        stopServiceBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent service = new Intent(v.getContext(), MyService.class);
                stopService(service);
            }
        });

        notificationBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) v.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder builder = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel notificationChannel = new NotificationChannel("ID", "Name", importance);
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder = new NotificationCompat.Builder(getApplicationContext(), notificationChannel.getId());
                } else {
                    builder = new NotificationCompat.Builder(getApplicationContext());
                }

                Intent it = new Intent(v.getContext(), MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(v.getContext(), 1,it, 0);


                builder = builder
                        .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                        .setContentTitle("title")
                        .setContentText("msg")
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .setContentIntent(pi);
                notificationManager.notify(1, builder.build());
                finish();
            }
        });
    }

    public void navigateToSecond(View view) {
        Intent it1 = new Intent(this, Second.class);
        startActivity(it1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT).show();
        return true;
    }

    public void startAlarm(View view) {
        Toast.makeText(this, "startAlarm", Toast.LENGTH_SHORT).show();
        //Register BroadCast receiver
        IntentFilter filter = new IntentFilter("android.intent.action.ALARM");
        registerReceiver(new AlarmReceiver(), filter);

        // Create Intent to for the receiver
        Intent it = new Intent();
        it.setAction("android.intent.action.ALARM");

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, it, 0);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*10, pi);
    }

    public void stopAlarm(View view) {
        Toast.makeText(this, "stopAlarm", Toast.LENGTH_SHORT).show();
        Intent it = new Intent();
        it.setAction("android.intent.action.ALARM");

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, it, 0);

        manager.cancel(pi);
    }

    JobScheduler jobScheduler;
    JobInfo jobInfo;
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
    Log.i("JobScheduler","?????");
        if (isChecked) {
            JobInfo.Builder jobInfoBuilder = new JobInfo.Builder(1, new ComponentName(getPackageName(), MyJobService.class.getName()));

            jobInfoBuilder.setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                    .setRequiresDeviceIdle(false);

            jobInfo = jobInfoBuilder.build();
            jobScheduler.schedule(jobInfo);
        } else {
            jobScheduler.cancelAll();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sh = getSharedPreferences("MyShared", MODE_PRIVATE);
        SharedPreferences.Editor editor= sh.edit();
        editor.putString("name", sharedPrefEditText.getText().toString());
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sh = getSharedPreferences("MyShared", MODE_PRIVATE);
        String s = sh.getString("name", "Default name");
        sharedPrefEditText.setText(s);
    }

    public void toSQLiteClicked(View view) {
        Intent it = new Intent(this, SQLiteActivity.class);
        startActivity(it);
    }

    public void onContentProviderClicked(View view) {
        Intent it = new Intent(this, ContentProviderActivity.class);
        startActivity(it);
    }
}

package com.example.maxwang.myapplication;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class MyJobService extends JobService {
    public MyJobService() {
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i("JobService", "onStartJob");
        Toast.makeText(this, "Job Started", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i("JobService", "onStopJob");
        Toast.makeText(this, "Job Stopped", Toast.LENGTH_SHORT).show();
        return true;
    }
}

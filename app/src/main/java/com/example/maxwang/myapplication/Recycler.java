package com.example.maxwang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Recycler extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        String titles[] = getResources().getStringArray(R.array.titles);
        String details[] = getResources().getStringArray(R.array.details);
        int imagesIds[] = {
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_background
        };

        rv = findViewById(R.id.recycler_view);
        RecyclerViewAdapter rva = new RecyclerViewAdapter(this, titles, details, imagesIds);
        rv.setAdapter(rva);
        rv.setLayoutManager(new LinearLayoutManager(this));
    }


}

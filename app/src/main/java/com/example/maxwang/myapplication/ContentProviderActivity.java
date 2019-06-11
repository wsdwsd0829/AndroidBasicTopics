package com.example.maxwang.myapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ContentProviderActivity extends AppCompatActivity {

    EditText e1, e2;

    ContentValues values = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        e1 = findViewById(R.id.employee);
        e2 = findViewById(R.id.job_desc);
    }

    public void onSaved(View view) {
        values.put("emp_name", e1.getText().toString());
        values.put("profile", e2.getText().toString());
        Uri uri = getContentResolver().insert(CompanyContentProvider.CONTENT_URI, values);
        Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
    }

    public void onLoad(View view) {
        Cursor cr = getContentResolver().query(CompanyContentProvider.CONTENT_URI, null, null ,null, "_id");
        StringBuilder sb = new StringBuilder();
        Log.i("@@@@DB"," OnLoad CR " +  cr.toString());
        while (cr.moveToNext()) {
            Log.i("@@@@DB"," SB " +  sb.toString());
            int id = cr.getInt(0);
            String s1 = cr.getString(1);
            String s2 = cr.getString(2);
            sb.append(id).append(" ").append(s1).append(" ").append(s2).append("\n");
        }
        Toast.makeText(this, sb.toString(), Toast.LENGTH_SHORT).show();
    }
}

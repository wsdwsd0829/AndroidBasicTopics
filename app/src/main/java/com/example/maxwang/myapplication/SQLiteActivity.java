package com.example.maxwang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SQLiteActivity extends AppCompatActivity {
    EditText name;
    EditText college;

    MyCoreDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        name = findViewById(R.id.student_name);
        college = findViewById(R.id.college);

        db = new MyCoreDatabase(this);
    }

    public void onSave(View view) {
        db.insert(name.getText().toString(), college.getText().toString());
    }

    public void onLoad(View view) {
        db.getAll();
    }
}

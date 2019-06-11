package com.example.maxwang.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UITestActivity extends AppCompatActivity {
    TextView nameView;
    Button nameButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uitest);
        // hwy name View not showing
        nameView = findViewById(R.id.student_name);
        nameButton = findViewById(R.id.name_btn);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("nameKey");
        System.out.print(name);
        nameView.setText(name);
        nameButton.setText(name);
    }
}

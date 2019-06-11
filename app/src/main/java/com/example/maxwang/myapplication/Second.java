package com.example.maxwang.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class Second extends AppCompatActivity {

    Button bt1;
    Button bt2;
    EditText et1;
    EditText et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        bt1 = findViewById(R.id.alert);
        bt2 = findViewById(R.id.progress);

        et1 = findViewById(R.id.email);
        et2 = findViewById(R.id.password);

        registerForContextMenu(et1);
        registerForContextMenu(et2);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            case R.id.email:
                getMenuInflater().inflate(R.menu.edit1, menu);
                break;
            case R.id.password:
                getMenuInflater().inflate(R.menu.edit2, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast toast = Toast.makeText(this, item.getTitle().toString(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
        return true;
    }

    public void showAlert(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Are you sure?");
         alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
             }
         });
         alertDialog.setCancelable(false);
         alertDialog.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialog, int which) {
                 finish();
             }
         });
         alertDialog.show();
    }

    public void showProgress(View view) {
        //Deprecated
        ProgressDialog.Builder progressDialog = new ProgressDialog.Builder(this);
        progressDialog.setTitle("Downloading");
        progressDialog.show();
        progressDialog.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    });
}




}

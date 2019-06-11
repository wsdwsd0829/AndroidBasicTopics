package com.example.maxwang.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyCoreDatabase extends SQLiteOpenHelper {

    static final private String DB_NAME = "Education";
    static final private String DB_TABLE = "Students";
    static final private int DB_VER = 1;

    Context ctx;
    SQLiteDatabase db;

    public MyCoreDatabase(Context ct) {
        super(ct, DB_NAME, null, DB_VER);
        ctx = ct;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_TABLE + " (_id integer primary key autoincrement, stu_name text, college_name text);");
        Log.i("Database", "DB table created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TABLE);
        onCreate(db);
    }

    public void insert(String s1, String s2) {
        db = getWritableDatabase();
        db.execSQL("insert into " + DB_TABLE + " (stu_name, college_name) values ('"+s1+"', '"+ s2 + "');");

        Toast.makeText(ctx, "Data Saved", Toast.LENGTH_SHORT).show();
    }

    public void getAll() {
        db = getReadableDatabase();
        Cursor cr = db.rawQuery("Select * from " + DB_TABLE, null);
        StringBuilder str = new StringBuilder();
        while (cr.moveToNext()) {
            String s1 = cr.getString(1); //0 is id
            String s2 = cr.getString(2);
            str.append(s1).append("     ").append(s2).append("\n");
            Toast.makeText(ctx, str.toString(), Toast.LENGTH_SHORT).show();
        }

    }
}

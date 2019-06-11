package com.example.maxwang.myapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

import java.util.Objects;

public class CompanyContentProvider extends ContentProvider {
    public CompanyContentProvider() {
    }

    public static final String AUTHORITY = "com.maxwang.company.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/emp");

    public static int EMP = 1;
    public static int EMP_ID = 2;

    static UriMatcher myUri = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        myUri.addURI(AUTHORITY, "emp", EMP);
        myUri.addURI(AUTHORITY, "emp/#", EMP_ID); //# means can be any id
    }

    SQLiteDatabase db;
    MyDatabaseHelper helper;
    private static final String DB_NAME = "company";
    private static final String DB_TABLE = "emp";
    private static final int DB_VER = 1;


    private class MyDatabaseHelper extends SQLiteOpenHelper {
        Context ctx;

        public MyDatabaseHelper(Context ct) {
            super(ct, DB_NAME, null, DB_VER);
            ctx = ct;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + DB_TABLE + " (_id integer primary key autoincrement, emp_name text, profile text);");
            Log.i("Database", "DB table created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists " + DB_TABLE);
            onCreate(db);
        }
    }

    @Override
    public boolean onCreate() {
        helper = new MyDatabaseHelper(getContext());
        db = helper.getWritableDatabase();
        Log.i("@@@@DB", db != null ? "true" : "False");
        return db != null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Log.i("@@@@DB", values.toString());
        long row = db.insert(DB_TABLE, null, values);
        if (row > 0) {
            uri = ContentUris.withAppendedId(CONTENT_URI, row);
            getContext().getContentResolver().notifyChange(uri, null);
            Log.i("@@@@DB", "notifi");
        }
        return uri;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder myQuery = new SQLiteQueryBuilder();
        myQuery.setTables(DB_TABLE);
        db = helper.getReadableDatabase();
        Cursor cr = myQuery.query(db, null, null, null, null, null, "_id");
        cr.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);
        Log.i("@@@@DB", "CR " + cr.toString());
        return cr;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}

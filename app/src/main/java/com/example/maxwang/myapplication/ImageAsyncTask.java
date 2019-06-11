package com.example.maxwang.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    Context ctx;

    ImageAsyncTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        URL url = null;
        String result = "";
        try {
            url = new URL(strings[0]);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(10000);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            Bitmap bm = BitmapFactory.decodeStream(inputStream);
            return bm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        NetworkConnection.iv.setImageBitmap(bitmap);
    }
}

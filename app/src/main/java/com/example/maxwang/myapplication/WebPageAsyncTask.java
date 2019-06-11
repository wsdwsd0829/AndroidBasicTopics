package com.example.maxwang.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebPageAsyncTask extends AsyncTask<String, Void, String> {
    Context ctx;

    WebPageAsyncTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected String doInBackground(String... strings) {
        URL url = null;
        String result = "";
        try {
            url = new URL(strings[0]);
            URLConnection conn = url.openConnection();
            conn.setConnectTimeout(20000);
            conn.setReadTimeout(10000);
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            StringBuilder strBuilder = new StringBuilder();
            while ( (line = reader.readLine()) != null ) {
                Log.i("Line", line);
                strBuilder.append(line).append("\n");
            }

            reader.close();
            inputStream.close();

            return strBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        NetworkConnection.tv.setText(s);
    }
}

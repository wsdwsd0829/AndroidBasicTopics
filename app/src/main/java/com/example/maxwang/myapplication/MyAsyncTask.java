package com.example.maxwang.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void, Integer, String> {
    ProgressDialog pd;
    Context ctx;

    MyAsyncTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd = new ProgressDialog(ctx);
        pd.setTitle("downloading");
        pd.setMessage("Progress");
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setMax(10);
        pd.setButton(ProgressDialog.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                cancel(true);
            }
        });
        pd.show();
    }

    @Override
    protected String doInBackground(Void... voids) {
        for(int i=1;i<=10;i++) {
            try {
                Thread.sleep(1000);
                Log.i("Thread", "processing " + i);
                publishProgress(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "Fail";
            }
        }
        return "success";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        pd.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        pd.dismiss();
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }
}

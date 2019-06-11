package com.example.maxwang.myapplication;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class NetworkConnection extends AppCompatActivity {
    Button downloadWebPageButton;
    Button downloadImageButton;
    static TextView tv;
    static ImageView iv;
    ConnectivityManager connmanager;
    NetworkInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_connection);

        downloadImageButton = findViewById(R.id.download_image);
        downloadWebPageButton = findViewById(R.id.download_webpage);
        tv = findViewById(R.id.webpage_result);
        iv = findViewById(R.id.image_result);

        connmanager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        downloadWebPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebPageAsyncTask wpat =  new WebPageAsyncTask(v.getContext());
                wpat.execute("https://google.com");
            }
        });

        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info = connmanager.getActiveNetworkInfo();

                if (info != null && info.isConnected()) {
                    ImageAsyncTask iat = new ImageAsyncTask(v.getContext());
                    iat.execute("https://images.pexels.com/photos/248797/pexels-photo-248797.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500");
                } else {
                    Toast.makeText(v.getContext(), "NO CONNECTION", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

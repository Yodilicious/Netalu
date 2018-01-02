package com.netalu.netaluapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FileDownloaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_downloader);
    }

    public class NetaluAsyncTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            return null;
        }
    }

}

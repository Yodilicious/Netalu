package com.netalu.netaluapp;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.app.ProgressDialog;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloaderActivity extends AppCompatActivity {

    FileDownloaderActivity activity;
    Button imageButton;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_downloader);

        activity = this;

        imageButton = (Button)findViewById(R.id.downloadButton);
        image = (ImageView)findViewById(R.id.downloadImage);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetaluAsyncTask task = new NetaluAsyncTask();
                task.execute("https://images5.alphacoders.com/568/568653.jpg");
            }
        });
    }

    public class NetaluAsyncTask extends AsyncTask<String, Integer, String> {

        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... strings) {

            String message = null;
            String path = strings[0];
            int file_length;

            try {
                URL url = new URL(path);
                URLConnection urlConnection = url.openConnection();

                urlConnection.connect();

                file_length = urlConnection.getContentLength();

                File new_folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "imageFolder");

                if(!new_folder.exists())
                {
                    if(!new_folder.mkdir()) {
                        String error = "Hello ERROR";
                    }
                }

                File output_file = new File(new_folder, "downloaded_image.jpg");
                OutputStream outputStream = new FileOutputStream(output_file);

                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                byte[] data = new byte[1024];
                int total = 0;
                int count;

                while((count = inputStream.read(data)) != -1) {
                    total += count;

                    outputStream.write(data, 0, count);
                    int progress = 100 * total / file_length;
                    publishProgress(progress);
                }
                inputStream.close();
                outputStream.close();

            } catch (MalformedURLException e) {

                message = e.getMessage();
            } catch (IOException e) {

                message = e.getMessage();
            } catch (Exception e) {

                message = e.getMessage();
            }

            if(message == null) {
                return "Download Complete.";
            }

            return message;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {

            progressDialog.hide();
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

            File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "imageFolder");
            File output_file = new File(folder, "downloaded_image.jpg");
            String path = output_file.toString();
            image.setImageDrawable(Drawable.createFromPath(path));
        }

        @Override
        protected void onPreExecute() {

            progressDialog = new ProgressDialog(activity);
            progressDialog.setTitle("Download in Progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }
    }

}

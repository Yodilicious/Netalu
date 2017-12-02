package com.netalu.netaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.BusinessFoodGroup;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        Thread timer = new Thread() {

            @Override
            public void run() {
               try {
                   sleep(1000);
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
                   finish();
               }catch (InterruptedException e) {
                   e.printStackTrace();
               }
            }
        };
        timer.start();
    }
}

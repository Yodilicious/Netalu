package com.netalu.netaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {

    private NetaluOpenHelper mDbOpenHelper;

    @Override
    protected void onDestroy() {

        mDbOpenHelper.close();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbOpenHelper = new NetaluOpenHelper(this);

        setContentView(R.layout.activity_main_menu);
    }
}

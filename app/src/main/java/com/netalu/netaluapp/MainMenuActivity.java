package com.netalu.netaluapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainMenuActivity extends AppCompatActivity {

    private NetaluOpenHelper mDbOpenHelper;

    @Override
    protected void onDestroy() {

        mDbOpenHelper.close();
        super.onDestroy();
    }

    private void displayMainMenu() {

        SQLiteDatabase db = mDbOpenHelper.getReadableDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbOpenHelper = new NetaluOpenHelper(this);

        displayMainMenu();

        setContentView(R.layout.activity_main_menu);
    }
}

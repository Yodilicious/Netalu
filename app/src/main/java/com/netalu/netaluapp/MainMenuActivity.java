package com.netalu.netaluapp;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private AppDatabase database;
    private FoodGroup foodGroup;

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void displayMainMenu() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = AppDatabase.getDatabase(getApplicationContext());

        displayMainMenu();

        setContentView(R.layout.activity_main_menu);
    }
}

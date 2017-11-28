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

        database.foodGroupDao().removeAllFoodGroups();

        List<FoodGroup> foodGroups = database.foodGroupDao().getAllFoodGroups();

        if(foodGroups.size() == 0) {

            database.foodGroupDao().addFoodGroup(new FoodGroup(1, "Beeferoni", "Cool as a beefee!"));
            foodGroup = database.foodGroupDao().getAllFoodGroups().get(0);
            Toast.makeText(this, String.valueOf(foodGroup.id), Toast.LENGTH_SHORT).show();
        }

        displayMainMenu();

        setContentView(R.layout.activity_main_menu);
    }
}

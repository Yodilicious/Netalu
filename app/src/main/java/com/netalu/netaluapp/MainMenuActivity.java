package com.netalu.netaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private AppDatabase database;
    private List<FoodGroup> foodGroups;

    LinearLayout linearLayout;

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void displayMainMenu() {

        linearLayout = (LinearLayout)findViewById(R.id.linearLayout2);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i = 0; i < foodGroups.size(); i++) {

            FoodGroup fg = foodGroups.get(i);

            Button button = new Button(this);
            button.setText(fg.name);

            linearLayout.addView(button, lp);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        database = AppDatabase.getDatabase(getApplicationContext());

        foodGroups = database.foodGroupDao().getAllFoodGroups();

        displayMainMenu();
    }
}

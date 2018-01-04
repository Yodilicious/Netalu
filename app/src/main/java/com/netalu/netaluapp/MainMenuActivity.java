package com.netalu.netaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    private AppDatabase database;
    private List<FoodGroup> foodGroups;
    private Button button;

    LinearLayout linearLayout;

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    private void displayMainMenu() {

        linearLayout = (LinearLayout)findViewById(R.id.linearLayout2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for(int i = 0; i < foodGroups.size(); i++) {

            FoodGroup foodGroup = foodGroups.get(i);

            Button button = new Button(this);
            button.setText(foodGroup.name);

            final String foodGroupName = foodGroup.name;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    launchShowBusinessActivity(foodGroupName);
                }
            });

            linearLayout.addView(button, layoutParams);
        }
    }

    private void launchShowBusinessActivity(final String name) {

        Intent intent = new Intent(this, MainListActivity.class);
        intent.putExtra("FoodGroupName", name);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        database = AppDatabase.getDatabase(getApplicationContext());

        foodGroups = database.foodGroupDao().getAllFoodGroups();

        displayMainMenu();

        Button contactButton = (Button) findViewById(R.id.contactsButton);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContactsActivity();
            }
        });

        button = (Button) findViewById(R.id.addNewBusiness);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewBusiness();
            }
        });
    }

    public void addNewBusiness(){
        Intent intent = new Intent(this, AddNewBusinessActivity.class);
        startActivity(intent);
    }

    public void openContactsActivity(){
        Intent intent = new Intent(this, ContactsActivity.class);
        startActivity(intent);
    }
}

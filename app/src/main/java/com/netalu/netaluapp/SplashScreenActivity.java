package com.netalu.netaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        database = AppDatabase.getDatabase(getApplicationContext());

        database.businessDao().deleteAllBusinesses();
        database.businessFoodGroupDao().deleteAllBusinessFoodGroups();
        database.foodGroupDao().deleteAllFoodGroups();
        database.reviewDao().deleteAllReviews();
        database.scheduleDao().deleteAllSchedules();
        database.scheduleHourDao().deleteAllScheduleHours();
        database.userDao().deleteAllUsers();

        List<FoodGroup> foodGroups = database.foodGroupDao().getAllFoodGroups();

        if(foodGroups.size() == 0) {
            database.foodGroupDao().addFoodGroup(new FoodGroup(1, "Beeferoni", "Cool as a beefee!"));
            database.foodGroupDao().addFoodGroup(new FoodGroup(2, "Porkerino", "Sexy as a Porky Piggy!"));
            database.foodGroupDao().addFoodGroup(new FoodGroup(3, "Poulterini", "Yummy as a Turkey!"));
            database.foodGroupDao().addFoodGroup(new FoodGroup(4, "Veniserani", "Scrumptious as a Moosey!"));
        }

        List<Business> businesses = database.businessDao().getAllBusinesses();

        if(businesses.size() == 0) {
            database.businessDao().addBusiness(new Business(1, "Old MacDonald's Farm", "We sell lots of Beef and Pork products", "34993 Church Road",
                    "P.O Box #12", "Norwich", "Ontario", "N0J 1P0", "345-333-2345", "www.OldMacDonald'sFarm.com"));
            database.businessDao().addBusiness(new Business(2, "Mother Goose Farms", "We sell lots of Poultry products. Come visit us!", "34678 Downey Street",
                    "P.O Box #114", "Burgessville", "Ontario", "N0J 1C0", "519-393-7777", "www.MotherGooseFarms.com"));
            database.businessDao().addBusiness(new Business(3, "Turkey Lurkey Farms", "Juicy and plump turkey for sale!", "67895 Feather Road",
                    "P.O Box #33", "Otterville", "Ontario", "N4S 1T3", "544-030-7893", "www.TurkeyLurkeyFarms.com"));
            database.businessDao().addBusiness(new Business(4, "Deer Creek Farms", "Lots of Deer meat to eat!", "57438 Bambi Street",
                    "P.O Box #344", "Norwich", "Ontario", "N0J 1P0", "432-333-5432", "www.DeerCreekFarms.com"));
        }

        Thread timer = new Thread() {

            @Override
            public void run() {
               try {
                   sleep(3000);
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

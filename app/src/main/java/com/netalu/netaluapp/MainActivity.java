package com.netalu.netaluapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.BusinessFoodGroup;
import com.netalu.netaluapp.database.FoodGroup;
import com.netalu.netaluapp.database.User;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainActivity extends AppCompatActivity {

    private AppDatabase database;
    private Button loginButton;
    private Button registerButton;

    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = false;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };

    public MainActivity() {
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        database = AppDatabase.getDatabase(getApplicationContext());

        database.businessFoodGroupDao().deleteAllBusinessFoodGroups();
        database.businessDao().deleteAllBusinesses();
        database.foodGroupDao().deleteAllFoodGroups();
        database.reviewDao().deleteAllReviews();
        database.scheduleDao().deleteAllSchedules();
        database.scheduleHourDao().deleteAllScheduleHours();
        database.userDao().deleteAllUsers();

        List<FoodGroup> foodGroups = database.foodGroupDao().getAllFoodGroups();

        if(foodGroups.size() == 0) {
            database.foodGroupDao().addFoodGroup(new FoodGroup(1, "Beef", "Cool as a beefee!"));
            database.foodGroupDao().addFoodGroup(new FoodGroup(2, "Pork", "Sexy as a Porky Piggy!"));
            database.foodGroupDao().addFoodGroup(new FoodGroup(3, "Poultery", "Yummy as a Turkey!"));
            database.foodGroupDao().addFoodGroup(new FoodGroup(4, "Venison", "Scrumptious as a Moosey!"));
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

        List<BusinessFoodGroup> bsfg = database.businessFoodGroupDao().getAllBusinessFoodGroups();

        if(bsfg.size() == 0) {

            database.businessFoodGroupDao().addBusinessFoodGroup(new BusinessFoodGroup(1, 1,1));
            database.businessFoodGroupDao().addBusinessFoodGroup(new BusinessFoodGroup(2, 2,2));
            database.businessFoodGroupDao().addBusinessFoodGroup(new BusinessFoodGroup(3, 3,3));
            database.businessFoodGroupDao().addBusinessFoodGroup(new BusinessFoodGroup(3, 3,4));
            database.businessFoodGroupDao().addBusinessFoodGroup(new BusinessFoodGroup(4, 4,3));
            database.businessFoodGroupDao().addBusinessFoodGroup(new BusinessFoodGroup(4, 4,4));
        }

        List<User> users = database.userDao().getAllUsers();

        if(users.size() == 0) {

            database.userDao().addUser(new User(1,"Jodi","Visser","Ontario", "N0J 1C0", "visser.jodi@gmail.com", "access"));
        }

        loginButton = (Button) findViewById(R.id.loginButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginActivity();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterActivity();
            }
        });
    }

    private void launchLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void launchRegisterActivity() {

        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}

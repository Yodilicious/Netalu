package com.netalu.netaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.netalu.netaluapp.database.AppDatabase;

public class AddNewBusinessActivity extends AppCompatActivity {

    private AppDatabase database;
    private Button addBusinessButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_business);
    }
}

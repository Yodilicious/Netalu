package com.netalu.netaluapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.FoodGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CategorySelectActivity extends ListActivity {

    private AppDatabase database;
    private List<String> listValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);

        setListAdapter(null);

        database = AppDatabase.getDatabase(getApplicationContext());

        List<FoodGroup> foodGroups = database.foodGroupDao().getAllFoodGroups();

        listValues = new ArrayList<String>();

        for(Iterator<FoodGroup> i = foodGroups.iterator(); i.hasNext();) {

            FoodGroup foodGroup = i.next();

            listValues.add(foodGroup.name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.select_category_layout, R.id.titleTextView, listValues);
        setListAdapter(adapter);
    }
}

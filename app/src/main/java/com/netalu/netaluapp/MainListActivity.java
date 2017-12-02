package com.netalu.netaluapp;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainListActivity extends ListActivity {

    private TextView text;
    private List<String> listValues;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);

        setListAdapter(null);

        String foodGroupName = getIntent().getStringExtra("FoodGroupName");

        database = AppDatabase.getDatabase(getApplicationContext());

        List<Business> businesses = database.businessDao().getBusinessForFoodGroupName(foodGroupName);

        TextView frowny = (TextView) findViewById(R.id.frownyTextView);
        TextView emptyListMessage = (TextView) findViewById(R.id.emptyListMessageTextView);
        TextView title = (TextView) findViewById(R.id.titleTextView);
        title.setText(foodGroupName);

        if(businesses.size() == 0) {

            frowny.setVisibility(View.VISIBLE);
            emptyListMessage.setVisibility(View.VISIBLE);
        } else {

            frowny.setVisibility(View.GONE);
            emptyListMessage.setVisibility(View.GONE);
        }

        listValues = new ArrayList<String>();

        for(Iterator<Business> i = businesses.iterator(); i.hasNext();) {

            Business business = i.next();

            listValues.add(business.name);
        }

        CustomListAdapter myAdapter = new CustomListAdapter(this, businesses.toArray(new Business[0]));

        setListAdapter(myAdapter);
    }
}
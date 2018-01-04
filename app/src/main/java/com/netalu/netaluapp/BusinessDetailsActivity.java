package com.netalu.netaluapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.FoodGroup;

import org.w3c.dom.Text;

public class BusinessDetailsActivity extends AppCompatActivity {

    private AppDatabase database;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        Button mainListButton = (Button) findViewById(R.id.mainListButton);

        mainListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBusinessListActivity();
            }
        });

        int businessId = getIntent().getIntExtra("BusinessId", 0);

        database = AppDatabase.getDatabase(getApplicationContext());

        Business business = database.businessDao().getBusiness(businessId).get(0);
        FoodGroup category = database.foodGroupDao().getFoodGroup(business.food_group_id).get(0);

        categoryName = category.name;

        TextView businessNameDetail = (TextView) findViewById(R.id.businessNameDetailTextView);
        TextView descriptionDetail = (TextView) findViewById(R.id.descriptionDetailTextView);
        TextView categoryDetail = (TextView) findViewById(R.id.categoryDetailTextView);
        TextView businessAddress1Detail = (TextView) findViewById(R.id.businessAddress1DetailTextView);
        TextView businessAddress2Detail = (TextView) findViewById(R.id.businessAddress2DetailTextView);
        TextView cityDetail = (TextView) findViewById(R.id.cityDetailTextView);
        TextView provinceDetail = (TextView) findViewById(R.id.provinceDetailTextView);
        TextView postalCodeDetail = (TextView) findViewById(R.id.postalCodeDetailTextView);
        TextView phoneDetail = (TextView) findViewById(R.id.phoneDetailTextView);
        TextView emailDetail = (TextView) findViewById(R.id.emailDetailTextView);
        TextView websiteDetail = (TextView) findViewById(R.id.websiteDetailTextView);
        TextView longitudeDetail = (TextView) findViewById(R.id.longitudeDetailTextView);
        TextView latitudeDetail = (TextView) findViewById(R.id.latitudeDetailTextView);

        businessNameDetail.setText(business.name);
        descriptionDetail.setText(business.description);
        categoryDetail.setText(categoryName);
        businessAddress1Detail.setText(business.address1);
        businessAddress2Detail.setText(business.address2);
        cityDetail.setText(business.city);
        provinceDetail.setText(business.province);
        postalCodeDetail.setText(business.postal_code);
        phoneDetail.setText(business.phone_number);
        emailDetail.setText(business.email);
        websiteDetail.setText(business.website);
        longitudeDetail.setText(business.longitude);
        latitudeDetail.setText(business.latitude);
    }

    private void launchBusinessListActivity() {

        Intent intent = new Intent(this, MainListActivity.class);
        intent.putExtra("FoodGroupName", categoryName);
        startActivity(intent);
    }
}

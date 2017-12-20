package com.netalu.netaluapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;

import org.w3c.dom.Text;

public class BusinessDetailsActivity extends AppCompatActivity {

    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        int businessId = getIntent().getIntExtra("BusinessId", 0);

        database = AppDatabase.getDatabase(getApplicationContext());

        Business business = database.businessDao().getBusiness(businessId).get(0);


        TextView businessNameDetail = (TextView) findViewById(R.id.businessNameDetailTextView);
        TextView descriptionDetail = (TextView) findViewById(R.id.descriptionDetailTextView);
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
}

package com.netalu.netaluapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.Business;
import com.netalu.netaluapp.database.User;

import java.util.List;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class AddNewBusinessActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppDatabase database;
    private List<User> users;
    private Button addBusinessButton;
    private Button downloadImageButton;
    private Spinner spinner;
    private ImageView imageView;
    private static final int CAMERA_REQUEST = 1888;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private String longitude;
    private String latitude;

    private static final String[] paths =
            {
                    "Alberta",
                    "British Columbia",
                    "Manitoba",
                    "New Brunswick",
                    "Newfoundland and Labrador",
                    "Nova Scotia",
                    "Ontario",
                    "Prince Edward Island",
                    "Quebec",
                    "Saskatchewan",
                    "Northwest Territories",
                    "Nunavut",
                    "Yukon"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_business);

        database = AppDatabase.getDatabase(getApplicationContext());

        addBusinessButton = (Button) findViewById(R.id.addNewBusiness);
        downloadImageButton = (Button) findViewById(R.id.downloadImageButton);

        spinner = (Spinner) findViewById(R.id.provinceSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AddNewBusinessActivity.this,
                android.R.layout.simple_spinner_item, paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        int selectSpinnerPosition = adapter.getPosition("Ontario");
        spinner.setSelection(selectSpinnerPosition);

        addBusinessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchBusinessDetailsActivity();
            }
        });

        downloadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchFileDownloaderActivity();
            }
        });

        imageView = (ImageView) this.findViewById(R.id.cameraImageView);
        Button photoButton = (Button) this.findViewById(R.id.photoButton);
        photoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });

        getLastLocation();
    }

    public void onLocationChanged(Location location) {
        // New location has now been determined
        longitude = Double.toString(location.getLongitude());
        latitude = Double.toString(location.getLatitude());
        //String msg = "Updated Location: " +
        //        Double.toString(location.getLatitude()) + "," +
        //        Double.toString(location.getLongitude());
        //Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        // You can now create a LatLng Object for use with maps
        //LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
    }

    public void getLastLocation() {
        // Get last known recent location using new Google Play Services SDK (v11+)
        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // GPS location can be null if GPS is switched off
                        if (location != null) {
                            onLocationChanged(location);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("MapDemoActivity", "Error trying to get last GPS location");
                        e.printStackTrace();
                    }
                });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    private void launchFileDownloaderActivity() {

        Intent intent = new Intent(this, FileDownloaderActivity.class);
        startActivity(intent);
    }

    private void launchBusinessDetailsActivity() {

        boolean isValid = true;

        EditText businessName = (EditText) findViewById(R.id.businessNameEditText);
        EditText description = (EditText) findViewById(R.id.descriptionEditText);
        EditText city = (EditText) findViewById(R.id.cityEditText);
        EditText businessAddress1 = (EditText) findViewById(R.id.businessAddress1EditText);
        EditText businessAddress2 = (EditText) findViewById(R.id.businessAddress2EditText);
        Spinner province = (Spinner) findViewById(R.id.provinceSpinner);
        EditText postalCode = (EditText) findViewById(R.id.postalCodeEditText);
        EditText phone = (EditText) findViewById(R.id.phoneEditText);
        EditText email = (EditText) findViewById(R.id.emailEditText);
        EditText website = (EditText) findViewById(R.id.websiteEditText);

        if (businessName.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Business Name cannot be empty.", Toast.LENGTH_LONG).show();
            businessName.setError("Business Name cannot be empty.");

            isValid = false;
        }

        if (description.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Description cannot be empty.", Toast.LENGTH_LONG).show();
            description.setError("Description cannot be empty.");

            isValid = false;
        }

        if (city.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "City cannot be empty.", Toast.LENGTH_LONG).show();
            description.setError("City cannot be empty.");

            isValid = false;
        }

        if (businessAddress1.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Business Address cannot be empty.", Toast.LENGTH_LONG).show();
            description.setError("Business Address cannot be empty.");

            isValid = false;
        }

        if (postalCode.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Postal Code cannot be empty.", Toast.LENGTH_LONG).show();
            postalCode.setError("Postal Code cannot be empty.");

            isValid = false;
        }

        if (phone.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Phone cannot be empty.", Toast.LENGTH_LONG).show();
            phone.setError("Phone cannot be empty.");

            isValid = false;
        }

        if (email.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Email cannot be empty.", Toast.LENGTH_LONG).show();
            phone.setError("Email cannot be empty.");

            isValid = false;
        }

        if (website.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Website cannot be empty.", Toast.LENGTH_LONG).show();
            phone.setError("Website cannot be empty.");

            isValid = false;
        }

        if (isValid) {

            users = database.userDao().getUserByEmail(email.getText().toString());

            if(users.size() == 0) {

                String businessNameStr = businessName.getText().toString();
                String descriptionStr = description.getText().toString();
                String cityStr = city.getText().toString();
                String businessAddress1Str = businessAddress1.getText().toString();
                String businessAddress2Str = businessAddress2.getText().toString();
                String provinceStr = province.getSelectedItem().toString();
                String postalCodeStr = postalCode.getText().toString();
                String phoneStr = phone.getText().toString();
                String emailStr = email.getText().toString();
                String websiteStr = website.getText().toString();

                Business business = new Business(0, businessNameStr, descriptionStr, cityStr,
                        businessAddress1Str, businessAddress2Str, provinceStr, postalCodeStr, phoneStr, emailStr, websiteStr, longitude, latitude);

                database.businessDao().addBusiness(business);
                int id = database.businessDao().getLastBusinessId();

                Intent intent = new Intent(this, BusinessDetailsActivity.class);
                intent.putExtra("BusinessId", id);
                startActivity(intent);

            } else {
                Toast.makeText(getApplicationContext(), "This email already exists.", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                // Whatever you want to happen when the first item gets selected
                break;
            case 1:
                // Whatever you want to happen when the second item gets selected
                break;
            case 2:
                // Whatever you want to happen when the third item gets selected
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

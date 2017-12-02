package com.netalu.netaluapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.User;

import java.util.List;

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private AppDatabase database;
    private List<User> users;
    private Button registerButton;
    private Spinner spinner;
    private static final String[]paths =
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
        setContentView(R.layout.activity_registration);

        database = AppDatabase.getDatabase(getApplicationContext());

        registerButton = (Button) findViewById(R.id.registrationSubmitButton);

        spinner = (Spinner)findViewById(R.id.provinceSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegistrationActivity.this,
                android.R.layout.simple_spinner_item,paths);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        int selectSpinnerPosition= adapter.getPosition("Ontario");
        spinner.setSelection(selectSpinnerPosition);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchRegisterActivity();
            }
        });
    }

    private void launchRegisterActivity() {

        boolean isValid = true;

        EditText firstName = (EditText) findViewById(R.id.firstNameEditText);
        EditText lastName = (EditText) findViewById(R.id.lastNameEditText);
        Spinner province = (Spinner) findViewById(R.id.provinceSpinner);
        EditText postalCode = (EditText) findViewById(R.id.postalCodeEditText);
        EditText email = (EditText) findViewById(R.id.emailEditText);
        EditText password = (EditText) findViewById(R.id.passwordEditText);

        if(firstName.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "First Name cannot be empty.", Toast.LENGTH_LONG).show();
            firstName.setError("First Name cannot be empty.");

            isValid = false;
        }

        if(lastName.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Last Name cannot be empty.", Toast.LENGTH_LONG).show();
            lastName.setError("Last Name cannot be empty.");

            isValid = false;
        }

        if(postalCode.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Postal Code cannot be empty.", Toast.LENGTH_LONG).show();
            postalCode.setError("Postal Code cannot be empty.");

            isValid = false;
        }

        if(email.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Email cannot be empty.", Toast.LENGTH_LONG).show();
            email.setError("Email cannot be empty.");

            isValid = false;
        }

        if(password.getText().toString().equals("")) {

            Toast.makeText(getApplicationContext(), "Password cannot be empty", Toast.LENGTH_LONG).show();
            password.setError("Password cannot be empty");

            isValid = false;
        }

        if(isValid) {

            users  = database.userDao().getUserByEmail(email.getText().toString());

            if(users.size() == 0) {

                String firstNameStr = firstName.getText().toString();
                String lastNameStr = lastName.getText().toString();
                String provinceStr = province.getSelectedItem().toString();
                String postalCodeStr = postalCode.getText().toString();
                String emailStr = email.getText().toString();
                String passwordStr = password.getText().toString();

                database.userDao().addUser(new User(0, firstNameStr, lastNameStr, provinceStr, postalCodeStr, emailStr, passwordStr));

                Intent intent = new Intent(this, LoginActivity.class);
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

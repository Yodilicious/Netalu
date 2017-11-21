package com.netalu.netaluapp;

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

public class RegistrationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        EditText password1 = (EditText) findViewById(R.id.passwordTextEdit);
        EditText password2 = (EditText) findViewById(R.id.password2TextEdit);

        if(password1.getText().toString().equals(password2.getText().toString()) && !password1.getText().toString().equals("") && !password2.getText().toString().equals("")) {

            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
        } else {

            Toast.makeText(getApplicationContext(), "Password does not Match", Toast.LENGTH_LONG).show();
            password1.setError("Password does not Match");
            password2.setError("Password does not Match");
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

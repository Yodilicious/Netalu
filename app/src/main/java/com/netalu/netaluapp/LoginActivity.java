package com.netalu.netaluapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private AppDatabase database;
    private List<User> users;

    private Button loginButton;
    private Button cancelButton;

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        database = AppDatabase.getDatabase(getApplicationContext());

        loginButton = (Button) findViewById(R.id.loginButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginActivity();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMainActivity();
            }
        });
    }

    private void launchLoginActivity() {

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        users = database.userDao().getUserByEmail(emailEditText.getText().toString());

        if(users.size() == 1) {

            User user = users.get(0);

            if (emailEditText.getText().toString().equals("")) {

                CreateErrorDialog("Please enter your email address.");
            } else if (!emailEditText.getText().toString().equals(user.email)) {

                CreateErrorDialog("The email does not match.");
            } else if (passwordEditText.getText().toString().equals("")) {

                CreateErrorDialog("Please enter your password.");
            } else if (!passwordEditText.getText().toString().equals(user.password)) {

                CreateErrorDialog("Password does not match.");
            } else {

                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
            }
        } else {

            CreateErrorDialog("User not found. Please check your email address.");
        }
    }

    private void CreateErrorDialog(String text) {
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setCancelable(false); // This blocks the 'BACK' button
        ad.setMessage(text);
        ad.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        ad.show();
    }

    private void launchMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

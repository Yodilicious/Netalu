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

    private EditText usernameTextEdit;
    private EditText passwordTextEdit;

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

        usernameTextEdit = (EditText) findViewById(R.id.usernameEditText);
        passwordTextEdit = (EditText) findViewById(R.id.passwordTextEdit);

        users = database.userDao().getUserByUsername(usernameTextEdit.getText().toString());

        if(users.size() == 1) {

            User user = users.get(0);

            if (usernameTextEdit.getText().toString().equals("")) {

                CreateErrorDialog("Please enter your username.");
            } else if (!usernameTextEdit.getText().toString().equals(user.username)) {

                CreateErrorDialog("The username does not match.");
            } else if (passwordTextEdit.getText().toString().equals("")) {

                CreateErrorDialog("Please enter your password.");
            } else if (!passwordTextEdit.getText().toString().equals(user.password)) {

                CreateErrorDialog("Password does not match.");
            } else {

                Intent intent = new Intent(this, MainMenuActivity.class);
                startActivity(intent);
            }
        } else {

            CreateErrorDialog("User not found. Please check your username.");
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

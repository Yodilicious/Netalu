package com.netalu.netaluapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;
    private Button cancelButton;

    private EditText usernameTextEdit;
    private EditText passwordTextEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        if(usernameTextEdit.getText().toString().equals("")) {

            CreateErrorDialog("Please enter your username.");
        } else if(passwordTextEdit.getText().toString().equals("")) {

            CreateErrorDialog("Please enter your password.");
        } else {

            Intent intent = new Intent(this, MainMenuActivity.class);
            startActivity(intent);
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

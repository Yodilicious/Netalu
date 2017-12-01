package com.netalu.netaluapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.netalu.netaluapp.database.AppDatabase;
import com.netalu.netaluapp.database.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "9f6b49a9-84ad-4e43-ace4-b3883280cb97";

    private AppDatabase database;
    private List<User> users;

    private Button loginButton;
    private Button cancelButton;

    private EditText emailEditText;
    private EditText passwordEditText;
    private Switch rememberCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        rememberCredentials = (Switch) findViewById(R.id.rememberPasswordSwitch);

        database = AppDatabase.getDatabase(getApplicationContext());

        loginButton = (Button) findViewById(R.id.loginButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        boolean saveCredentials = settings.getBoolean("rememberCredentials", false);
        String email = settings.getString("email", "");
        String password = settings.getString("password", "");

        rememberCredentials.setChecked(saveCredentials);
        emailEditText.setText(email);
        passwordEditText.setText(password);

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
        rememberCredentials = (Switch) findViewById(R.id.rememberPasswordSwitch);

        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        users = database.userDao().getUserByEmail(email);

        if(users.size() == 1) {

            User user = users.get(0);

            if (email.equals("")) {

                CreateErrorDialog("Please enter your email address.");
            } else if (!email.equals(user.email)) {

                CreateErrorDialog("The email does not match.");
            } else if (password.equals("")) {

                CreateErrorDialog("Please enter your password.");
            } else if (!password.equals(user.password)) {

                CreateErrorDialog("Password does not match.");
            } else {

                if(rememberCredentials.isChecked()) {

                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("rememberCredentials", true);
                    editor.putString("email", email);
                    editor.putString("password", password);
                    editor.commit();
                } else {

                    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("rememberCredentials", false);
                    editor.putString("email", "");
                    editor.putString("password", "");
                    editor.commit();
                }

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

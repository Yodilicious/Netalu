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
import android.widget.Toast;

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

                Toast.makeText(getApplicationContext(), "Please enter your email address.", Toast.LENGTH_LONG).show();
            } else if (!email.equals(user.email)) {

                Toast.makeText(getApplicationContext(), "The email does not match.", Toast.LENGTH_LONG).show();
            } else if (password.equals("")) {

                Toast.makeText(getApplicationContext(), "Please enter your password.", Toast.LENGTH_LONG).show();
            } else if (!password.equals(user.password)) {

                Toast.makeText(getApplicationContext(), "Password does not match.", Toast.LENGTH_LONG).show();
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

            Toast.makeText(getApplicationContext(), "User is not found. Please check your email address.", Toast.LENGTH_LONG).show();
        }
    }

    private void launchMainActivity() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

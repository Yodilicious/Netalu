package com.netalu.netaluapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.iid.FirebaseInstanceId;
import com.netalu.netaluapp.services.NetaluFirebaseInstanceIDService;

public class ContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#000000"));
        setSupportActionBar(toolbar);

        Button deviceTokenButton = (Button) findViewById(R.id.deviceTokenButton);

        deviceTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String token = FirebaseInstanceId.getInstance().getToken();

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("token", token);
                clipboard.setPrimaryClip(clip);

                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), token, Snackbar.LENGTH_INDEFINITE);
                snackbar.show();
            }
        });

        ImageView phoneImage = (ImageView) findViewById(R.id.phoneImage);
        ImageView emailImage = (ImageView) findViewById(R.id.emailImage);
        ImageView websiteImage = (ImageView) findViewById(R.id.websiteImage);

        phoneImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:2262325520"));
                startActivity(intent);
            }
        });

        emailImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:visser.jodi@gmail.com"));
                startActivity(intent);
            }
        });

        websiteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:visser.jodi@gmail.com"));
                startActivity(intent);
            }
        });

        FloatingActionButton phone = (FloatingActionButton) findViewById(R.id.phone);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:2262325520"));
                startActivity(intent);
            }
        });

        FloatingActionButton website = (FloatingActionButton) findViewById(R.id.website);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.google.com"));
                startActivity(intent);
            }
        });
    }
}

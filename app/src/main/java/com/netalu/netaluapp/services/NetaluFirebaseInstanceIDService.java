package com.netalu.netaluapp.services;

import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class NetaluFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "NetaluFirebaseIDService";

    @Override
    public void onTokenRefresh() {

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}

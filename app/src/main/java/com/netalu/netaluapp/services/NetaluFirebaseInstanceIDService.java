package com.netalu.netaluapp.services;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class NetaluFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "NetaluFirebaseIDService";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
    }
}

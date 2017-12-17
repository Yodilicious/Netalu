package com.netalu.netaluapp;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class NetaluLocationListener implements LocationListener {

    private String longitude;
    public String getLongitude() {
        return longitude;
    }

    private String latitude;
    public String getLatitude() {
        return latitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        longitude = Double.toString(location.getLongitude());
        latitude = Double.toString(location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

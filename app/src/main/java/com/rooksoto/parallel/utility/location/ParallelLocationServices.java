package com.rooksoto.parallel.utility.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

/**
 * Created by rook on 2/27/17.
 */

public class ParallelLocationServices {
    private Context context;
    private GoogleApiClient googleApiClientInstance;
    private static final String TAG = "ParallelLocationService";

    public ParallelLocationServices(Context context) {
        this.context = context;

        getInstance();
        googleApiClientInstance.connect();

    }

    public void reconnect() {
        googleApiClientInstance.reconnect();
    }

    public void disconnect() {
        googleApiClientInstance.disconnect();
    }

    public GoogleApiClient getInstance() {

        if (googleApiClientInstance != null) {
            return googleApiClientInstance;
        } else {
            googleApiClientInstance = new GoogleApiClient.Builder(context)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {
                            Log.d(TAG, "onConnected: Connected to Google Play Services");
                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            Log.d(TAG, "onConnectionSuspended: Suspended Connection to Google Play Services");
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Log.d(TAG, "onConnectionFailed: Failed to Connect to Google Play Services" + connectionResult.getErrorMessage());
                        }
                    })
                    .build();
        }
        return googleApiClientInstance;
    }

    public void startLocationMonitoring() {

        LocationRequest locationRequest = LocationRequest.create()
                .setInterval(10000)
                .setFastestInterval(5000)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClientInstance, locationRequest, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.d(TAG, "onLocationChanged: User Moved - Lat: " + location.getLatitude() + ", Long: " + location.getLongitude());
                    // No code necessary here
                    // Logic will be handled by Geofencing class(es)
                }
            });
        } else {
            // TODO: 2/28/17 - Code a handle for user not giving permission for "Fine Location" (ask again?)
        }


    }

    public void stopLocationMonitoring() {
        // TODO: 2/28/17 - Code a handle to stop location monitoring (might be unnecessary)
    }
}

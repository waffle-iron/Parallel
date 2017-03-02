package com.rooksoto.parallel.geolocation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

/**
 * Created by rook on 3/2/17.
 */

public class ParallelLocationService {

    private static final String TAG = "ParallelLocationService";


    private static GoogleApiClient googleApiClient = null;
    private static ParallelLocationService instance;
    private static long latitude;
    private static long longitude;

    public static ParallelLocationService getInstance() {
        if(instance == null) {
            instance = new ParallelLocationService();
        }
        return instance;
    }

    private ParallelLocationService() {

        googleApiClient = new GoogleApiClient.Builder(googleApiClient.getContext()) // FIXME: 3/2/17 - googleApiClient.getContext() may return null;
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.d(TAG, "Successfully Connected to GPS-L");

                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.d(TAG, "Connection to GPS-L Suspended");

                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d(TAG, "Failed to Connect to GPS-L");
                    }
                })
                .build();
    }

    public static long getLatitude() {
        return latitude;
    }

    public static long getLongitude() {
        return longitude;
    }
}

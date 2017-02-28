package com.rooksoto.parallel.activities;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;
import com.rooksoto.parallel.utility.location.ParallelLocationServices;

public class ActivityHub extends AppCompatActivity {
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private ParallelLocationServices locationServices;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        initialize();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onStart() {
        super.onStart();
        // Request permissions at runtime if Android version >= Marshmallow
        requestLocationPermissions();
        // Check if Google Play Services is installed
        checkForGooglePlayServices();
        // Intstantiates ParallelLocationServicesClass
        initLocationServices();
        //After ParallelLocatinServices is instantiated, connect
        locationServices.reconnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void checkForGooglePlayServices() {
        // The following code checks to see if Google Play Services exists
        // If it does not, a prompt asking the user to install Play Services will appear
        int response = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (response != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, response, 1).show();
        }
    }

    private void initLocationServices() {
        locationServices = new ParallelLocationServices(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestLocationPermissions() {
        requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION},
                9999);
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        // Disconnects from Location Services if app is stopped
        locationServices.disconnect();
    }

    @Override
    public void onBackPressed () {
        mCustomAlertDialog.exit(this);
        //super.onBackPressed();
    }
}
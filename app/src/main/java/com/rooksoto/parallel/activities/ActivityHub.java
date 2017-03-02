package com.rooksoto.parallel.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

public class ActivityHub extends AppCompatActivity {
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    private static final String TAG = "ActivityHub";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        initialize();
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkForGoogleApiAvail();
    }

    private void checkForGoogleApiAvail() {
        int hasGpsInstalled = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (hasGpsInstalled != ConnectionResult.SUCCESS) {
            Log.d(TAG, "Google API Not Available on This Device");
            // TODO: 3/2/17 - Show "Get Google Play Services" dialog
        } else {
            Log.d(TAG, "Google API is Available");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT >= 23) {
            getLocationPermissions();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocationPermissions() {
        requestPermissions(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION},
        9999);

    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    @Override
    public void onBackPressed () {
        mCustomAlertDialog.exit(this);
        //super.onBackPressed();
    }
}
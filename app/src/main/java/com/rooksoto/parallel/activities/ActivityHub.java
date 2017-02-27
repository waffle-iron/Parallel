package com.rooksoto.parallel.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

public class ActivityHub extends AppCompatActivity {
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        initialize();
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
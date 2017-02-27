package com.rooksoto.parallel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.FragmentStartCreateAccount;
import com.rooksoto.parallel.fragments.FragmentStartLogin;
import com.rooksoto.parallel.fragments.FragmentStartSplash;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

public class ActivityStart extends AppCompatActivity {
    private int containerID = R.id.activity_start_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();

        loadFragmentSplash();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentSplash () {
        FragmentStartSplash mFragmentStartSplash = new FragmentStartSplash();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartSplash)
                .commit();
    }

    private void loadFragmentLogin () {
        FragmentStartLogin mFragmentStartLogin = new FragmentStartLogin();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartLogin)
                .commit();
    }

    private void loadFragmentCreateAccount () {
        FragmentStartCreateAccount mFragmentStartCreateAccount = new FragmentStartCreateAccount();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartCreateAccount)
                .commit();
    }

    public void onClickToLogin (View view) {
        mCustomSoundEffects.setDefaultClick();
        loadFragmentLogin();
    }

    public void onClicktoCreateAccount (View view) {
        mCustomSoundEffects.setDefaultClick();
        loadFragmentCreateAccount();
    }

    public void onClicktoActivityMain (View view) {
        mCustomSoundEffects.setDefaultClick();
        Intent fromActivityStartToActivityMain = new Intent(this, ActivityMain.class);
        startActivity(fromActivityStartToActivityMain);
        finish();
    }

    @Override
    public void onBackPressed () {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(containerID);
        if (currentFragment instanceof FragmentStartLogin) {
            mCustomAlertDialog.exit(this);
        } else if (currentFragment instanceof FragmentStartCreateAccount) {
            loadFragmentLogin();
        } else {
            super.onBackPressed();
        }
    }
}

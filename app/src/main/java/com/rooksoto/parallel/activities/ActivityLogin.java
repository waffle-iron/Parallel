package com.rooksoto.parallel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.activityLogin.FragmentLoginCreateAccount;
import com.rooksoto.parallel.fragments.activityLogin.FragmentLoginLogin;
import com.rooksoto.parallel.fragments.activityLogin.FragmentLoginSplash;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

public class ActivityLogin extends AppCompatActivity {
    private int containerID = R.id.activity_login_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        loadFragmentSplash();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentSplash () {
        FragmentLoginSplash mFragmentLoginSplash = new FragmentLoginSplash();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentLoginSplash)
                .commit();
    }

    private void loadFragmentLogin () {
        FragmentLoginLogin mFragmentLoginLogin = new FragmentLoginLogin();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentLoginLogin)
                .commit();
    }

    private void loadFragmentCreateAccount () {
        FragmentLoginCreateAccount mFragmentLoginCreateAccount = new FragmentLoginCreateAccount();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentLoginCreateAccount)
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
        Intent fromActivityStartToActivityMain = new Intent(this, ActivityStart.class);
        startActivity(fromActivityStartToActivityMain);
        finish();
    }

    public void onClicktoActivityHub (View view) {
        mCustomSoundEffects.setDefaultClick();
        Intent fromActivityStartToActivityHub = new Intent(this, ActivityHub.class);
        startActivity(fromActivityStartToActivityHub);
        finish();
    }

    @Override
    public void onBackPressed () {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(containerID);
        if (currentFragment instanceof FragmentLoginLogin) {
            mCustomAlertDialog.exit(this);
        } else if (currentFragment instanceof FragmentLoginCreateAccount) {
            loadFragmentLogin();
        } else {
            super.onBackPressed();
        }
    }
}

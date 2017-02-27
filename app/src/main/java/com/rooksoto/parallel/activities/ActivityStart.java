package com.rooksoto.parallel.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.FragmentCreateAccount;
import com.rooksoto.parallel.fragments.FragmentLogin;
import com.rooksoto.parallel.fragments.FragmentSplash;

public class ActivityStart extends AppCompatActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        loadFragmentSplash();
    }

    private void loadFragmentSplash () {
        FragmentSplash mFragmentSplash = new FragmentSplash();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_start_fragment_container, mFragmentSplash)
                .commit();
    }

    private void loadFragmentLogin () {
        FragmentLogin mFragmentLogin = new FragmentLogin();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_start_fragment_container, mFragmentLogin)
                .commit();
    }

    private void loadFragmentCreateAccount () {
        FragmentCreateAccount mFragmentCreateAccount = new FragmentCreateAccount();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_start_fragment_container, mFragmentCreateAccount)
                .commit();
    }

    public void onClickToLogin (View view) {
        loadFragmentLogin();
    }

    public void onClicktoCreateAccount (View view) {
        loadFragmentCreateAccount();
    }

    public void onClicktoActivityMain (View view) {
        Intent fromActivityStartToActivityMain = new Intent(this, ActivityMain.class);
        startActivity(fromActivityStartToActivityMain);
    }
}

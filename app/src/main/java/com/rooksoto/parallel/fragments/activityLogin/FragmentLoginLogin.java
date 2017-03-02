package com.rooksoto.parallel.fragments.activityLogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;

import com.labo.kaji.fragmentanimations.PushPullAnimation;
import com.rooksoto.parallel.R;

public class FragmentLoginLogin extends Fragment {
    private View mView;
    private Button buttonLogin;
    private String username;
    private String password;

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return PushPullAnimation.create(PushPullAnimation.RIGHT, enter, 250);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_login, container, false);
        initialize();
        return mView;
    }

    private void initialize () {
        EditText editTextUsername = (EditText) mView.findViewById(R.id.fragment_login_login_edittext_username);
        EditText editTextPassword = (EditText) mView.findViewById(R.id.fragment_login_login_edittext_password);

        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
    }
}

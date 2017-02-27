package com.rooksoto.parallel.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.rooksoto.parallel.R;

public class CustomToast {
    private static Toast toastInflater;

    public static void show (View viewParam, String messageParam) {
        if (toastInflater != null) {
            toastInflater.cancel();
        }
        toastInflater = new Toast(viewParam.getContext());
        toastInflater.setDuration(Toast.LENGTH_SHORT);
        LayoutInflater inflater = (LayoutInflater) viewParam.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.toast_textview);
        textView.setText(messageParam);
        toastInflater.setView(view);
        toastInflater.show();
    }
}
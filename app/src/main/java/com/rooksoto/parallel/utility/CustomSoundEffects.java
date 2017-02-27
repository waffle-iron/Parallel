package com.rooksoto.parallel.utility;

import android.view.SoundEffectConstants;
import android.view.View;

public class CustomSoundEffects {
    private View mView;

    public CustomSoundEffects (View viewParam) {
        this.mView = viewParam;
    }

    public void setDefaultClick () {
        mView.playSoundEffect(SoundEffectConstants.CLICK);
    }
}


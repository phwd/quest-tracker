package com.oculus.panelapp.keyboardv2.assistant;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;

public class TranscriptionVolumeIndicator extends ProgressBar {
    public TranscriptionVolumeIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setProgress(0);
    }

    public void setVolumne(float f) {
        setProgress((int) (((float) getMax()) * f));
    }
}

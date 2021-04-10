package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.common.ocui.R;

public class OCSlider extends SeekBar {
    private static final String TAG = LoggingUtil.tag(OCSlider.class);
    private OCEventHandler mEventHandler;
    private SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;

    public OCSlider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ocButtonStyle);
        Resources resources = context.getResources();
        setProgressDrawable(resources.getDrawable(R.drawable.ocslider_progress, context.getTheme()));
        setThumb(resources.getDrawable(R.drawable.ocslider_thumb, context.getTheme()));
        setBackground(null);
        setSplitTrack(false);
        super.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            /* class com.oculus.ocui.OCSlider.AnonymousClass1 */

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (OCSlider.this.mOnSeekBarChangeListener != null) {
                    OCSlider.this.mOnSeekBarChangeListener.onProgressChanged(seekBar, i, z);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (OCSlider.this.mOnSeekBarChangeListener != null) {
                    OCSlider.this.mOnSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                if (OCSlider.this.mEventHandler != null) {
                    OCSlider.this.mEventHandler.onButtonClick();
                } else {
                    Log.e(OCSlider.TAG, "Event handler not set, this slider has no haptics");
                }
                if (OCSlider.this.mOnSeekBarChangeListener != null) {
                    OCSlider.this.mOnSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        });
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }
}

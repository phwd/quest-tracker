package com.oculus.ocui;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.socialplatform.R;

public class OCSlider extends SeekBar {
    public static final String TAG = LoggingUtil.tag(OCSlider.class);
    public OCEventHandler mEventHandler;
    public SeekBar.OnSeekBarChangeListener mOnSeekBarChangeListener;

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
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = OCSlider.this.mOnSeekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onProgressChanged(seekBar, i, z);
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = OCSlider.this.mOnSeekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                OCEventHandler oCEventHandler = OCSlider.this.mEventHandler;
                if (oCEventHandler != null) {
                    oCEventHandler.onButtonClick();
                } else {
                    Log.e(OCSlider.TAG, "Event handler not set, this slider has no haptics");
                }
                SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = OCSlider.this.mOnSeekBarChangeListener;
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        });
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        setAlpha(OCConstants.getOpacity(getResources(), z));
    }

    public void setEventHandler(OCEventHandler oCEventHandler) {
        this.mEventHandler = oCEventHandler;
    }

    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.mOnSeekBarChangeListener = onSeekBarChangeListener;
    }
}

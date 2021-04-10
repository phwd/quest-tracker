package com.oculus.panelapp.anytimeui.v2.tablet.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.panelapp.anytimeui.R;

public final class LoadingDots extends LinearLayout {
    private static final float ALPHA_HALF_WAVE_LENGTH = 0.41666666f;
    private static final String TAG = LoggingUtil.tag(LoadingDots.class);
    private ValueAnimator mAnimationFraction;
    private ImageView mDot1;
    private ImageView mDot2;
    private ImageView mDot3;

    public LoadingDots(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize() {
        this.mDot1 = (ImageView) findViewById(R.id.loading_dot_1);
        this.mDot2 = (ImageView) findViewById(R.id.loading_dot_2);
        this.mDot3 = (ImageView) findViewById(R.id.loading_dot_3);
        this.mAnimationFraction = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.mAnimationFraction.setRepeatCount(-1);
        this.mAnimationFraction.setRepeatMode(1);
        this.mAnimationFraction.setDuration(1000L);
        this.mAnimationFraction.setInterpolator(new LinearInterpolator());
        this.mAnimationFraction.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            /* class com.oculus.panelapp.anytimeui.v2.tablet.loading.LoadingDots.AnonymousClass1 */

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoadingDots.this.mDot1.setAlpha(LoadingDots.this.getAlpha(floatValue, LoadingDots.ALPHA_HALF_WAVE_LENGTH));
                LoadingDots.this.mDot2.setAlpha(LoadingDots.this.getAlpha(floatValue, 0.5f));
                LoadingDots.this.mDot3.setAlpha(LoadingDots.this.getAlpha(floatValue, 0.5833334f));
            }
        });
    }

    public void start() {
        ValueAnimator valueAnimator = this.mAnimationFraction;
        if (valueAnimator == null) {
            Log.e(TAG, "Start should not be called before initialize: unable to start animation.");
        } else {
            valueAnimator.start();
        }
    }

    public void end() {
        ValueAnimator valueAnimator = this.mAnimationFraction;
        if (valueAnimator == null) {
            Log.e(TAG, "End should not be called before initialize: unable to end animation.");
        } else {
            valueAnimator.end();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private float getAlpha(float f, float f2) {
        if (f2 - ALPHA_HALF_WAVE_LENGTH > f || f > f2 + ALPHA_HALF_WAVE_LENGTH) {
            return 0.0f;
        }
        return (float) ((Math.sin(((double) (((f - f2) / ALPHA_HALF_WAVE_LENGTH) + 0.5f)) * 3.141592653589793d) * 0.5d) + 0.5d);
    }
}

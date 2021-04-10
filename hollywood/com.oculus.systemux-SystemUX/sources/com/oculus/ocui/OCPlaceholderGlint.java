package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.ocui.R;
import com.oculus.common.ocui.databinding.OcplaceholderGlintBinding;

public class OCPlaceholderGlint extends ConstraintLayout {
    private long animationDurationFastMs;
    private long animationDurationMediumMs;
    private AnimationSet mAnimationSet;
    private boolean mAutoStart;
    private OcplaceholderGlintBinding mBinding;

    public OCPlaceholderGlint(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OcplaceholderGlintBinding.inflate(LayoutInflater.from(context), this, true);
        this.animationDurationFastMs = (long) context.getResources().getInteger(R.integer.ocanimation_fast2);
        this.animationDurationMediumMs = (long) context.getResources().getInteger(R.integer.ocanimation_medium2);
        setClipToOutline(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.OCPlaceholderGlint);
        int integer = obtainStyledAttributes.getInteger(R.styleable.OCPlaceholderGlint_startOffset, 0);
        this.mAutoStart = obtainStyledAttributes.getBoolean(R.styleable.OCPlaceholderGlint_autoStart, true);
        initializeAnimation(integer);
        obtainStyledAttributes.recycle();
    }

    private void initializeAnimation(int i) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(this.animationDurationMediumMs);
        long j = (long) i;
        alphaAnimation.setStartOffset(j);
        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setDuration(this.animationDurationMediumMs);
        alphaAnimation2.setStartOffset(this.animationDurationMediumMs + this.animationDurationFastMs + j);
        this.mAnimationSet = new AnimationSet(false);
        this.mAnimationSet.addAnimation(alphaAnimation);
        this.mAnimationSet.addAnimation(alphaAnimation2);
        this.mAnimationSet.setFillAfter(true);
        this.mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            /* class com.oculus.ocui.OCPlaceholderGlint.AnonymousClass1 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                OCPlaceholderGlint.this.mAnimationSet.setStartOffset(OCPlaceholderGlint.this.animationDurationFastMs);
                alphaAnimation.setStartOffset(0);
                alphaAnimation2.setStartOffset(OCPlaceholderGlint.this.animationDurationMediumMs + OCPlaceholderGlint.this.animationDurationFastMs);
                OCPlaceholderGlint.this.mBinding.placeholderActive.startAnimation(OCPlaceholderGlint.this.mAnimationSet);
            }
        });
        if (this.mAutoStart) {
            this.mBinding.placeholderActive.startAnimation(this.mAnimationSet);
        }
    }

    public void setOffset(int i) {
        initializeAnimation(i);
    }

    public void startAnimation() {
        this.mBinding.placeholderActive.startAnimation(this.mAnimationSet);
    }
}

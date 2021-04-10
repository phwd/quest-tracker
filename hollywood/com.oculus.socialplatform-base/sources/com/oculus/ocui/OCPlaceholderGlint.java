package com.oculus.ocui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.oculus.common.ocui.databinding.OcplaceholderGlintBinding;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;

public class OCPlaceholderGlint extends ConstraintLayout {
    public long animationDurationFastMs;
    public long animationDurationMediumMs;
    public AnimationSet mAnimationSet;
    public boolean mAutoStart;
    public OcplaceholderGlintBinding mBinding;

    private void initializeAnimation(int i) {
        final AlphaAnimation alphaAnimation = new AlphaAnimation((float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, 1.0f);
        alphaAnimation.setDuration(this.animationDurationMediumMs);
        long j = (long) i;
        alphaAnimation.setStartOffset(j);
        final AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        alphaAnimation2.setDuration(this.animationDurationMediumMs);
        alphaAnimation2.setStartOffset(this.animationDurationMediumMs + this.animationDurationFastMs + j);
        AnimationSet animationSet = new AnimationSet(false);
        this.mAnimationSet = animationSet;
        animationSet.addAnimation(alphaAnimation);
        this.mAnimationSet.addAnimation(alphaAnimation2);
        this.mAnimationSet.setFillAfter(true);
        this.mAnimationSet.setAnimationListener(new Animation.AnimationListener() {
            /* class com.oculus.ocui.OCPlaceholderGlint.AnonymousClass1 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                OCPlaceholderGlint oCPlaceholderGlint = OCPlaceholderGlint.this;
                oCPlaceholderGlint.mAnimationSet.setStartOffset(oCPlaceholderGlint.animationDurationFastMs);
                alphaAnimation.setStartOffset(0);
                AlphaAnimation alphaAnimation = alphaAnimation2;
                OCPlaceholderGlint oCPlaceholderGlint2 = OCPlaceholderGlint.this;
                alphaAnimation.setStartOffset(oCPlaceholderGlint2.animationDurationMediumMs + oCPlaceholderGlint2.animationDurationFastMs);
                OCPlaceholderGlint oCPlaceholderGlint3 = OCPlaceholderGlint.this;
                oCPlaceholderGlint3.mBinding.placeholderActive.startAnimation(oCPlaceholderGlint3.mAnimationSet);
            }
        });
        if (this.mAutoStart) {
            this.mBinding.placeholderActive.startAnimation(this.mAnimationSet);
        }
    }

    public void startAnimation() {
        this.mBinding.placeholderActive.startAnimation(this.mAnimationSet);
    }

    public OCPlaceholderGlint(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mBinding = OcplaceholderGlintBinding.inflate(LayoutInflater.from(context), this, true);
        this.animationDurationFastMs = (long) context.getResources().getInteger(R.integer.ocanimation_fast2);
        this.animationDurationMediumMs = (long) context.getResources().getInteger(R.integer.ocanimation_medium2);
        setClipToOutline(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.oculus.common.ocui.R.styleable.OCPlaceholderGlint);
        int integer = obtainStyledAttributes.getInteger(1, 0);
        this.mAutoStart = obtainStyledAttributes.getBoolean(0, true);
        initializeAnimation(integer);
        obtainStyledAttributes.recycle();
    }

    public void setOffset(int i) {
        initializeAnimation(i);
    }
}

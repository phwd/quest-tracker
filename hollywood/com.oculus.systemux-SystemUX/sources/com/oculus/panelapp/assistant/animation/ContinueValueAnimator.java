package com.oculus.panelapp.assistant.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;

public abstract class ContinueValueAnimator<T> implements Animator.AnimatorListener, ValueAnimator.AnimatorUpdateListener {
    private ValueAnimator mAnimator;
    private T mInitialValue;
    private int mTargetWidth;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public abstract T onSetInitialValue(Animator animator);

    public abstract void onUpdateValue(float f);

    public ContinueValueAnimator() {
        this(ValueAnimator.ofFloat(0.0f, 1.0f));
    }

    public ContinueValueAnimator(ValueAnimator valueAnimator) {
        this.mAnimator = valueAnimator;
        this.mAnimator.addListener(this);
        this.mAnimator.addUpdateListener(this);
    }

    public T getInitialValue() {
        return this.mInitialValue;
    }

    public ValueAnimator getAnimator() {
        return this.mAnimator;
    }

    public void onAnimationStart(Animator animator) {
        this.mInitialValue = onSetInitialValue(animator);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        onUpdateValue(valueAnimator.getAnimatedFraction());
    }
}

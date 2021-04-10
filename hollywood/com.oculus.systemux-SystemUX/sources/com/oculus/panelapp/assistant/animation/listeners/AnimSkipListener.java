package com.oculus.panelapp.assistant.animation.listeners;

import android.animation.Animator;

public class AnimSkipListener implements Animator.AnimatorListener {
    private SkipEvaluator mSkipEvaluator;

    public interface SkipEvaluator {
        boolean onEvaluateSkip();
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public AnimSkipListener(SkipEvaluator skipEvaluator) {
        this.mSkipEvaluator = skipEvaluator;
    }

    public void onAnimationStart(Animator animator) {
        if (this.mSkipEvaluator.onEvaluateSkip()) {
            animator.cancel();
        }
    }
}

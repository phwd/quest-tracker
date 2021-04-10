package com.oculus.panelapp.assistant.animation.listeners;

import android.animation.Animator;

public class AnimSuccessListener implements Animator.AnimatorListener {
    private boolean mCancelled;
    private Runnable mRunnable;

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public AnimSuccessListener(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        if (!this.mCancelled) {
            this.mRunnable.run();
        }
    }

    public void onAnimationCancel(Animator animator) {
        this.mCancelled = true;
    }
}

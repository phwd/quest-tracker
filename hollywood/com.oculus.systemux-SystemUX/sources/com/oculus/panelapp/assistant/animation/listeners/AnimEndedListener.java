package com.oculus.panelapp.assistant.animation.listeners;

import android.animation.Animator;

public class AnimEndedListener implements Animator.AnimatorListener {
    private Runnable mRunnable;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public void onAnimationStart(Animator animator) {
    }

    public AnimEndedListener(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void onAnimationEnd(Animator animator) {
        this.mRunnable.run();
    }
}

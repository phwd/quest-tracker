package com.oculus.panelapp.assistant.animation.listeners;

import android.animation.Animator;

public class AnimStartListener implements Animator.AnimatorListener {
    private Runnable mRunnable;

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationEnd(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    public AnimStartListener(Runnable runnable) {
        this.mRunnable = runnable;
    }

    public void onAnimationStart(Animator animator) {
        this.mRunnable.run();
    }
}

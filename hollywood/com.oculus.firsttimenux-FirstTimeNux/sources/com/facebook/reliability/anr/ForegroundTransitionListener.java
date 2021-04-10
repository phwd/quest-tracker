package com.facebook.reliability.anr;

public interface ForegroundTransitionListener {
    void onBackground();

    void onForeground();
}

package com.oculus.horizon.foreground;

public interface ApplicationForegroundListener {
    void onApplicationBackground(long j);

    void onApplicationForeground();
}

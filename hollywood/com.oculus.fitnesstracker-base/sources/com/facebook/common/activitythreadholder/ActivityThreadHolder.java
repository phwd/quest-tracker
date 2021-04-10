package com.facebook.common.activitythreadholder;

import android.app.ActivityThread;

public final class ActivityThreadHolder {
    private static ActivityThread sActivityThread;

    public static ActivityThread getActivityThread() {
        ActivityThread activityThread = sActivityThread;
        if (activityThread != null) {
            return activityThread;
        }
        ActivityThread currentActivityThread = ActivityThread.currentActivityThread();
        sActivityThread = currentActivityThread;
        return currentActivityThread;
    }
}

package com.facebook.common.activitythreadholder;

import android.app.ActivityThread;

public final class ActivityThreadHolder {
    private static ActivityThread sActivityThread;

    public static ActivityThread getActivityThread() {
        ActivityThread at = sActivityThread;
        if (at != null) {
            return at;
        }
        ActivityThread at2 = ActivityThread.currentActivityThread();
        sActivityThread = at2;
        return at2;
    }
}

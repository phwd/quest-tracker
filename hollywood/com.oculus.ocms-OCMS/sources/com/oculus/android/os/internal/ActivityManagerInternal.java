package com.oculus.android.os.internal;

import android.app.ActivityManager;

public final class ActivityManagerInternal {
    public static int getCurrentUser() {
        return ActivityManager.getCurrentUser();
    }
}

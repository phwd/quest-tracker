package com.facebook.base.lwperf.perfstats;

import android.app.ActivityManager;
import android.app.Application;
import android.os.Build;
import android.os.PowerManager;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.facebook.common.activitythreadholder.ActivityThreadHolder;
import com.facebook.common.procread.ProcReader;

public final class BackgroundChecker {
    private static final BackgroundChecker INSTANCE = new BackgroundChecker();
    private static final String IS_BACKGROUND_SUFFIX = "/bg_non_interactive";
    private static final String IS_BACKGROUND_SUFFIX_NEW_VERSIONS = "/background";
    private static final long MIN_RECALCULATE_TIME_MS = 500;
    private static final long NOT_CHECKED = -1;
    public static final int OVERRIDE_STATE_FOREGROUND = 2;
    public static final int OVERRIDE_STATE_INTERACTIVE = 3;
    public static final int OVERRIDE_STATE_INVISIBLE = 0;
    public static final int OVERRIDE_STATE_UNSET = -1;
    public static final int OVERRIDE_STATE_VISIBLE = 1;
    private static final String TAG = "BackgroundChecker";
    private volatile boolean mHasBeenBackgrounded;
    private volatile boolean mIsBackgrounded = false;
    private volatile Boolean mIsStartedInTheBackground;
    private volatile long mLastCalculatedTime = -1;
    private volatile int mOverrideState = -1;

    private boolean isVisible(int i) {
        return i <= 200;
    }

    private BackgroundChecker() {
    }

    public static BackgroundChecker getInstance() {
        return INSTANCE;
    }

    public boolean checkIfStartupWasInTheBackground() {
        if (this.mIsStartedInTheBackground == null) {
            boolean isBackgroundedNow = isBackgroundedNow();
            this.mIsStartedInTheBackground = Boolean.valueOf(isBackgroundedNow);
            return isBackgroundedNow;
        }
        throw new IllegalStateException("checkIfStartupWasInTheBackground has already been called!");
    }

    public Boolean getWasAppStartedInTheBackground() {
        return this.mIsStartedInTheBackground;
    }

    public boolean hasBeenBackgrounded() {
        if (this.mHasBeenBackgrounded) {
            return true;
        }
        isRateLimitedBackground();
        return this.mHasBeenBackgrounded;
    }

    public boolean isRateLimitedBackground() {
        long j = this.mLastCalculatedTime;
        if (j == -1 || nowMs() - j >= MIN_RECALCULATE_TIME_MS) {
            return isBackgroundedNow();
        }
        return this.mIsBackgrounded;
    }

    public boolean isInvisible() {
        return isBackgroundedNow() || this.mOverrideState == 0 || !isVisible(getImp());
    }

    public boolean isVisible() {
        return this.mOverrideState >= 1 || isVisible(getImp());
    }

    public boolean isForeground() {
        return this.mOverrideState >= 2 || isForeground(getImp());
    }

    public boolean isInteractive() {
        return this.mOverrideState >= 3 || isInteractive(getImp());
    }

    public void setOverrideState(int i) {
        this.mOverrideState = i;
    }

    private static int getImp() {
        if (app().getBaseContext() == null) {
            return Integer.MAX_VALUE;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            return runningAppProcessInfo.importance;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo2 : ((ActivityManager) app().getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo2.pid == Process.myPid()) {
                return runningAppProcessInfo2.importance;
            }
        }
        return Integer.MAX_VALUE;
    }

    private boolean isForeground(int i) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (app().getBaseContext() == null) {
                return false;
            }
            if (!((PowerManager) app().getSystemService("power")).isInteractive()) {
                if (i <= 325) {
                    return true;
                }
                return false;
            }
        }
        if (i <= 100) {
            return true;
        }
        return false;
    }

    private boolean isInteractive(int i) {
        if (!isForeground(i)) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 23) {
            return ((PowerManager) app().getSystemService("power")).isScreenOn();
        }
        if (i <= 100) {
            return true;
        }
        return false;
    }

    public boolean isBackgroundedNow() {
        boolean calculateIsBackgroundedNow = calculateIsBackgroundedNow();
        long nowMs = nowMs();
        this.mIsBackgrounded = calculateIsBackgroundedNow;
        if (calculateIsBackgroundedNow) {
            this.mHasBeenBackgrounded = true;
        }
        this.mLastCalculatedTime = nowMs;
        return calculateIsBackgroundedNow;
    }

    public String collectBackgroundStats() {
        return String.format("0x%04X", Byte.valueOf((byte) (((byte) (((byte) (((byte) ((isVisible() ? (byte) 1 : 0) | ((isForeground() ? 1 : 0) << 1))) | ((isInteractive() ? 1 : 0) << 2))) | ((isBackgroundedNow() ? 1 : 0) << 3))) | ((isRateLimitedBackground() ? 1 : 0) << 4))));
    }

    private boolean calculateIsBackgroundedNow() {
        try {
            int myPid = Process.myPid();
            String readProcFileEntirely = ProcReader.readProcFileEntirely("/proc/" + myPid + "/cgroup");
            if (readProcFileEntirely == null) {
                return false;
            }
            if (readProcFileEntirely.contains(IS_BACKGROUND_SUFFIX) || readProcFileEntirely.contains(IS_BACKGROUND_SUFFIX_NEW_VERSIONS)) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            Log.d(TAG, "Runtime Exception reading proc to determine if in the background", e);
            return false;
        }
    }

    private static long nowMs() {
        return SystemClock.uptimeMillis();
    }

    private static Application app() {
        return ActivityThreadHolder.getActivityThread().getApplication();
    }
}

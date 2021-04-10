package com.oculus.os;

import android.os.IBinder;

public class VrApiManager implements IBinder.DeathRecipient {
    public static final int CLOCK_DOMAIN_COUNT = 4;
    public static final int CLOCK_DOMAIN_CPU = 0;
    public static final int CLOCK_DOMAIN_CPUBW = 2;
    public static final int CLOCK_DOMAIN_GPU = 1;
    public static final int CLOCK_DOMAIN_GPUBW = 3;
    public static final int DVFS_POLICY_COUNT = 3;
    public static final int DVFS_POLICY_DYNAMIC = 0;
    public static final int DVFS_POLICY_MAX = 2;
    public static final int DVFS_POLICY_STATIC = 1;
    public static final int FP_TEMP_DANGER = 2;
    public static final int FP_TEMP_ELEVATED = 1;
    public static final int FP_TEMP_NORMAL = 0;
    public static final int POWER_LEVEL_MINIMAL = 2;
    public static final int POWER_LEVEL_NORMAL = 0;
    public static final int POWER_LEVEL_POWERSAVE = 1;
    public static final int THREAD_POLICY_BACKGROUND = 0;
    public static final int THREAD_POLICY_COMPUTE = 1;
    public static final int THREAD_POLICY_COUNT = 6;
    public static final int THREAD_POLICY_ELEVATED = 3;
    public static final int THREAD_POLICY_ISOCHRONOUS = 4;
    public static final int THREAD_POLICY_LATENCY_CRITICAL = 5;
    public static final int THREAD_POLICY_LEVEL_COUNT = 3;
    public static final int THREAD_POLICY_LEVEL_HIGH = 2;
    public static final int THREAD_POLICY_LEVEL_LOW = 0;
    public static final int THREAD_POLICY_LEVEL_MEDIUM = 1;
    public static final int THREAD_POLICY_NORMAL = 2;

    VrApiManager() {
        throw new RuntimeException("Stub!");
    }

    public static VrApiManager getInstance() {
        throw new RuntimeException("Stub!");
    }

    public void binderDied() {
        throw new RuntimeException("Stub!");
    }

    public boolean setThreadPolicy(int i, int i2, int i3) {
        throw new RuntimeException("Stub!");
    }

    public PerfRange getPerformanceRange(int i) {
        throw new RuntimeException("Stub!");
    }

    public boolean lockVrPerformance(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public void releaseVrPerformance() {
        throw new RuntimeException("Stub!");
    }

    public String getDeviceProperty(String str) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDeviceProperty(String str, String str2) {
        throw new RuntimeException("Stub!");
    }

    public DisplayParams getDisplayParams() {
        throw new RuntimeException("Stub!");
    }

    public int getDisplayBrightness() {
        throw new RuntimeException("Stub!");
    }

    public boolean setDisplayBrightness(int i) {
        throw new RuntimeException("Stub!");
    }

    public int[] getClockFrequencies(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public int getPowerLevelState() {
        throw new RuntimeException("Stub!");
    }

    public int[] getSupportedDisplayRefreshRates() {
        throw new RuntimeException("Stub!");
    }

    public boolean setDisplayRefreshRate(int i) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public boolean setPowerSaveMode(boolean z) {
        throw new RuntimeException("Stub!");
    }

    public int getCurrentClockFrequency(int i, int i2) {
        throw new RuntimeException("Stub!");
    }

    public int getCurrentClockPerfLevel(int i, int i2) {
        throw new RuntimeException("Stub!");
    }
}

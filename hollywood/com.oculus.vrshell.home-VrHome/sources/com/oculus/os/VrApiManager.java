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

    public boolean setThreadPolicy(int policy, int level, int threadId) {
        throw new RuntimeException("Stub!");
    }

    public PerfRange getPerformanceRange(int clockDomain) {
        throw new RuntimeException("Stub!");
    }

    public boolean lockVrPerformance(int cpuLevel, int gpuLevel) {
        throw new RuntimeException("Stub!");
    }

    public void releaseVrPerformance() {
        throw new RuntimeException("Stub!");
    }

    public String getDeviceProperty(String propertyName) {
        throw new RuntimeException("Stub!");
    }

    public boolean setDeviceProperty(String name, String value) {
        throw new RuntimeException("Stub!");
    }

    public DisplayParams getDisplayParams() {
        throw new RuntimeException("Stub!");
    }

    public int getDisplayBrightness() {
        throw new RuntimeException("Stub!");
    }

    public boolean setDisplayBrightness(int brightness) {
        throw new RuntimeException("Stub!");
    }

    public int[] getClockFrequencies(int clockDomain, int coreId) {
        throw new RuntimeException("Stub!");
    }

    public int getPowerLevelState() {
        throw new RuntimeException("Stub!");
    }

    public int[] getSupportedDisplayRefreshRates() {
        throw new RuntimeException("Stub!");
    }

    public boolean setDisplayRefreshRate(int refreshRate) {
        throw new RuntimeException("Stub!");
    }

    @Deprecated
    public boolean setPowerSaveMode(boolean mode) {
        throw new RuntimeException("Stub!");
    }

    public int getCurrentClockFrequency(int clockDomain, int coreId) {
        throw new RuntimeException("Stub!");
    }

    public int getCurrentClockPerfLevel(int clockDomain, int coreId) {
        throw new RuntimeException("Stub!");
    }
}

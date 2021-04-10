package com.oculus.os;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import com.android.internal.annotations.VisibleForTesting;
import oculus.internal.IVrApiService;

public class VrApiManager implements IBinder.DeathRecipient {
    public static final int CLOCK_DOMAIN_COUNT = 4;
    public static final int CLOCK_DOMAIN_CPU = 0;
    public static final int CLOCK_DOMAIN_CPUBW = 2;
    public static final int CLOCK_DOMAIN_GPU = 1;
    public static final int CLOCK_DOMAIN_GPUBW = 3;
    private static final boolean DEBUG = false;
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
    private static final String TAG = "VrApiManager";
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
    private static final String VR_API_SERVICE = "VrApi";
    private static VrApiManager sInstance;
    private IVrApiService mService;

    public static VrApiManager getInstance() {
        if (sInstance == null) {
            sInstance = new VrApiManager();
        }
        return sInstance;
    }

    private VrApiManager() {
        ensureServiceConnected();
    }

    @VisibleForTesting
    public VrApiManager(IVrApiService service) {
        this.mService = service;
    }

    private void ensureServiceConnected() {
        if (this.mService == null) {
            IBinder b = ServiceManager.getService(VR_API_SERVICE);
            this.mService = IVrApiService.Stub.asInterface(b);
            if (this.mService == null) {
                Log.wtf(TAG, "Failed to get VrApi service");
                return;
            }
            try {
                b.linkToDeath(this, 0);
            } catch (RemoteException e) {
                Log.e(TAG, "linkToDeath failed", e);
            }
        }
    }

    public void binderDied() {
        Log.d(TAG, "Remote service died, resetting mService");
        this.mService = null;
    }

    public boolean setThreadPolicy(int policy, int level, int threadId) {
        ensureServiceConnected();
        try {
            this.mService.setThreadPolicy(policy, level, threadId);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "setThreadPolicy: encountered exception", e);
            return DEBUG;
        }
    }

    public PerfRange getPerformanceRange(int clockDomain) {
        ensureServiceConnected();
        try {
            return this.mService.getPerformanceRange(clockDomain);
        } catch (Exception e) {
            Log.e(TAG, "getPerformanceRange: encountered exception", e);
            return null;
        }
    }

    public boolean lockVrPerformance(int cpuLevel, int gpuLevel) {
        ensureServiceConnected();
        try {
            this.mService.lockVrPerformance(cpuLevel, gpuLevel);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "lockVrPerformance: encountered exception", e);
            return DEBUG;
        }
    }

    public void releaseVrPerformance() {
        ensureServiceConnected();
        try {
            this.mService.releaseVrPerformance();
        } catch (Exception e) {
            Log.e(TAG, "releaseVrPerformance: encountered exception", e);
        }
    }

    public String getDeviceProperty(String propertyName) {
        ensureServiceConnected();
        try {
            return this.mService.getDeviceProperty(propertyName);
        } catch (Exception e) {
            Log.e(TAG, "getDeviceProperty: encountered exception", e);
            return null;
        }
    }

    public boolean setDeviceProperty(String name, String value) {
        ensureServiceConnected();
        try {
            this.mService.setDeviceProperty(name, value);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "setDeviceProperty: encountered exception", e);
            return DEBUG;
        }
    }

    public DisplayParams getDisplayParams() {
        ensureServiceConnected();
        try {
            return this.mService.getDisplayParams();
        } catch (Exception e) {
            Log.e(TAG, "getDisplayParams: encountered exception", e);
            return null;
        }
    }

    public int getDisplayBrightness() {
        ensureServiceConnected();
        try {
            return this.mService.getDisplayBrightness();
        } catch (Exception e) {
            Log.e(TAG, "getDisplayBrightness: encountered exception", e);
            return -1;
        }
    }

    public boolean setDisplayBrightness(int brightness) {
        ensureServiceConnected();
        try {
            this.mService.setDisplayBrightness(brightness);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "setDisplayBrightness: encountered exception", e);
            return DEBUG;
        }
    }

    public int[] getClockFrequencies(int clockDomain, int coreId) {
        ensureServiceConnected();
        try {
            this.mService.getClockFrequencies(clockDomain, coreId, null);
            return null;
        } catch (Exception e) {
            Log.e(TAG, "getClockFrequencies: encountered exception", e);
            return null;
        }
    }

    public int getPowerLevelState() {
        ensureServiceConnected();
        try {
            return this.mService.getPowerLevelState();
        } catch (Exception e) {
            Log.e(TAG, "getPowerLevelState: encountered exception", e);
            return -1;
        }
    }

    public int[] getSupportedDisplayRefreshRates() {
        ensureServiceConnected();
        try {
            return this.mService.getSupportedDisplayRefreshRates();
        } catch (Exception e) {
            Log.e(TAG, "getSupportedDisplayRefreshRates: encountered exception", e);
            return null;
        }
    }

    public boolean setDisplayRefreshRate(int refreshRate) {
        ensureServiceConnected();
        try {
            this.mService.setDisplayRefreshRate(refreshRate);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "setDisplayRefreshRate: encountered exception", e);
            return DEBUG;
        }
    }

    @Deprecated
    public boolean setPowerSaveMode(boolean mode) {
        Log.w(TAG, "setPowerSaveMode: Deprecated, do not use");
        return DEBUG;
    }

    public int getCurrentClockFrequency(int clockDomain, int coreId) {
        ensureServiceConnected();
        try {
            return this.mService.getCurrentClockFrequency(clockDomain, coreId);
        } catch (Exception e) {
            Log.e(TAG, "getCurrentClockFrequency: encountered exception", e);
            return 0;
        }
    }

    public int getCurrentClockPerfLevel(int clockDomain, int coreId) {
        ensureServiceConnected();
        try {
            return this.mService.getCurrentClockPerfLevel(clockDomain, coreId);
        } catch (Exception e) {
            Log.e(TAG, "getCurrentClockFrequency: encountered exception", e);
            return -1;
        }
    }
}

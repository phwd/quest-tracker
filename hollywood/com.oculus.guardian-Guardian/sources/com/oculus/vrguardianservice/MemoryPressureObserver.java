package com.oculus.vrguardianservice;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.os.SystemResourceManager;

public class MemoryPressureObserver {
    private static final int MEMORY_PRESSURE_RELIEF_PERIOD_MS = 1000;
    private static final String TAG = "MemoryPressureObserver";
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private MemoryPressure mMemoryPressure = MemoryPressure.Unknown;
    private final MemoryPressureCallback mMemoryPressureCallback;
    private final Runnable mMemoryPressureReliefRunnable = new Runnable() {
        /* class com.oculus.vrguardianservice.MemoryPressureObserver.AnonymousClass1 */

        public void run() {
            try {
                MemoryPressure memoryPressure = MemoryPressureObserver.this.getCurrentMemoryPressureState();
                Log.d(MemoryPressureObserver.TAG, "Current memory pressure: " + memoryPressure);
                int i = AnonymousClass2.$SwitchMap$com$oculus$vrguardianservice$MemoryPressure[memoryPressure.ordinal()];
                if (i != 1) {
                    if (i == 2 || i == 3 || i == 4) {
                        MemoryPressureObserver.this.mHandler.postDelayed(this, 1000);
                    }
                }
                MemoryPressureObserver.this.setCurrentMemoryPressure(memoryPressure);
            } catch (Exception e) {
                Log.d(MemoryPressureObserver.TAG, "Exception: ");
            }
        }
    };

    public MemoryPressureObserver(MemoryPressureCallback callback) {
        setCurrentMemoryPressure(getCurrentMemoryPressureState());
        this.mMemoryPressureCallback = callback;
    }

    public MemoryPressure getCurrentMemoryPressureState() {
        return MemoryPressure.fromMemoryPressureValue(getCurrentMemoryPressureFloatValue());
    }

    public float getCurrentMemoryPressureFloatValue() {
        return SystemResourceManager.getMemoryPressure();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setCurrentMemoryPressure(MemoryPressure memoryPressure) {
        if (memoryPressure != this.mMemoryPressure) {
            Log.d(TAG, "setCurrentMemoryPressure:" + memoryPressure.stringVal());
            this.mMemoryPressure = memoryPressure;
            MemoryPressureCallback memoryPressureCallback = this.mMemoryPressureCallback;
            if (memoryPressureCallback != null) {
                memoryPressureCallback.OnMemoryPressureStateChanged(this.mMemoryPressure);
            }
            if (memoryPressure == MemoryPressure.None) {
                this.mHandler.removeCallbacks(this.mMemoryPressureReliefRunnable);
            } else if (Build.VERSION.SDK_INT >= 29) {
                startMemoryPressureReliefCheck();
            }
        }
    }

    public void onNativeTrimMemory(int level) {
        if (level == 5) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_RUNNING_MODERATE");
            setCurrentMemoryPressure(MemoryPressure.Some);
        } else if (level == 10) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_RUNNING_LOW");
            setCurrentMemoryPressure(MemoryPressure.Some);
        } else if (level == 15) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_RUNNING_CRITICAL");
            setCurrentMemoryPressure(MemoryPressure.Critical);
        } else if (level == 20) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_UI_HIDDEN IGNORED");
        } else if (level == 40) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_BACKGROUND IGNORED");
        } else if (level == 60) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_MODERATE IGNORED");
        } else if (level != 80) {
            Log.d(TAG, "onTrimMemory default (treated as critical)");
            setCurrentMemoryPressure(MemoryPressure.Critical);
        } else {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_COMPLETE IGNORED");
        }
    }

    /* renamed from: com.oculus.vrguardianservice.MemoryPressureObserver$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrguardianservice$MemoryPressure = new int[MemoryPressure.values().length];

        static {
            try {
                $SwitchMap$com$oculus$vrguardianservice$MemoryPressure[MemoryPressure.None.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$vrguardianservice$MemoryPressure[MemoryPressure.Unknown.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$vrguardianservice$MemoryPressure[MemoryPressure.Some.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$oculus$vrguardianservice$MemoryPressure[MemoryPressure.Critical.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private void startMemoryPressureReliefCheck() {
        this.mHandler.removeCallbacks(this.mMemoryPressureReliefRunnable);
        this.mHandler.postDelayed(this.mMemoryPressureReliefRunnable, 1000);
    }
}

package com.oculus.vrshell.memorypressure;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.os.SystemResourceManager;

public class MemoryPressureObserver {
    private static final int MEMORY_PRESSURE_RELIEF_PERIOD_MS = 1000;
    private static final String TAG = LoggingUtil.tag(MemoryPressureObserver.class);
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private MemoryPressure mMemoryPressure = MemoryPressure.Unknown;
    private final MemoryPressureCallback mMemoryPressureCallback;
    private final Runnable mMemoryPressureReliefRunnable = new Runnable() {
        /* class com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass1 */

        public void run() {
            try {
                MemoryPressure currentMemoryPressureState = MemoryPressureObserver.this.getCurrentMemoryPressureState();
                String str = MemoryPressureObserver.TAG;
                Log.d(str, "Current memory pressure: " + currentMemoryPressureState);
                int i = AnonymousClass2.$SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure[currentMemoryPressureState.ordinal()];
                if (i != 1) {
                    if (i == 2 || i == 3 || i == 4) {
                        MemoryPressureObserver.this.mHandler.postDelayed(this, 1000);
                    }
                }
                MemoryPressureObserver.this.setCurrentMemoryPressure(currentMemoryPressureState);
            } catch (Exception unused) {
                Log.d(MemoryPressureObserver.TAG, "Exception: ");
            }
        }
    };

    public MemoryPressureObserver(MemoryPressureCallback memoryPressureCallback) {
        setCurrentMemoryPressure(getCurrentMemoryPressureState());
        this.mMemoryPressureCallback = memoryPressureCallback;
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
            String str = TAG;
            Log.d(str, "setCurrentMemoryPressure:" + memoryPressure.stringVal());
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

    public void onNativeTrimMemory(int i) {
        String str = TAG;
        Log.d(str, "onTrimMemory:" + i);
        if (i == 5) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_RUNNING_MODERATE");
            setCurrentMemoryPressure(MemoryPressure.Some);
        } else if (i == 10) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_RUNNING_LOW");
            setCurrentMemoryPressure(MemoryPressure.Some);
        } else if (i == 15) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_RUNNING_CRITICAL");
            setCurrentMemoryPressure(MemoryPressure.Critical);
        } else if (i == 20) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_UI_HIDDEN IGNORED");
        } else if (i == 40) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_BACKGROUND IGNORED");
        } else if (i == 60) {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_MODERATE IGNORED");
        } else if (i != 80) {
            Log.d(TAG, "onTrimMemory default (treated as critical)");
            setCurrentMemoryPressure(MemoryPressure.Critical);
        } else {
            Log.d(TAG, "onTrimMemory TRIM_MEMORY_COMPLETE IGNORED");
        }
    }

    /* renamed from: com.oculus.vrshell.memorypressure.MemoryPressureObserver$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure = new int[MemoryPressure.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.vrshell.memorypressure.MemoryPressure[] r0 = com.oculus.vrshell.memorypressure.MemoryPressure.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass2.$SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure = r0
                int[] r0 = com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass2.$SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.vrshell.memorypressure.MemoryPressure r1 = com.oculus.vrshell.memorypressure.MemoryPressure.None     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass2.$SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.vrshell.memorypressure.MemoryPressure r1 = com.oculus.vrshell.memorypressure.MemoryPressure.Unknown     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass2.$SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.vrshell.memorypressure.MemoryPressure r1 = com.oculus.vrshell.memorypressure.MemoryPressure.Some     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass2.$SwitchMap$com$oculus$vrshell$memorypressure$MemoryPressure     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.vrshell.memorypressure.MemoryPressure r1 = com.oculus.vrshell.memorypressure.MemoryPressure.Critical     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.vrshell.memorypressure.MemoryPressureObserver.AnonymousClass2.<clinit>():void");
        }
    }

    private void startMemoryPressureReliefCheck() {
        this.mHandler.removeCallbacks(this.mMemoryPressureReliefRunnable);
        this.mHandler.postDelayed(this.mMemoryPressureReliefRunnable, 1000);
    }
}

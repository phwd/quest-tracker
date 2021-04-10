package com.facebook.errorreporting.nightwatch;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.TrafficStats;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import com.facebook.storage.common.external.ExternalStoragePath;
import com.facebook.ultralight.names.UltralightNames;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Nightwatch {
    private static final String LOG_TAG = Nightwatch.class.getSimpleName();
    private static final String WATCHER_BINARY = "libwatcher_binary.so";
    public static final String WATCHER_DIR = "watcher";
    public static final String WATCHER_FILE = "nightwatch.txt";
    @Nullable
    public static File currentLogFile = null;
    private static boolean isRunning = false;
    private static final Object isRunningLock = new Object();
    private static final ThreadLocal<Boolean> okToLoadNightWatch = new ThreadLocal<>();

    /* access modifiers changed from: private */
    public static void setIsRunning() {
        synchronized (isRunningLock) {
            isRunning = true;
        }
    }

    private static boolean running() {
        boolean z;
        synchronized (isRunningLock) {
            z = isRunning;
        }
        return z;
    }

    public static class NightwatchConfigs {
        private boolean monitorResources;
        private int monitorResourcesIntervalMs;
        private int processType;
        private boolean saveExitStatus;
        private int tickInfoCount;
        private boolean useFastOrCriticalJniMethodsIfCan;
        private boolean useLssForExec;
        private boolean useMmap;
        private boolean useSetSid;

        public NightwatchConfigs(boolean saveExitStatus2, boolean useSetSid2, boolean useLssForExec2, boolean useMmap2, boolean monitorResources2, int monitorResourcesIntervalMs2, int tickInfoCount2, int processType2, boolean useFastOrCriticalJniMethodsIfCan2) {
            this.saveExitStatus = saveExitStatus2;
            this.useSetSid = useSetSid2;
            this.useLssForExec = useLssForExec2;
            this.useMmap = useMmap2;
            this.monitorResources = monitorResources2;
            this.monitorResourcesIntervalMs = monitorResourcesIntervalMs2;
            this.tickInfoCount = tickInfoCount2;
            this.processType = processType2;
            this.useFastOrCriticalJniMethodsIfCan = useFastOrCriticalJniMethodsIfCan2;
        }

        public boolean shouldRunNightwatch() {
            return this.saveExitStatus || this.monitorResources;
        }
    }

    public static void startWatcher(Context context, String processName, NightwatchConfigs configs) {
        Random insecureRandom = new Random();
        startWatcher(new File(context.getDir(WATCHER_DIR, 0), processName.replace(':', '_') + UltralightNames.FQN_SEPARATOR + new UUID(insecureRandom.nextLong(), insecureRandom.nextLong()).toString() + ".txt"), configs);
    }

    public static void startWatcher(Context context, String processName, String aslSessionId, NightwatchConfigs configs) {
        startWatcher(new File(context.getDir(WATCHER_DIR, 0), processName.replace(':', '_') + UltralightNames.FQN_SEPARATOR + aslSessionId + ".txt"), configs);
    }

    public static void startWatcher(File logPath, NightwatchConfigs configs) {
        okToLoadNightWatch.set(Boolean.TRUE);
        currentLogFile = logPath;
        NightwatchNative.startWatcher(logPath, configs);
    }

    public static void reportDataToNightWatch(Context context) {
        if (running()) {
            reportBatteryCapacityToNightWatch(context);
            reportAvailableRootStorageToNightWatch();
            reportAvailableExternalStorageToNightWatch();
            reportBatteryTemperatureToNightWatch(context);
            reportTrafficTotalRxToNightWatch();
            reportTrafficTotalTxToNightWatch();
            reportTrafficMobileRxToNightWatch();
            reportTrafficMobileTxToNightWatch();
            reportConnectionTypeToNightWatch(context);
        }
    }

    /* access modifiers changed from: package-private */
    public enum NightwatchDataType {
        BatteryCapacity(0),
        RootStorage(1),
        ExternalStorage(2),
        BatteryTemperature(3),
        TrafficTotalRx(4),
        TrafficTotalTx(5),
        TrafficMobileRx(6),
        TrafficMobileTx(7),
        ConnectionType(8),
        ProcessImportance(9);
        
        private final int dataType;

        private NightwatchDataType(int dataType2) {
            this.dataType = dataType2;
        }

        public int getDataType() {
            return this.dataType;
        }
    }

    private static void reportBatteryCapacityToNightWatch(Context context) {
        int capacity = -1;
        if (Build.VERSION.SDK_INT >= 21) {
            BatteryManager mBatteryManager = (BatteryManager) context.getSystemService("batterymanager");
            if (mBatteryManager != null) {
                capacity = mBatteryManager.getIntProperty(4);
            } else {
                return;
            }
        } else {
            Scanner scanner = new Scanner("/sys/class/power_supply/battery/capacity");
            if (scanner.hasNextInt()) {
                capacity = scanner.nextInt();
            }
        }
        if (capacity > -1) {
            NightwatchNative.recordDataInNightWatch((long) capacity, NightwatchDataType.BatteryCapacity.getDataType());
        }
    }

    @TargetApi(18)
    private static void reportAvailableRootStorageToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            NightwatchNative.recordDataInNightWatch(new StatFs(((File) Assertions.assumeNotNull(Environment.getDataDirectory())).getAbsolutePath()).getAvailableBytes(), NightwatchDataType.RootStorage.getDataType());
        }
    }

    @TargetApi(18)
    private static void reportAvailableExternalStorageToNightWatch() {
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                long availableStorage = new StatFs(((File) Assertions.assumeNotNull(ExternalStoragePath.getDirectory(0))).getAbsolutePath()).getAvailableBytes();
                if (availableStorage > 0) {
                    NightwatchNative.recordDataInNightWatch(availableStorage, NightwatchDataType.ExternalStorage.getDataType());
                }
            }
        } catch (IllegalArgumentException e) {
        }
    }

    private static void reportBatteryTemperatureToNightWatch(Context context) {
        try {
            Intent intent = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (intent != null) {
                NightwatchNative.recordDataInNightWatch((long) intent.getIntExtra("temperature", 0), NightwatchDataType.BatteryTemperature.getDataType());
            }
        } catch (SecurityException e) {
        }
    }

    @TargetApi(18)
    private static void reportTrafficTotalRxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long meter = TrafficStats.getTotalRxBytes();
                if (meter > 0) {
                    NightwatchNative.recordDataInNightWatch(meter, NightwatchDataType.TrafficTotalRx.getDataType());
                }
            } catch (Exception e) {
            }
        }
    }

    @TargetApi(18)
    private static void reportTrafficTotalTxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long meter = TrafficStats.getTotalTxBytes();
                if (meter > 0) {
                    NightwatchNative.recordDataInNightWatch(meter, NightwatchDataType.TrafficTotalTx.getDataType());
                }
            } catch (Exception e) {
            }
        }
    }

    @TargetApi(18)
    private static void reportTrafficMobileRxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long meter = TrafficStats.getMobileRxBytes();
                if (meter > 0) {
                    NightwatchNative.recordDataInNightWatch(meter, NightwatchDataType.TrafficMobileRx.getDataType());
                }
            } catch (Exception e) {
            }
        }
    }

    @TargetApi(18)
    private static void reportTrafficMobileTxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long meter = TrafficStats.getMobileTxBytes();
                if (meter > 0) {
                    NightwatchNative.recordDataInNightWatch(meter, NightwatchDataType.TrafficMobileTx.getDataType());
                }
            } catch (Exception e) {
            }
        }
    }

    public static void reportConnectionTypeToNightWatch(Context context) {
        NetworkInfo activeNetwork;
        NetworkCapabilities capabilities;
        int connectionType = 0;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!(cm == null || (capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork())) == null)) {
                    if (capabilities.hasTransport(1)) {
                        connectionType = 2;
                    } else if (capabilities.hasTransport(0)) {
                        connectionType = 1;
                    }
                }
            } else if (!(cm == null || (activeNetwork = cm.getActiveNetworkInfo()) == null)) {
                if (activeNetwork.getType() == 1) {
                    connectionType = 2;
                } else if (activeNetwork.getType() == 0) {
                    connectionType = 1;
                }
            }
            NightwatchNative.recordDataInNightWatch((long) connectionType, NightwatchDataType.ConnectionType.getDataType());
        } catch (Exception e) {
        }
    }

    public static void reportProcessImportanceToNightwatch(int importance) {
        if (running()) {
            NightwatchNative.recordDataInNightWatch((long) importance, NightwatchDataType.ProcessImportance.getDataType());
        }
    }

    public static int recordTickInNightWatch(long actualUptimeMs, long expectedUptimeMs, long nextExpectedUptimeMs, long relativeThreadTimeMs) {
        if (!running()) {
            return 1;
        }
        return NightwatchNative.recordTickInNightWatch(actualUptimeMs, expectedUptimeMs, nextExpectedUptimeMs, relativeThreadTimeMs);
    }

    /* access modifiers changed from: private */
    @DoNotStrip
    @SuppressLint({"MissingNativeLoadLibrary"})
    public static class NightwatchNative {
        private static final boolean CAN_USE_CRITICAL_NATIVE_METHODS;
        private static boolean sHasLinkedFastMethods = false;

        private static native int nRecordDataInNightWatch(long j, int i);

        private static native int nRecordDataInNightWatch_FAST_JNI(long j, int i);

        private static native int nRecordTickInNightWatch(long j, long j2, long j3, long j4);

        private static native int nRecordTickInNightWatch_FAST_JNI(long j, long j2, long j3, long j4);

        private static native int start(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, int i3, boolean z6, boolean z7);

        private NightwatchNative() {
        }

        static {
            boolean canUseCriticalNativeMethod = false;
            if (Build.VERSION.SDK_INT >= 26) {
                try {
                    Class.forName("dalvik.annotation.optimization.CriticalNative");
                    canUseCriticalNativeMethod = true;
                } catch (ClassNotFoundException e) {
                    BLog.d(Nightwatch.LOG_TAG, e, "Critical Native could not be found. Using normal fastjni!");
                    canUseCriticalNativeMethod = false;
                }
            }
            CAN_USE_CRITICAL_NATIVE_METHODS = canUseCriticalNativeMethod;
            if (!((Boolean) Assertions.assumeNotNull(Boolean.TRUE)).equals(Nightwatch.okToLoadNightWatch.get())) {
                throw new RuntimeException("trying to load nightwatch before nightwatch starts!");
            }
            NativeLoader.loadLibrary("fbnightwatch");
        }

        /* access modifiers changed from: private */
        public static void startWatcher(File logPath, NightwatchConfigs configs) {
            try {
                String logPathStr = logPath.getCanonicalPath();
                BLog.d(Nightwatch.LOG_TAG, "Watcher file is %s", logPathStr);
                String watcherBinaryPath = NativeLoader.getLibraryPath(Nightwatch.WATCHER_BINARY);
                if (watcherBinaryPath == null) {
                    BLog.e(Nightwatch.LOG_TAG, "Could not find watcher binary");
                    return;
                }
                boolean shouldUseFastMethods = configs.useFastOrCriticalJniMethodsIfCan;
                int ret = start(watcherBinaryPath, logPathStr, configs.saveExitStatus, configs.useSetSid, configs.useLssForExec, configs.useMmap, configs.monitorResources, configs.monitorResourcesIntervalMs, configs.tickInfoCount, configs.processType, shouldUseFastMethods, CAN_USE_CRITICAL_NATIVE_METHODS && shouldUseFastMethods);
                Nightwatch.setIsRunning();
                sHasLinkedFastMethods = shouldUseFastMethods;
                BLog.d(Nightwatch.LOG_TAG, "Status starting watch: %d", Integer.valueOf(ret));
            } catch (IOException e) {
                BLog.e(Nightwatch.LOG_TAG, "Error starting watcher", e);
            }
        }

        /* access modifiers changed from: private */
        public static int recordDataInNightWatch(long capacity, int type) {
            if (shouldUseCriticalNativeMethods()) {
                return NightwatchOreo.recordDataInNightWatch(capacity, type);
            }
            if (shouldUseFastJniNativeMethods()) {
                return nRecordDataInNightWatch_FAST_JNI(capacity, type);
            }
            return nRecordDataInNightWatch(capacity, type);
        }

        /* access modifiers changed from: private */
        public static int recordTickInNightWatch(long actualUptimeMs, long expectedUptimeMs, long nextExpectedUptimeMs, long relativeThreadTimeMs) {
            if (shouldUseCriticalNativeMethods()) {
                return NightwatchOreo.recordTickInNightWatch(actualUptimeMs, expectedUptimeMs, nextExpectedUptimeMs, relativeThreadTimeMs);
            }
            if (shouldUseFastJniNativeMethods()) {
                return nRecordTickInNightWatch_FAST_JNI(actualUptimeMs, expectedUptimeMs, nextExpectedUptimeMs, relativeThreadTimeMs);
            }
            return nRecordTickInNightWatch(actualUptimeMs, expectedUptimeMs, nextExpectedUptimeMs, relativeThreadTimeMs);
        }

        private static boolean shouldUseCriticalNativeMethods() {
            return CAN_USE_CRITICAL_NATIVE_METHODS && sHasLinkedFastMethods;
        }

        private static boolean shouldUseFastJniNativeMethods() {
            return !CAN_USE_CRITICAL_NATIVE_METHODS && sHasLinkedFastMethods;
        }
    }
}

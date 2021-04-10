package com.facebook.errorreporting.nightwatch;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
    private static final String LOG_TAG = "Nightwatch";
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
        private int mmapUpdateMinIntervalMs;
        private boolean monitorResources;
        private int monitorResourcesIntervalMs;
        private boolean saveExitStatus;
        private boolean splitMmap;
        private int tickInfoCount;
        private boolean useFastOrCriticalJniMethodsIfCan;
        private boolean useLssForExec;
        private boolean useMmap;
        private boolean useSetSid;

        public NightwatchConfigs(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, boolean z6, int i3, boolean z7) {
            this.saveExitStatus = z;
            this.useSetSid = z2;
            this.useLssForExec = z3;
            this.useMmap = z4;
            this.monitorResources = z5;
            this.monitorResourcesIntervalMs = i;
            this.tickInfoCount = i2;
            this.splitMmap = z6;
            this.mmapUpdateMinIntervalMs = i3;
            this.useFastOrCriticalJniMethodsIfCan = z7;
        }

        public boolean shouldRunNightwatch() {
            return this.saveExitStatus || this.monitorResources;
        }
    }

    public static void startWatcher(Context context, String str, NightwatchConfigs nightwatchConfigs) {
        Random random = new Random();
        UUID uuid = new UUID(random.nextLong(), random.nextLong());
        startWatcher(new File(context.getDir(WATCHER_DIR, 0), str.replace(':', '_') + UltralightNames.FQN_SEPARATOR + uuid.toString() + ".txt"), nightwatchConfigs);
    }

    public static void startWatcher(Context context, String str, String str2, NightwatchConfigs nightwatchConfigs) {
        startWatcher(new File(context.getDir(WATCHER_DIR, 0), str.replace(':', '_') + UltralightNames.FQN_SEPARATOR + str2 + ".txt"), nightwatchConfigs);
    }

    public static void startWatcher(File file, NightwatchConfigs nightwatchConfigs) {
        okToLoadNightWatch.set(Boolean.TRUE);
        currentLogFile = file;
        NightwatchNative.startWatcher(file, nightwatchConfigs);
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

        private NightwatchDataType(int i) {
            this.dataType = i;
        }

        public int getDataType() {
            return this.dataType;
        }
    }

    private static void reportBatteryCapacityToNightWatch(Context context) {
        int i;
        if (Build.VERSION.SDK_INT >= 21) {
            BatteryManager batteryManager = (BatteryManager) context.getSystemService("batterymanager");
            if (batteryManager != null) {
                i = batteryManager.getIntProperty(4);
            } else {
                return;
            }
        } else {
            Scanner scanner = new Scanner("/sys/class/power_supply/battery/capacity");
            i = scanner.hasNextInt() ? scanner.nextInt() : -1;
        }
        if (i > -1) {
            NightwatchNative.recordDataInNightWatch((long) i, NightwatchDataType.BatteryCapacity.getDataType());
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
                long availableBytes = new StatFs(((File) Assertions.assumeNotNull(ExternalStoragePath.getDirectory(0))).getAbsolutePath()).getAvailableBytes();
                if (availableBytes > 0) {
                    NightwatchNative.recordDataInNightWatch(availableBytes, NightwatchDataType.ExternalStorage.getDataType());
                }
            }
        } catch (IllegalArgumentException unused) {
        }
    }

    private static void reportBatteryTemperatureToNightWatch(Context context) {
        try {
            Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            if (registerReceiver != null) {
                NightwatchNative.recordDataInNightWatch((long) registerReceiver.getIntExtra("temperature", 0), NightwatchDataType.BatteryTemperature.getDataType());
            }
        } catch (SecurityException unused) {
        }
    }

    @TargetApi(18)
    private static void reportTrafficTotalRxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long totalRxBytes = TrafficStats.getTotalRxBytes();
                if (totalRxBytes > 0) {
                    NightwatchNative.recordDataInNightWatch(totalRxBytes, NightwatchDataType.TrafficTotalRx.getDataType());
                }
            } catch (Exception unused) {
            }
        }
    }

    @TargetApi(18)
    private static void reportTrafficTotalTxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long totalTxBytes = TrafficStats.getTotalTxBytes();
                if (totalTxBytes > 0) {
                    NightwatchNative.recordDataInNightWatch(totalTxBytes, NightwatchDataType.TrafficTotalTx.getDataType());
                }
            } catch (Exception unused) {
            }
        }
    }

    @TargetApi(18)
    private static void reportTrafficMobileRxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long mobileRxBytes = TrafficStats.getMobileRxBytes();
                if (mobileRxBytes > 0) {
                    NightwatchNative.recordDataInNightWatch(mobileRxBytes, NightwatchDataType.TrafficMobileRx.getDataType());
                }
            } catch (Exception unused) {
            }
        }
    }

    @TargetApi(18)
    private static void reportTrafficMobileTxToNightWatch() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                long mobileTxBytes = TrafficStats.getMobileTxBytes();
                if (mobileTxBytes > 0) {
                    NightwatchNative.recordDataInNightWatch(mobileTxBytes, NightwatchDataType.TrafficMobileTx.getDataType());
                }
            } catch (Exception unused) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003e, code lost:
        if (r5.getType() == 0) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void reportConnectionTypeToNightWatch(android.content.Context r5) {
        /*
            java.lang.String r0 = "connectivity"
            java.lang.Object r5 = r5.getSystemService(r0)
            android.net.ConnectivityManager r5 = (android.net.ConnectivityManager) r5
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x004d }
            r1 = 23
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 < r1) goto L_0x002b
            if (r5 == 0) goto L_0x0042
            android.net.Network r0 = r5.getActiveNetwork()     // Catch:{ Exception -> 0x004d }
            android.net.NetworkCapabilities r5 = r5.getNetworkCapabilities(r0)     // Catch:{ Exception -> 0x004d }
            if (r5 == 0) goto L_0x0042
            boolean r0 = r5.hasTransport(r4)     // Catch:{ Exception -> 0x004d }
            if (r0 == 0) goto L_0x0024
            goto L_0x0043
        L_0x0024:
            boolean r5 = r5.hasTransport(r3)     // Catch:{ Exception -> 0x004d }
            if (r5 == 0) goto L_0x0042
            goto L_0x0040
        L_0x002b:
            if (r5 == 0) goto L_0x0042
            android.net.NetworkInfo r5 = r5.getActiveNetworkInfo()     // Catch:{ Exception -> 0x004d }
            if (r5 == 0) goto L_0x0042
            int r0 = r5.getType()     // Catch:{ Exception -> 0x004d }
            if (r0 != r4) goto L_0x003a
            goto L_0x0043
        L_0x003a:
            int r5 = r5.getType()     // Catch:{ Exception -> 0x004d }
            if (r5 != 0) goto L_0x0042
        L_0x0040:
            r2 = 1
            goto L_0x0043
        L_0x0042:
            r2 = 0
        L_0x0043:
            long r0 = (long) r2     // Catch:{ Exception -> 0x004d }
            com.facebook.errorreporting.nightwatch.Nightwatch$NightwatchDataType r5 = com.facebook.errorreporting.nightwatch.Nightwatch.NightwatchDataType.ConnectionType     // Catch:{ Exception -> 0x004d }
            int r5 = r5.getDataType()     // Catch:{ Exception -> 0x004d }
            com.facebook.errorreporting.nightwatch.Nightwatch.NightwatchNative.access$100(r0, r5)     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.errorreporting.nightwatch.Nightwatch.reportConnectionTypeToNightWatch(android.content.Context):void");
    }

    public static void reportProcessImportanceToNightwatch(int i) {
        if (running()) {
            NightwatchNative.recordDataInNightWatch((long) i, NightwatchDataType.ProcessImportance.getDataType());
        }
    }

    public static int recordTickInNightWatch(long j, long j2, long j3, long j4) {
        if (!running()) {
            return 1;
        }
        return NightwatchNative.recordTickInNightWatch(j, j2, j3, j4);
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

        private static native int start(String str, String str2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, int i2, boolean z6, int i3, boolean z7, boolean z8);

        private NightwatchNative() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0039  */
        static {
            /*
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 0
                r2 = 26
                if (r0 < r2) goto L_0x0018
                java.lang.String r0 = "dalvik.annotation.optimization.CriticalNative"
                java.lang.Class.forName(r0)     // Catch:{ ClassNotFoundException -> 0x000e }
                r0 = 1
                goto L_0x0019
            L_0x000e:
                r0 = move-exception
                java.lang.String r2 = com.facebook.errorreporting.nightwatch.Nightwatch.access$300()
                java.lang.String r3 = "Critical Native could not be found. Using normal fastjni!"
                com.facebook.debug.log.BLog.d(r2, r0, r3)
            L_0x0018:
                r0 = 0
            L_0x0019:
                com.facebook.errorreporting.nightwatch.Nightwatch.NightwatchNative.CAN_USE_CRITICAL_NATIVE_METHODS = r0
                java.lang.Boolean r0 = java.lang.Boolean.TRUE
                java.lang.Object r0 = com.facebook.infer.annotation.Assertions.assumeNotNull(r0)
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                java.lang.ThreadLocal r2 = com.facebook.errorreporting.nightwatch.Nightwatch.access$400()
                java.lang.Object r2 = r2.get()
                boolean r0 = r0.equals(r2)
                if (r0 == 0) goto L_0x0039
                java.lang.String r0 = "fbnightwatch"
                com.facebook.soloader.nativeloader.NativeLoader.loadLibrary(r0)
                com.facebook.errorreporting.nightwatch.Nightwatch.NightwatchNative.sHasLinkedFastMethods = r1
                return
            L_0x0039:
                java.lang.RuntimeException r0 = new java.lang.RuntimeException
                java.lang.String r1 = "trying to load nightwatch before nightwatch starts!"
                r0.<init>(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.errorreporting.nightwatch.Nightwatch.NightwatchNative.<clinit>():void");
        }

        /* access modifiers changed from: private */
        public static void startWatcher(File file, NightwatchConfigs nightwatchConfigs) {
            try {
                String canonicalPath = file.getCanonicalPath();
                BLog.d(Nightwatch.LOG_TAG, "Watcher file is %s", canonicalPath);
                String libraryPath = NativeLoader.getLibraryPath(Nightwatch.WATCHER_BINARY);
                if (libraryPath == null) {
                    BLog.e(Nightwatch.LOG_TAG, "Could not find watcher binary");
                    return;
                }
                boolean z = nightwatchConfigs.useFastOrCriticalJniMethodsIfCan;
                int start = start(libraryPath, canonicalPath, nightwatchConfigs.saveExitStatus, nightwatchConfigs.useSetSid, nightwatchConfigs.useLssForExec, nightwatchConfigs.useMmap, nightwatchConfigs.monitorResources, nightwatchConfigs.monitorResourcesIntervalMs, nightwatchConfigs.tickInfoCount, nightwatchConfigs.splitMmap, nightwatchConfigs.mmapUpdateMinIntervalMs, z, CAN_USE_CRITICAL_NATIVE_METHODS && z);
                Nightwatch.setIsRunning();
                sHasLinkedFastMethods = z;
                BLog.d(Nightwatch.LOG_TAG, "Status starting watch: %d", Integer.valueOf(start));
            } catch (IOException e) {
                BLog.e(Nightwatch.LOG_TAG, "Error starting watcher", e);
            }
        }

        /* access modifiers changed from: private */
        public static int recordDataInNightWatch(long j, int i) {
            if (shouldUseCriticalNativeMethods()) {
                return NightwatchOreo.recordDataInNightWatch(j, i);
            }
            if (shouldUseFastJniNativeMethods()) {
                return nRecordDataInNightWatch_FAST_JNI(j, i);
            }
            return nRecordDataInNightWatch(j, i);
        }

        /* access modifiers changed from: private */
        public static int recordTickInNightWatch(long j, long j2, long j3, long j4) {
            if (shouldUseCriticalNativeMethods()) {
                return NightwatchOreo.recordTickInNightWatch(j, j2, j3, j4);
            }
            if (shouldUseFastJniNativeMethods()) {
                return nRecordTickInNightWatch_FAST_JNI(j, j2, j3, j4);
            }
            return nRecordTickInNightWatch(j, j2, j3, j4);
        }

        private static boolean shouldUseCriticalNativeMethods() {
            return CAN_USE_CRITICAL_NATIVE_METHODS && sHasLinkedFastMethods;
        }

        private static boolean shouldUseFastJniNativeMethods() {
            return !CAN_USE_CRITICAL_NATIVE_METHODS && sHasLinkedFastMethods;
        }
    }
}

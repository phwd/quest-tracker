package com.facebook.errorreporting.nightwatch;

import android.annotation.SuppressLint;
import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class Nightwatch {
    private static final String LOG_TAG = "Nightwatch";
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
    }

    public static void startWatcher(Context context, String str, NightwatchConfigs nightwatchConfigs) {
        Random random = new Random();
        UUID uuid = new UUID(random.nextLong(), random.nextLong());
        startWatcher(new File(context.getDir("watcher", 0), str.replace(':', '_') + "_" + uuid.toString() + ".txt"), nightwatchConfigs);
    }

    public static void startWatcher(Context context, String str, String str2, NightwatchConfigs nightwatchConfigs) {
        startWatcher(new File(context.getDir("watcher", 0), str.replace(':', '_') + "_" + str2 + ".txt"), nightwatchConfigs);
    }

    public static void startWatcher(File file, NightwatchConfigs nightwatchConfigs) {
        okToLoadNightWatch.set(Boolean.TRUE);
        currentLogFile = file;
        NightwatchNative.startWatcher(file, nightwatchConfigs);
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
                String libraryPath = NativeLoader.getLibraryPath("libwatcher_binary.so");
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
    }
}

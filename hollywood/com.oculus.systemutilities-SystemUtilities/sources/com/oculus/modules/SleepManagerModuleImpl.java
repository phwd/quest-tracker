package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SleepManagerModule;
import com.oculus.os.SettingsManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class SleepManagerModuleImpl extends SleepManagerModule {
    private static final int DEFAULT_OCULUS_AUTOSLEEP_TIME = 15;
    private static final boolean DEFAULT_OCULUS_AUTOWAKE = false;
    private static final String TAG = SleepManagerModuleImpl.class.getSimpleName();
    private static final Set<Integer> validAutoSleepTimesSeconds = new HashSet(Arrays.asList(Integer.valueOf((int) DEFAULT_OCULUS_AUTOSLEEP_TIME), 60, 120, 180, 300, 900, 14400));
    private final SettingsManager mSettingsManager = new SettingsManager();

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SleepManagerModule
    public void setAutoWakeEnabledImpl(boolean autoWakeEnabled, Resolver<Boolean> resolver) {
        boolean success = this.mSettingsManager.setBoolean("autowake", autoWakeEnabled);
        Log.d(TAG, "Set OCULUS_AUTOWAKE to value " + autoWakeEnabled);
        resolver.resolve(Boolean.valueOf(success));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0045, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0046, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.SleepManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getAutoWakeEnabledImpl(com.oculus.modules.codegen.Resolver<java.lang.Boolean> r8) {
        /*
            r7 = this;
            com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.SleepManagerModuleImpl.TAG
            java.lang.String r3 = "getAutoWakeEnabledImpl"
            r1.<init>(r2, r3)
            r3 = 0
            com.oculus.os.SettingsManager r2 = r7.mSettingsManager     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r4 = "autowake"
            r5 = 0
            boolean r0 = r2.getBoolean(r4, r5)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r2 = com.oculus.modules.SleepManagerModuleImpl.TAG     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            r4.<init>()     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r5 = "Got OCULUS_AUTOWAKE value "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            android.util.Log.d(r2, r4)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            r8.resolve(r2)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            if (r1 == 0) goto L_0x0039
            if (r3 == 0) goto L_0x003f
            r1.close()     // Catch:{ Throwable -> 0x003a }
        L_0x0039:
            return
        L_0x003a:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0039
        L_0x003f:
            r1.close()
            goto L_0x0039
        L_0x0043:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0045 }
        L_0x0045:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
        L_0x0049:
            if (r1 == 0) goto L_0x0050
            if (r3 == 0) goto L_0x0056
            r1.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x0050:
            throw r2
        L_0x0051:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x0050
        L_0x0056:
            r1.close()
            goto L_0x0050
        L_0x005a:
            r2 = move-exception
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SleepManagerModuleImpl.getAutoWakeEnabledImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SleepManagerModule
    public void setAutoSleepTimeImpl(double sleepTime, Resolver<Boolean> resolver) {
        if (!validAutoSleepTimesSeconds.contains(Integer.valueOf((int) sleepTime))) {
            Log.e(TAG, "Failed to set OCULUS_AUTOSLEEP_TIME. Invalid value " + sleepTime);
            return;
        }
        boolean success = this.mSettingsManager.setInt("autosleep_time", (int) sleepTime);
        Log.d(TAG, "Set OCULUS_AUTOSLEEP_TIME to value " + sleepTime);
        resolver.resolve(Boolean.valueOf(success));
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
        r4 = r3;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005c, code lost:
        r3 = th;
     */
    @Override // com.oculus.modules.codegen.SleepManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getAutoSleepTimeImpl(com.oculus.modules.codegen.Resolver<java.lang.Double> r9) {
        /*
            r8 = this;
            com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock
            java.lang.String r3 = com.oculus.modules.SleepManagerModuleImpl.TAG
            java.lang.String r4 = "getAutoSleepTimeImpl"
            r2.<init>(r3, r4)
            r4 = 0
            com.oculus.os.SettingsManager r3 = r8.mSettingsManager     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.String r5 = "autosleep_time"
            r6 = 15
            int r3 = r3.getInt(r5, r6)     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            double r0 = (double) r3     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.String r3 = com.oculus.modules.SleepManagerModuleImpl.TAG     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            r5.<init>()     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.String r6 = "Got OCULUS_AUTOSLEEP_TIME value "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.StringBuilder r5 = r5.append(r0)     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.String r5 = r5.toString()     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            android.util.Log.d(r3, r5)     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            java.lang.Double r3 = java.lang.Double.valueOf(r0)     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            r9.resolve(r3)     // Catch:{ Throwable -> 0x0045, all -> 0x005c }
            if (r2 == 0) goto L_0x003b
            if (r4 == 0) goto L_0x0041
            r2.close()     // Catch:{ Throwable -> 0x003c }
        L_0x003b:
            return
        L_0x003c:
            r3 = move-exception
            r4.addSuppressed(r3)
            goto L_0x003b
        L_0x0041:
            r2.close()
            goto L_0x003b
        L_0x0045:
            r3 = move-exception
            throw r3     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
        L_0x004b:
            if (r2 == 0) goto L_0x0052
            if (r4 == 0) goto L_0x0058
            r2.close()     // Catch:{ Throwable -> 0x0053 }
        L_0x0052:
            throw r3
        L_0x0053:
            r5 = move-exception
            r4.addSuppressed(r5)
            goto L_0x0052
        L_0x0058:
            r2.close()
            goto L_0x0052
        L_0x005c:
            r3 = move-exception
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SleepManagerModuleImpl.getAutoSleepTimeImpl(com.oculus.modules.codegen.Resolver):void");
    }
}

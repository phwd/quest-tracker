package com.oculus.modules;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;
import com.oculus.modules.codegen.BatteryManagerModule;
import org.json.JSONObject;

public class BatteryManagerModuleImpl extends BatteryManagerModule {
    private static final String TAG = BatteryManagerModule.class.getSimpleName();
    private JSONObject batteryDataObject = null;
    private int batteryLevel = 0;
    private int batteryStatus = 0;
    private final PowerManager mPowerManager;
    private boolean powerSaveMode;

    public BatteryManagerModuleImpl(Context context) {
        super(context);
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.powerSaveMode = this.mPowerManager.isPowerSaveMode();
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BATTERY_CHANGED");
        filter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
        return filter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00c1, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00c2, code lost:
        r12 = r11;
        r11 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ea, code lost:
        r11 = th;
     */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processIntent(android.content.Intent r18, boolean r19) {
        /*
        // Method dump skipped, instructions count: 274
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.BatteryManagerModuleImpl.processIntent(android.content.Intent, boolean):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.BatteryManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLowPowerModeImpl(boolean r7, com.oculus.modules.codegen.Resolver<java.lang.Boolean> r8) {
        /*
            r6 = this;
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.BatteryManagerModuleImpl.TAG
            java.lang.String r3 = "setLowPowerModeImpl"
            r0.<init>(r2, r3)
            r3 = 0
            com.oculus.os.VrApiManager r2 = com.oculus.os.VrApiManager.getInstance()     // Catch:{ Throwable -> 0x002e, all -> 0x004e }
            boolean r1 = r2.setPowerSaveMode(r7)     // Catch:{ Throwable -> 0x002e, all -> 0x004e }
            if (r1 == 0) goto L_0x0023
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r7)     // Catch:{ Throwable -> 0x002e, all -> 0x004e }
            r8.resolve(r2)     // Catch:{ Throwable -> 0x002e, all -> 0x004e }
        L_0x001b:
            if (r0 == 0) goto L_0x0022
            if (r3 == 0) goto L_0x0041
            r0.close()     // Catch:{ Throwable -> 0x003c }
        L_0x0022:
            return
        L_0x0023:
            java.lang.Exception r2 = new java.lang.Exception
            java.lang.String r4 = "Failed to set Low Power Mode"
            r2.<init>(r4)
            r8.reject(r2)
            goto L_0x001b
        L_0x002e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0030 }
        L_0x0030:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L_0x0034:
            if (r0 == 0) goto L_0x003b
            if (r3 == 0) goto L_0x004a
            r0.close()     // Catch:{ Throwable -> 0x0045 }
        L_0x003b:
            throw r2
        L_0x003c:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0022
        L_0x0041:
            r0.close()
            goto L_0x0022
        L_0x0045:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x003b
        L_0x004a:
            r0.close()
            goto L_0x003b
        L_0x004e:
            r2 = move-exception
            goto L_0x0034
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.BatteryManagerModuleImpl.setLowPowerModeImpl(boolean, com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.BatteryManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getLowPowerModeAsyncImpl(com.oculus.modules.codegen.Resolver<com.oculus.modules.codegen.BatteryManagerModule.GetLowPowerModeAsyncResult> r7) {
        /*
            r6 = this;
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.BatteryManagerModuleImpl.TAG
            java.lang.String r3 = "getLowPowerModeAsyncImpl"
            r0.<init>(r2, r3)
            r3 = 0
            com.oculus.modules.codegen.BatteryManagerModule$GetLowPowerModeAsyncResult r1 = new com.oculus.modules.codegen.BatteryManagerModule$GetLowPowerModeAsyncResult     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            r1.<init>()     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            android.os.PowerManager r2 = r6.mPowerManager     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            boolean r2 = r2.isPowerSaveMode()     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            r1.isBatterySaver = r2     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            r7.resolve(r1)     // Catch:{ Throwable -> 0x002b, all -> 0x0042 }
            if (r0 == 0) goto L_0x0021
            if (r3 == 0) goto L_0x0027
            r0.close()     // Catch:{ Throwable -> 0x0022 }
        L_0x0021:
            return
        L_0x0022:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0021
        L_0x0027:
            r0.close()
            goto L_0x0021
        L_0x002b:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x002d }
        L_0x002d:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L_0x0031:
            if (r0 == 0) goto L_0x0038
            if (r3 == 0) goto L_0x003e
            r0.close()     // Catch:{ Throwable -> 0x0039 }
        L_0x0038:
            throw r2
        L_0x0039:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x0038
        L_0x003e:
            r0.close()
            goto L_0x0038
        L_0x0042:
            r2 = move-exception
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.BatteryManagerModuleImpl.getLowPowerModeAsyncImpl(com.oculus.modules.codegen.Resolver):void");
    }
}

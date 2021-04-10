package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.SwapSystemButtonHandednessModule;
import com.oculus.os.SettingsManager;

public class SwapSystemButtonHandednessModuleImpl extends SwapSystemButtonHandednessModule {
    private static final boolean SWAP_SYSTEM_BUTTON_HANDEDNESS_DEFAULT_VALUE = false;
    private static final String SWAP_SYSTEM_BUTTON_HANDEDNESS_SETTING_NAME = "swap_system_button_handedness";
    private static final String TAG = SwapSystemButtonHandednessModuleImpl.class.getSimpleName();
    private final SettingsManager mSettingsManager = new SettingsManager();

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0042, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0043, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0057, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.SwapSystemButtonHandednessModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getSwapSystemButtonHandednessImpl(com.oculus.modules.codegen.Resolver<java.lang.Boolean> r10) {
        /*
            r9 = this;
            com.oculus.panellib.SystraceBlock r1 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.SwapSystemButtonHandednessModuleImpl.TAG
            java.lang.String r3 = "getSwapSystemButtonHandednessImpl"
            r1.<init>(r2, r3)
            r3 = 0
            com.oculus.os.SettingsManager r2 = r9.mSettingsManager     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            java.lang.String r4 = "swap_system_button_handedness"
            r5 = 0
            boolean r0 = r2.getBoolean(r4, r5)     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            java.lang.String r2 = com.oculus.modules.SwapSystemButtonHandednessModuleImpl.TAG     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            java.lang.String r4 = "Got swap system button handedness: %b"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            r6 = 0
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r0)     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            r5[r6] = r7     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            android.util.Log.d(r2, r4)     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r0)     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            r10.resolve(r2)     // Catch:{ Throwable -> 0x0040, all -> 0x0057 }
            if (r1 == 0) goto L_0x0036
            if (r3 == 0) goto L_0x003c
            r1.close()     // Catch:{ Throwable -> 0x0037 }
        L_0x0036:
            return
        L_0x0037:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0036
        L_0x003c:
            r1.close()
            goto L_0x0036
        L_0x0040:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0042 }
        L_0x0042:
            r3 = move-exception
            r8 = r3
            r3 = r2
            r2 = r8
        L_0x0046:
            if (r1 == 0) goto L_0x004d
            if (r3 == 0) goto L_0x0053
            r1.close()     // Catch:{ Throwable -> 0x004e }
        L_0x004d:
            throw r2
        L_0x004e:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x004d
        L_0x0053:
            r1.close()
            goto L_0x004d
        L_0x0057:
            r2 = move-exception
            goto L_0x0046
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.SwapSystemButtonHandednessModuleImpl.getSwapSystemButtonHandednessImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.SwapSystemButtonHandednessModule
    public void setSwapSystemButtonHandednessImpl(boolean enabled, Resolver<Boolean> resolver) {
        if (this.mSettingsManager.setBoolean(SWAP_SYSTEM_BUTTON_HANDEDNESS_SETTING_NAME, enabled)) {
            Log.d(TAG, String.format("Set swap system button handedness: %b", Boolean.valueOf(enabled)));
            resolver.resolve(true);
            return;
        }
        Log.e(TAG, "Failed to set swap system button handedness. Database transaction was unsuccessful.");
        resolver.reject(new Exception("Failed to set swap system button handedness"));
    }
}

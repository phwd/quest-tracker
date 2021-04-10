package com.oculus.modules;

import android.util.Log;
import com.oculus.modules.codegen.MicrophoneManagerModule;
import com.oculus.modules.codegen.Resolver;
import com.oculus.os.SettingsManager;

public class MicrophoneManagerModuleImpl extends MicrophoneManagerModule {
    private static final boolean DEFAULT_MIC_MUTED_STATUS = false;
    private static final String TAG = MicrophoneManagerModuleImpl.class.getSimpleName();
    private final SettingsManager mSettingsManager = new SettingsManager();

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
    @Override // com.oculus.modules.codegen.MicrophoneManagerModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void getMicrophoneStatusImpl(com.oculus.modules.codegen.Resolver<java.lang.Boolean> r8) {
        /*
            r7 = this;
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.MicrophoneManagerModuleImpl.TAG
            java.lang.String r3 = "getMicrophoneStatusImpl"
            r0.<init>(r2, r3)
            r3 = 0
            com.oculus.os.SettingsManager r2 = r7.mSettingsManager     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r4 = "mic_muted"
            r5 = 0
            boolean r1 = r2.getBoolean(r4, r5)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r2 = com.oculus.modules.MicrophoneManagerModuleImpl.TAG     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            r4.<init>()     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r5 = "Got microphone status from SettingsManager. Muted = "
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.StringBuilder r4 = r4.append(r1)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.String r4 = r4.toString()     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            android.util.Log.d(r2, r4)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            r8.resolve(r2)     // Catch:{ Throwable -> 0x0043, all -> 0x005a }
            if (r0 == 0) goto L_0x0039
            if (r3 == 0) goto L_0x003f
            r0.close()     // Catch:{ Throwable -> 0x003a }
        L_0x0039:
            return
        L_0x003a:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0039
        L_0x003f:
            r0.close()
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
            if (r0 == 0) goto L_0x0050
            if (r3 == 0) goto L_0x0056
            r0.close()     // Catch:{ Throwable -> 0x0051 }
        L_0x0050:
            throw r2
        L_0x0051:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x0050
        L_0x0056:
            r0.close()
            goto L_0x0050
        L_0x005a:
            r2 = move-exception
            goto L_0x0049
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.MicrophoneManagerModuleImpl.getMicrophoneStatusImpl(com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.MicrophoneManagerModule
    public void setMicrophoneStatusImpl(boolean muted, Resolver<Boolean> resolver) {
        if (!this.mSettingsManager.setBoolean("mic_muted", muted)) {
            Log.e(TAG, "Failed to set microphone status in SettingsManager");
            resolver.reject(new Exception("Failed to set microphone status in SettingsManager"));
        }
        Log.d(TAG, "Set microphone status in SettingsManager. Muted = " + muted);
        resolver.resolve(true);
    }
}

package com.facebook.mobileconfig;

import X.KV;
import android.content.res.AssetManager;
import com.facebook.common.jniexecutors.AndroidAsyncExecutorFactory;
import com.facebook.jni.HybridData;
import com.facebook.tigon.iface.TigonServiceHolder;
import com.facebook.xanalytics.XAnalyticsHolder;
import java.util.Map;

public class MobileConfigDependenciesInFBApps {
    public final HybridData mHybridData;

    public MobileConfigDependenciesInFBApps(AndroidAsyncExecutorFactory androidAsyncExecutorFactory, TigonServiceHolder tigonServiceHolder, boolean z, XAnalyticsHolder xAnalyticsHolder) {
        this.mHybridData = initHybrid(null, tigonServiceHolder, false, xAnalyticsHolder);
    }

    private native MobileConfigManagerHolderImpl createManagerInternal(String str, String str2, String str3, String str4, String str5, AssetManager assetManager, boolean z, MobileConfigManagerParamsHolder mobileConfigManagerParamsHolder, Map map);

    public static native HybridData initHybrid(AndroidAsyncExecutorFactory androidAsyncExecutorFactory, TigonServiceHolder tigonServiceHolder, boolean z, XAnalyticsHolder xAnalyticsHolder);

    public static native void setNetworkService(MobileConfigManagerHolderImpl mobileConfigManagerHolderImpl, TigonServiceHolder tigonServiceHolder, boolean z);

    static {
        KV.A01("mobileconfig-jni");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x002a, code lost:
        if (r14.isEmpty() != false) goto L_0x002c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.mobileconfig.MobileConfigManagerHolderImpl createManager(java.io.File r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, android.content.res.AssetManager r16, boolean r17, com.facebook.mobileconfig.MobileConfigManagerParamsHolder r18, java.util.Map r19) {
        /*
            r10 = this;
            java.lang.String r5 = ""
            r7 = 0
            java.lang.String r1 = r11.getPath()
            r8 = r18
            r2 = r12
            r9 = r19
            r0 = r10
            r3 = r13
            r4 = r14
            r6 = r16
            com.facebook.mobileconfig.MobileConfigManagerHolderImpl r2 = r0.createManagerInternal(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            if (r2 == 0) goto L_0x002f
            boolean r0 = r2.isValid()
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = r11.getAbsolutePath()
            r2.mDataDirPath = r0
            if (r14 == 0) goto L_0x002c
            boolean r1 = r14.isEmpty()
            r0 = 1
            if (r1 == 0) goto L_0x002d
        L_0x002c:
            r0 = 0
        L_0x002d:
            r2.mHasSessionId = r0
        L_0x002f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.mobileconfig.MobileConfigDependenciesInFBApps.createManager(java.io.File, java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.content.res.AssetManager, boolean, com.facebook.mobileconfig.MobileConfigManagerParamsHolder, java.util.Map):com.facebook.mobileconfig.MobileConfigManagerHolderImpl");
    }
}

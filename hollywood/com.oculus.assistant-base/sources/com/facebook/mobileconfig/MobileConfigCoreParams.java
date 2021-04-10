package com.facebook.mobileconfig;

import X.KV;
import com.facebook.jni.HybridData;

public class MobileConfigCoreParams {
    public final HybridData mHybridData = initHybrid();

    public static native HybridData initHybrid();

    public native boolean isMCListEnabled();

    public native void setMCListEnabled(boolean z);

    public native void setSkipBufferVerification(boolean z);

    public native boolean skipBufferVerification();

    static {
        KV.A01("mobileconfig-jni");
    }
}

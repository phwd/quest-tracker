package com.facebook.mobileconfig;

import X.C0431hn;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigCoreParams {
    @DoNotStrip
    public final HybridData mHybridData = initHybrid();

    public static native HybridData initHybrid();

    public native boolean isMCListEnabled();

    public native void setMCListEnabled(boolean z);

    public native void setSkipBufferVerification(boolean z);

    public native boolean skipBufferVerification();

    static {
        C0431hn.A00("mobileconfig-jni");
    }
}

package com.facebook.mobileconfig;

import X.AnonymousClass0lD;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigFetcherHandler {
    @DoNotStrip
    public final HybridData mHybridData;

    @DoNotStrip
    public native void onCompletion(boolean z, String str);

    static {
        AnonymousClass0lD.A01("mobileconfig-jni");
    }

    @DoNotStrip
    public MobileConfigFetcherHandler(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

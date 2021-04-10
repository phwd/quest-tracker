package com.facebook.mobileconfig;

import X.KV;
import com.facebook.jni.HybridData;

public class MobileConfigFetcherHandler {
    public final HybridData mHybridData;

    public native void onCompletion(boolean z, String str);

    static {
        KV.A01("mobileconfig-jni");
    }

    public MobileConfigFetcherHandler(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

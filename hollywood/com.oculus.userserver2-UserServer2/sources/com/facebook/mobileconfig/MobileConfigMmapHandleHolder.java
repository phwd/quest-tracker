package com.facebook.mobileconfig;

import X.Q9;
import X.g6;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

public class MobileConfigMmapHandleHolder extends Q9 {
    @DoNotStrip
    public final HybridData mHybridData;

    public native String getFilename();

    static {
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }

    public MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

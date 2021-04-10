package com.facebook.mobileconfig;

import X.AbstractC01180Sh;
import X.AnonymousClass0lD;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

public class MobileConfigMmapHandleHolder extends AbstractC01180Sh {
    @DoNotStrip
    public final HybridData mHybridData;

    public native String getFilename();

    static {
        AnonymousClass0lD.A01("mobileconfig-jni");
    }

    public MobileConfigMmapHandleHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

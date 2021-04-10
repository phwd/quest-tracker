package com.facebook.jni;

import X.AnonymousClass0lD;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class CpuCapabilitiesJni {
    @DoNotStrip
    public static native boolean nativeDeviceSupportsNeon();

    @DoNotStrip
    public static native boolean nativeDeviceSupportsVFPFP16();

    @DoNotStrip
    public static native boolean nativeDeviceSupportsX86();

    static {
        AnonymousClass0lD.A01("fb");
    }
}

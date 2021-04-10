package com.facebook.jni;

import X.C03250cX;
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
        C03250cX.A01("fb");
    }
}

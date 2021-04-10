package com.facebook.jni;

import X.C05400jG;
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
        C05400jG.A00("fb");
    }
}

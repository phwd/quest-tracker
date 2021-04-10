package com.facebook.jni;

import X.g6;
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
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}

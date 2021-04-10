package com.facebook.jni;

import X.C0431hn;
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
        C0431hn.A00("fb");
    }
}

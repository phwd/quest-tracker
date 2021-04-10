package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigCrashReportUtils {
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    public native void addCanaryData(String str, String str2);

    public native void clear();

    public native long count();

    public native Map<String, String> getAllCanaryData();

    public native Map<String, Long> getAllLastFetchTimestamps();

    static {
        NativeLoader.loadLibrary("mobileconfig-jni");
    }

    private MobileConfigCrashReportUtils() {
    }
}

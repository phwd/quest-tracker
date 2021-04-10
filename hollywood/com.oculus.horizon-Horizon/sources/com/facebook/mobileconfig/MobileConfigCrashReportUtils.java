package com.facebook.mobileconfig;

import X.C03250cX;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Map;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigCrashReportUtils {
    @DoNotStrip
    public final HybridData mHybridData = initHybrid();

    public static native HybridData initHybrid();

    public native void addCanaryData(String str, String str2);

    public native void clear();

    public native long count();

    public native Map<String, String> getAllCanaryData();

    public native Map<String, Long> getAllLastFetchTimestamps();

    static {
        C03250cX.A01("mobileconfig-jni");
    }
}

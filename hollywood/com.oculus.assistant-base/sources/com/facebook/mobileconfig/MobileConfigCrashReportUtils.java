package com.facebook.mobileconfig;

import X.KV;
import com.facebook.jni.HybridData;
import java.util.Map;

public class MobileConfigCrashReportUtils {
    public final HybridData mHybridData = initHybrid();

    public static native HybridData initHybrid();

    public native void addCanaryData(String str, String str2);

    public native void clear();

    public native long count();

    public native Map getAllCanaryData();

    public native Map getAllLastFetchTimestamps();

    static {
        KV.A01("mobileconfig-jni");
    }
}

package com.facebook.mobileconfig;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.soloader.nativeloader.NativeLoader;
import java.util.Map;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class MobileConfigCrashReportUtils {
    @GuardedBy("MobileConfigCrashReportUtils.class")
    @Nullable
    private static MobileConfigCrashReportUtils sInstance;
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

    public static synchronized MobileConfigCrashReportUtils getInstance() {
        MobileConfigCrashReportUtils mobileConfigCrashReportUtils;
        synchronized (MobileConfigCrashReportUtils.class) {
            if (sInstance == null) {
                sInstance = new MobileConfigCrashReportUtils();
            }
            mobileConfigCrashReportUtils = sInstance;
        }
        return mobileConfigCrashReportUtils;
    }

    private MobileConfigCrashReportUtils() {
    }

    public String getSerializedCanaryData() {
        Map<String, String> allCanaryData = getAllCanaryData();
        StringBuilder sb = new StringBuilder((allCanaryData.size() * 100) + 50);
        sb.append("[");
        boolean z = true;
        for (Map.Entry<String, String> entry : allCanaryData.entrySet()) {
            if (!z) {
                sb.append(",");
            }
            z = false;
            sb.append("\"");
            sb.append(entry.getKey());
            sb.append("\"");
        }
        sb.append("]");
        return sb.toString();
    }

    public String getSerializedLastFetchTimestampData() {
        Map<String, Long> allLastFetchTimestamps = getAllLastFetchTimestamps();
        StringBuilder sb = new StringBuilder((allLastFetchTimestamps.size() * 50) + 50);
        sb.append("{");
        boolean z = true;
        for (Map.Entry<String, Long> entry : allLastFetchTimestamps.entrySet()) {
            if (!z) {
                sb.append(",");
            }
            z = false;
            sb.append("\"");
            sb.append(entry.getKey());
            sb.append("\":");
            sb.append(entry.getValue().toString());
        }
        sb.append("}");
        return sb.toString();
    }
}

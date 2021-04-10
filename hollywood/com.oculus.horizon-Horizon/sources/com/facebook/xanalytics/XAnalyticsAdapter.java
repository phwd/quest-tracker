package com.facebook.xanalytics;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface XAnalyticsAdapter {
    @DoNotStrip
    void cleanup();

    @DoNotStrip
    void flush();

    @DoNotStrip
    String getStructureSamplingConfig(String str);

    @DoNotStrip
    void logCounter(String str, double d);

    @DoNotStrip
    void logCounter(String str, double d, String str2);

    @DoNotStrip
    void logEvent(String str, String str2, String str3, boolean z, double d);

    @DoNotStrip
    void logEventBypassSampling(String str, String str2);

    @DoNotStrip
    boolean shouldLog(String str);
}

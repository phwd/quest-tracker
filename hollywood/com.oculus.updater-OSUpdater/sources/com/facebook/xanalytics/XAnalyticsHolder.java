package com.facebook.xanalytics;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public abstract class XAnalyticsHolder {
    @DoNotStrip
    protected final HybridData mHybridData;

    @DoNotStrip
    public abstract void cleanup();

    @DoNotStrip
    public abstract void flush();

    @DoNotStrip
    public abstract void logCounter(String str, long j);

    @DoNotStrip
    public abstract void logCounter(String str, long j, String str2);

    @DoNotStrip
    public abstract void logEvent(String str, String str2);

    @DoNotStrip
    public abstract void logEvent(String str, String str2, String str3);

    @DoNotStrip
    public abstract void logRealtimeEvent(String str, String str2);

    @DoNotStrip
    public abstract void logRealtimeEvent(String str, String str2, String str3);

    protected XAnalyticsHolder(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

package com.facebook.xanalytics;

import X.C0431hn;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class XAnalyticsAdapterHolder extends XAnalyticsHolder {
    public final XAnalyticsAdapter mAdapter;

    @DoNotStrip
    public static native HybridData initHybrid(XAnalyticsAdapter xAnalyticsAdapter);

    static {
        C0431hn.A00("xanalyticsadapter-jni");
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void cleanup() {
        this.mAdapter.cleanup();
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void flush() {
        this.mAdapter.flush();
    }

    @DoNotStrip
    public String getStructureSamplingConfig(String str) {
        return this.mAdapter.getStructureSamplingConfig(str);
    }

    public XAnalyticsAdapterHolder(XAnalyticsAdapter xAnalyticsAdapter) {
        super(initHybrid(xAnalyticsAdapter));
        this.mAdapter = xAnalyticsAdapter;
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void logCounter(String str, long j) {
        this.mAdapter.logCounter(str, (double) j, "counters");
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void logCounter(String str, long j, String str2) {
        this.mAdapter.logCounter(str, (double) j, str2);
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void logEvent(String str, String str2) {
        logEvent(str, str2, null);
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void logEvent(String str, String str2, String str3) {
        this.mAdapter.logEvent(str, str2, str3, false, -1.0d);
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void logRealtimeEvent(String str, String str2) {
        this.mAdapter.logEvent(str, str2, null, true, -1.0d);
    }

    @Override // com.facebook.xanalytics.XAnalyticsHolder
    @DoNotStrip
    public void logRealtimeEvent(String str, String str2, String str3) {
        this.mAdapter.logEvent(str, str2, str3, true, -1.0d);
    }
}

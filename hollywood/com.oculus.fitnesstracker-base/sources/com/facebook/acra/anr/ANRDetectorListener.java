package com.facebook.acra.anr;

public interface ANRDetectorListener {
    String getBlackBoxTraceId();

    String getLongStallTraceId();
}

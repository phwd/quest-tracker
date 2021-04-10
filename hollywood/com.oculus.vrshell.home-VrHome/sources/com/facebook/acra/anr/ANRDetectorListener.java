package com.facebook.acra.anr;

import javax.annotation.Nullable;

public interface ANRDetectorListener {
    @Nullable
    String getBlackBoxTraceId();

    @Nullable
    String getLongStallTraceId();

    void onEndANRDataCapture();

    void onStartANRDataCapture();
}

package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonSamplingPolicy {
    @DoNotStrip
    public int cellTowerInfoWeight;
    @DoNotStrip
    public int certDataWeight;
    @DoNotStrip
    public boolean enableEndToEndTracingForTa;
    @DoNotStrip
    public int flowTimeWeight;
    @DoNotStrip
    public int httpMeasurementWeight;
    @DoNotStrip
    public boolean printTraceEvents;
    @DoNotStrip
    public int rmdWeight;
    @DoNotStrip
    public boolean triggerMobileHttpRequestLoggingForTa;
}

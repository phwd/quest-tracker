package com.facebook.tigon.iface;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class TigonSamplingConfigInfo {
    public static final int CELL_TOWER = 8;
    public static final int CERTIFICATE_VERIFICATION = 4;
    public static final int FLOW_TIME_DATA = 2;
    public static final int HTTP_FLOW_STATS = 1;
    public static final int HTTP_MEASUREMENT = 64;
    public static final int HTTP_REQUEST_PROPERTIES_DATA = 32;
    public static final int PRINT_TRACE_EVENTS = 16;
    public static final int TRANSIENT_ANALYZER_DATA = 128;
    private final int mCellTowerInfoWeight;
    private final int mFlowTimeWeight;
    private final int mHttpMeasurementWeight;
    private final int mSamplingMask;

    public TigonSamplingConfigInfo(int i, int i2, int i3, int i4) {
        this.mSamplingMask = i;
        this.mFlowTimeWeight = i2;
        this.mCellTowerInfoWeight = i3;
        this.mHttpMeasurementWeight = i4;
    }

    public int samplingMask() {
        return this.mSamplingMask;
    }

    public int flowTimeWeight() {
        return this.mFlowTimeWeight;
    }

    public int cellTowerInfoWeight() {
        return this.mCellTowerInfoWeight;
    }

    public int httpMeasurementWeight() {
        return this.mHttpMeasurementWeight;
    }
}

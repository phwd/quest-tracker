package com.facebook.wifiscan;

import com.facebook.annotations.OkToExtend;
import com.facebook.infer.annotation.Nullsafe;

@OkToExtend
@Nullsafe(Nullsafe.Mode.LOCAL)
public class WifiScanConfig {
    public static final boolean DEFAULT_CAN_RUN_IN_BACKGROUND = false;
    public static final long DEFAULT_MAXIMUM_APS_PER_SCAN = 50;
    public static final long DEFAULT_MAX_AGE_FROM_MAX_WIFI_TIMESTAMP_MS = -1;
    public static final long DEFAULT_MINIMIM_REQUIRED_RSSI = -85;
    public static final long DEFAULT_MINIMUM_APS_PER_SCAN = 10;
    public static final long DEFAULT_TIMESTAMP_FIX_WINDOW_MS = 600000;
    public static final long DEFAULT_WIFI_SCAN_FUTURE_MAX_AGE_MS = -1;
    public static final long DEFAULT_WIFI_SCAN_MAX_AGE_MS = 1800000;
    protected final boolean mCanRunInBackground;
    protected final boolean mIsWifiScanEnabled;
    protected final long mMaxAgeFromMaxWifiTimestampMs;
    protected final int mMaximumResultsPerScan;
    protected final int mMinimumRequiredRssi;
    protected final int mMinimumResultsPerScan;
    protected final long mTimestampCorrectionWindowMs;
    protected final long mWifiScanFutureMaxAgeMs;
    protected final long mWifiScanMaxAgeMs;

    public WifiScanConfig(boolean z, long j, long j2, long j3, long j4, long j5, long j6, long j7, boolean z2) {
        this.mIsWifiScanEnabled = z;
        this.mTimestampCorrectionWindowMs = j;
        this.mWifiScanMaxAgeMs = j2;
        this.mWifiScanFutureMaxAgeMs = j3;
        this.mMaxAgeFromMaxWifiTimestampMs = j4;
        this.mMinimumRequiredRssi = (int) j5;
        this.mMinimumResultsPerScan = (int) j6;
        this.mMaximumResultsPerScan = (int) j7;
        this.mCanRunInBackground = z2;
    }

    @Deprecated
    public WifiScanConfig(boolean z, long j, long j2, long j3, long j4, long j5, long j6, long j7) {
        this.mIsWifiScanEnabled = z;
        this.mTimestampCorrectionWindowMs = j;
        this.mWifiScanMaxAgeMs = j2;
        this.mWifiScanFutureMaxAgeMs = j3;
        this.mMaxAgeFromMaxWifiTimestampMs = j4;
        this.mMinimumRequiredRssi = (int) j5;
        this.mMinimumResultsPerScan = (int) j6;
        this.mMaximumResultsPerScan = (int) j7;
        this.mCanRunInBackground = false;
    }

    public static WifiScanConfig getDefault() {
        return new WifiScanConfig(true, DEFAULT_TIMESTAMP_FIX_WINDOW_MS, DEFAULT_WIFI_SCAN_MAX_AGE_MS, -1, -1, -85, 10, 50, false);
    }

    public static WifiScanConfig getDefaultWithBackground() {
        return new WifiScanConfig(true, DEFAULT_TIMESTAMP_FIX_WINDOW_MS, DEFAULT_WIFI_SCAN_MAX_AGE_MS, -1, -1, -85, 10, 50, true);
    }

    public boolean isWifiScanEnabled() {
        return this.mIsWifiScanEnabled;
    }

    public long getTimestampCorrectionWindowMs() {
        return this.mTimestampCorrectionWindowMs;
    }

    public long getWifiScanMaxAgeMs() {
        return this.mWifiScanMaxAgeMs;
    }

    public long getWifiScanFutureMaxAgeMs() {
        return this.mWifiScanFutureMaxAgeMs;
    }

    public long getMaxAgeFromMaxWifiTimestampMs() {
        return this.mMaxAgeFromMaxWifiTimestampMs;
    }

    public int getMinimumRequiredRssi() {
        return this.mMinimumRequiredRssi;
    }

    public int getMinimumResultsPerScan() {
        return this.mMinimumResultsPerScan;
    }

    public int getMaximumResultsPerScan() {
        return this.mMaximumResultsPerScan;
    }

    public boolean getCanRunInBackground() {
        return this.mCanRunInBackground;
    }
}

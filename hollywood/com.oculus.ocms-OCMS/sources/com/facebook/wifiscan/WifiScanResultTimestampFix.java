package com.facebook.wifiscan;

import android.annotation.TargetApi;
import android.net.wifi.ScanResult;
import android.os.Build;
import com.facebook.common.time.Clock;
import com.facebook.common.time.MonotonicClock;
import com.facebook.infer.annotation.Nullsafe;
import java.util.List;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class WifiScanResultTimestampFix {
    private final MonotonicClock mSinceBootClock;
    private final long mTimestampCorrectionWindowMs;
    private final Clock mWallClock;

    public WifiScanResultTimestampFix(Clock clock, MonotonicClock monotonicClock, long j) {
        this.mWallClock = clock;
        this.mSinceBootClock = monotonicClock;
        this.mTimestampCorrectionWindowMs = j;
    }

    public void fixTimestamp(ScanResult scanResult) {
        fixTimestamp(scanResult, this.mTimestampCorrectionWindowMs);
    }

    @TargetApi(17)
    public void fixTimestamp(ScanResult scanResult, long j) {
        if (scanResult != null && j > 0 && Build.VERSION.SDK_INT >= 17) {
            long now = this.mWallClock.now() - scanResult.timestamp;
            if (Math.abs(now) <= j) {
                scanResult.timestamp = (this.mSinceBootClock.now() - now) * 1000;
            }
        }
    }

    public void fixTimestamps(List<ScanResult> list) {
        fixTimestamps(list, this.mTimestampCorrectionWindowMs);
    }

    public void fixTimestamps(List<ScanResult> list, long j) {
        if (list != null) {
            for (ScanResult scanResult : list) {
                fixTimestamp(scanResult, j);
            }
        }
    }
}

package com.facebook.wifiscan;

import android.annotation.TargetApi;
import android.net.wifi.ScanResult;
import android.os.Build;
import com.facebook.common.time.TimeConversions;
import com.facebook.infer.annotation.Nullsafe;
import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class ScanResultAgeUtil {
    public static final long SKIP_FUTURE_AGE_LIMIT_CHECK = -1;
    public static final long SKIP_NEWEST_TIMESTAMP_LIMIT_CHECK = -1;

    @TargetApi(17)
    public static long ageMs(ScanResult scanResult, long j) {
        Preconditions.checkState(Build.VERSION.SDK_INT >= 17);
        return j - TimeConversions.microsecondsToMillisecondsRounding(scanResult.timestamp);
    }

    @TargetApi(17)
    public static long ageMs(WifiScanResult wifiScanResult, long j) {
        Preconditions.checkState(Build.VERSION.SDK_INT >= 17);
        return j - wifiScanResult.timestampMs;
    }

    public static long getMaxScanTimestamp(List<WifiScanResult> list) {
        if (Build.VERSION.SDK_INT < 17 || list == null || list.isEmpty()) {
            return -1;
        }
        long j = Long.MIN_VALUE;
        for (WifiScanResult wifiScanResult : list) {
            if (wifiScanResult.timestampMs > j) {
                j = wifiScanResult.timestampMs;
            }
        }
        return j;
    }

    public static long getMaxScanTimestampForOs(@Nullable List<ScanResult> list) {
        if (Build.VERSION.SDK_INT < 17 || list == null || list.isEmpty()) {
            return -1;
        }
        long j = Long.MIN_VALUE;
        for (ScanResult scanResult : list) {
            if (scanResult.timestamp > j) {
                j = scanResult.timestamp;
            }
        }
        return j;
    }

    @Nullable
    static List<ScanResult> getResultsMatchingAgeLimit(@Nullable List<ScanResult> list, long j, long j2) {
        return getResultsMatchingAgeLimit(list, j, -1, -1, j2);
    }

    @Nullable
    static List<ScanResult> getResultsMatchingAgeLimit(@Nullable List<ScanResult> list, long j, long j2, long j3, long j4) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        long maxScanTimestamp = getMaxScanTimestamp(list, j4, j2);
        for (ScanResult scanResult : list) {
            long ageMs = ageMs(scanResult, j4);
            if (ageMs <= j && isAgeLimitBeforeFutureAgeLimit(ageMs, j2) && isTimestampInRangeOfNewestTimestamp(scanResult, maxScanTimestamp, j3)) {
                arrayList.add(scanResult);
            }
        }
        return arrayList;
    }

    private static boolean isAgeLimitBeforeFutureAgeLimit(long j, long j2) {
        return j2 == -1 || j > 0 || Math.abs(j) <= j2;
    }

    private static boolean isTimestampInRangeOfNewestTimestamp(ScanResult scanResult, long j, long j2) {
        long microsecondsToMillisecondsRounding = j != Long.MIN_VALUE ? TimeConversions.microsecondsToMillisecondsRounding(j) : Long.MIN_VALUE;
        return j2 == -1 || microsecondsToMillisecondsRounding == Long.MIN_VALUE || ageMs(scanResult, microsecondsToMillisecondsRounding) <= j2;
    }

    @TargetApi(17)
    private static long getMaxScanTimestamp(List<ScanResult> list, long j, long j2) {
        Preconditions.checkState(Build.VERSION.SDK_INT >= 17);
        long j3 = Long.MIN_VALUE;
        for (ScanResult scanResult : list) {
            if (isAgeLimitBeforeFutureAgeLimit(ageMs(scanResult, j), j2)) {
                j3 = Math.max(scanResult.timestamp, j3);
            }
        }
        return j3;
    }

    private ScanResultAgeUtil() {
    }
}

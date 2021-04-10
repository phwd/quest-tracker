package com.facebook.location.battery;

public interface LocationBatteryMetricsCollector {
    void recordCoarseLocationAccess(String str, long j);

    void recordFineLocationAccess(String str, long j);

    void recordMediumLocationAccess(String str, long j);

    void recordWifiScan();
}

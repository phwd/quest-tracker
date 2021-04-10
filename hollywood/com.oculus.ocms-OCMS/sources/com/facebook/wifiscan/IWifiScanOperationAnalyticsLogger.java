package com.facebook.wifiscan;

import javax.annotation.Nullable;

public interface IWifiScanOperationAnalyticsLogger {
    void reportActiveScanTriggered(@Nullable String str);

    void reportWifiCollectionStats(int i, int i2, int i3, int i4);
}

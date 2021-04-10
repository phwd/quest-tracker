package com.facebook.wifiscan;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.os.Build;
import com.facebook.location.appstate.GeoApiAnalyticsLogger;
import com.facebook.location.appstate.GeoApiLocationAppStateListener;
import java.util.List;
import javax.annotation.Nullable;

public class WifiScanner {
    @Nullable
    private GeoApiAnalyticsLogger mGeoApiAnalyticsLogger;
    @Nullable
    private GeoApiLocationAppStateListener mGeoApiLocationAppStateListener;
    private final WifiScan mWifiScan;
    private final WifiScanConfig mWifiScanConfig;

    public WifiScanner(WifiScanConfig wifiScanConfig, WifiScan wifiScan, @Nullable GeoApiLocationAppStateListener geoApiLocationAppStateListener, @Nullable GeoApiAnalyticsLogger geoApiAnalyticsLogger) {
        this.mWifiScanConfig = wifiScanConfig;
        this.mWifiScan = wifiScan;
        this.mGeoApiLocationAppStateListener = geoApiLocationAppStateListener;
        this.mGeoApiAnalyticsLogger = geoApiAnalyticsLogger;
    }

    @Nullable
    public WifiScanResult getConnectedWifi(String str) {
        if (!doesPassBackgroundFailsafe()) {
            log("getConnectedWifi", str, true);
            return null;
        }
        log("getConnectedWifi", str, false);
        return this.mWifiScan.getConnectedWifi();
    }

    @Nullable
    @Deprecated
    public WifiScanResult getConnectedWifi() {
        if (!doesPassBackgroundFailsafe()) {
            return null;
        }
        return this.mWifiScan.getConnectedWifi();
    }

    @Nullable
    public List<WifiScanResult> getRecentWifiScanResults(String str) {
        return getScanResults(str, this.mWifiScanConfig.getWifiScanMaxAgeMs());
    }

    @Nullable
    public List<WifiScanResult> getScanResults(String str, long j) {
        if (!doesPassBackgroundFailsafe()) {
            log("getScanResults", str, true);
            return null;
        }
        log("getScanResults", str, false);
        if (!this.mWifiScanConfig.isWifiScanEnabled()) {
            return null;
        }
        return filterScanResults(this.mWifiScan.getLastWifiScan(j, this.mWifiScanConfig.getWifiScanFutureMaxAgeMs(), this.mWifiScanConfig.getMaxAgeFromMaxWifiTimestampMs()));
    }

    @Nullable
    public List<ScanResult> getScanResults(String str) {
        if (!doesPassBackgroundFailsafe()) {
            log("getScanResults", str, true);
            return null;
        }
        log("getScanResults", str, false);
        return this.mWifiScan.getLastWifiScan();
    }

    @Nullable
    @Deprecated
    public List<WifiScanResult> getRecentWifiScanResults() {
        return getScanResults(this.mWifiScanConfig.getWifiScanMaxAgeMs());
    }

    @Nullable
    @Deprecated
    public List<WifiScanResult> getScanResults(long j) {
        if (!doesPassBackgroundFailsafe() || !this.mWifiScanConfig.isWifiScanEnabled()) {
            return null;
        }
        return filterScanResults(this.mWifiScan.getLastWifiScan(j, this.mWifiScanConfig.getWifiScanFutureMaxAgeMs(), this.mWifiScanConfig.getMaxAgeFromMaxWifiTimestampMs()));
    }

    @Nullable
    @Deprecated
    public List<ScanResult> getScanResults() {
        if (!doesPassBackgroundFailsafe()) {
            return null;
        }
        return this.mWifiScan.getLastWifiScan();
    }

    public boolean canGetWifiResults() {
        if (doesPassBackgroundFailsafe() && this.mWifiScanConfig.isWifiScanEnabled()) {
            return this.mWifiScan.canGetLastWifiScan();
        }
        return false;
    }

    public boolean triggerActiveWifiScan(String str) {
        if (!doesPassBackgroundFailsafe()) {
            log("triggerActiveWifiScan", str, true);
            return false;
        }
        log("triggerActiveWifiScan", str, false);
        if (!this.mWifiScanConfig.isWifiScanEnabled()) {
            return false;
        }
        return this.mWifiScan.triggerActiveWifiScan(str);
    }

    @Nullable
    public WifiInfo getConnectionInfo(String str) {
        if (!doesPassBackgroundFailsafe()) {
            log("getConnectionInfo", str, true);
            return null;
        }
        log("getConnectionInfo", str, false);
        if (this.mWifiScanConfig.isWifiScanEnabled()) {
            return this.mWifiScan.getConnectionInfo();
        }
        return null;
    }

    @Nullable
    @Deprecated
    public WifiInfo getConnectionInfo() {
        if (doesPassBackgroundFailsafe() && this.mWifiScanConfig.isWifiScanEnabled()) {
            return this.mWifiScan.getConnectionInfo();
        }
        return null;
    }

    @Nullable
    public List<WifiConfiguration> getConfiguredNetworks(String str) {
        if (!doesPassBackgroundFailsafe()) {
            log("getConfiguredNetworks", str, true);
            return null;
        }
        log("getConfiguredNetworks", str, false);
        if (this.mWifiScanConfig.isWifiScanEnabled()) {
            return this.mWifiScan.getConfiguredNetworks();
        }
        return null;
    }

    @Nullable
    @Deprecated
    public List<WifiConfiguration> getConfiguredNetworks() {
        if (doesPassBackgroundFailsafe() && this.mWifiScanConfig.isWifiScanEnabled()) {
            return this.mWifiScan.getConfiguredNetworks();
        }
        return null;
    }

    @Nullable
    private List<WifiScanResult> filterScanResults(List<WifiScanResult> list) {
        return WifiScanFilter.filterScanResults(list, this.mWifiScanConfig.getMinimumRequiredRssi(), this.mWifiScanConfig.getMinimumResultsPerScan(), this.mWifiScanConfig.getMaximumResultsPerScan());
    }

    private boolean doesPassBackgroundFailsafe() {
        if (!isAndroidQOrHigher() || this.mGeoApiLocationAppStateListener == null || this.mWifiScanConfig.mCanRunInBackground) {
            return true;
        }
        return this.mGeoApiLocationAppStateListener.isAppInForeground();
    }

    private static boolean isAndroidQOrHigher() {
        return Build.VERSION.SDK_INT >= 29;
    }

    private void log(String str, String str2, boolean z) {
        GeoApiAnalyticsLogger geoApiAnalyticsLogger = this.mGeoApiAnalyticsLogger;
        if (geoApiAnalyticsLogger != null) {
            geoApiAnalyticsLogger.reportGeoApiUse("WifiScanner", str, z, false, null, str2, null, null, null);
        }
    }
}

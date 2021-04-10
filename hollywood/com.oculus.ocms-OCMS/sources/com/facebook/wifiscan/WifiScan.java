package com.facebook.wifiscan;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.common.time.Clock;
import com.facebook.common.time.MonotonicClock;
import com.facebook.debug.log.BLog;
import com.facebook.location.appstate.GeoApiLocationAppStateListener;
import com.facebook.location.battery.LocationBatteryMetricsCollector;
import com.facebook.privacy.datacollection.Semantic;
import com.facebook.privacy.datacollection.SemanticType;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import javax.annotation.Nullable;

@Semantic(SemanticType.WIFI_INFO)
public class WifiScan {
    private static final String SSID_NO_MAP = "_nomap";
    private static final String SSID_OPTOUT = "_optout";
    private static final String TAG = "WifiScan";
    private final boolean mCanRunInBackground;
    private final Context mContext;
    private final WifiScanEligibilityUtil mEligibilityUtil;
    @Nullable
    private final GeoApiLocationAppStateListener mGeoApiLocationAppStateListener;
    private ScheduledExecutorService mLightweightTaskHandlerExecutorService;
    @Nullable
    private final LocationBatteryMetricsCollector mLocationBatteryMetricsCollector;
    private final MonotonicClock mSinceBootClock;
    private final Clock mWallClock;
    @Nullable
    private final IWifiScanOperationAnalyticsLogger mWifiScanOperationAnalyticsLogger;
    private final WifiScanResultTimestampFix mWifiScanResultTimestampFix;

    public WifiScan(Context context, Clock clock, MonotonicClock monotonicClock, ScheduledExecutorService scheduledExecutorService, WifiScanEligibilityUtil wifiScanEligibilityUtil, WifiScanResultTimestampFix wifiScanResultTimestampFix, @Nullable LocationBatteryMetricsCollector locationBatteryMetricsCollector, @Nullable IWifiScanOperationAnalyticsLogger iWifiScanOperationAnalyticsLogger, @Nullable GeoApiLocationAppStateListener geoApiLocationAppStateListener, boolean z) {
        this.mContext = context.getApplicationContext();
        this.mWallClock = clock;
        this.mSinceBootClock = monotonicClock;
        this.mLightweightTaskHandlerExecutorService = scheduledExecutorService;
        this.mEligibilityUtil = wifiScanEligibilityUtil;
        this.mWifiScanResultTimestampFix = wifiScanResultTimestampFix;
        this.mLocationBatteryMetricsCollector = locationBatteryMetricsCollector;
        this.mWifiScanOperationAnalyticsLogger = iWifiScanOperationAnalyticsLogger;
        this.mGeoApiLocationAppStateListener = geoApiLocationAppStateListener;
        this.mCanRunInBackground = z;
    }

    public WifiScan(Context context, Clock clock, MonotonicClock monotonicClock, ScheduledExecutorService scheduledExecutorService, long j, @Nullable GeoApiLocationAppStateListener geoApiLocationAppStateListener, boolean z) {
        this(context, clock, monotonicClock, scheduledExecutorService, new WifiScanEligibilityUtil(context), new WifiScanResultTimestampFix(clock, monotonicClock, j), null, null, geoApiLocationAppStateListener, false);
    }

    @Deprecated
    public WifiScan(Context context, Clock clock, MonotonicClock monotonicClock, ScheduledExecutorService scheduledExecutorService, long j) {
        this(context, clock, monotonicClock, scheduledExecutorService, new WifiScanEligibilityUtil(context), new WifiScanResultTimestampFix(clock, monotonicClock, j), null, null, null, true);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    @SuppressLint({"MissingPermission", "WifiManagerPotentialLeak"})
    public List<ScanResult> getWifiManagerScanResults(boolean z) {
        List<ScanResult> scanResults;
        ArrayList arrayList = null;
        if (!doesPassBackgroundFailsafe()) {
            return null;
        }
        if ((z || canGetLastWifiScan()) && (scanResults = ((WifiManager) this.mContext.getSystemService("wifi")).getScanResults()) != null) {
            arrayList = new ArrayList(scanResults.size());
            for (ScanResult scanResult : scanResults) {
                if (scanResult != null && !isWifiSsidBlockListed(scanResult.SSID)) {
                    arrayList.add(scanResult);
                }
            }
        }
        return arrayList;
    }

    @Nullable
    @SuppressLint({"MissingPermission", "WifiManagerPotentialLeak"})
    public WifiScanResult getConnectedWifi() {
        if (!doesPassBackgroundFailsafe()) {
            return null;
        }
        if (!this.mEligibilityUtil.appHasPermissionToGetConnectedWifi()) {
            logAccessWithoutPermission();
            return null;
        }
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        String wifiInfoSSID = getWifiInfoSSID(connectionInfo);
        if (connectionInfo == null || TextUtils.isEmpty(connectionInfo.getBSSID()) || connectionInfo.getSupplicantState() != SupplicantState.COMPLETED || isWifiSsidBlockListed(wifiInfoSSID)) {
            return null;
        }
        return WifiScanResult.fromWifiInfo(this.mWallClock.now(), connectionInfo, wifiInfoSSID, hasCaptivePortal());
    }

    @Nullable
    @SuppressLint({"MissingPermission", "WifiManagerPotentialLeak", "CatchGeneralException", "BadMethodUse-android.net.wifi.WifiManager.getConnectionInfo"})
    public WifiInfo getConnectionInfo() {
        if (!doesPassBackgroundFailsafe()) {
            return null;
        }
        if (!this.mEligibilityUtil.appHasPermissionToGetConnectedWifi()) {
            logAccessWithoutPermission();
            return null;
        }
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        try {
            return wifiManager.getConnectionInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    @SuppressLint({"MissingPermission", "WifiManagerPotentialLeak", "CatchGeneralException", "BadMethodUse-android.net.wifi.WifiManager.getConfiguredNetworks"})
    public List<WifiConfiguration> getConfiguredNetworks() {
        if (!doesPassBackgroundFailsafe()) {
            return null;
        }
        if (!this.mEligibilityUtil.appHasPermissionToGetConnectedWifi()) {
            logAccessWithoutPermission();
            return null;
        }
        WifiManager wifiManager = (WifiManager) this.mContext.getSystemService("wifi");
        if (wifiManager == null) {
            return null;
        }
        try {
            return wifiManager.getConfiguredNetworks();
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    @SuppressLint({"MissingPermission", "WifiManagerPotentialLeak", "CatchGeneralException"})
    @TargetApi(23)
    private Boolean hasCaptivePortal() {
        NetworkCapabilities activeWifiNetworkCapabilities;
        try {
            if (Build.VERSION.SDK_INT < 23) {
                return null;
            }
            if (!this.mEligibilityUtil.appHasPermissionToAccessNetworkInfo()) {
                return null;
            }
            ConnectivityManager connectivityManager = (ConnectivityManager) this.mContext.getSystemService("connectivity");
            if (connectivityManager == null || (activeWifiNetworkCapabilities = getActiveWifiNetworkCapabilities(connectivityManager)) == null) {
                return null;
            }
            return Boolean.valueOf(activeWifiNetworkCapabilities.hasCapability(17));
        } catch (Exception e) {
            BLog.e(TAG, "Cannot check if the connected wifi has the Captive Portal capability", e);
            return null;
        }
    }

    @Nullable
    @SuppressLint({"MissingPermission"})
    @TargetApi(21)
    private static NetworkCapabilities getActiveWifiNetworkCapabilities(ConnectivityManager connectivityManager) {
        for (Network network : connectivityManager.getAllNetworks()) {
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            if (networkCapabilities != null && networkCapabilities.hasTransport(1)) {
                return networkCapabilities;
            }
        }
        return null;
    }

    private static int getSafeLength(String str) {
        BreakIterator characterInstance = BreakIterator.getCharacterInstance();
        characterInstance.setText(str);
        return characterInstance.last();
    }

    @Nullable
    private static String getWifiInfoSSID(WifiInfo wifiInfo) {
        int safeLength;
        String ssid = wifiInfo != null ? wifiInfo.getSSID() : null;
        if (ssid == null || (safeLength = getSafeLength(ssid)) <= 2) {
            return ssid;
        }
        int i = safeLength - 1;
        return (ssid.codePointAt(0) == 34 && ssid.codePointAt(i) == 34) ? ssid.substring(1, i) : ssid;
    }

    private static boolean isWifiSsidBlockListed(String str) {
        if (str != null) {
            return str.endsWith(SSID_NO_MAP) || str.contains(SSID_OPTOUT);
        }
        return false;
    }

    @Nullable
    public List<WifiScanResult> getLastWifiScan(long j) {
        return getLastWifiScan(j, -1, -1);
    }

    @Nullable
    public List<WifiScanResult> getLastWifiScan(long j, long j2, long j3) {
        if (!this.mEligibilityUtil.areTimestampsSupported()) {
            return null;
        }
        try {
            List<ScanResult> wifiManagerScanResults = getWifiManagerScanResults(true);
            this.mWifiScanResultTimestampFix.fixTimestamps(wifiManagerScanResults);
            List<ScanResult> resultsMatchingAgeLimit = ScanResultAgeUtil.getResultsMatchingAgeLimit(wifiManagerScanResults, j, j2, j3, this.mSinceBootClock.now());
            reportWifiCollectionStats(wifiManagerScanResults, resultsMatchingAgeLimit);
            return WifiScanResult.fromScanResults(resultsMatchingAgeLimit, this.mWallClock, this.mSinceBootClock);
        } catch (SecurityException unused) {
            return null;
        }
    }

    @Nullable
    public List<ScanResult> getLastWifiScan() {
        try {
            return getWifiManagerScanResults(true);
        } catch (SecurityException unused) {
            return null;
        }
    }

    private void reportWifiCollectionStats(@Nullable List<ScanResult> list, @Nullable List<ScanResult> list2) {
        int i;
        int i2;
        if (this.mWifiScanOperationAnalyticsLogger != null) {
            long maxScanTimestampForOs = ScanResultAgeUtil.getMaxScanTimestampForOs(list);
            int i3 = -1;
            if (maxScanTimestampForOs >= 0) {
                long now = this.mSinceBootClock.now() - (maxScanTimestampForOs / 1000);
                i = (int) (now / 1000);
                i3 = (int) ((this.mWallClock.now() - now) / 1000);
            } else {
                i = -1;
            }
            int i4 = 0;
            if (list == null) {
                i2 = 0;
            } else {
                i2 = list.size();
            }
            if (list2 != null) {
                i4 = list2.size();
            }
            this.mWifiScanOperationAnalyticsLogger.reportWifiCollectionStats(i, i3, i2, i4);
        }
    }

    public boolean canGetLastWifiScan() {
        if (!this.mEligibilityUtil.areTimestampsSupported()) {
            return false;
        }
        if (!this.mEligibilityUtil.appHasPermissionToAccessCache()) {
            logAccessWithoutPermission();
            return false;
        } else if (!this.mEligibilityUtil.isWifiAvailable()) {
            return false;
        } else {
            if (this.mEligibilityUtil.isScanAlwaysAvailable() || this.mEligibilityUtil.isWifiEnabled()) {
                return true;
            }
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public boolean triggerActiveWifiScan(@Nullable String str) {
        boolean z = false;
        if (!doesPassBackgroundFailsafe()) {
            return false;
        }
        if (canGetLastWifiScan() && (z = ((WifiManager) this.mContext.getSystemService("wifi")).startScan())) {
            IWifiScanOperationAnalyticsLogger iWifiScanOperationAnalyticsLogger = this.mWifiScanOperationAnalyticsLogger;
            if (iWifiScanOperationAnalyticsLogger != null) {
                iWifiScanOperationAnalyticsLogger.reportActiveScanTriggered(str);
            }
            LocationBatteryMetricsCollector locationBatteryMetricsCollector = this.mLocationBatteryMetricsCollector;
            if (locationBatteryMetricsCollector != null) {
                locationBatteryMetricsCollector.recordWifiScan();
            }
        }
        return z;
    }

    public boolean isWifiEnabled() {
        WifiManager wifiManager = (WifiManager) this.mContext.getApplicationContext().getSystemService("wifi");
        return wifiManager != null && wifiManager.isWifiEnabled();
    }

    public WifiScanOperation newWifiScanOperation() {
        return new WifiScanOperation(this, this.mContext, this.mEligibilityUtil, this.mSinceBootClock, this.mWallClock, this.mWifiScanResultTimestampFix, this.mLightweightTaskHandlerExecutorService);
    }

    private boolean doesPassBackgroundFailsafe() {
        GeoApiLocationAppStateListener geoApiLocationAppStateListener;
        if (!isAndroidQOrHigher() || (geoApiLocationAppStateListener = this.mGeoApiLocationAppStateListener) == null || this.mCanRunInBackground) {
            return true;
        }
        return geoApiLocationAppStateListener.isAppInForeground();
    }

    private static boolean isAndroidQOrHigher() {
        return Build.VERSION.SDK_INT >= 29;
    }

    private static void logAccessWithoutPermission() {
        BLog.d(TAG, "Missing ACCESS_FINE_LOCATION permission");
    }
}

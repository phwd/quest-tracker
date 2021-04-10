package com.facebook.wifiscan;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.os.Build;
import com.facebook.annotations.OkToExtend;
import com.facebook.common.time.Clock;
import com.facebook.common.time.MonotonicClock;
import com.facebook.privacy.datacollection.Semantic;
import com.facebook.privacy.datacollection.SemanticType;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@OkToExtend
@Semantic(SemanticType.WIFI_INFO)
public class WifiScanResult {
    public final String BSSID;
    @Nullable
    public final String SSID;
    @Nullable
    public final String capabilities;
    @Nullable
    public final Integer distanceMm;
    @Nullable
    public final Integer distanceStd;
    @Nullable
    public final Integer frequency;
    @Nullable
    public final Boolean hasCaptivePortal;
    public final int level;
    @Nullable
    private ScanResult mNativeScanResult;
    @Nullable
    public final String operatorName;
    public final long timestampMs;
    @Nullable
    public final String venueName;

    public WifiScanResult(long j, String str, int i, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable Boolean bool) {
        this.timestampMs = j;
        this.BSSID = str;
        this.level = i;
        this.SSID = str2;
        this.frequency = num;
        this.capabilities = str3;
        this.operatorName = null;
        this.venueName = null;
        this.hasCaptivePortal = bool;
        this.distanceMm = null;
        this.distanceStd = null;
    }

    public WifiScanResult(long j, String str, int i, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.timestampMs = j;
        this.BSSID = str;
        this.level = i;
        this.SSID = str2;
        this.frequency = num;
        this.capabilities = str3;
        this.operatorName = str4;
        this.venueName = str5;
        this.hasCaptivePortal = null;
        this.distanceMm = null;
        this.distanceStd = null;
    }

    public WifiScanResult(long j, String str, int i, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool) {
        this.timestampMs = j;
        this.BSSID = str;
        this.level = i;
        this.SSID = str2;
        this.frequency = num;
        this.capabilities = str3;
        this.operatorName = str4;
        this.venueName = str5;
        this.hasCaptivePortal = bool;
        this.distanceMm = null;
        this.distanceStd = null;
    }

    public WifiScanResult(long j, String str, int i, @Nullable String str2, @Nullable Integer num, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Boolean bool, @Nullable Integer num2, @Nullable Integer num3) {
        this.timestampMs = j;
        this.BSSID = str;
        this.level = i;
        this.SSID = str2;
        this.frequency = num;
        this.capabilities = str3;
        this.operatorName = str4;
        this.venueName = str5;
        this.hasCaptivePortal = bool;
        this.distanceMm = num2;
        this.distanceStd = num3;
    }

    @Nullable
    public static List<WifiScanResult> fromScanResults(@Nullable List<ScanResult> list, Clock clock, MonotonicClock monotonicClock) {
        String str;
        String str2;
        if (list == null || Build.VERSION.SDK_INT < 17) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (ScanResult scanResult : list) {
            sb.setLength(0);
            if (scanResult.capabilities != null) {
                sb.append(scanResult.capabilities);
            }
            if (Build.VERSION.SDK_INT >= 23) {
                String charSequence = (scanResult.operatorFriendlyName == null || scanResult.operatorFriendlyName.length() <= 0) ? null : scanResult.operatorFriendlyName.toString();
                String charSequence2 = (scanResult.venueName == null || scanResult.venueName.length() <= 0) ? null : scanResult.venueName.toString();
                if (scanResult.is80211mcResponder()) {
                    sb.append("[MC]");
                }
                str2 = charSequence;
                str = charSequence2;
            } else {
                str2 = null;
                str = null;
            }
            WifiScanResult wifiScanResult = new WifiScanResult(clock.now() - (monotonicClock.now() - (scanResult.timestamp / 1000)), scanResult.BSSID, scanResult.level, scanResult.SSID, Integer.valueOf(scanResult.frequency), sb.toString(), str2, str);
            wifiScanResult.mNativeScanResult = scanResult;
            arrayList.add(wifiScanResult);
        }
        return arrayList;
    }

    public static WifiScanResult fromWifiInfo(long j, WifiInfo wifiInfo, String str, @Nullable Boolean bool) {
        return new WifiScanResult(j, wifiInfo.getBSSID(), wifiInfo.getRssi(), str, Build.VERSION.SDK_INT >= 21 ? Integer.valueOf(wifiInfo.getFrequency()) : null, null, bool);
    }

    @Nullable
    public synchronized ScanResult getNativeScanResult() {
        return this.mNativeScanResult;
    }

    public synchronized void setNativeScanResult(@Nullable ScanResult scanResult) {
        this.mNativeScanResult = scanResult;
    }
}

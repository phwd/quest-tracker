package com.facebook.wifiscan;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;

public class WifiScanEligibilityUtil {
    private final Context mContext;

    public WifiScanEligibilityUtil(Context context) {
        this.mContext = context;
    }

    public boolean isWifiAvailable() {
        try {
            return this.mContext.getPackageManager().hasSystemFeature("android.hardware.wifi");
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean areTimestampsSupported() {
        return Build.VERSION.SDK_INT >= 17;
    }

    public boolean appHasPermissionToGetConnectedWifi() {
        return hasPermission("android.permission.ACCESS_WIFI_STATE") && appHasLocationPermission();
    }

    public boolean appHasPermissionToAccessCache() {
        return hasPermission("android.permission.ACCESS_WIFI_STATE") && appHasLocationPermission();
    }

    public boolean appHasPermissionToScan() {
        return appHasPermissionToAccessCache() && hasPermission("android.permission.CHANGE_WIFI_STATE");
    }

    public boolean appHasPermissionToAccessNetworkInfo() {
        return hasPermission("android.permission.ACCESS_NETWORK_STATE");
    }

    public boolean isWifiEnabled() {
        try {
            return ((WifiManager) this.mContext.getSystemService("wifi")).isWifiEnabled();
        } catch (SecurityException unused) {
            return false;
        }
    }

    public boolean isScanAlwaysAvailable() {
        if (Build.VERSION.SDK_INT < 18) {
            return false;
        }
        return isScanAlwaysAvailableInternal();
    }

    private boolean appHasLocationPermission() {
        if (this.mContext.getApplicationInfo().targetSdkVersion >= 26) {
            return hasPermission("android.permission.ACCESS_FINE_LOCATION");
        }
        return hasPermission("android.permission.ACCESS_COARSE_LOCATION") || hasPermission("android.permission.ACCESS_FINE_LOCATION");
    }

    private boolean hasPermission(String str) {
        return this.mContext.checkCallingOrSelfPermission(str) == 0;
    }

    @SuppressLint({"MissingPermission"})
    @TargetApi(18)
    private boolean isScanAlwaysAvailableInternal() {
        try {
            return ((WifiManager) this.mContext.getSystemService("wifi")).isScanAlwaysAvailable();
        } catch (SecurityException unused) {
            return false;
        }
    }
}

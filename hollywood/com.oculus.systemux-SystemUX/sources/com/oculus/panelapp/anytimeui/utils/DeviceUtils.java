package com.oculus.panelapp.anytimeui.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import com.oculus.common.logutilities.LoggingUtil;

public final class DeviceUtils {
    private static final String TAG = LoggingUtil.tag(DeviceUtils.class);

    public static boolean isADBEnabled(Context context) {
        return Settings.Secure.getInt(context.getContentResolver(), "adb_enabled", 0) == 1;
    }

    public static boolean isWifiConnected(Context context) {
        return ((WifiManager) context.getSystemService("wifi")).isWifiEnabled() && ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetwork() != null;
    }
}

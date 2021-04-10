package com.facebook.wifiscan;

import android.content.Context;
import android.os.Handler;
import com.facebook.common.executors.HandlerListeningExecutorServiceImpl;
import com.facebook.common.time.RealtimeSinceBootClock;
import com.facebook.common.time.SystemClock;
import com.facebook.location.appstate.GeoApiLocationAppStateListener;
import javax.annotation.Nullable;

public class WifiScannerFactory {
    public static WifiScanner getWifiScannerForConfig(Context context, WifiScanConfig wifiScanConfig) {
        return getWifiScannerForConfig(context, wifiScanConfig, GeoApiLocationAppStateListener.getInstance(context));
    }

    public static WifiScanner getWifiScannerForConfig(Context context, WifiScanConfig wifiScanConfig, @Nullable GeoApiLocationAppStateListener geoApiLocationAppStateListener) {
        SystemClock systemClock = SystemClock.get();
        RealtimeSinceBootClock realtimeSinceBootClock = RealtimeSinceBootClock.get();
        return new WifiScanner(wifiScanConfig, new WifiScan(context, systemClock, realtimeSinceBootClock, new HandlerListeningExecutorServiceImpl(new Handler(context.getMainLooper())), new WifiScanEligibilityUtil(context), new WifiScanResultTimestampFix(systemClock, realtimeSinceBootClock, wifiScanConfig.getTimestampCorrectionWindowMs()), null, null, geoApiLocationAppStateListener, wifiScanConfig.mCanRunInBackground), geoApiLocationAppStateListener, null);
    }
}

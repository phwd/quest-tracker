package com.oculus.modules;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.WifiManagerModule;

public class WifiManagerModuleImpl extends WifiManagerModule {
    public static final int NUM_SIGNAL_LEVELS = 3;
    public static final String TAG = "WifiManagerModule";
    public final ConnectivityManager mConnectivityManager;
    public final Context mContext;
    public String mSSID;
    public int mWifiLevel;
    public final WifiManager mWifiManager;
    public int mWifiState;

    private void emitOnWifiUpdatedEvent() {
        WifiManagerModule.WifiUpdate wifiUpdate = new WifiManagerModule.WifiUpdate();
        wifiUpdate.level = (double) this.mWifiLevel;
        wifiUpdate.ssid = this.mSSID;
        wifiUpdate.state = (double) this.mWifiState;
        emitOnWifiUpdated(wifiUpdate);
    }

    private void retrieveCurrentSSID() {
        String str;
        NetworkInfo activeNetworkInfo = this.mConnectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
            Log.e("WifiManagerModule", "No Wi-Fi connection");
            str = null;
        } else {
            str = this.mWifiManager.getConnectionInfo().getSSID();
            int length = str.length();
            if (length >= 2 && str.startsWith("\"") && str.endsWith("\"")) {
                str = str.substring(1, length - 1);
            }
        }
        this.mSSID = str;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        return intentFilter;
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean z) {
        int i = this.mWifiLevel;
        int i2 = this.mWifiState;
        if (intent.getAction() == "android.net.wifi.RSSI_CHANGED") {
            this.mWifiLevel = WifiManager.calculateSignalLevel(intent.getIntExtra("newRssi", 0), 3);
        } else if (intent.getAction() == "android.net.wifi.WIFI_STATE_CHANGED") {
            this.mWifiState = intent.getIntExtra("wifi_state", 1);
        }
        retrieveCurrentSSID();
        if (z || i != this.mWifiLevel || i2 != this.mWifiState) {
            emitOnWifiUpdatedEvent();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0041, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x003d, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WifiManagerModuleImpl(android.content.Context r4) {
        /*
            r3 = this;
            r3.<init>(r4)
            r3.mContext = r4
            java.lang.String r1 = "WifiManagerModule"
            java.lang.String r0 = "GetWifiManager"
            com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock
            r2.<init>(r1, r0)
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x003b }
            android.content.Context r1 = r0.getApplicationContext()     // Catch:{ all -> 0x003b }
            java.lang.String r0 = "connectivity"
            java.lang.Object r0 = r1.getSystemService(r0)     // Catch:{ all -> 0x003b }
            android.net.ConnectivityManager r0 = (android.net.ConnectivityManager) r0     // Catch:{ all -> 0x003b }
            r3.mConnectivityManager = r0     // Catch:{ all -> 0x003b }
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x003b }
            android.content.Context r1 = r0.getApplicationContext()     // Catch:{ all -> 0x003b }
            java.lang.String r0 = "wifi"
            java.lang.Object r0 = r1.getSystemService(r0)     // Catch:{ all -> 0x003b }
            android.net.wifi.WifiManager r0 = (android.net.wifi.WifiManager) r0     // Catch:{ all -> 0x003b }
            r3.mWifiManager = r0     // Catch:{ all -> 0x003b }
            r2.close()
            r0 = 0
            r3.mWifiLevel = r0
            r3.retrieveCurrentSSID()
            r0 = 1
            r3.mWifiState = r0
            return
        L_0x003b:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003d }
        L_0x003d:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0041 }
        L_0x0041:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.WifiManagerModuleImpl.<init>(android.content.Context):void");
    }

    @Override // com.oculus.modules.codegen.WifiManagerModule
    public void getConnectedSSIDImpl(Resolver<String> resolver) {
        retrieveCurrentSSID();
        resolver.resolve(this.mSSID);
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void onNoStickyIntent() {
        emitOnWifiUpdatedEvent();
    }
}

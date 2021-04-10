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
    private static final int NUM_SIGNAL_LEVELS = 3;
    private static final String TAG = WifiManagerModule.class.getSimpleName();
    private final ConnectivityManager mConnectivityManager;
    private final Context mContext;
    private String mSSID;
    private int mWifiLevel;
    private final WifiManager mWifiManager;
    private int mWifiState;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004c, code lost:
        r2 = r1;
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        r1 = th;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public WifiManagerModuleImpl(android.content.Context r6) {
        /*
            r5 = this;
            r5.<init>(r6)
            r5.mContext = r6
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r1 = com.oculus.modules.WifiManagerModuleImpl.TAG
            java.lang.String r2 = "GetWifiManager"
            r0.<init>(r1, r2)
            r2 = 0
            android.content.Context r1 = r5.mContext     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            java.lang.String r3 = "connectivity"
            java.lang.Object r1 = r1.getSystemService(r3)     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            r5.mConnectivityManager = r1     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            android.content.Context r1 = r5.mContext     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            android.content.Context r1 = r1.getApplicationContext()     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            java.lang.String r3 = "wifi"
            java.lang.Object r1 = r1.getSystemService(r3)     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            android.net.wifi.WifiManager r1 = (android.net.wifi.WifiManager) r1     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            r5.mWifiManager = r1     // Catch:{ Throwable -> 0x0049, all -> 0x0060 }
            if (r0 == 0) goto L_0x0036
            if (r2 == 0) goto L_0x0045
            r0.close()     // Catch:{ Throwable -> 0x0040 }
        L_0x0036:
            r1 = 0
            r5.mWifiLevel = r1
            r5.retrieveCurrentSSID()
            r1 = 1
            r5.mWifiState = r1
            return
        L_0x0040:
            r1 = move-exception
            r2.addSuppressed(r1)
            goto L_0x0036
        L_0x0045:
            r0.close()
            goto L_0x0036
        L_0x0049:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x004b }
        L_0x004b:
            r2 = move-exception
            r4 = r2
            r2 = r1
            r1 = r4
        L_0x004f:
            if (r0 == 0) goto L_0x0056
            if (r2 == 0) goto L_0x005c
            r0.close()     // Catch:{ Throwable -> 0x0057 }
        L_0x0056:
            throw r1
        L_0x0057:
            r3 = move-exception
            r2.addSuppressed(r3)
            goto L_0x0056
        L_0x005c:
            r0.close()
            goto L_0x0056
        L_0x0060:
            r1 = move-exception
            goto L_0x004f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.WifiManagerModuleImpl.<init>(android.content.Context):void");
    }

    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public IntentFilter getIntentFilter() {
        IntentFilter filter = new IntentFilter("android.net.wifi.RSSI_CHANGED");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        return filter;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void onNoStickyIntent() {
        emitOnWifiUpdatedEvent();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.panellib.modules.AbstractBroadcastReceiverModule
    public void processIntent(Intent intent, boolean forceNativeUpdate) {
        Log.d(TAG, "processIntent()");
        int previousWifiLevel = this.mWifiLevel;
        int previousWifiState = this.mWifiState;
        if (intent.getAction() == "android.net.wifi.RSSI_CHANGED") {
            this.mWifiLevel = WifiManager.calculateSignalLevel(intent.getIntExtra("newRssi", 0), 3);
        } else if (intent.getAction() == "android.net.wifi.WIFI_STATE_CHANGED") {
            this.mWifiState = intent.getIntExtra("wifi_state", 1);
        }
        retrieveCurrentSSID();
        if (forceNativeUpdate || previousWifiLevel != this.mWifiLevel || previousWifiState != this.mWifiState) {
            emitOnWifiUpdatedEvent();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.WifiManagerModule
    public void getConnectedSSIDImpl(Resolver<String> resolver) {
        retrieveCurrentSSID();
        resolver.resolve(this.mSSID);
    }

    private void retrieveCurrentSSID() {
        NetworkInfo network = this.mConnectivityManager.getActiveNetworkInfo();
        if (network == null || !network.isConnectedOrConnecting()) {
            Log.e(TAG, "No Wi-Fi connection");
            this.mSSID = null;
            return;
        }
        String ssid = this.mWifiManager.getConnectionInfo().getSSID();
        if (ssid.length() >= 2 && ssid.startsWith("\"") && ssid.endsWith("\"")) {
            ssid = ssid.substring(1, ssid.length() - 1);
        }
        this.mSSID = ssid;
        Log.d(TAG, "Got connected SSID: " + this.mSSID);
    }

    private void emitOnWifiUpdatedEvent() {
        WifiManagerModule.WifiUpdate update = new WifiManagerModule.WifiUpdate();
        update.level = (double) this.mWifiLevel;
        update.ssid = this.mSSID;
        update.state = (double) this.mWifiState;
        Log.d(TAG, "Calling emitOnWifiUpdated()");
        emitOnWifiUpdated(update);
    }
}

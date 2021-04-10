package com.oculus.unifiedtelemetry.collectors;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.AbstractC0385gk;
import X.Fa;
import X.QC;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.google.common.collect.ImmutableMap;
import com.oculus.unifiedtelemetry.unifiedlogging.LoggingHandler;
import com.oculus.util.network.NetworkUtils;

@Dependencies({"_UL__ULSEP_com_oculus_unifiedtelemetry_unifiedlogging_LoggingHandler_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class WiFiCollector implements ICollector, AbstractC0385gk {
    public static final String KEY_AUTHENTICATION = "authentication";
    public static final String KEY_CONNECTING_TIME = "connecting_time";
    public static final String KEY_DEVICE_UPTIME = "device_uptime_ms";
    public static final String KEY_DOZING = "is_dozing";
    public static final String KEY_IPV4_ADDRESS = "ipv4_address";
    public static final String KEY_IPV6_ADDRESS = "ipv6_address";
    public static final String KEY_IS_CONNECTED = "is_connected";
    public static final String KEY_NETWORK_TYPE = "network_type";
    public static final String KEY_RSSI_DBM = "rssi_dbm";
    public static final String KEY_SCREEN_ON = "is_screen_on";
    public static final String KEY_WIFI_ENABLED_STATE = "wifi_enabled_state";
    public static final String KEY_WIFI_SCORE = "wifi_score";
    public static final String OCULUS_WIFI_ENABLED_CHANGED = "oculus_mobile_wifi_enabled_changed";
    public static final String OCULUS_WIFI_QUALITY = "oculus_mobile_wifi_quality";
    public static final String OCULUS_WIFI_STATE_CHANGED = "oculus_mobile_wifi_state_changed";
    public static final String TAG = "WiFiCollector";
    public static volatile WiFiCollector _UL__ULSEP_com_oculus_unifiedtelemetry_collectors_WiFiCollector_ULSEP_INSTANCE;
    public QC _UL_mInjectionContext;
    public BroadcastReceiver mBroadcastReceiver;
    public final NetworkSnapshot mNetworkSnapshot = new NetworkSnapshot();
    public boolean mRssiChanged = false;
    public final WifiManager mWiFiManager;

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A44() {
    }

    private void A00(NetworkSnapshot networkSnapshot) {
        if (networkSnapshot != null) {
            Event event = new Event(OCULUS_WIFI_QUALITY);
            boolean z = false;
            if (networkSnapshot.mNetworkState == NetworkInfo.DetailedState.CONNECTED) {
                z = true;
            }
            event.mContent.putBoolean(KEY_IS_CONNECTED, z);
            event.A03(KEY_CONNECTING_TIME, networkSnapshot.mConnectionEndTimeMs - networkSnapshot.mConnectionStartTimeMs);
            StringBuilder sb = new StringBuilder();
            sb.append(networkSnapshot.mNetworkType);
            sb.append(" ");
            sb.append(networkSnapshot.mNetworkFrequencyGHz);
            sb.append("GHz");
            event.A06(KEY_NETWORK_TYPE, sb.toString());
            event.A02(KEY_RSSI_DBM, networkSnapshot.mRssi);
            event.A02(KEY_WIFI_SCORE, networkSnapshot.mScore);
            event.A01();
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
        }
    }

    private void A01(NetworkSnapshot networkSnapshot) {
        if (networkSnapshot != null) {
            Event event = new Event(OCULUS_WIFI_STATE_CHANGED);
            boolean z = false;
            if (networkSnapshot.mNetworkState == NetworkInfo.DetailedState.CONNECTED) {
                z = true;
            }
            event.mContent.putBoolean(KEY_IS_CONNECTED, z);
            event.A06(KEY_AUTHENTICATION, networkSnapshot.mAuthType);
            event.A06(KEY_IPV4_ADDRESS, TextUtils.join(", ", networkSnapshot.mIPv4));
            event.A06(KEY_IPV6_ADDRESS, TextUtils.join(", ", networkSnapshot.mIPv6));
            event.A01();
            ((LoggingHandler) AbstractC0096Hu.A03(0, 114, this._UL_mInjectionContext)).A07(event.mName, event.mContent);
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void A3k() {
        if (this.mRssiChanged) {
            A00(this.mNetworkSnapshot);
            this.mRssiChanged = false;
        }
    }

    @Override // com.oculus.unifiedtelemetry.collectors.ICollector
    public final void onStart() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put("android.net.conn.CONNECTIVITY_CHANGE", this);
        builder.put("android.net.wifi.STATE_CHANGE", this);
        builder.put("android.net.wifi.RSSI_CHANGED", this);
        builder.put("android.net.wifi.WIFI_STATE_CHANGED", this);
        this.mBroadcastReceiver = new Fa(builder.build().entrySet().iterator());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.RSSI_CHANGED");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        ((Context) AbstractC0096Hu.A03(1, 3, this._UL_mInjectionContext)).registerReceiver(this.mBroadcastReceiver, intentFilter);
    }

    @Inject
    public WiFiCollector(AbstractC0247Xu xu) {
        QC qc = new QC(2, xu);
        this._UL_mInjectionContext = qc;
        this.mWiFiManager = (WifiManager) ((Context) AbstractC0096Hu.A03(1, 3, qc)).getSystemService(NetworkUtils.WIFI);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:118:0x0263, code lost:
        if (r6.isDeviceIdleMode() == false) goto L_0x0265;
     */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0137  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x019d  */
    @Override // X.AbstractC0385gk
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A3q(android.content.Context r11, android.content.Intent r12, X.AbstractC0386gl r13) {
        /*
        // Method dump skipped, instructions count: 716
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.unifiedtelemetry.collectors.WiFiCollector.A3q(android.content.Context, android.content.Intent, X.gl):void");
    }
}

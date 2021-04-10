package com.oculus.alpenglow.induction;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import com.facebook.inject.ApplicationScoped;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import java.util.List;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
@ApplicationScoped
public class WifiProvisioning {
    public static final String SETUP_NETWORK_SSID = "oculus-setup";
    public static final String TAG = "EnterpriseServer.WifiProvisioning";
    public static volatile WifiProvisioning _UL__ULSEP_com_oculus_alpenglow_induction_WifiProvisioning_ULSEP_INSTANCE;
    public AnonymousClass0R7 _UL_mInjectionContext;

    @SuppressLint({"BadMethodUse-android.net.wifi.WifiManager.getConfiguredNetworks"})
    public final void A00() {
        WifiManager wifiManager = (WifiManager) ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSystemService(WifiManager.class);
        if (wifiManager == null) {
            AnonymousClass0NK.A01(TAG, "could not access wifi manager");
            return;
        }
        List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
        if (configuredNetworks == null || configuredNetworks.isEmpty()) {
            WifiConfiguration wifiConfiguration = new WifiConfiguration();
            wifiConfiguration.SSID = AnonymousClass006.A07("\"", SETUP_NETWORK_SSID, "\"");
            wifiConfiguration.hiddenSSID = false;
            wifiConfiguration.priority = 0;
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiManager.setWifiEnabled(true);
            wifiManager.enableNetwork(wifiManager.addNetwork(wifiConfiguration), true);
            wifiManager.reconnect();
        }
    }

    @Inject
    public WifiProvisioning(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}

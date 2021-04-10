package com.oculus.alpenglow.wifi;

import X.AbstractC02990bJ;
import X.AnonymousClass006;
import X.AnonymousClass0Lh;
import X.AnonymousClass0NK;
import X.AnonymousClass0R7;
import X.AnonymousClass0u6;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.alpenglow.config.ConfigChangeListener;
import com.oculus.alpenglow.config.Device;
import com.oculus.alpenglow.graphql.enums.GraphQLHWMNetworkType;
import java.util.Map;
import java.util.stream.Collectors;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID"})
public class WifiConfigChangeListener implements ConfigChangeListener {
    public static final String TAG = "EnterpriseServer.WifiConfigChangeListener";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Override // com.oculus.alpenglow.config.ConfigChangeListener
    public final void A5z(Device device, Device device2) {
        String str;
        String str2;
        Device.ManagementInfo A3y;
        Device.ManagementInfo.DeviceConfig A3Q;
        Device.ManagementInfo.DeviceConfig.NetworkConfig A4A;
        Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks A4v;
        boolean z;
        String str3;
        String str4;
        if (device2 == null || (A3y = device2.A3y()) == null || (A3Q = A3y.A3Q()) == null || (A4A = A3Q.A4A()) == null || (A4v = A4A.A4v()) == null) {
            str = TAG;
            str2 = "no wifi config provided by ITAP";
        } else {
            WifiManager wifiManager = (WifiManager) ((Context) AnonymousClass0Lh.A03(0, 4, this._UL_mInjectionContext)).getSystemService(WifiManager.class);
            if (wifiManager == null) {
                str = TAG;
                str2 = "couldn't access wifi manager";
            } else {
                Map map = (Map) wifiManager.getConfiguredNetworks().stream().collect(Collectors.toMap($$Lambda$WifiConfigChangeListener$T6g9hq1nhKZmP9thMde7UkkU5GU2.INSTANCE, $$Lambda$WifiConfigChangeListener$mTeROEbsaus1jPfQJrOZgrwLeRQ2.INSTANCE));
                if (A4v != null) {
                    AnonymousClass0u6<? extends Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes> it = A4v.A4C().iterator();
                    while (it.hasNext()) {
                        Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes nodes = (Device.ManagementInfo.DeviceConfig.NetworkConfig.WifiNetworks.Nodes) it.next();
                        String A4X = nodes.A4X();
                        if (!TextUtils.isEmpty(A4X)) {
                            WifiConfiguration wifiConfiguration = (WifiConfiguration) map.get(A4X);
                            if (wifiConfiguration == null) {
                                wifiConfiguration = new WifiConfiguration();
                                if (A4X != null) {
                                    str4 = AnonymousClass006.A07("\"", A4X, "\"");
                                } else {
                                    str4 = "\"\"";
                                }
                                wifiConfiguration.SSID = str4;
                                wifiConfiguration.hiddenSSID = false;
                                z = false;
                            } else {
                                z = true;
                            }
                            if (nodes.A4B() == GraphQLHWMNetworkType.WPA2) {
                                String A3H = nodes.A3H();
                                if (A3H != null) {
                                    str3 = AnonymousClass006.A07("\"", A3H, "\"");
                                } else {
                                    str3 = "\"\"";
                                }
                                wifiConfiguration.preSharedKey = str3;
                                wifiConfiguration.allowedKeyManagement.set(1);
                            } else {
                                wifiConfiguration.allowedKeyManagement.set(0);
                            }
                            if (z) {
                                wifiManager.updateNetwork(wifiConfiguration);
                            } else {
                                wifiManager.enableNetwork(wifiManager.addNetwork(wifiConfiguration), false);
                            }
                        }
                    }
                    return;
                }
                return;
            }
        }
        AnonymousClass0NK.A01(str, str2);
    }

    @Inject
    public WifiConfigChangeListener(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}

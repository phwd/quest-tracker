package com.oculus.alpenglow.induction;

import android.net.wifi.WifiConfiguration;
import com.oculus.alpenglow.util.StringUtils;
import java.util.function.Predicate;

/* renamed from: com.oculus.alpenglow.induction.-$$Lambda$WifiProvisioning$HBImXg6ECWUu7m0Gk60rWYWG8mA2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$WifiProvisioning$HBImXg6ECWUu7m0Gk60rWYWG8mA2 implements Predicate {
    public static final /* synthetic */ $$Lambda$WifiProvisioning$HBImXg6ECWUu7m0Gk60rWYWG8mA2 INSTANCE = new $$Lambda$WifiProvisioning$HBImXg6ECWUu7m0Gk60rWYWG8mA2();

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        return WifiProvisioning.SETUP_NETWORK_SSID.equals(StringUtils.A00(((WifiConfiguration) obj).SSID));
    }
}

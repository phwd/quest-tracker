package com.oculus.alpenglow.wifi;

import android.net.wifi.WifiConfiguration;
import com.oculus.alpenglow.util.StringUtils;
import java.util.function.Function;

/* renamed from: com.oculus.alpenglow.wifi.-$$Lambda$WifiConfigChangeListener$T6g9hq1nhKZmP9thMde7UkkU5GU2  reason: invalid class name */
public final /* synthetic */ class $$Lambda$WifiConfigChangeListener$T6g9hq1nhKZmP9thMde7UkkU5GU2 implements Function {
    public static final /* synthetic */ $$Lambda$WifiConfigChangeListener$T6g9hq1nhKZmP9thMde7UkkU5GU2 INSTANCE = new $$Lambda$WifiConfigChangeListener$T6g9hq1nhKZmP9thMde7UkkU5GU2();

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return StringUtils.A00(((WifiConfiguration) obj).SSID);
    }
}

package com.oculus.http.useragent;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.stringformat.StringFormatUtil;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Map;

public class UserAgentBuilder {
    public static final String FB_APP_NAME = "FBAN";
    public static final String FB_APP_VERSION = "FBAV";
    public static final String FB_APP_VERSION_MAP = "FBVM";
    public static final String FB_BRAND = "FBBD";
    public static final String FB_BUILD_VERSION = "FBBV";
    public static final String FB_CARRIER = "FBCR";
    public static final String FB_CPU_ABI = "FBCA";
    public static final String FB_DEVICE = "FBDV";
    public static final String FB_DEVICE_WIDE_STATE = "FBDW";
    public static final String FB_LOCALE = "FBLC";
    public static final String FB_MANUFACTURER = "FBMF";
    public static final String FB_PACKAGE_NAME = "FBPN";
    public static final String FB_SYSTEM_VERSION = "FBSV";
    public static final ImmutableList<String> FIXED_ORDER_KEYS = ImmutableList.of(FB_APP_NAME, FB_APP_VERSION, FB_DEVICE, FB_CARRIER, FB_LOCALE, FB_SYSTEM_VERSION);
    public static final ImmutableList<String> OPTIONAL_KEYS = ImmutableList.of(FB_BRAND, FB_BUILD_VERSION, FB_CPU_ABI, FB_MANUFACTURER, FB_PACKAGE_NAME);
    public static final ImmutableList<String> OPTIONAL_KEYS_SKIP_IF_NULL = ImmutableList.of(FB_DEVICE_WIDE_STATE, FB_APP_VERSION_MAP);
    public String mHttpAgent;
    public final Map<String, String> mProperties = new HashMap();

    @VisibleForTesting
    public static final String A00(String str) {
        String str2;
        if (str == null || str.isEmpty()) {
            return "null";
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                str2 = "&amp;";
            } else if (charAt < ' ' || charAt > '~') {
                sb.append("&#");
                sb.append(Integer.toString(charAt));
                str2 = ";";
            } else {
                sb.append(charAt);
            }
            sb.append(str2);
        }
        return sb.toString().replace("/", "-").replace(";", "-");
    }

    public UserAgentBuilder(Context context) {
        this.mProperties.put(FB_BRAND, Build.BRAND);
        this.mProperties.put(FB_DEVICE, Build.MODEL);
        this.mProperties.put(FB_CARRIER, ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
        this.mProperties.put(FB_MANUFACTURER, Build.MANUFACTURER);
        this.mProperties.put(FB_SYSTEM_VERSION, Build.VERSION.RELEASE);
        this.mProperties.put(FB_CPU_ABI, StringFormatUtil.formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }
}

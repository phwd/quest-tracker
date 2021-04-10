package com.oculus.common.httpclient;

import android.os.Build;
import android.text.TextUtils;
import com.facebook.debug.log.LoggingUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAgentBuilder {
    private static final String FB_APP_NAME = "FBAN";
    private static final String FB_APP_VERSION = "FBAV";
    private static final String FB_BOARD = "FBBR";
    private static final String FB_BRAND = "FBBD";
    private static final String FB_BUILD_VERSION = "FBBV";
    private static final String FB_CARRIER = "FBCR";
    private static final String FB_CPU_ABI = "FBCA";
    private static final String FB_DEVICE = "FBDV";
    private static final String FB_LOCALE = "FBLC";
    private static final String FB_MANUFACTURER = "FBMF";
    private static final String FB_PACKAGE_NAME = "FBPN";
    private static final String FB_SYSTEM_VERSION = "FBSV";
    private static final List<String> FIXED_ORDER_KEYS = Collections.unmodifiableList(Arrays.asList(FB_APP_NAME, FB_APP_VERSION, FB_DEVICE, FB_CARRIER, FB_LOCALE, FB_SYSTEM_VERSION));
    private static final List<String> OPTIONAL_KEYS = Collections.unmodifiableList(Arrays.asList(FB_BOARD, FB_BRAND, FB_BUILD_VERSION, FB_CPU_ABI, FB_MANUFACTURER, FB_PACKAGE_NAME));
    private String mHttpAgent;
    private final Map<String, String> mProperties = new HashMap();

    private static String formatStrLocaleSafe(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    private UserAgentBuilder() {
        this.mProperties.put(FB_BOARD, Build.BOARD);
        this.mProperties.put(FB_BRAND, Build.BRAND);
        this.mProperties.put(FB_DEVICE, Build.MODEL);
        this.mProperties.put(FB_MANUFACTURER, Build.MANUFACTURER);
        this.mProperties.put(FB_SYSTEM_VERSION, Build.VERSION.RELEASE);
        this.mProperties.put(FB_CPU_ABI, formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    public static UserAgentBuilder newBuilder() {
        return new UserAgentBuilder();
    }

    public String build(String str, String str2, String str3, String str4, String str5, String str6) {
        return setHttpAgent(str).setAppName(str2).setAppVersion(str3).setBuildVersion(str5).setLocale(str6).setPackageName(str4).build();
    }

    public UserAgentBuilder setCarrier(String str) {
        this.mProperties.put(FB_CARRIER, str);
        return this;
    }

    public UserAgentBuilder setAppName(String str) {
        this.mProperties.put(FB_APP_NAME, str);
        return this;
    }

    public UserAgentBuilder setAppVersion(String str) {
        this.mProperties.put(FB_APP_VERSION, str);
        return this;
    }

    public UserAgentBuilder setBuildVersion(String str) {
        this.mProperties.put(FB_BUILD_VERSION, str);
        return this;
    }

    public UserAgentBuilder setHttpAgent(String str) {
        this.mHttpAgent = str;
        return this;
    }

    public UserAgentBuilder setLocale(String str) {
        this.mProperties.put(FB_LOCALE, str);
        return this;
    }

    public UserAgentBuilder setPackageName(String str) {
        this.mProperties.put(FB_PACKAGE_NAME, str);
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHttpAgent);
        sb.append(" [");
        for (String str : FIXED_ORDER_KEYS) {
            sb.append(formatStrLocaleSafe("%s/%s;", str, sanitize(this.mProperties.get(str))));
        }
        for (String str2 : OPTIONAL_KEYS) {
            sb.append(formatStrLocaleSafe("%s/%s;", str2, sanitize(this.mProperties.get(str2))));
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public String sanitize(String str) {
        return (TextUtils.isEmpty(str) || str == null) ? LoggingUtil.NO_HASHCODE : xmlEncodeNonLatin(str).replace("/", "-").replace(";", "-");
    }

    private String xmlEncodeNonLatin(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt < ' ' || charAt > '~') {
                sb.append("&#");
                sb.append(Integer.toString(charAt));
                sb.append(";");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}

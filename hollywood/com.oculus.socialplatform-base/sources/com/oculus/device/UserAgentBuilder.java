package com.oculus.device;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;

public class UserAgentBuilder implements IUserAgentBuilder {
    public static final String FB_APP_NAME = "FBAN";
    public static final String FB_APP_VERSION = "FBAV";
    public static final String FB_BOARD = "FBBR";
    public static final String FB_BRAND = "FBBD";
    public static final String FB_BUILD_VERSION = "FBBV";
    public static final String FB_CARRIER = "FBCR";
    public static final String FB_CPU_ABI = "FBCA";
    public static final String FB_DEVICE = "FBDV";
    public static final String FB_LOCALE = "FBLC";
    public static final String FB_MANUFACTURER = "FBMF";
    public static final String FB_PACKAGE_NAME = "FBPN";
    public static final String FB_SYSTEM_VERSION = "FBSV";
    public static final List<String> FIXED_ORDER_KEYS = Collections.unmodifiableList(Arrays.asList("FBAN", "FBAV", "FBDV", "FBCR", "FBLC", "FBSV"));
    public static final List<String> OPTIONAL_KEYS = Collections.unmodifiableList(Arrays.asList("FBBR", "FBBD", "FBBV", "FBCA", "FBMF", "FBPN"));
    public String mHttpAgent;
    public final Map<String, String> mProperties;

    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return String.format(null, str, objArr);
    }

    public static UserAgentBuilder newBuilder() {
        return new UserAgentBuilder();
    }

    private String xmlEncodeNonLatin(String str) {
        String str2;
        if (str == null) {
            return "";
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
        return sb.toString();
    }

    public UserAgentBuilder setAppName(String str) {
        this.mProperties.put("FBAN", str);
        return this;
    }

    public UserAgentBuilder setAppVersion(String str) {
        this.mProperties.put("FBAV", str);
        return this;
    }

    public UserAgentBuilder setBuildVersion(String str) {
        this.mProperties.put("FBBV", str);
        return this;
    }

    public UserAgentBuilder setCarrier(String str) {
        this.mProperties.put("FBCR", str);
        return this;
    }

    public UserAgentBuilder setLocale(String str) {
        this.mProperties.put("FBLC", str);
        return this;
    }

    public UserAgentBuilder setPackageName(String str) {
        this.mProperties.put("FBPN", str);
        return this;
    }

    public UserAgentBuilder() {
        HashMap hashMap = new HashMap();
        this.mProperties = hashMap;
        hashMap.put("FBBR", Build.BOARD);
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", String.format(null, "%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    public String sanitize(String str) {
        if (TextUtils.isEmpty(str) || str == null) {
            return "null";
        }
        return xmlEncodeNonLatin(str).replace("/", HelpFormatter.DEFAULT_OPT_PREFIX).replace(";", HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    public UserAgentBuilder setHttpAgent(String str) {
        this.mHttpAgent = str;
        return this;
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHttpAgent);
        sb.append(" [");
        for (String str : FIXED_ORDER_KEYS) {
            sb.append(String.format(null, "%s/%s;", str, sanitize(this.mProperties.get(str))));
        }
        for (String str2 : OPTIONAL_KEYS) {
            sb.append(String.format(null, "%s/%s;", str2, sanitize(this.mProperties.get(str2))));
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // com.oculus.device.IUserAgentBuilder
    public String build(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        this.mHttpAgent = str;
        setAppName(str2);
        setAppVersion(str3);
        setBuildVersion(str5);
        setLocale(str6);
        setPackageName(str4);
        return build();
    }
}

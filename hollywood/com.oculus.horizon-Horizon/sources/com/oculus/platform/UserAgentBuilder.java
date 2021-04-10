package com.oculus.platform;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAgentBuilder {
    public static final String FB_APP_NAME = "FBAN";
    public static final String FB_APP_VERSION = "FBAV";
    public static final String FB_BRAND = "FBBD";
    public static final String FB_BUILD_VERSION = "FBBV";
    public static final String FB_CARRIER = "FBCR";
    public static final String FB_CPU_ABI = "FBCA";
    public static final String FB_DEVICE = "FBDV";
    public static final String FB_ENGINE = "ENGINE";
    public static final String FB_LOCALE = "FBLC";
    public static final String FB_MANUFACTURER = "FBMF";
    public static final String FB_PACKAGE_NAME = "FBPN";
    public static final String FB_PLATFORM = "PLATFORM";
    public static final String FB_SYSTEM_VERSION = "FBSV";
    public static final List<String> FIXED_ORDER_KEYS = new ArrayList<String>() {
        /* class com.oculus.platform.UserAgentBuilder.AnonymousClass1 */

        {
            add("FBAN");
            add("FBAV");
            add("FBDV");
            add("FBCR");
            add("FBLC");
            add("FBSV");
        }
    };
    public static final List<String> OPTIONAL_KEYS = new ArrayList<String>() {
        /* class com.oculus.platform.UserAgentBuilder.AnonymousClass2 */

        {
            add("FBBD");
            add("FBBV");
            add("FBCA");
            add("FBMF");
            add("FBPN");
            add(UserAgentBuilder.FB_PLATFORM);
            add(UserAgentBuilder.FB_ENGINE);
        }
    };
    public String mHttpAgent;
    public final Map<String, String> mProperties = new HashMap();

    public static UserAgentBuilder newBuilder(Context context) {
        return new UserAgentBuilder(context);
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

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHttpAgent);
        sb.append(" [");
        for (String str : FIXED_ORDER_KEYS) {
            sb.append(String.format("%s/%s;", str, sanitize(this.mProperties.get(str))));
        }
        for (String str2 : OPTIONAL_KEYS) {
            if (this.mProperties.containsKey(str2)) {
                sb.append(String.format("%s/%s;", str2, sanitize(this.mProperties.get(str2))));
            }
        }
        sb.append("]");
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

    public UserAgentBuilder setEngine(String str) {
        this.mProperties.put(FB_ENGINE, str);
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

    public UserAgentBuilder(Context context) {
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBCR", ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", String.format("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
        this.mProperties.put(FB_PLATFORM, "ANDROID");
    }

    public String sanitize(String str) {
        if (TextUtils.isEmpty(str)) {
            return "null";
        }
        return xmlEncodeNonLatin(str).replace("/", "-").replace(";", "-");
    }

    public UserAgentBuilder setHttpAgent(String str) {
        this.mHttpAgent = str;
        return this;
    }
}

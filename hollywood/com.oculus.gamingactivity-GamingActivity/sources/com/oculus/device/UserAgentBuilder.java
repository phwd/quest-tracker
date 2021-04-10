package com.oculus.device;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.facebook.debug.log.LoggingUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAgentBuilder implements IUserAgentBuilder {
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

    private static String formatStrLocaleSafe(String str, Object... args) {
        return String.format(null, str, args);
    }

    public UserAgentBuilder() {
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

    @Override // com.oculus.device.IUserAgentBuilder
    public String build(Context context, String httpAgent, String mobileAppName, String appVersion, String packageName, String buildVersion, String locale) {
        return setHttpAgent(httpAgent).setAppName(mobileAppName).setAppVersion(appVersion).setBuildVersion(buildVersion).setLocale(locale).setPackageName(packageName).build();
    }

    public UserAgentBuilder setCarrier(String carrier) {
        this.mProperties.put(FB_CARRIER, carrier);
        return this;
    }

    public UserAgentBuilder setAppName(String appName) {
        this.mProperties.put(FB_APP_NAME, appName);
        return this;
    }

    public UserAgentBuilder setAppVersion(String appVersion) {
        this.mProperties.put(FB_APP_VERSION, appVersion);
        return this;
    }

    public UserAgentBuilder setBuildVersion(String buildVersion) {
        this.mProperties.put(FB_BUILD_VERSION, buildVersion);
        return this;
    }

    public UserAgentBuilder setHttpAgent(String httpAgent) {
        this.mHttpAgent = httpAgent;
        return this;
    }

    public UserAgentBuilder setLocale(String locale) {
        this.mProperties.put(FB_LOCALE, locale);
        return this;
    }

    public UserAgentBuilder setPackageName(String packageName) {
        this.mProperties.put(FB_PACKAGE_NAME, packageName);
        return this;
    }

    public String build() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.mHttpAgent).append(" [");
        for (String key : FIXED_ORDER_KEYS) {
            builder.append(formatStrLocaleSafe("%s/%s;", key, sanitize(this.mProperties.get(key))));
        }
        for (String key2 : OPTIONAL_KEYS) {
            builder.append(formatStrLocaleSafe("%s/%s;", key2, sanitize(this.mProperties.get(key2))));
        }
        builder.append("]");
        return builder.toString();
    }

    /* access modifiers changed from: package-private */
    public String sanitize(String value) {
        if (TextUtils.isEmpty(value) || value == null) {
            return LoggingUtil.NO_HASHCODE;
        }
        return xmlEncodeNonLatin(value).replace("/", "-").replace(";", "-");
    }

    private String xmlEncodeNonLatin(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '&') {
                sb.append("&amp;");
            } else if (ch < ' ' || ch > '~') {
                sb.append("&#");
                sb.append(Integer.toString(ch));
                sb.append(";");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}

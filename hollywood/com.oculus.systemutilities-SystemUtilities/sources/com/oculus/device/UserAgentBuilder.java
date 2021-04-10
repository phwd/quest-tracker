package com.oculus.device;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAgentBuilder implements IUserAgentBuilder {
    private static final List<String> FIXED_ORDER_KEYS = Collections.unmodifiableList(Arrays.asList("FBAN", "FBAV", "FBDV", "FBCR", "FBLC", "FBSV"));
    private static final List<String> OPTIONAL_KEYS = Collections.unmodifiableList(Arrays.asList("FBBR", "FBBD", "FBBV", "FBCA", "FBMF", "FBPN"));
    private String mHttpAgent;
    private final Map<String, String> mProperties = new HashMap();

    private static String formatStrLocaleSafe(String str, Object... args) {
        return String.format(null, str, args);
    }

    public UserAgentBuilder() {
        this.mProperties.put("FBBR", Build.BOARD);
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    @Override // com.oculus.device.IUserAgentBuilder
    public String build(Context context, String httpAgent, String mobileAppName, String appVersion, String packageName, String buildVersion, String locale) {
        return setHttpAgent(httpAgent).setAppName(mobileAppName).setAppVersion(appVersion).setBuildVersion(buildVersion).setLocale(locale).setPackageName(packageName).build();
    }

    public UserAgentBuilder setAppName(String appName) {
        this.mProperties.put("FBAN", appName);
        return this;
    }

    public UserAgentBuilder setAppVersion(String appVersion) {
        this.mProperties.put("FBAV", appVersion);
        return this;
    }

    public UserAgentBuilder setBuildVersion(String buildVersion) {
        this.mProperties.put("FBBV", buildVersion);
        return this;
    }

    public UserAgentBuilder setHttpAgent(String httpAgent) {
        this.mHttpAgent = httpAgent;
        return this;
    }

    public UserAgentBuilder setLocale(String locale) {
        this.mProperties.put("FBLC", locale);
        return this;
    }

    public UserAgentBuilder setPackageName(String packageName) {
        this.mProperties.put("FBPN", packageName);
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
            return "null";
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

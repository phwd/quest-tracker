package com.oculus.http.useragent;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import com.facebook.common.string.StringUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Map;

public class UserAgentBuilder {
    private static final String FB_APP_NAME = "FBAN";
    private static final String FB_APP_VERSION = "FBAV";
    private static final String FB_APP_VERSION_MAP = "FBVM";
    private static final String FB_BRAND = "FBBD";
    private static final String FB_BUILD_VERSION = "FBBV";
    private static final String FB_CARRIER = "FBCR";
    private static final String FB_CPU_ABI = "FBCA";
    private static final String FB_DEVICE = "FBDV";
    private static final String FB_DEVICE_WIDE_STATE = "FBDW";
    private static final String FB_LOCALE = "FBLC";
    private static final String FB_MANUFACTURER = "FBMF";
    private static final String FB_PACKAGE_NAME = "FBPN";
    private static final String FB_SYSTEM_VERSION = "FBSV";
    private static final ImmutableList<String> FIXED_ORDER_KEYS = ImmutableList.of(FB_APP_NAME, FB_APP_VERSION, FB_DEVICE, FB_CARRIER, FB_LOCALE, FB_SYSTEM_VERSION);
    private static final ImmutableList<String> OPTIONAL_KEYS = ImmutableList.of(FB_BRAND, FB_BUILD_VERSION, FB_CPU_ABI, FB_MANUFACTURER, FB_PACKAGE_NAME);
    private static final ImmutableList<String> OPTIONAL_KEYS_SKIP_IF_NULL = ImmutableList.of(FB_DEVICE_WIDE_STATE, FB_APP_VERSION_MAP);
    private String mHttpAgent;
    private final Map<String, String> mProperties = new HashMap();

    public UserAgentBuilder(Context context) {
        this.mProperties.put(FB_BRAND, Build.BRAND);
        this.mProperties.put(FB_DEVICE, Build.MODEL);
        this.mProperties.put(FB_CARRIER, ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
        this.mProperties.put(FB_MANUFACTURER, Build.MANUFACTURER);
        this.mProperties.put(FB_SYSTEM_VERSION, Build.VERSION.RELEASE);
        this.mProperties.put(FB_CPU_ABI, StringUtil.formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    public UserAgentBuilder setAppName(String appName) {
        this.mProperties.put(FB_APP_NAME, appName);
        return this;
    }

    public UserAgentBuilder setUserAgentAppVersionMap(String appVersionMap) {
        this.mProperties.put(FB_APP_VERSION_MAP, appVersionMap);
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

    public UserAgentBuilder setDeviceWideState(String deviceWideState) {
        this.mProperties.put(FB_DEVICE_WIDE_STATE, deviceWideState);
        return this;
    }

    public String build() {
        Preconditions.checkNotNull(this.mHttpAgent);
        StringBuilder builder = new StringBuilder();
        builder.append(this.mHttpAgent).append(" [");
        UnmodifiableIterator<String> it = FIXED_ORDER_KEYS.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Preconditions.checkNotNull(this.mProperties.get(key));
            builder.append(StringUtil.formatStrLocaleSafe("%s/%s;", key, sanitize(this.mProperties.get(key))));
        }
        UnmodifiableIterator<String> it2 = OPTIONAL_KEYS.iterator();
        while (it2.hasNext()) {
            String key2 = it2.next();
            builder.append(StringUtil.formatStrLocaleSafe("%s/%s;", key2, sanitize(this.mProperties.get(key2))));
        }
        UnmodifiableIterator<String> it3 = OPTIONAL_KEYS_SKIP_IF_NULL.iterator();
        while (it3.hasNext()) {
            String key3 = it3.next();
            if (this.mProperties.get(key3) != null) {
                builder.append(StringUtil.formatStrLocaleSafe("%s/%s;", key3, sanitize(this.mProperties.get(key3))));
            }
        }
        builder.append("]");
        return builder.toString();
    }

    /* access modifiers changed from: package-private */
    public String sanitize(String value) {
        if (Strings.isNullOrEmpty(value)) {
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

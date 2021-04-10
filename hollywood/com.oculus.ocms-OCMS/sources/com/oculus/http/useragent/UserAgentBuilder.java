package com.oculus.http.useragent;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.string.StringUtil;
import com.facebook.debug.log.LoggingUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;

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

    public UserAgentBuilder setAppName(String str) {
        this.mProperties.put(FB_APP_NAME, str);
        return this;
    }

    public UserAgentBuilder setUserAgentAppVersionMap(String str) {
        this.mProperties.put(FB_APP_VERSION_MAP, str);
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

    public UserAgentBuilder setDeviceWideState(String str) {
        this.mProperties.put(FB_DEVICE_WIDE_STATE, str);
        return this;
    }

    public String build() {
        Preconditions.checkNotNull(this.mHttpAgent);
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHttpAgent);
        sb.append(" [");
        UnmodifiableIterator<String> it = FIXED_ORDER_KEYS.iterator();
        while (it.hasNext()) {
            String next = it.next();
            Preconditions.checkNotNull(this.mProperties.get(next));
            sb.append(StringUtil.formatStrLocaleSafe("%s/%s;", next, sanitize(this.mProperties.get(next))));
        }
        UnmodifiableIterator<String> it2 = OPTIONAL_KEYS.iterator();
        while (it2.hasNext()) {
            String next2 = it2.next();
            sb.append(StringUtil.formatStrLocaleSafe("%s/%s;", next2, sanitize(this.mProperties.get(next2))));
        }
        UnmodifiableIterator<String> it3 = OPTIONAL_KEYS_SKIP_IF_NULL.iterator();
        while (it3.hasNext()) {
            String next3 = it3.next();
            if (this.mProperties.get(next3) != null) {
                sb.append(StringUtil.formatStrLocaleSafe("%s/%s;", next3, sanitize(this.mProperties.get(next3))));
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public String sanitize(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return LoggingUtil.NO_HASHCODE;
        }
        return xmlEncodeNonLatin(str).replace("/", HelpFormatter.DEFAULT_OPT_PREFIX).replace(";", HelpFormatter.DEFAULT_OPT_PREFIX);
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

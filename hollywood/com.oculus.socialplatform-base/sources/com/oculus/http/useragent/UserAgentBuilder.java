package com.oculus.http.useragent;

import X.AbstractC05710wh;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.stringformat.StringFormatUtil;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.oculus.vrshell.notifications.NotificationUri;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;

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
    public static final ImmutableList<String> FIXED_ORDER_KEYS = ImmutableList.A04();
    public static final ImmutableList<String> OPTIONAL_KEYS = ImmutableList.A03();
    public static final ImmutableList<String> OPTIONAL_KEYS_SKIP_IF_NULL = ImmutableList.A07(FB_DEVICE_WIDE_STATE, FB_APP_VERSION_MAP);
    public String mHttpAgent;
    public final Map<String, String> mProperties = new HashMap();

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
        String str = this.mHttpAgent;
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" [");
            AbstractC05710wh<String> A0I = FIXED_ORDER_KEYS.iterator();
            while (A0I.hasNext()) {
                String next = A0I.next();
                if (this.mProperties.get(next) != null) {
                    sb.append(StringFormatUtil.formatStrLocaleSafe("%s/%s;", next, sanitize(this.mProperties.get(next))));
                } else {
                    throw null;
                }
            }
            AbstractC05710wh<String> A0I2 = OPTIONAL_KEYS.iterator();
            while (A0I2.hasNext()) {
                String next2 = A0I2.next();
                sb.append(StringFormatUtil.formatStrLocaleSafe("%s/%s;", next2, sanitize(this.mProperties.get(next2))));
            }
            AbstractC05710wh<String> A0I3 = OPTIONAL_KEYS_SKIP_IF_NULL.iterator();
            while (A0I3.hasNext()) {
                String next3 = A0I3.next();
                if (this.mProperties.get(next3) != null) {
                    sb.append(StringFormatUtil.formatStrLocaleSafe("%s/%s;", next3, sanitize(this.mProperties.get(next3))));
                }
            }
            sb.append("]");
            return sb.toString();
        }
        throw null;
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

    public UserAgentBuilder setDeviceWideState(String str) {
        this.mProperties.put(FB_DEVICE_WIDE_STATE, str);
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

    public UserAgentBuilder setUserAgentAppVersionMap(String str) {
        this.mProperties.put(FB_APP_VERSION_MAP, str);
        return this;
    }

    public UserAgentBuilder(Context context) {
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBCR", ((TelephonyManager) context.getSystemService(NotificationUri.PHONE)).getNetworkOperatorName());
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", StringFormatUtil.formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    @VisibleForTesting
    public String sanitize(String str) {
        if (Strings.isNullOrEmpty(str)) {
            return "null";
        }
        return xmlEncodeNonLatin(str).replace("/", HelpFormatter.DEFAULT_OPT_PREFIX).replace(";", HelpFormatter.DEFAULT_OPT_PREFIX);
    }

    public UserAgentBuilder setHttpAgent(String str) {
        this.mHttpAgent = str;
        return this;
    }
}

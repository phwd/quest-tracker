package com.oculus.http.useragent;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.string.StringUtil;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import com.oculus.common.build.BuildConfig;
import java.util.HashMap;
import java.util.Map;

public class UserAgentBuilder {
    private static final ImmutableList<String> FIXED_ORDER_KEYS = ImmutableList.of("FBAN", "FBAV", "FBDV", "FBCR", "FBLC", "FBSV");
    private static final ImmutableList<String> OPTIONAL_KEYS = ImmutableList.of("FBBD", "FBBV", "FBCA", "FBMF", "FBPN");
    private static final ImmutableList<String> OPTIONAL_KEYS_SKIP_IF_NULL = ImmutableList.of("FBDW", "FBVM");
    private String mHttpAgent;
    private final Map<String, String> mProperties = new HashMap();

    public UserAgentBuilder(Context context) {
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBCR", ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", StringUtil.formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    public UserAgentBuilder setAppName(String str) {
        this.mProperties.put("FBAN", str);
        return this;
    }

    public UserAgentBuilder setUserAgentAppVersionMap(String str) {
        this.mProperties.put("FBVM", str);
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

    public UserAgentBuilder setHttpAgent(String str) {
        this.mHttpAgent = str;
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
            return "null";
        }
        return xmlEncodeNonLatin(str).replace("/", "-").replace(";", "-");
    }

    private String xmlEncodeNonLatin(String str) {
        if (str == null) {
            return BuildConfig.PROVIDER_SUFFIX;
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

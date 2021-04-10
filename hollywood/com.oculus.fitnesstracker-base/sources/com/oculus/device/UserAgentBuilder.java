package com.oculus.device;

import android.os.Build;
import android.text.TextUtils;
import com.oculus.common.build.BuildConfig;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class UserAgentBuilder implements IUserAgentBuilder {
    private static final List<String> FIXED_ORDER_KEYS = Collections.unmodifiableList(Arrays.asList("FBAN", "FBAV", "FBDV", "FBCR", "FBLC", "FBSV"));
    private static final List<String> OPTIONAL_KEYS = Collections.unmodifiableList(Arrays.asList("FBBR", "FBBD", "FBBV", "FBCA", "FBMF", "FBPN"));
    private String mHttpAgent;
    private final Map<String, String> mProperties = new HashMap();

    public UserAgentBuilder() {
        this.mProperties.put("FBBR", Build.BOARD);
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", String.format(null, "%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    public static UserAgentBuilder newBuilder() {
        return new UserAgentBuilder();
    }

    private String sanitize(String str) {
        return (TextUtils.isEmpty(str) || str == null) ? "null" : xmlEncodeNonLatin(str).replace("/", "-").replace(";", "-");
    }

    private static String xmlEncodeNonLatin(String str) {
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

    @Override // com.oculus.device.IUserAgentBuilder
    public final String build$2e2641d1(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mHttpAgent = str;
        this.mProperties.put("FBAN", str2);
        this.mProperties.put("FBAV", str3);
        this.mProperties.put("FBBV", str5);
        this.mProperties.put("FBLC", str6);
        this.mProperties.put("FBPN", str4);
        StringBuilder sb = new StringBuilder();
        sb.append(this.mHttpAgent);
        sb.append(" [");
        for (String str7 : FIXED_ORDER_KEYS) {
            sb.append(String.format(null, "%s/%s;", str7, sanitize(this.mProperties.get(str7))));
        }
        for (String str8 : OPTIONAL_KEYS) {
            sb.append(String.format(null, "%s/%s;", str8, sanitize(this.mProperties.get(str8))));
        }
        sb.append("]");
        return sb.toString();
    }
}

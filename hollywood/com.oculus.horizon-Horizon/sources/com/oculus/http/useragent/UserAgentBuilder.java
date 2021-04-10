package com.oculus.http.useragent;

import X.AbstractC07380s1;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;
import androidx.annotation.VisibleForTesting;
import com.facebook.common.stringformat.StringFormatUtil;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Map;

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
    public static final ImmutableList<String> FIXED_ORDER_KEYS = ImmutableList.A0A("FBAN", "FBAV", "FBDV", "FBCR", "FBLC", "FBSV");
    public static final ImmutableList<String> OPTIONAL_KEYS = ImmutableList.A04();
    public static final ImmutableList<String> OPTIONAL_KEYS_SKIP_IF_NULL = ImmutableList.A08(FB_DEVICE_WIDE_STATE, FB_APP_VERSION_MAP);
    public String mHttpAgent;
    public final Map<String, String> mProperties = new HashMap();

    public final String A01() {
        String str = this.mHttpAgent;
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" [");
            AbstractC07380s1<String> A0K = FIXED_ORDER_KEYS.iterator();
            while (A0K.hasNext()) {
                String next = A0K.next();
                if (this.mProperties.get(next) != null) {
                    sb.append(StringFormatUtil.formatStrLocaleSafe("%s/%s;", next, A00(this.mProperties.get(next))));
                } else {
                    throw null;
                }
            }
            AbstractC07380s1<String> A0K2 = OPTIONAL_KEYS.iterator();
            while (A0K2.hasNext()) {
                String next2 = A0K2.next();
                sb.append(StringFormatUtil.formatStrLocaleSafe("%s/%s;", next2, A00(this.mProperties.get(next2))));
            }
            AbstractC07380s1<String> A0K3 = OPTIONAL_KEYS_SKIP_IF_NULL.iterator();
            while (A0K3.hasNext()) {
                String next3 = A0K3.next();
                if (this.mProperties.get(next3) != null) {
                    sb.append(StringFormatUtil.formatStrLocaleSafe("%s/%s;", next3, A00(this.mProperties.get(next3))));
                }
            }
            sb.append("]");
            return sb.toString();
        }
        throw null;
    }

    public UserAgentBuilder(Context context) {
        this.mProperties.put("FBBD", Build.BRAND);
        this.mProperties.put("FBDV", Build.MODEL);
        this.mProperties.put("FBCR", ((TelephonyManager) context.getSystemService("phone")).getNetworkOperatorName());
        this.mProperties.put("FBMF", Build.MANUFACTURER);
        this.mProperties.put("FBSV", Build.VERSION.RELEASE);
        this.mProperties.put("FBCA", StringFormatUtil.formatStrLocaleSafe("%s:%s", Build.CPU_ABI, Build.CPU_ABI2));
    }

    @VisibleForTesting
    private final String A00(String str) {
        String str2;
        String str3;
        if (Strings.isNullOrEmpty(str)) {
            return "null";
        }
        if (str != null) {
            int length = str.length();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                char charAt = str.charAt(i);
                if (charAt == '&') {
                    str3 = "&amp;";
                } else if (charAt < ' ' || charAt > '~') {
                    sb.append("&#");
                    sb.append(Integer.toString(charAt));
                    str3 = ";";
                } else {
                    sb.append(charAt);
                }
                sb.append(str3);
            }
            str2 = sb.toString();
        } else {
            str2 = "";
        }
        return str2.replace("/", "-").replace(";", "-");
    }
}

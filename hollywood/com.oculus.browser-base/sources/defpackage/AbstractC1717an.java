package defpackage;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: an  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1717an {
    public static String a(String str) {
        if (str != null) {
            return b("com.google.android.gms.cast.CATEGORY_CAST", str, null);
        }
        throw new IllegalArgumentException("applicationId cannot be null");
    }

    public static String b(String str, String str2, Collection collection) {
        StringBuilder sb = new StringBuilder(str);
        String upperCase = str2.toUpperCase();
        if (!upperCase.matches("[A-F0-9]+")) {
            throw new IllegalArgumentException(str2.length() != 0 ? "Invalid application ID: ".concat(str2) : new String("Invalid application ID: "));
        }
        sb.append("/");
        sb.append(upperCase);
        if (collection != null) {
            if (!collection.isEmpty()) {
                sb.append("/");
                Iterator it = collection.iterator();
                boolean z = true;
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    GF1.d(str3);
                    if (z) {
                        z = false;
                    } else {
                        sb.append(",");
                    }
                    if (!GF1.f8078a.matcher(str3).matches()) {
                        StringBuilder sb2 = new StringBuilder(str3.length());
                        for (int i = 0; i < str3.length(); i++) {
                            char charAt = str3.charAt(i);
                            if (((charAt >= 'A' && charAt <= 'Z') || (charAt >= 'a' && charAt <= 'z') || ((charAt >= '0' && charAt <= '9') || charAt == '_' || charAt == '-')) || charAt == '.' || charAt == ':') {
                                sb2.append(charAt);
                            } else {
                                sb2.append(String.format("%%%04x", Integer.valueOf(charAt & 65535)));
                            }
                        }
                        str3 = sb2.toString();
                    }
                    sb.append(str3);
                }
            } else {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
        }
        if (collection == null) {
            sb.append("/");
        }
        sb.append("/");
        sb.append("/");
        sb.append("ALLOW_IPV6");
        return sb.toString();
    }
}

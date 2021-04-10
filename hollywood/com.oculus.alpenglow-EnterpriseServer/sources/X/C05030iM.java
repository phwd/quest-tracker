package X;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Set;

/* renamed from: X.0iM  reason: invalid class name and case insensitive filesystem */
public final class C05030iM {
    public static AnonymousClass0ap A00(Uri uri) {
        String str;
        String scheme = uri.getScheme();
        String authority = uri.getAuthority();
        if (scheme == null || authority == null) {
            C05010iJ r1 = new C05010iJ();
            r1.A01 = uri.getPath();
            r1.A02 = scheme;
            r1.A00 = authority;
            return new AnonymousClass0ap(r1.A02, r1.A00, r1.A01, uri.getQuery());
        }
        if (TextUtils.isEmpty(uri.getPath())) {
            str = null;
        } else {
            str = "/--sanitized--";
        }
        String str2 = null;
        if (!TextUtils.isEmpty(uri.getQuery())) {
            try {
                Set<String> queryParameterNames = uri.getQueryParameterNames();
                if (queryParameterNames != null && !queryParameterNames.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    for (String str3 : queryParameterNames) {
                        if (sb.length() > 0) {
                            sb.append('&');
                        }
                        sb.append(str3);
                        sb.append("=--sanitized--");
                    }
                    str2 = sb.toString();
                }
            } catch (UnsupportedOperationException unused) {
            }
        }
        return new AnonymousClass0ap(scheme, authority, str, str2);
    }
}

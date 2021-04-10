package X;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Set;

public final class gp {
    public static XQ A00(Uri uri) {
        String str;
        String scheme = uri.getScheme();
        String authority = uri.getAuthority();
        if (scheme == null || authority == null) {
            C0387gm gmVar = new C0387gm();
            gmVar.A01 = uri.getPath();
            gmVar.A02 = scheme;
            gmVar.A00 = authority;
            return new XQ(gmVar.A02, gmVar.A00, gmVar.A01, uri.getQuery());
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
        return new XQ(scheme, authority, str, str2);
    }
}

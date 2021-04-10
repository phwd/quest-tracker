package X;

import android.net.Uri;
import android.text.TextUtils;
import java.util.Set;

public final class JN {
    public static C0947pP A00(Uri uri) {
        String str;
        String scheme = uri.getScheme();
        String authority = uri.getAuthority();
        if (scheme == null || authority == null) {
            JL jl = new JL();
            jl.A01 = uri.getPath();
            jl.A02 = scheme;
            jl.A00 = authority;
            return new C0947pP(jl.A02, jl.A00, jl.A01, uri.getQuery());
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
        return new C0947pP(scheme, authority, str, str2);
    }
}

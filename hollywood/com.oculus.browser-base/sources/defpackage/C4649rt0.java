package defpackage;

import android.net.Uri;

/* renamed from: rt0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4649rt0 {

    /* renamed from: a  reason: collision with root package name */
    public final Uri f11230a;

    public C4649rt0(Uri uri) {
        this.f11230a = uri;
    }

    public static C4649rt0 a(Uri uri) {
        if (!(uri == null || uri.getScheme() == null || uri.getAuthority() == null)) {
            String scheme = uri.getScheme();
            if (!scheme.equals("http") && !scheme.equals("https")) {
                return null;
            }
            int port = uri.getPort();
            if (scheme.equals("http") && port == 80) {
                port = -1;
            }
            if (scheme.equals("https") && port == 443) {
                port = -1;
            }
            String host = uri.getHost();
            if (port != -1) {
                host = host + ":" + port;
            }
            try {
                return new C4649rt0(uri.normalizeScheme().buildUpon().opaquePart("").fragment("").path("").encodedAuthority(host).clearQuery().build());
            } catch (UnsupportedOperationException unused) {
            }
        }
        return null;
    }

    public static C4649rt0 b(String str) {
        return a(Uri.parse(str));
    }

    public static C4649rt0 c(String str) {
        Uri parse = Uri.parse(str);
        C4649rt0 a2 = a(parse);
        if (a2 != null) {
            return a2;
        }
        throw new IllegalArgumentException(AbstractC2531fV.c("Could not parse: ", parse));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C4649rt0.class != obj.getClass()) {
            return false;
        }
        return this.f11230a.equals(((C4649rt0) obj).f11230a);
    }

    public int hashCode() {
        return this.f11230a.hashCode();
    }

    public String toString() {
        return this.f11230a.toString();
    }
}

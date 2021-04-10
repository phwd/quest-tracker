package X;

import java.io.IOException;

/* renamed from: X.0kh  reason: invalid class name */
public enum AnonymousClass0kh {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    public final String protocol;

    public static AnonymousClass0kh get(String str) throws IOException {
        AnonymousClass0kh r1 = HTTP_1_0;
        if (!str.equals(r1.protocol)) {
            r1 = HTTP_1_1;
            if (!str.equals(r1.protocol)) {
                r1 = HTTP_2;
                if (!str.equals(r1.protocol)) {
                    r1 = SPDY_3;
                    if (!str.equals(r1.protocol)) {
                        throw new IOException(AnonymousClass006.A05("Unexpected protocol: ", str));
                    }
                }
            }
        }
        return r1;
    }

    public String toString() {
        return this.protocol;
    }

    /* access modifiers changed from: public */
    AnonymousClass0kh(String str) {
        this.protocol = str;
    }
}

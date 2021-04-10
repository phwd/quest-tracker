package X;

import java.io.IOException;

/* renamed from: X.0wP  reason: invalid class name and case insensitive filesystem */
public enum EnumC08350wP {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    public final String protocol;

    public static EnumC08350wP get(String str) throws IOException {
        EnumC08350wP r1 = HTTP_1_0;
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

    /* access modifiers changed from: public */
    EnumC08350wP(String str) {
        this.protocol = str;
    }

    public String toString() {
        return this.protocol;
    }
}

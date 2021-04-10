package X;

import java.io.IOException;

/* renamed from: X.bm  reason: case insensitive filesystem */
public enum EnumC0549bm {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    public final String protocol;

    public static EnumC0549bm get(String str) {
        EnumC0549bm bmVar = HTTP_1_0;
        if (!str.equals(bmVar.protocol)) {
            bmVar = HTTP_1_1;
            if (!str.equals(bmVar.protocol)) {
                bmVar = HTTP_2;
                if (!str.equals(bmVar.protocol)) {
                    bmVar = SPDY_3;
                    if (!str.equals(bmVar.protocol)) {
                        throw new IOException(AnonymousClass08.A04("Unexpected protocol: ", str));
                    }
                }
            }
        }
        return bmVar;
    }

    /* access modifiers changed from: public */
    EnumC0549bm(String str) {
        this.protocol = str;
    }

    public String toString() {
        return this.protocol;
    }
}

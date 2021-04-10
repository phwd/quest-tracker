package X;

import java.io.IOException;

/* renamed from: X.dl  reason: case insensitive filesystem */
public enum EnumC0364dl {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    public final String protocol;

    public static EnumC0364dl get(String str) throws IOException {
        EnumC0364dl dlVar = HTTP_1_0;
        if (!str.equals(dlVar.protocol)) {
            dlVar = HTTP_1_1;
            if (!str.equals(dlVar.protocol)) {
                dlVar = HTTP_2;
                if (!str.equals(dlVar.protocol)) {
                    dlVar = SPDY_3;
                    if (!str.equals(dlVar.protocol)) {
                        throw new IOException(AnonymousClass06.A04("Unexpected protocol: ", str));
                    }
                }
            }
        }
        return dlVar;
    }

    /* access modifiers changed from: public */
    EnumC0364dl(String str) {
        this.protocol = str;
    }

    public String toString() {
        return this.protocol;
    }
}

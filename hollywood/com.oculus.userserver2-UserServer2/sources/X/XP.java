package X;

import java.io.IOException;

public enum XP {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    public final String protocol;

    public static XP get(String str) throws IOException {
        XP xp = HTTP_1_0;
        if (!str.equals(xp.protocol)) {
            xp = HTTP_1_1;
            if (!str.equals(xp.protocol)) {
                xp = HTTP_2;
                if (!str.equals(xp.protocol)) {
                    xp = SPDY_3;
                    if (!str.equals(xp.protocol)) {
                        throw new IOException(AnonymousClass06.A03("Unexpected protocol: ", str));
                    }
                }
            }
        }
        return xp;
    }

    /* access modifiers changed from: public */
    XP(String str) {
        this.protocol = str;
    }

    public String toString() {
        return this.protocol;
    }
}

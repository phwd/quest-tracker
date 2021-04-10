package okhttp3;

import X.AnonymousClass006;
import java.io.IOException;

public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2");
    
    public final String protocol;

    public static Protocol get(String str) throws IOException {
        Protocol protocol2 = HTTP_1_0;
        if (!str.equals(protocol2.protocol)) {
            protocol2 = HTTP_1_1;
            if (!str.equals(protocol2.protocol)) {
                protocol2 = HTTP_2;
                if (!str.equals(protocol2.protocol)) {
                    protocol2 = SPDY_3;
                    if (!str.equals(protocol2.protocol)) {
                        throw new IOException(AnonymousClass006.A07("Unexpected protocol: ", str));
                    }
                }
            }
        }
        return protocol2;
    }

    /* access modifiers changed from: public */
    Protocol(String str) {
        this.protocol = str;
    }

    public String toString() {
        return this.protocol;
    }
}
package X;

/* renamed from: X.0kA  reason: invalid class name and case insensitive filesystem */
public enum EnumC05630kA {
    TLS_1_3("TLSv1.3"),
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    public final String javaName;

    public String javaName() {
        return this.javaName;
    }

    /* access modifiers changed from: public */
    EnumC05630kA(String str) {
        this.javaName = str;
    }

    public static EnumC05630kA forJavaName(String str) {
        int hashCode = str.hashCode();
        if (hashCode != 79201641) {
            if (hashCode != 79923350) {
                switch (hashCode) {
                    case -503070503:
                        if (str.equals("TLSv1.1")) {
                            return TLS_1_1;
                        }
                        break;
                    case -503070502:
                        if (str.equals("TLSv1.2")) {
                            return TLS_1_2;
                        }
                        break;
                    case -503070501:
                        if (str.equals("TLSv1.3")) {
                            return TLS_1_3;
                        }
                        break;
                }
            } else if (str.equals("TLSv1")) {
                return TLS_1_0;
            }
        } else if (str.equals("SSLv3")) {
            return SSL_3_0;
        }
        throw new IllegalArgumentException(AnonymousClass006.A05("Unexpected TLS version: ", str));
    }
}

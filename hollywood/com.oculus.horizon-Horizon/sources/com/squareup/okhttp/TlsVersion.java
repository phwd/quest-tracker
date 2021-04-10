package com.squareup.okhttp;

import X.AnonymousClass006;

public enum TlsVersion {
    TLS_1_2("TLSv1.2"),
    TLS_1_1("TLSv1.1"),
    TLS_1_0("TLSv1"),
    SSL_3_0("SSLv3");
    
    public final String javaName;

    /* access modifiers changed from: public */
    TlsVersion(String str) {
        this.javaName = str;
    }

    public static TlsVersion forJavaName(String str) {
        switch (str.hashCode()) {
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
            case 79201641:
                if (str.equals("SSLv3")) {
                    return SSL_3_0;
                }
                break;
            case 79923350:
                if (str.equals("TLSv1")) {
                    return TLS_1_0;
                }
                break;
        }
        throw new IllegalArgumentException(AnonymousClass006.A05("Unexpected TLS version: ", str));
    }

    public String javaName() {
        return this.javaName;
    }
}

package com.android.okhttp;

import com.android.okhttp.internal.Util;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

public final class ConnectionSpec {
    private static final CipherSuite[] APPROVED_CIPHER_SUITES = {CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
    public static final ConnectionSpec CLEARTEXT = new Builder(false).build();
    public static final ConnectionSpec COMPATIBLE_TLS = new Builder(MODERN_TLS).tlsVersions(TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
    public static final ConnectionSpec MODERN_TLS = new Builder(true).cipherSuites(APPROVED_CIPHER_SUITES).tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_1, TlsVersion.TLS_1_0).supportsTlsExtensions(true).build();
    private final String[] cipherSuites;
    private final boolean supportsTlsExtensions;
    private final boolean tls;
    private final String[] tlsVersions;

    private ConnectionSpec(Builder builder) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }

    public boolean isTls() {
        return this.tls;
    }

    public List<CipherSuite> cipherSuites() {
        String[] strArr = this.cipherSuites;
        if (strArr == null) {
            return null;
        }
        CipherSuite[] result = new CipherSuite[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.cipherSuites;
            if (i >= strArr2.length) {
                return Util.immutableList(result);
            }
            result[i] = CipherSuite.forJavaName(strArr2[i]);
            i++;
        }
    }

    public List<TlsVersion> tlsVersions() {
        String[] strArr = this.tlsVersions;
        if (strArr == null) {
            return null;
        }
        TlsVersion[] result = new TlsVersion[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.tlsVersions;
            if (i >= strArr2.length) {
                return Util.immutableList(result);
            }
            result[i] = TlsVersion.forJavaName(strArr2[i]);
            i++;
        }
    }

    public boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    /* access modifiers changed from: package-private */
    public void apply(SSLSocket sslSocket, boolean isFallback) {
        ConnectionSpec specToApply = supportedSpec(sslSocket, isFallback);
        String[] strArr = specToApply.tlsVersions;
        if (strArr != null) {
            sslSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = specToApply.cipherSuites;
        if (strArr2 != null) {
            sslSocket.setEnabledCipherSuites(strArr2);
        }
    }

    private ConnectionSpec supportedSpec(SSLSocket sslSocket, boolean isFallback) {
        String[] cipherSuitesIntersection;
        String[] tlsVersionsIntersection;
        String[] strArr = this.cipherSuites;
        if (strArr != null) {
            cipherSuitesIntersection = (String[]) Util.intersect(String.class, strArr, sslSocket.getEnabledCipherSuites());
        } else {
            cipherSuitesIntersection = sslSocket.getEnabledCipherSuites();
        }
        String[] strArr2 = this.tlsVersions;
        if (strArr2 != null) {
            tlsVersionsIntersection = (String[]) Util.intersect(String.class, strArr2, sslSocket.getEnabledProtocols());
        } else {
            tlsVersionsIntersection = sslSocket.getEnabledProtocols();
        }
        if (isFallback && Util.contains(sslSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV")) {
            cipherSuitesIntersection = Util.concat(cipherSuitesIntersection, "TLS_FALLBACK_SCSV");
        }
        return new Builder(this).cipherSuites(cipherSuitesIntersection).tlsVersions(tlsVersionsIntersection).build();
    }

    public boolean isCompatible(SSLSocket socket) {
        if (!this.tls) {
            return false;
        }
        String[] strArr = this.tlsVersions;
        if (strArr != null && !nonEmptyIntersection(strArr, socket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.cipherSuites;
        if (strArr2 == null || nonEmptyIntersection(strArr2, socket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    private static boolean nonEmptyIntersection(String[] a, String[] b) {
        if (a == null || b == null || a.length == 0 || b.length == 0) {
            return false;
        }
        for (String toFind : a) {
            if (Util.contains(b, toFind)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (!(other instanceof ConnectionSpec)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        ConnectionSpec that = (ConnectionSpec) other;
        boolean z = this.tls;
        if (z != that.tls) {
            return false;
        }
        if (!z || (Arrays.equals(this.cipherSuites, that.cipherSuites) && Arrays.equals(this.tlsVersions, that.tlsVersions) && this.supportsTlsExtensions == that.supportsTlsExtensions)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.tls) {
            return (((((17 * 31) + Arrays.hashCode(this.cipherSuites)) * 31) + Arrays.hashCode(this.tlsVersions)) * 31) + (!this.supportsTlsExtensions ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.tls) {
            return "ConnectionSpec()";
        }
        String tlsVersionsString = "[all enabled]";
        String cipherSuitesString = this.cipherSuites != null ? cipherSuites().toString() : tlsVersionsString;
        if (this.tlsVersions != null) {
            tlsVersionsString = tlsVersions().toString();
        }
        return "ConnectionSpec(cipherSuites=" + cipherSuitesString + ", tlsVersions=" + tlsVersionsString + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
    }

    public static final class Builder {
        private String[] cipherSuites;
        private boolean supportsTlsExtensions;
        private boolean tls;
        private String[] tlsVersions;

        Builder(boolean tls2) {
            this.tls = tls2;
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }

        public Builder allEnabledCipherSuites() {
            if (this.tls) {
                this.cipherSuites = null;
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder cipherSuites(CipherSuite... cipherSuites2) {
            if (this.tls) {
                String[] strings = new String[cipherSuites2.length];
                for (int i = 0; i < cipherSuites2.length; i++) {
                    strings[i] = cipherSuites2[i].javaName;
                }
                return cipherSuites(strings);
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder cipherSuites(String... cipherSuites2) {
            if (!this.tls) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (cipherSuites2.length != 0) {
                this.cipherSuites = (String[]) cipherSuites2.clone();
                return this;
            } else {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
        }

        public Builder allEnabledTlsVersions() {
            if (this.tls) {
                this.tlsVersions = null;
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder tlsVersions(TlsVersion... tlsVersions2) {
            if (this.tls) {
                String[] strings = new String[tlsVersions2.length];
                for (int i = 0; i < tlsVersions2.length; i++) {
                    strings[i] = tlsVersions2[i].javaName;
                }
                return tlsVersions(strings);
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder tlsVersions(String... tlsVersions2) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (tlsVersions2.length != 0) {
                this.tlsVersions = (String[]) tlsVersions2.clone();
                return this;
            } else {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
        }

        public Builder supportsTlsExtensions(boolean supportsTlsExtensions2) {
            if (this.tls) {
                this.supportsTlsExtensions = supportsTlsExtensions2;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(this);
        }
    }
}

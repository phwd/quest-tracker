package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

public final class ConnectionSpec {
    public static final CipherSuite[] APPROVED_CIPHER_SUITES;
    public static final ConnectionSpec CLEARTEXT = new ConnectionSpec(new Builder(false));
    public static final ConnectionSpec COMPATIBLE_TLS;
    public static final ConnectionSpec MODERN_TLS;
    public final String[] cipherSuites;
    public final boolean supportsTlsExtensions;
    public final boolean tls;
    public final String[] tlsVersions;

    public static final class Builder {
        public String[] cipherSuites;
        public boolean supportsTlsExtensions;
        public boolean tls;
        public String[] tlsVersions;

        public Builder allEnabledCipherSuites() {
            if (this.tls) {
                this.cipherSuites = null;
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder allEnabledTlsVersions() {
            if (this.tls) {
                this.tlsVersions = null;
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public ConnectionSpec build() {
            return new ConnectionSpec(this);
        }

        public Builder supportsTlsExtensions(boolean z) {
            if (this.tls) {
                this.supportsTlsExtensions = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public Builder(ConnectionSpec connectionSpec) {
            this.tls = connectionSpec.tls;
            this.cipherSuites = connectionSpec.cipherSuites;
            this.tlsVersions = connectionSpec.tlsVersions;
            this.supportsTlsExtensions = connectionSpec.supportsTlsExtensions;
        }

        public Builder(boolean z) {
            this.tls = z;
        }

        public Builder cipherSuites(CipherSuite... cipherSuiteArr) {
            if (this.tls) {
                int length = cipherSuiteArr.length;
                String[] strArr = new String[length];
                for (int i = 0; i < length; i++) {
                    strArr[i] = cipherSuiteArr[i].javaName;
                }
                cipherSuites(strArr);
                return this;
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public Builder cipherSuites(String... strArr) {
            if (!this.tls) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            } else if (strArr.length != 0) {
                this.cipherSuites = (String[]) strArr.clone();
                return this;
            } else {
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
        }

        public Builder tlsVersions(TlsVersion... tlsVersionArr) {
            if (this.tls) {
                int length = tlsVersionArr.length;
                String[] strArr = new String[length];
                for (int i = 0; i < length; i++) {
                    strArr[i] = tlsVersionArr[i].javaName;
                }
                tlsVersions(strArr);
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public Builder tlsVersions(String... strArr) {
            if (!this.tls) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            } else if (strArr.length != 0) {
                this.tlsVersions = (String[]) strArr.clone();
                return this;
            } else {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
        }
    }

    public static boolean nonEmptyIntersection(String[] strArr, String[] strArr2) {
        if (!(strArr == null || strArr2 == null || (r2 = strArr.length) == 0 || strArr2.length == 0)) {
            for (String str : strArr) {
                if (Util.contains(strArr2, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    static {
        CipherSuite[] cipherSuiteArr = {CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA};
        APPROVED_CIPHER_SUITES = cipherSuiteArr;
        Builder builder = new Builder(true);
        builder.cipherSuites(cipherSuiteArr);
        TlsVersion tlsVersion = TlsVersion.TLS_1_2;
        TlsVersion tlsVersion2 = TlsVersion.TLS_1_1;
        TlsVersion tlsVersion3 = TlsVersion.TLS_1_0;
        builder.tlsVersions(tlsVersion, tlsVersion2, tlsVersion3);
        builder.supportsTlsExtensions(true);
        ConnectionSpec connectionSpec = new ConnectionSpec(builder);
        MODERN_TLS = connectionSpec;
        Builder builder2 = new Builder(connectionSpec);
        builder2.tlsVersions(tlsVersion3);
        builder2.supportsTlsExtensions(true);
        COMPATIBLE_TLS = new ConnectionSpec(builder2);
    }

    private ConnectionSpec supportedSpec(SSLSocket sSLSocket, boolean z) {
        String[] enabledCipherSuites;
        String[] enabledProtocols;
        String[] strArr = this.cipherSuites;
        if (strArr != null) {
            enabledCipherSuites = (String[]) Util.intersect(String.class, strArr, sSLSocket.getEnabledCipherSuites());
        } else {
            enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
        }
        String[] strArr2 = this.tlsVersions;
        if (strArr2 != null) {
            enabledProtocols = (String[]) Util.intersect(String.class, strArr2, sSLSocket.getEnabledProtocols());
        } else {
            enabledProtocols = sSLSocket.getEnabledProtocols();
        }
        if (z && Util.contains(sSLSocket.getSupportedCipherSuites(), "TLS_FALLBACK_SCSV")) {
            enabledCipherSuites = Util.concat(enabledCipherSuites, "TLS_FALLBACK_SCSV");
        }
        Builder builder = new Builder(this);
        builder.cipherSuites(enabledCipherSuites);
        builder.tlsVersions(enabledProtocols);
        return new ConnectionSpec(builder);
    }

    public List<CipherSuite> cipherSuites() {
        String[] strArr = this.cipherSuites;
        if (strArr == null) {
            return null;
        }
        CipherSuite[] cipherSuiteArr = new CipherSuite[strArr.length];
        int i = 0;
        while (true) {
            String[] strArr2 = this.cipherSuites;
            if (i >= strArr2.length) {
                return Util.immutableList(cipherSuiteArr);
            }
            cipherSuiteArr[i] = CipherSuite.forJavaName(strArr2[i]);
            i++;
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof ConnectionSpec) {
            if (obj != this) {
                ConnectionSpec connectionSpec = (ConnectionSpec) obj;
                boolean z = this.tls;
                if (z != connectionSpec.tls || (z && (!Arrays.equals(this.cipherSuites, connectionSpec.cipherSuites) || !Arrays.equals(this.tlsVersions, connectionSpec.tlsVersions) || this.supportsTlsExtensions != connectionSpec.supportsTlsExtensions))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.tls) {
            return ((((527 + Arrays.hashCode(this.cipherSuites)) * 31) + Arrays.hashCode(this.tlsVersions)) * 31) + (!this.supportsTlsExtensions ? 1 : 0);
        }
        return 17;
    }

    public boolean isCompatible(SSLSocket sSLSocket) {
        String[] strArr;
        if (!this.tls || ((strArr = this.tlsVersions) != null && !nonEmptyIntersection(strArr, sSLSocket.getEnabledProtocols()))) {
            return false;
        }
        String[] strArr2 = this.cipherSuites;
        if (strArr2 == null || nonEmptyIntersection(strArr2, sSLSocket.getEnabledCipherSuites())) {
            return true;
        }
        return false;
    }

    public boolean isTls() {
        return this.tls;
    }

    public boolean supportsTlsExtensions() {
        return this.supportsTlsExtensions;
    }

    public List<TlsVersion> tlsVersions() {
        String[] strArr = this.tlsVersions;
        if (strArr == null) {
            return null;
        }
        int length = strArr.length;
        TlsVersion[] tlsVersionArr = new TlsVersion[length];
        for (int i = 0; i < length; i++) {
            tlsVersionArr[i] = TlsVersion.forJavaName(strArr[i]);
        }
        return Util.immutableList(tlsVersionArr);
    }

    public String toString() {
        String str;
        if (!this.tls) {
            return "ConnectionSpec()";
        }
        String str2 = "[all enabled]";
        if (this.cipherSuites != null) {
            str = cipherSuites().toString();
        } else {
            str = str2;
        }
        if (this.tlsVersions != null) {
            str2 = tlsVersions().toString();
        }
        return "ConnectionSpec(cipherSuites=" + str + ", tlsVersions=" + str2 + ", supportsTlsExtensions=" + this.supportsTlsExtensions + ")";
    }

    public void apply(SSLSocket sSLSocket, boolean z) {
        ConnectionSpec supportedSpec = supportedSpec(sSLSocket, z);
        String[] strArr = supportedSpec.tlsVersions;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = supportedSpec.cipherSuites;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    public ConnectionSpec(Builder builder) {
        this.tls = builder.tls;
        this.cipherSuites = builder.cipherSuites;
        this.tlsVersions = builder.tlsVersions;
        this.supportsTlsExtensions = builder.supportsTlsExtensions;
    }
}

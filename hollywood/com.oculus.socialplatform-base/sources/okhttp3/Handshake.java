package okhttp3;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import okhttp3.internal.Util;

public final class Handshake {
    public final CipherSuite cipherSuite;
    public final List<Certificate> localCertificates;
    public final List<Certificate> peerCertificates;
    public final TlsVersion tlsVersion;

    public boolean equals(Object obj) {
        if (!(obj instanceof Handshake)) {
            return false;
        }
        Handshake handshake = (Handshake) obj;
        if (!Util.equal(this.cipherSuite, handshake.cipherSuite) || !this.cipherSuite.equals(handshake.cipherSuite) || !this.peerCertificates.equals(handshake.peerCertificates) || !this.localCertificates.equals(handshake.localCertificates)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        TlsVersion tlsVersion2 = this.tlsVersion;
        if (tlsVersion2 != null) {
            i = tlsVersion2.hashCode();
        } else {
            i = 0;
        }
        return ((((((527 + i) * 31) + this.cipherSuite.hashCode()) * 31) + this.peerCertificates.hashCode()) * 31) + this.localCertificates.hashCode();
    }

    public Principal localPrincipal() {
        if (!this.localCertificates.isEmpty()) {
            return ((X509Certificate) this.localCertificates.get(0)).getSubjectX500Principal();
        }
        return null;
    }

    public Principal peerPrincipal() {
        if (!this.peerCertificates.isEmpty()) {
            return ((X509Certificate) this.peerCertificates.get(0)).getSubjectX500Principal();
        }
        return null;
    }

    public Handshake(TlsVersion tlsVersion2, CipherSuite cipherSuite2, List<Certificate> list, List<Certificate> list2) {
        this.tlsVersion = tlsVersion2;
        this.cipherSuite = cipherSuite2;
        this.peerCertificates = list;
        this.localCertificates = list2;
    }

    public CipherSuite cipherSuite() {
        return this.cipherSuite;
    }

    public List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    public List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }

    public TlsVersion tlsVersion() {
        return this.tlsVersion;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0029  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0033  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static okhttp3.Handshake get(javax.net.ssl.SSLSession r5) {
        /*
            java.lang.String r0 = r5.getCipherSuite()
            if (r0 == 0) goto L_0x0040
            okhttp3.CipherSuite r4 = okhttp3.CipherSuite.forJavaName(r0)
            java.lang.String r0 = r5.getProtocol()
            if (r0 == 0) goto L_0x0038
            okhttp3.TlsVersion r3 = okhttp3.TlsVersion.forJavaName(r0)
            java.security.cert.Certificate[] r0 = r5.getPeerCertificates()     // Catch:{ SSLPeerUnverifiedException -> 0x001f }
            if (r0 == 0) goto L_0x001f
            java.util.List r2 = okhttp3.internal.Util.immutableList(r0)
            goto L_0x0023
        L_0x001f:
            java.util.List r2 = java.util.Collections.emptyList()
        L_0x0023:
            java.security.cert.Certificate[] r0 = r5.getLocalCertificates()
            if (r0 == 0) goto L_0x0033
            java.util.List r1 = okhttp3.internal.Util.immutableList(r0)
        L_0x002d:
            okhttp3.Handshake r0 = new okhttp3.Handshake
            r0.<init>(r3, r4, r2, r1)
            return r0
        L_0x0033:
            java.util.List r1 = java.util.Collections.emptyList()
            goto L_0x002d
        L_0x0038:
            java.lang.String r1 = "tlsVersion == null"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L_0x0040:
            java.lang.String r1 = "cipherSuite == null"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.Handshake.get(javax.net.ssl.SSLSession):okhttp3.Handshake");
    }

    public static Handshake get(TlsVersion tlsVersion2, CipherSuite cipherSuite2, List<Certificate> list, List<Certificate> list2) {
        if (cipherSuite2 != null) {
            return new Handshake(tlsVersion2, cipherSuite2, Util.immutableList(list), Util.immutableList(list2));
        }
        throw new NullPointerException("cipherSuite == null");
    }
}

package com.squareup.okhttp;

import com.squareup.okhttp.internal.Util;
import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;

public final class Handshake {
    public final String cipherSuite;
    public final List<Certificate> localCertificates;
    public final List<Certificate> peerCertificates;

    public boolean equals(Object obj) {
        if (!(obj instanceof Handshake)) {
            return false;
        }
        Handshake handshake = (Handshake) obj;
        if (!this.cipherSuite.equals(handshake.cipherSuite) || !this.peerCertificates.equals(handshake.peerCertificates) || !this.localCertificates.equals(handshake.localCertificates)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((527 + this.cipherSuite.hashCode()) * 31) + this.peerCertificates.hashCode()) * 31) + this.localCertificates.hashCode();
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

    public Handshake(String str, List<Certificate> list, List<Certificate> list2) {
        this.cipherSuite = str;
        this.peerCertificates = list;
        this.localCertificates = list2;
    }

    public String cipherSuite() {
        return this.cipherSuite;
    }

    public List<Certificate> localCertificates() {
        return this.localCertificates;
    }

    public List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }

    public static Handshake get(String str, List<Certificate> list, List<Certificate> list2) {
        if (str != null) {
            return new Handshake(str, Util.immutableList(list), Util.immutableList(list2));
        }
        throw new IllegalArgumentException("cipherSuite == null");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.squareup.okhttp.Handshake get(javax.net.ssl.SSLSession r4) {
        /*
            java.lang.String r3 = r4.getCipherSuite()
            if (r3 == 0) goto L_0x002a
            java.security.cert.Certificate[] r0 = r4.getPeerCertificates()     // Catch:{ SSLPeerUnverifiedException -> 0x0011 }
            if (r0 == 0) goto L_0x0011
            java.util.List r2 = com.squareup.okhttp.internal.Util.immutableList(r0)
            goto L_0x0015
        L_0x0011:
            java.util.List r2 = java.util.Collections.emptyList()
        L_0x0015:
            java.security.cert.Certificate[] r0 = r4.getLocalCertificates()
            if (r0 == 0) goto L_0x0025
            java.util.List r1 = com.squareup.okhttp.internal.Util.immutableList(r0)
        L_0x001f:
            com.squareup.okhttp.Handshake r0 = new com.squareup.okhttp.Handshake
            r0.<init>(r3, r2, r1)
            return r0
        L_0x0025:
            java.util.List r1 = java.util.Collections.emptyList()
            goto L_0x001f
        L_0x002a:
            java.lang.String r1 = "cipherSuite == null"
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.Handshake.get(javax.net.ssl.SSLSession):com.squareup.okhttp.Handshake");
    }
}

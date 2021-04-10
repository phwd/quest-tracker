package com.squareup.okhttp.internal.tls;

import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;

public final class CertificateChainCleaner {
    public static final int MAX_SIGNERS = 9;
    public final TrustRootIndex trustRootIndex;

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0056, code lost:
        throw new javax.net.ssl.SSLPeerUnverifiedException(r0.toString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.security.cert.Certificate> clean(java.util.List<java.security.cert.Certificate> r9) throws javax.net.ssl.SSLPeerUnverifiedException {
        /*
        // Method dump skipped, instructions count: 130
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.tls.CertificateChainCleaner.clean(java.util.List):java.util.List");
    }

    public CertificateChainCleaner(TrustRootIndex trustRootIndex2) {
        this.trustRootIndex = trustRootIndex2;
    }

    private boolean verifySignature(X509Certificate x509Certificate, X509Certificate x509Certificate2) {
        if (!x509Certificate.getIssuerDN().equals(x509Certificate2.getSubjectDN())) {
            return false;
        }
        try {
            x509Certificate.verify(x509Certificate2.getPublicKey());
            return true;
        } catch (GeneralSecurityException unused) {
            return false;
        }
    }
}

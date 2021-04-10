package com.facebook.mobilenetwork.internal.certificateverifier;

import X.AbstractC0172Gc;
import X.C0171Gb;
import X.C0898oN;
import X.Kf;
import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class CertificateVerifier {
    public final Kf mFbHostnameVerifier;
    public final C0171Gb mFbPinningSSLContextFactory;

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.security.cert.X509Certificate[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: X.oN */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: java.security.cert.X509Certificate */
    /* JADX WARN: Multi-variable type inference failed */
    public void verify(byte[][] bArr, String str) {
        int length = bArr.length;
        X509Certificate[] x509CertificateArr = new X509Certificate[length];
        CertificateFactory instance = CertificateFactory.getInstance("X509");
        if (instance != null) {
            for (int i = 0; i < length; i++) {
                x509CertificateArr[i] = instance.generateCertificate(new ByteArrayInputStream(bArr[i]));
            }
            X509TrustManager x509TrustManager = this.mFbPinningSSLContextFactory.A01[0];
            if (x509TrustManager instanceof AbstractC0172Gc) {
                ((AbstractC0172Gc) x509TrustManager).A1Q(x509CertificateArr, "ECDHE_ECDSA", str);
            } else {
                x509TrustManager.checkServerTrusted(x509CertificateArr, "ECDHE_ECDSA");
            }
            if (!this.mFbHostnameVerifier.A02(str, x509CertificateArr[0]).A01) {
                throw new CertificateException("Hostname verification failed.");
            }
            return;
        }
        throw new CertificateException("Unable to create certificate factory.");
    }

    public CertificateVerifier(long j, boolean z) {
        C0171Gb gb = new C0171Gb(j, false);
        this.mFbPinningSSLContextFactory = gb;
        if (z && !gb.A00) {
            X509TrustManager[] x509TrustManagerArr = gb.A01;
            x509TrustManagerArr[0] = new C0898oN((AbstractC0172Gc) x509TrustManagerArr[0]);
            gb.A00 = true;
        }
        this.mFbHostnameVerifier = new Kf();
    }
}

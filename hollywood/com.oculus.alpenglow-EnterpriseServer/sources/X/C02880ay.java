package X;

import android.annotation.TargetApi;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

@TargetApi(24)
/* renamed from: X.0ay  reason: invalid class name and case insensitive filesystem */
public final class C02880ay extends X509ExtendedTrustManager implements X509TrustManager {
    public final C02870ax A00;

    public C02880ay(long j) {
        C04520gm r1;
        synchronized (C04520gm.class) {
            r1 = C04520gm.A02;
            if (r1 == null) {
                r1 = new C04520gm();
                C04520gm.A02 = r1;
            }
        }
        this.A00 = new C02870ax(j, r1);
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.A00.getAcceptedIssuers();
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.A00.checkServerTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        C02870ax r1 = this.A00;
        ((X509ExtendedTrustManager) r1.A03).checkServerTrusted(x509CertificateArr, str, socket);
        r1.A00(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        C02870ax r1 = this.A00;
        ((X509ExtendedTrustManager) r1.A03).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        r1.A00(x509CertificateArr);
    }
}

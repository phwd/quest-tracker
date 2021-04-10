package X;

import android.annotation.TargetApi;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;

@TargetApi(24)
/* renamed from: X.0nt  reason: invalid class name */
public final class AnonymousClass0nt extends X509ExtendedTrustManager implements X509TrustManager {
    public final C06590no A00;

    public AnonymousClass0nt(long j) {
        C07600us r1;
        synchronized (C07600us.class) {
            r1 = C07600us.A02;
            if (r1 == null) {
                r1 = new C07600us();
                C07600us.A02 = r1;
            }
        }
        this.A00 = new C06590no(j, r1);
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
        C06590no r1 = this.A00;
        ((X509ExtendedTrustManager) r1.A03).checkServerTrusted(x509CertificateArr, str, socket);
        r1.A00(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        C06590no r1 = this.A00;
        ((X509ExtendedTrustManager) r1.A03).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        r1.A00(x509CertificateArr);
    }
}

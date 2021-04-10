package X;

import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;

/* renamed from: X.oL  reason: case insensitive filesystem */
public final class C0896oL extends X509ExtendedTrustManager implements AbstractC0172Gc {
    public final C0897oM A00;

    public C0896oL(long j) {
        C0617d4 d4Var;
        synchronized (C0617d4.class) {
            d4Var = C0617d4.A02;
            if (d4Var == null) {
                d4Var = new C0617d4();
                C0617d4.A02 = d4Var;
            }
        }
        this.A00 = new C0897oM(j, d4Var);
    }

    @Override // X.AbstractC0172Gc
    public final void A1Q(X509Certificate[] x509CertificateArr, String str, String str2) {
        this.A00.A1Q(x509CertificateArr, str, str2);
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.A00.getAcceptedIssuers();
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
        throw new CertificateException("Client certificates not supported!");
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        this.A00.checkServerTrusted(x509CertificateArr, str);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) {
        C0897oM oMVar = this.A00;
        ((X509ExtendedTrustManager) oMVar.A02).checkServerTrusted(x509CertificateArr, str, socket);
        oMVar.A01(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) {
        C0897oM oMVar = this.A00;
        ((X509ExtendedTrustManager) oMVar.A02).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        oMVar.A01(x509CertificateArr);
    }
}

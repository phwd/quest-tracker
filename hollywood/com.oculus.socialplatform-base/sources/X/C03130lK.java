package X;

import android.annotation.TargetApi;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@TargetApi(24)
/* renamed from: X.0lK  reason: invalid class name and case insensitive filesystem */
public final class C03130lK extends X509ExtendedTrustManager implements AnonymousClass0UZ {
    public final C03120lJ A00;

    @Override // X.AnonymousClass0UZ
    public final void A25(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        this.A00.A25(x509CertificateArr, str, str2);
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return this.A00.getAcceptedIssuers();
    }

    public C03130lK(long j) {
        this.A00 = new C03120lJ(j, SystemKeyStore.getInstance());
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
        C03120lJ r1 = this.A00;
        ((X509ExtendedTrustManager) r1.A02).checkServerTrusted(x509CertificateArr, str, socket);
        r1.A01(x509CertificateArr);
    }

    @Override // javax.net.ssl.X509ExtendedTrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        C03120lJ r1 = this.A00;
        ((X509ExtendedTrustManager) r1.A02).checkServerTrusted(x509CertificateArr, str, sSLEngine);
        r1.A01(x509CertificateArr);
    }
}

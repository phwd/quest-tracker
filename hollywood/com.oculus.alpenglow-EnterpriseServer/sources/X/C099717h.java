package X;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.17h  reason: invalid class name and case insensitive filesystem */
public class C099717h implements X509TrustManager {
    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    public final X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}

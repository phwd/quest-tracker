package X;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.oN  reason: case insensitive filesystem */
public final class C0898oN extends Gf implements AbstractC0172Gc {
    public final X509TrustManagerExtensions A00 = new X509TrustManagerExtensions((X509TrustManager) super.A01[0]);
    public final AbstractC0172Gc A01;

    @Override // X.AbstractC0172Gc
    public final void A1Q(X509Certificate[] x509CertificateArr, String str, String str2) {
        try {
            this.A01.A1Q(x509CertificateArr, str, str2);
        } catch (CertificateException unused) {
            this.A00.checkServerTrusted(x509CertificateArr, str, str2);
        }
    }

    public C0898oN(AbstractC0172Gc gc) {
        super(gc);
        this.A01 = gc;
    }
}

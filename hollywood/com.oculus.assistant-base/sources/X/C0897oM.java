package X;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.X509Certificate;

/* renamed from: X.oM  reason: case insensitive filesystem */
public final class C0897oM extends C0174Ge implements AbstractC0172Gc {
    public final X509TrustManagerExtensions A00 = new X509TrustManagerExtensions(this.A02);

    @Override // X.AbstractC0172Gc
    public final void A1Q(X509Certificate[] x509CertificateArr, String str, String str2) {
        A00(this.A00.checkServerTrusted(x509CertificateArr, str, str2));
    }

    public C0897oM(long j, C0617d4 d4Var) {
        super(j, d4Var);
    }
}

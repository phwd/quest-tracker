package X;

import android.annotation.TargetApi;
import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.annotation.Nullable;
import org.thoughtcrime.ssl.pinning.SystemKeyStore;

@TargetApi(17)
/* renamed from: X.0lJ  reason: invalid class name and case insensitive filesystem */
public final class C03120lJ extends AnonymousClass0Ub implements AnonymousClass0UZ {
    public final X509TrustManagerExtensions A00 = new X509TrustManagerExtensions(this.A02);

    @Override // X.AnonymousClass0UZ
    public final void A25(X509Certificate[] x509CertificateArr, String str, String str2) throws CertificateException {
        A00(this.A00.checkServerTrusted(x509CertificateArr, str, str2));
    }

    public C03120lJ(long j, @Nullable SystemKeyStore systemKeyStore) {
        super(j, systemKeyStore);
    }
}

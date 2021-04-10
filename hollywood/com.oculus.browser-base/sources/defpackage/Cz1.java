package defpackage;

import android.net.http.X509TrustManagerExtensions;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/* renamed from: Cz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Cz1 implements Bz1 {

    /* renamed from: a  reason: collision with root package name */
    public final X509TrustManagerExtensions f7852a;

    public Cz1(X509TrustManager x509TrustManager) {
        this.f7852a = new X509TrustManagerExtensions(x509TrustManager);
    }

    public List a(X509Certificate[] x509CertificateArr, String str, String str2) {
        try {
            return this.f7852a.checkServerTrusted(x509CertificateArr, str, str2);
        } catch (RuntimeException e) {
            AbstractC1220Ua0.a("X509Util", "checkServerTrusted() unexpectedly threw: %s", e);
            throw new CertificateException(e);
        }
    }
}

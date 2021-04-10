package X;

import javax.net.ssl.X509TrustManager;

/* renamed from: X.Qi  reason: case insensitive filesystem */
public final class C0116Qi {
    public final X509TrustManager[] A00;

    public C0116Qi(long j) {
        X509TrustManager[] x509TrustManagerArr = new X509TrustManager[1];
        this.A00 = x509TrustManagerArr;
        x509TrustManagerArr[0] = new SK(j);
    }
}

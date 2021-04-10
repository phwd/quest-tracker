package X;

import javax.net.ssl.X509TrustManager;

public final class SN {
    public final X509TrustManager[] A00;

    public SN(long j) {
        X509TrustManager[] x509TrustManagerArr = new X509TrustManager[1];
        this.A00 = x509TrustManagerArr;
        x509TrustManagerArr[0] = new XV(j);
    }
}

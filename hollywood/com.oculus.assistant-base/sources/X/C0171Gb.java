package X;

import android.os.Build;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.Gb  reason: case insensitive filesystem */
public final class C0171Gb {
    public boolean A00 = false;
    public final X509TrustManager[] A01;

    public C0171Gb(long j) {
        C0617d4 d4Var;
        X509TrustManager ge;
        X509TrustManager[] x509TrustManagerArr = new X509TrustManager[1];
        this.A01 = x509TrustManagerArr;
        if (Build.VERSION.SDK_INT >= 24) {
            ge = new C0896oL(j);
        } else {
            synchronized (C0617d4.class) {
                d4Var = C0617d4.A02;
                if (d4Var == null) {
                    d4Var = new C0617d4();
                    C0617d4.A02 = d4Var;
                }
            }
            ge = new C0174Ge(j, d4Var);
        }
        x509TrustManagerArr[0] = ge;
    }

    public C0171Gb(long j, boolean z) {
        X509TrustManager[] x509TrustManagerArr = new X509TrustManager[1];
        this.A01 = x509TrustManagerArr;
        x509TrustManagerArr[0] = new C0897oM(j, null);
    }
}

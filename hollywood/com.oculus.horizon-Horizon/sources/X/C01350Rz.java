package X;

import android.os.Build;
import javax.net.ssl.X509TrustManager;

/* renamed from: X.0Rz  reason: invalid class name and case insensitive filesystem */
public final class C01350Rz {
    public final X509TrustManager[] A00;

    public C01350Rz(long j) {
        C07600us r0;
        X509TrustManager r1;
        X509TrustManager[] x509TrustManagerArr = new X509TrustManager[1];
        this.A00 = x509TrustManagerArr;
        if (Build.VERSION.SDK_INT >= 24) {
            r1 = new AnonymousClass0nt(j);
        } else {
            synchronized (C07600us.class) {
                r0 = C07600us.A02;
                if (r0 == null) {
                    r0 = new C07600us();
                    C07600us.A02 = r0;
                }
            }
            r1 = new AnonymousClass0S1(j, r0);
        }
        x509TrustManagerArr[0] = r1;
    }
}

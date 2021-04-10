package X;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: X.0Md  reason: invalid class name and case insensitive filesystem */
public final class C01200Md implements AbstractC08380wS {
    public Object A00;
    public C08090vs A01;
    public final AnonymousClass0N1 A02;
    public final boolean A03;
    public volatile boolean A04;

    private C08610wt A00(C08400wU r14) {
        HostnameVerifier hostnameVerifier;
        C08540wk r7;
        SSLSocketFactory sSLSocketFactory = null;
        if (r14.A03.equals("https")) {
            AnonymousClass0N1 r0 = this.A02;
            sSLSocketFactory = r0.A0D;
            hostnameVerifier = r0.A0C;
            r7 = r0.A0G;
        } else {
            hostnameVerifier = null;
            r7 = null;
        }
        String str = r14.A02;
        int i = r14.A00;
        AnonymousClass0N1 r02 = this.A02;
        return new C08610wt(str, i, r02.A0K, r02.A0B, sSLSocketFactory, hostnameVerifier, r7, r02.A0F, r02.A05, r02.A0A, r02.A07, r02.A06);
    }

    private boolean A01(IOException iOException, boolean z) {
        this.A01.A04(iOException);
        if (this.A02.A0N && !(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException) : (iOException instanceof SocketTimeoutException) && !z)) {
            C08090vs r1 = this.A01;
            if (r1.A01 != null) {
                return true;
            }
            C08110vu r2 = r1.A09;
            if (r2.A00 < r2.A02.size() || r2.A01 < r2.A03.size() || (!r2.A06.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean A02(C08220wC r2, C08400wU r3) {
        C08400wU r22 = r2.A07.A03;
        if (!r22.A02.equals(r3.A02) || r22.A00 != r3.A00 || !r22.A03.equals(r3.A03)) {
            return false;
        }
        return true;
    }

    public C01200Md(AnonymousClass0N1 r1, boolean z) {
        this.A02 = r1;
        this.A03 = z;
    }

    /* JADX WARNING: Removed duplicated region for block: B:112:0x0071 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0071 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011b  */
    @Override // X.AbstractC08380wS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C08220wC intercept(X.AbstractC08390wT r16) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 458
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C01200Md.intercept(X.0wT):X.0wC");
    }
}

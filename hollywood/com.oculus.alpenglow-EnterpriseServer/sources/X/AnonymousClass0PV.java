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

/* renamed from: X.0PV  reason: invalid class name */
public final class AnonymousClass0PV implements AbstractC05850lW {
    public Object A00;
    public C05360jA A01;
    public final AnonymousClass0Qs A02;
    public final boolean A03;
    public volatile boolean A04;

    private C06800nu A00(C05890la r13) {
        HostnameVerifier hostnameVerifier;
        C06330ma r7;
        SSLSocketFactory sSLSocketFactory = null;
        if (r13.A03.equals("https")) {
            AnonymousClass0Qs r0 = this.A02;
            sSLSocketFactory = r0.A0B;
            hostnameVerifier = r0.A0A;
            r7 = r0.A0E;
        } else {
            hostnameVerifier = null;
            r7 = null;
        }
        String str = r13.A02;
        int i = r13.A00;
        AnonymousClass0Qs r02 = this.A02;
        return new C06800nu(str, i, r02.A0I, r02.A09, sSLSocketFactory, hostnameVerifier, r7, r02.A0D, r02.A08, r02.A05, r02.A04);
    }

    private boolean A01(IOException iOException, boolean z) {
        this.A01.A04(iOException);
        if (this.A02.A0L && !(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException) : (iOException instanceof SocketTimeoutException) && !z)) {
            C05360jA r1 = this.A01;
            if (r1.A01 != null) {
                return true;
            }
            C05380jD r2 = r1.A09;
            if (r2.A00 < r2.A02.size() || r2.A01 < r2.A03.size() || (!r2.A06.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean A02(C05660kD r2, C05890la r3) {
        C05890la r22 = r2.A07.A03;
        if (!r22.A02.equals(r3.A02) || r22.A00 != r3.A00 || !r22.A03.equals(r3.A03)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:108:0x0070 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x0070 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011d  */
    @Override // X.AbstractC05850lW
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C05660kD A5K(X.AnonymousClass0PX r18) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 458
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0PV.A5K(X.0PX):X.0kD");
    }

    public AnonymousClass0PV(AnonymousClass0Qs r1, boolean z) {
        this.A02 = r1;
        this.A03 = z;
    }
}

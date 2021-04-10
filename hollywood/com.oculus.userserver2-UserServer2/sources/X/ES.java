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

public final class ES implements XS {
    public Object A00;
    public X0 A01;
    public final AbstractC0054Ej A02;
    public final boolean A03;
    public volatile boolean A04;

    private C0190Xp A00(XT xt) {
        HostnameVerifier hostnameVerifier;
        C0183Xi xi;
        SSLSocketFactory sSLSocketFactory = null;
        if (xt.A03.equals("https")) {
            AbstractC0054Ej ej = this.A02;
            sSLSocketFactory = ej.A0D;
            hostnameVerifier = ej.A0C;
            xi = ej.A0G;
        } else {
            hostnameVerifier = null;
            xi = null;
        }
        String str = xt.A02;
        int i = xt.A00;
        AbstractC0054Ej ej2 = this.A02;
        return new C0190Xp(str, i, ej2.A0K, ej2.A0B, sSLSocketFactory, hostnameVerifier, xi, ej2.A0F, ej2.A05, ej2.A0A, ej2.A07, ej2.A06);
    }

    private boolean A01(IOException iOException, boolean z) {
        this.A01.A04(iOException);
        if (this.A02.A0N && !(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException) : (iOException instanceof SocketTimeoutException) && !z)) {
            X0 x0 = this.A01;
            if (x0.A01 != null) {
                return true;
            }
            X2 x2 = x0.A08;
            if (x2.A00 < x2.A02.size() || x2.A01 < x2.A03.size() || (!x2.A06.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean A02(XK xk, XT xt) {
        XT xt2 = xk.A07.A03;
        if (!xt2.A02.equals(xt.A02) || xt2.A00 != xt.A00 || !xt2.A03.equals(xt.A03)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0198, code lost:
        throw new java.net.ProtocolException(r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x006c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x006c A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0119  */
    @Override // X.XS
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.XK A29(X.EU r18) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 442
        */
        throw new UnsupportedOperationException("Method not decompiled: X.ES.A29(X.EU):X.XK");
    }

    public ES(AbstractC0054Ej ej, boolean z) {
        this.A02 = ej;
        this.A03 = z;
    }
}

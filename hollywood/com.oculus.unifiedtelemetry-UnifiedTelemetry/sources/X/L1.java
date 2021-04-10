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

public final class L1 implements Cdo {
    public Object A00;
    public C0350dM A01;
    public final LD A02;
    public final boolean A03;
    public volatile boolean A04;

    private eB A00(C0367dp dpVar) {
        HostnameVerifier hostnameVerifier;
        e4 e4Var;
        SSLSocketFactory sSLSocketFactory = null;
        if (dpVar.A03.equals("https")) {
            LD ld = this.A02;
            sSLSocketFactory = ld.A0D;
            hostnameVerifier = ld.A0C;
            e4Var = ld.A0G;
        } else {
            hostnameVerifier = null;
            e4Var = null;
        }
        String str = dpVar.A02;
        int i = dpVar.A00;
        LD ld2 = this.A02;
        return new eB(str, i, ld2.A0K, ld2.A0B, sSLSocketFactory, hostnameVerifier, e4Var, ld2.A0F, ld2.A05, ld2.A0A, ld2.A07, ld2.A06);
    }

    private boolean A01(IOException iOException, boolean z) {
        this.A01.A04(iOException);
        if (this.A02.A0N && !(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException) : (iOException instanceof SocketTimeoutException) && !z)) {
            C0350dM dMVar = this.A01;
            if (dMVar.A01 != null) {
                return true;
            }
            C0352dO dOVar = dMVar.A09;
            if (dOVar.A00 < dOVar.A02.size() || dOVar.A01 < dOVar.A03.size() || (!dOVar.A06.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean A02(C0359dg dgVar, C0367dp dpVar) {
        C0367dp dpVar2 = dgVar.A07.A03;
        if (!dpVar2.A02.equals(dpVar.A02) || dpVar2.A00 != dpVar.A00 || !dpVar2.A03.equals(dpVar.A03)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:109:0x0070 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x0070 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011d  */
    @Override // X.Cdo
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0359dg A38(X.L3 r18) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 458
        */
        throw new UnsupportedOperationException("Method not decompiled: X.L1.A38(X.L3):X.dg");
    }

    public L1(LD ld, boolean z) {
        this.A02 = ld;
        this.A03 = z;
    }
}

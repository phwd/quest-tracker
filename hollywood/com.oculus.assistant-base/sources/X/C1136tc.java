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

/* renamed from: X.tc  reason: case insensitive filesystem */
public final class C1136tc implements AbstractC0545bi {
    public Object A00;
    public c9 A01;
    public final C0548bl A02;
    public final boolean A03;
    public volatile boolean A04;

    private C0523bM A00(C0544bh bhVar) {
        HostnameVerifier hostnameVerifier;
        C0530bT bTVar;
        SSLSocketFactory sSLSocketFactory = null;
        if (bhVar.A03.equals("https")) {
            C0548bl blVar = this.A02;
            sSLSocketFactory = blVar.A0B;
            hostnameVerifier = blVar.A0A;
            bTVar = blVar.A0E;
        } else {
            hostnameVerifier = null;
            bTVar = null;
        }
        String str = bhVar.A02;
        int i = bhVar.A00;
        C0548bl blVar2 = this.A02;
        return new C0523bM(str, i, blVar2.A0I, blVar2.A09, sSLSocketFactory, hostnameVerifier, bTVar, blVar2.A0D, blVar2.A08, blVar2.A05, blVar2.A04);
    }

    private boolean A01(IOException iOException, boolean z) {
        this.A01.A04(iOException);
        if (this.A02.A0L && !(iOException instanceof ProtocolException) && (!(iOException instanceof InterruptedIOException) ? (!(iOException instanceof SSLHandshakeException) || !(iOException.getCause() instanceof CertificateException)) && !(iOException instanceof SSLPeerUnverifiedException) : (iOException instanceof SocketTimeoutException) && !z)) {
            c9 c9Var = this.A01;
            if (c9Var.A01 != null) {
                return true;
            }
            C0569c7 c7Var = c9Var.A09;
            if (c7Var.A00 < c7Var.A02.size() || c7Var.A01 < c7Var.A03.size() || (!c7Var.A06.isEmpty())) {
                return true;
            }
        }
        return false;
    }

    public static boolean A02(C0554br brVar, C0544bh bhVar) {
        C0544bh bhVar2 = brVar.A07.A03;
        if (!bhVar2.A02.equals(bhVar.A02) || bhVar2.A00 != bhVar.A00 || !bhVar2.A03.equals(bhVar.A03)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:107:0x0070 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0070 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x011b  */
    @Override // X.AbstractC0545bi
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.C0554br A3L(X.C1138te r18) {
        /*
        // Method dump skipped, instructions count: 462
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C1136tc.A3L(X.te):X.br");
    }

    public C1136tc(C0548bl blVar, boolean z) {
        this.A02 = blVar;
        this.A03 = z;
    }
}

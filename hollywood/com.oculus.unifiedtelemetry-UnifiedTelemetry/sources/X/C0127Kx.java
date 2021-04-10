package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;

/* renamed from: X.Kx  reason: case insensitive filesystem */
public final class C0127Kx implements dL {
    public int A00 = 0;
    public final KJ A01;
    public final LD A02;
    public final C0350dM A03;
    public final KC A04;

    public static final void A00(K4 k4) {
        ca caVar = k4.A00;
        ca caVar2 = ca.NONE;
        if (caVar2 != null) {
            k4.A00 = caVar2;
            caVar.clearDeadline();
            caVar.clearTimeout();
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final void A01(C0369ds dsVar, String str) throws IOException {
        int i = this.A00;
        if (i == 0) {
            KJ kj = this.A01;
            kj.A5y(str);
            kj.A5y(HttpRequestMultipart.LINE_FEED);
            String[] strArr = dsVar.A00;
            int length = strArr.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 << 1;
                kj.A5y(strArr[i3]);
                kj.A5y(": ");
                kj.A5y(strArr[i3 + 1]);
                kj.A5y(HttpRequestMultipart.LINE_FEED);
            }
            kj.A5y(HttpRequestMultipart.LINE_FEED);
            this.A00 = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // X.dL
    public final AbstractC0313cc A1j(C0362dj djVar, long j) {
        int i;
        if ("chunked".equalsIgnoreCase(djVar.A02.A00("Transfer-Encoding"))) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new Kz(this);
            }
        } else if (j != -1) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new Ky(this, j);
            }
        } else {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // X.dL
    public final void A2D() throws IOException {
        this.A01.flush();
    }

    @Override // X.dL
    public final C0360dh A4Z(boolean z) throws IOException {
        int i = this.A00;
        if (i == 1 || i == 3) {
            try {
                KC kc = this.A04;
                C0345dF A002 = C0345dF.A00(kc.A4c());
                C0360dh dhVar = new C0360dh();
                dhVar.A06 = A002.A02;
                int i2 = A002.A00;
                dhVar.A00 = i2;
                dhVar.A03 = A002.A01;
                C0370dt dtVar = new C0370dt();
                while (true) {
                    String A4c = kc.A4c();
                    if (A4c.length() == 0) {
                        break;
                    }
                    AbstractC0355dc.A00.A06(dtVar, A4c);
                }
                dhVar.A05 = new C0369ds(dtVar).A01();
                if (z && i2 == 100) {
                    return null;
                }
                this.A00 = 4;
                return dhVar;
            } catch (EOFException e) {
                StringBuilder sb = new StringBuilder("unexpected end of stream on ");
                sb.append(this.A03);
                IOException iOException = new IOException(sb.toString());
                iOException.initCause(e);
                throw iOException;
            }
        } else {
            throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
        }
    }

    @Override // X.dL
    public final void A5w(C0362dj djVar) throws IOException {
        Proxy.Type type = this.A03.A01().A0D.A01.type();
        StringBuilder sb = new StringBuilder();
        sb.append(djVar.A01);
        sb.append(' ');
        C0367dp dpVar = djVar.A03;
        if (dpVar.A03.equals("https") || type != Proxy.Type.HTTP) {
            sb.append(C0346dG.A00(dpVar));
        } else {
            sb.append(dpVar);
        }
        sb.append(" HTTP/1.1");
        A01(djVar.A02, sb.toString());
    }

    @Override // X.dL
    public final void cancel() {
        L6 A012 = this.A03.A01();
        if (A012 != null) {
            dZ.A07(A012.A03);
        }
    }

    @Override // X.dL
    public final void finishRequest() throws IOException {
        this.A01.flush();
    }

    public C0127Kx(LD ld, C0350dM dMVar, KC kc, KJ kj) {
        this.A02 = ld;
        this.A03 = dMVar;
        this.A04 = kc;
        this.A01 = kj;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        if (r3 != -1) goto L_0x0008;
     */
    @Override // X.dL
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC0358df A47(X.C0359dg r6) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 125
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0127Kx.A47(X.dg):X.df");
    }
}

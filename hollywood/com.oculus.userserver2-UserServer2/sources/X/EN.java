package X;

import com.facebook.acra.util.HttpRequestMultipart;
import java.io.EOFException;
import java.io.IOException;
import java.net.Proxy;

public final class EN implements AbstractC0174Wz {
    public int A00 = 0;
    public final Du A01;
    public final AbstractC0054Ej A02;
    public final X0 A03;
    public final Dp A04;

    public static final void A00(Dl dl) {
        WE we = dl.A00;
        WE we2 = WE.NONE;
        if (we2 != null) {
            dl.A00 = we2;
            we.clearDeadline();
            we.clearTimeout();
            return;
        }
        throw new IllegalArgumentException("delegate == null");
    }

    public final void A01(XW xw, String str) throws IOException {
        int i = this.A00;
        if (i == 0) {
            Du du = this.A01;
            du.A44(str);
            du.A44(HttpRequestMultipart.LINE_FEED);
            String[] strArr = xw.A00;
            int length = strArr.length >> 1;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i2 << 1;
                du.A44(strArr[i3]);
                du.A44(": ");
                du.A44(strArr[i3 + 1]);
                du.A44(HttpRequestMultipart.LINE_FEED);
            }
            du.A44(HttpRequestMultipart.LINE_FEED);
            this.A00 = 1;
            return;
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // X.AbstractC0174Wz
    public final WG A1H(XN xn, long j) {
        int i;
        if ("chunked".equalsIgnoreCase(xn.A02.A00("Transfer-Encoding"))) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new EQ(this);
            }
        } else if (j != -1) {
            i = this.A00;
            if (i == 1) {
                this.A00 = 2;
                return new EO(this, j);
            }
        } else {
            throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
        }
        throw new IllegalStateException(AnonymousClass06.A01("state: ", i));
    }

    @Override // X.AbstractC0174Wz
    public final void A1Z() throws IOException {
        this.A01.flush();
    }

    @Override // X.AbstractC0174Wz
    public final XL A38(boolean z) throws IOException {
        int i = this.A00;
        if (i == 1 || i == 3) {
            try {
                Dp dp = this.A04;
                C0168Wt A002 = C0168Wt.A00(dp.A3B());
                XL xl = new XL();
                xl.A06 = A002.A02;
                int i2 = A002.A00;
                xl.A00 = i2;
                xl.A03 = A002.A01;
                XX xx = new XX();
                while (true) {
                    String A3B = dp.A3B();
                    if (A3B.length() == 0) {
                        break;
                    }
                    XG.A00.A00(xx, A3B);
                }
                xl.A05 = new XW(xx).A01();
                if (z && i2 == 100) {
                    return null;
                }
                this.A00 = 4;
                return xl;
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

    @Override // X.AbstractC0174Wz
    public final void A42(XN xn) throws IOException {
        Proxy.Type type = this.A03.A01().A0D.A01.type();
        StringBuilder sb = new StringBuilder();
        sb.append(xn.A01);
        sb.append(' ');
        XT xt = xn.A03;
        if (xt.A03.equals("https") || type != Proxy.Type.HTTP) {
            sb.append(C0169Wu.A00(xt));
        } else {
            sb.append(xt);
        }
        sb.append(" HTTP/1.1");
        A01(xn.A02, sb.toString());
    }

    @Override // X.AbstractC0174Wz
    public final void finishRequest() throws IOException {
        this.A01.flush();
    }

    public EN(AbstractC0054Ej ej, X0 x0, Dp dp, Du du) {
        this.A02 = ej;
        this.A03 = x0;
        this.A04 = dp;
        this.A01 = du;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0056, code lost:
        if (r3 != -1) goto L_0x0008;
     */
    @Override // X.AbstractC0174Wz
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.XJ A2k(X.XK r6) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 125
        */
        throw new UnsupportedOperationException("Method not decompiled: X.EN.A2k(X.XK):X.XJ");
    }
}
